package BasicLectures.part2OOP

object L5Traits extends App {

  // abstract classes can have empty data types
  // they cannot be instantiated
  // they are used to be extended later
  abstract class Animal {
    val creatureType: String
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "K9"
    def eat: Unit = println("crunch crunch") // we do not need to write override
  }

  // traits
  trait Carnivore {
    def eat: Unit // this is also abstract
    val preferredMeal: String = "fresh meat"
  }

  class Crocodile extends Animal with Carnivore {
    val creatureType: String = "Croc"
    def eat: Unit = println("nom nom")
    def feel: String = "yum"
    def eat(animal: Animal): Unit =
      println(s"I'm a ${creatureType} and I'm eating a ${animal.creatureType}" +
      s", it is $feel")
  }

  val dog = new Dog
  val croc = new Crocodile

  croc eat dog

  // traits vs abstract classes
  // 1. traits do not have constructor parameters - possible in scala 3
  // 2. multiple traits may be inherited by the same class, but only 1 class
  // 3. traits = behaviour, abstract class is a type of thing

}
