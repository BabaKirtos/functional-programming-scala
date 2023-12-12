package BasicLectures.part5AdvancedScala

import java.awt.Composite
import scala.annotation.targetName
import scala.util.Try

object L1DarkSugar extends App {

  // Syntax Sugar 1. Methods with single parameter
  def singleArgs(args: Int): String = s"$args little ducks"
  // passing the argument from return value of code block
  val desc = singleArgs {
    // write some complex code
    3
  }
  println(desc)
  // same can be done with Try object
  val aTryInstance = Try { // java's try {...}
    throw new RuntimeException()
  }
  println(aTryInstance)
  val aList = List(1, 2, 3).map { x =>
    x + 1
  }
  println(aList)

  // Syntax Sugar 2. Single abstract method pattern
  trait Action {
    def act(n: Int): Int
  }
  val anInstance = new Action {
    override def act(n: Int): Int = n + 1
  }
  val aFun4kyInstance: Action = (x: Int) => x + 1 // magic

  // example: Runnable
  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("hello")
  })
  // similarly, lambda can implement runnable
  val aNewThread = new Thread(() => println("lambda scala"))

  abstract class AnAbstractType {
    def implemented: Int = 23
    def f(a: Int): Unit
  }
  val anAbstractInstance: AnAbstractType = (a: Int) => println(s"$a is a number")
  anAbstractInstance.f(2)

  // Syntax Sugar 3. :: and #:: methods are special
  val prependedList = 2 :: List(3, 4)
  // 2.::(List(3,4)), but there is no :: method in int class
  // compiler rewrites the above as:
  // List(3,4).::(2)
  // Scala spec: last character of the operator determines its associativity
  // If it ends in a colon, it is right associative, if not then left

  val newList = 1 :: 2 :: 3 :: List(4, 5) // equivalent to:
  val similarList = List(4, 5).::(3).::(4).::(5)

  class MyStream[T] {
    @targetName("-->:")
    def -->:(value: T): MyStream[T] = this
  }
  val myStream = 1 -->: 2 -->: 3 -->: new MyStream[Int]

  // Syntax Sugar 4. Multi word method naming
  class TeamGirl(name: String) {
    def `and then said`(gossip: String): Unit = println(s"$name said $gossip")
  }
  val lilly = new TeamGirl("Lilly")
  lilly `and then said` "Scala is sweet"

  // Syntax Sugar 5. Generics.: Infix types
  class Composite[A, B]
  // simple way
  val simpleComposite: Composite[Int, String] = new Composite[Int, String]
  // fancy way with Infix types
  val Composite: Int Composite String = new Composite[Int, String]

  @targetName("-->")
  class -->[A, B]
  val towards: Int --> String = new -->[Int, String]

  // Syntax Sugar 6. update() method, special like apply()
  val anArray = Array(1, 2, 3)
  anArray(2) = 7 // rewritten as anArray.update(index, value)
  // update() is used in mutable collections
  // remember to implement apply and update methods

  // Syntax sugar 7. Setters for mutable collections
  class Mutable {
    private var internalMember: Int = 0 // private for OOP encapsulation
    def member: Int = internalMember // "getter"
    def member_=(value: Int): Unit =
      internalMember = value // "setter"
  }
  val aMutableContainer = new Mutable
  aMutableContainer.member = 42 // rewritten as aMutableContainer.member_=(42)

}
