package AdvancedLectures.part1

object L0Recap {

  // values, types, expressions
  val aCondition = 1 == 1 // val is a constant
  val anIfExpression = if (aCondition) 42 else 55 // expressions are evaluated to a value

  val aCodeBlock = {
    if (aCondition) println("Condition is true")
    78
  }

  // functions
  def addOne(x: Int): Int = x + 1

  // Object Oriented Programming
  // test inheritance
  class Animal {
    def model: String = "Animal"

    def anAnimal = println("Coming from Animal!")

    def makeSound(): Unit = println("Animal makes a sound")
  }

  class Dog extends Animal {
    override def model: String = "Dog"

    def aDog = println("Coming from Dog!")

    override def makeSound(): Unit = println("Dog barks")
  }

  class Cat extends Animal {
    override def model: String = "Cat"

    def aCat = println("Coming from a Cat!")

    override def makeSound(): Unit = println("Cat meows")
  }

  val animalDog: Animal = new Dog
  val onlyDog: Dog = new Dog

  // animalDog is of type Animal and does not have access to
  // functions in Dog class even though it is instantiated as a new Dog
  // onlyDog on the other hand has access to functions of both the classes Animal and Dog
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

  // Multiple inheritance with abstract traits
  trait Carnivore {
    def eat(a: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    override def model: String = "Crocodile"

    override def eat(a: Animal): Unit = println(s"$model eats ${a.model}!")
  }

  val aCroc = new Crocodile
  aCroc.eat(animalDog)

  // Anonymous classes
  // Similar to instantiating a class which extends Carnivore trait
  val aCarnivore = new Carnivore {
    override def eat(a: Animal): Unit = println(s"I'm a Carnivore and I eat ${a.model}!")
  }
  aCarnivore.eat(new Cat)

  // Generics
  abstract class LList[A] {
    // type A is known inside this implementation
  }

  // Singletons and Companions
  object LList // companion object, used for instance-independent fields and methods ("static")

  // case classes have a lot of features like:
  // toString implemented, serializable, apply implemented etc.
  case class Person(name: String, age: Int)

  // Enums
  enum BasicColors {
    case RED, GREEN, BLUE
  }

  // exceptions and try/catch/finally
  def throwSomeExceptions(): Int = throw new NullPointerException()

  val aPotentialFail = try {
    throwSomeExceptions()
  } catch {
    case e: Exception => println(e.toString)
  } finally {
    // used for closing resources
    println("Some important logs!")
  }

  // functional programming
  val incrementer = new Function1[Int, Int] {
    override def apply(v1: Int): Int = v1 + 1
  }
  // can be written with Sugar
  val anonymousIncrementer = new(Int => Int) {
    override def apply(v1: Int): Int = v1 + 1
  }
  // can be written anonymously using lambda
  val lambdaIncrementer = (x: Int) => x + 1

  println(incrementer.apply(2))
  println(anonymousIncrementer(3))
  println(lambdaIncrementer(4))

  // Higher Order Functions
  // map, flatMap, filter are HoFs
  val anIncrementedList = List(1, 2, 3, 4).map(_ * 2)
  println(anIncrementedList)

  // for-comprehensions
  val pairs = for {
    number <- List(1, 2, 3, 4)
    char <- List('a', 'b', 'c')
  } yield s"$number-$char"
  println(pairs)

  // Options
  val anOption = Option(42)
  val emptyValue = List(1, 3, 5).find(_ % 2 == 0)
  println(anOption)
  println(emptyValue)

  // Pattern Matching
  val x = 2
  val order = x match {
    case 1 => "the one"
    case 2 => "the second"
    case _ => "not important"
  }
  println(order)

  // Match case classes, as extractors are implemented for them
  val bob = Person("Bob", 32)
  val greeting = bob match {
    case Person(n, a) if a < 21 => s"My name is $n, and I can't drink"
    case Person(n, a) => s"Hi, my name is $n, and I'm $a years old!"
    case null => "I'm nobody"
  }
  println(greeting)

  def main(args: Array[String]): Unit = {
  }
}










