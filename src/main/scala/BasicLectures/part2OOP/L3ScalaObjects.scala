package BasicLectures.part2OOP

import scala.language.postfixOps

object L3ScalaObjects {

  // one of the fundamental aspect of OOP is called class level programming
  // this means functionality that does not depend on an instance of a class
  // Universal functions, classes and constants available throughout the program
  // In java we use class for this
  // SCALA DOES NOT HAVE CLASS LEVEL FUNCTIONALITY ("static")
  // scala has objects, which is static like functionality

  // Objects provide static type programming of Java
  // They are Singleton in nature ie they have only 1 instance
  // They do not have any parameters
  object Person { // Only type and instance
    // static class level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    // factory method
    def apply(mother: Person, father: Person): Person = new Person("Bobby")
  }

  class Person(val name: String) {
    // instance level functionality
  }

  // We write Objects and classes with the same name, this is called COMPANIONS
  // This is to separate instance level functionality from class level functionality
  // They reside in the same scope and have the same name

  def main(args: Array[String]): Unit = {
    println(Person N_EYES)
    println(Person canFly)

    val mary = Person
    val tom = Person

    println(mary == tom) // points to the same object

    val olive = new Person("Olive")
    val dave = new Person("Dave")

    println(olive == dave) // points to 2 different instances of class Person

    val bobby = Person(olive, dave)
  }

  // Scala Applications = scala object with
  // the entrance point :
  // def main (args: Array[String]): Unit = {code}
  // we need to write this if we do not include extends App
  // The App already has the main method in it

  // All vals initialized with that object point to the same instance

  // Combination of objects and classes with same names are called COMPANIONS
  // they share each others private def and vals?

}
