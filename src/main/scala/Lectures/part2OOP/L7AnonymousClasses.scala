package Lectures.part2OOP

object L7AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }
  // anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("funny")
  }
  /*
    equivalent to:
  class L7AnonymousClasses$$anon$1 extends Animal {
    override def eat: Unit = println("funny")
  }

  val funnyAnimal: Animal = new L7AnonymousClasses$$anon$1
   */

  println(funnyAnimal.getClass)
}
