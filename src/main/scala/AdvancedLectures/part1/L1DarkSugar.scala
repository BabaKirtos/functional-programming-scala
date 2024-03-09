package AdvancedLectures.part1

import java.awt.Composite
import scala.annotation.targetName
import scala.util.Try

object L1DarkSugar extends App {

  // test inheritance
  class Animal {
    def anAnimal = println("Coming from Animal!")

    def makeSound(): Unit = println("Animal makes a sound")
  }

  class Dog extends Animal {
    def aDog = println("Coming from Dog!")

    override def makeSound(): Unit = println("Dog barks")
  }

  class Cat extends Animal {
    def aCat = println("Coming from a Cat!")

    override def makeSound(): Unit = println("Cat meows")
  }

  val animalDog: Animal = new Dog
  val onlyDog: Dog = new Dog

  // animalDog is of type Animal and does not have access to
  // functions in Dog class even though it is instantiated as
  // a new Dog
  // onlyDog on the other hand has access to functions of both
  // the classes Animal and Dog
  animalDog.anAnimal // Works
  //  animalDog.aDog // This does not work
  onlyDog.anAnimal // Works
  onlyDog.aDog // Works

  // Abstraction
  def makeAnimalSound(animal: Animal): Unit = {
    animal.makeSound()
  }

  // Polymorphic behavior
  val animals: Array[Animal] = Array(new Animal, new Dog, new Cat)

  for (animal <- animals) {
    makeAnimalSound(animal)
  }

  // Syntax Sugar 1. Methods with single parameter
  def singleArgs(args: Int): String = s"$args little ducks"

  val cumbersome = singleArgs({
    // using parenthesis and curly braces as a code block
    println("parenthesis block")
    4
  })
  // we can omit the parenthesis
  // passing the argument from return value of code block
  val desc = singleArgs {
    // write some complex code
    println("syntax block")
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

  val towards: Int --> String = new-->[Int, String]

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
