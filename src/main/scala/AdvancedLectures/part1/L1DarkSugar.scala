package AdvancedLectures.part1

import scala.annotation.{tailrec, targetName}
import scala.collection.immutable.ArraySeq
import scala.util.Try

object L1DarkSugar extends App {

  // Syntax Sugar 1. Methods with single parameter
  def singleArgs(args: Int): String = s"$args little ducks"

  val cumbersome = singleArgs({
    // using parenthesis and curly braces as a code block
    println("parenthesis block")
    4
  })
  println(cumbersome)

  // we can omit the parenthesis passing the argument
  // from the return value of code block
  val simple = singleArgs {
    // write some complex code
    println("syntax block")
    3
  }
  println(simple)

  // the same can be done with Try or Future object
  val aTryInstance = Try { // not java's try {...}
    throw new RuntimeException()
  }
  println(aTryInstance)

  // and with HoFs
  val aList = List(1, 2, 3).map { x =>
    println(x)
    x + 1
  }
  println(aList)

  // Syntax Sugar 2. Single abstract method pattern
  trait Action {
    // This is a single abstract method
    def act(n: Int): Int

    def multiplyTwo(n: Int) = n * 2
  }

  // Initializing Action using a single abstract method pattern
  val anInstance = new Action {
    override def act(n: Int): Int = n + 1
  }
  println(anInstance.act(9))
  println(anInstance.multiplyTwo(9))

  // lambda also works in initializing Action
  // We need to provide the return type
  val aFunkyInstance: Action = (x: Int) => x + 1
  println(aFunkyInstance.act(3))
  println(aFunkyInstance.multiplyTwo(3))

  // we can define a method to apply act method
  def applyAct(x: Int): Int = aFunkyInstance.act(x)

  def applyMultiplyTwo(x: Int) = aFunkyInstance.multiplyTwo(x)

  println(applyAct(5))
  println(applyMultiplyTwo(5))

  // The same is applicable for an Abstract class
  abstract class AnAbstractType {
    def implemented: Int = 23

    def f(a: Int): Unit
  }

  val anAbstractInstance: AnAbstractType = (a: Int) => println(s"$a is a number")
  anAbstractInstance.f(2)

  // example: Runnable, this is where single abstract method patterns are useful
  val aThread = new Thread(new Runnable {
    override def run(): Unit = {
      println("Hi Scala, from a new thread!!")
    }
  })

  // similarly, lambda can implement runnable
  val aNewThread = new Thread(() => {
    println("I'm from a different thread!!")
  })

  // Read more about threads in the below link:
  // https://medium.com/@lakkuga/daemon-and-non-daemon-threads-in-ddc091fabacd
  // Making the threads daemon
  //  aThread.setDaemon(true)
  //  aNewThread.setDaemon(true)
  // Start the threads
  aThread.start()
  aNewThread.start()
  // Now we need to join the threads or else the JVM will stop as soon as
  // the main thread is done
  //  aThread.join()
  //  aNewThread.join()
  /*
  There seems to be more caveats related to threads.
  - If the thread is a daemon thread, and if we join them, then the main waits for these
  threads to finish its execution before resuming it own execution and you can see
  the output messages delayed by a second.
  - If the threads are non-daemon threads, which all user defined threads are by default,
  then their execution is done in parallel to the main thread.
  Also, start() and run() seem to behave differently, we can research that later.
  What is the difference between start() and run() on threads?
  - thread.start(): This method is used to begin the execution of a thread
  by calling the run() method asynchronously. When you call start(), the thread's run()
  method is executed in a separate thread of control. This means that the code inside
  the run() method will run concurrently with the main thread or any other threads that are executing.
  - thread.run(): This method is simply a normal method call. It does not start a new thread;
  instead, it executes the run() method in the current thread of control.
  So, calling run() directly will execute the code sequentially in the same
  thread from which it was called. This defeats the purpose of multithreading because
  it doesn't create a new execution context.
   */

  // Syntax Sugar 3.
  // :: and #:: methods are special
  // we would assume that the compiler rewrites the below as
  // 2.::(List(3,4)), but there is no :: method in Int object
  // :: method is a part of the List object
  val prependedList = 2 :: List(3, 4)
  // compiler rewrites the above as:
  // List(3,4).::(2)
  // Scala spec: last character of the operator determines its associativity
  // If it ends in a colon, it is right associative, if not then left

  val newList = 1 :: 2 :: 3 :: List(4, 5)
  println(newList)
  // equivalent to:
  val similarList = List(4, 5).::(3).::(2).::(1)
  println(similarList)

  // Implementing our own custom symbol method `-->:`
  class MyStream[T](val value: T, val next: Option[MyStream[T]] = None) {
    @targetName("appendMyStream")
    infix def -->:(value: T): MyStream[T] = new MyStream(value, Some(this))

    override def toString: String = {
      @tailrec
      def collectValues(stream: Option[MyStream[T]], acc: List[T] = Nil): List[T] = stream match {
        case Some(s) if s.value != null => collectValues(s.next, s.value :: acc)
        case _ => acc.reverse
      }

      collectValues(Some(this)).mkString("MyStream(", ", ", ")")
    }
  }

  object MyStream {
    def empty[T]: MyStream[T] = new MyStream[T](null.asInstanceOf[T])
  }

  // Usage
  val myStream = 1 -->: 2 -->: 3 -->: MyStream.empty[Int]
  println(myStream.toString)

  // Syntax Sugar 4. Multi-word identifiers
  class Talker(name: String) {
    infix def `then said`(gossip: String): Unit = println(s"$name said $gossip")
  }

  val lilly = new Talker("Lilly")
  lilly `then said` "Scala is sweet"

  // Good use cases are HTTP libraries
  @targetName("Content-Type")
  object `Content-Type` {
    @targetName("application/json")
    val `application/json` = "application/JSON"
  }

  println(`Content-Type`.`application/json`)

  // Syntax Sugar 5. Generics.: Infix types
  // `@targetName` is used to provide more readable names when
  // Java byte codes are generated, so they should be descriptive
  // It will be useful to debug bytecode and for Java libraries too (Java interop)
  @targetName("InfixTrait")
  infix trait ==>[A, B] {
    @targetName("infixMethod")
    infix def `+++`(something: String): String = s"Infix trait, $something"
  }

  @targetName("InfixClass")
  case class **>[A, B](memberA: A, memberB: B) extends ==>[A, B] {

    def belongs = s"Infix class **>, with members ${memberA.toString}, ${memberB.toString}"
  }

  val compositeType: ==>[Int, String] = **>[Int, String](12, "Just another member")
  // As val compositeType is a super type of `**>`
  // It will not have access to `belongs` method
  println(compositeType +++ "Hi!!")

  // simple way
  val simpleComposite: **>[Int, String] = **>[Int, String](3, "Simple")
  // fancy way with Infix types
  val composite: Int **> String = **>[Int, String](5, "Infix")
  println(simpleComposite +++ "Not that simple!!")
  println(simpleComposite.belongs)
  println(composite +++ "Quite powerful")
  println(composite.belongs)

  // Syntax Sugar 6. update() method, it is special like apply()
  val anArray = Array(1, 2, 3)
  anArray.update(0, 9)
  anArray(2) = 7 // rewritten as anArray.update(index, value)
  // update() is used in mutable collections,
  // remember to implement apply and update methods
  println(anArray.mkString("Array(", ", ", ")"))

  // Syntax sugar 7. Setters for mutable collections
  class Mutable {
    // encapsulated data
    private var internalMember: Int = 0 // private for OOP encapsulation

    def member: Int = internalMember // "getter"

    // `_=` is used for setting, no space between _, and =
    // both methods should have the same name
    def member_=(value: Int): Unit =
      internalMember = value // "setter"
  }

  val aMutableContainer = new Mutable
  aMutableContainer.member = 42 // rewritten as aMutableContainer.member_=(42)
  println(aMutableContainer.member)

  // Syntax sugar 8. Variable arguments (varargs)
  // We can pass any number of arguments
  def methodWithVarargs(args: Int*) = args match {
    case list: List[Int] => list.toString()
    case arraySeq: ArraySeq[Int] => arraySeq.toString()
    case x => x.toString()
  }

  println(methodWithVarargs(1, 2, 3))
  println(methodWithVarargs())
  println(methodWithVarargs(5, 6, 7, 8, 9))

  // We can unwrap a collection and dynamically pass them
  val listParameter = List(1, 2, 3, 4, 5, 6)
  // the * will unwrap and pass the args to the method
  // Interestingly List is not converted to an ArraySeq
  // This could be because they belong to the same super type
  // TODO: Research why this happens
  println(methodWithVarargs(listParameter *))
  println(methodWithVarargs(Seq(3, 4, 5) *))
}
