package AdvancedLectures.part1

object L2AdvancedPatternMatching extends App {

  /*
  PM:
  - constants
  - objects (singleton)
  - wildcards
  - variables
  - infix patterns
  - Lists
  - case classes etc.
   */

  class Person(val name: String, val age: Int)

  object Person {

    // person match { Person(String, Int) => ... }
    def unapply(person: Person): Option[(String, Int)] = {
      if (person.age < 21) None
      else Some((person.name, person.age))
    }

    // int match { Person(String) => ... }
    def unapply(age: Int): Option[String] = {
      if (age < 21) Some("Young")
      else Some("Old")
    }
  }

  val baba = new Person("Baba", 32)
  val babaPM = baba match { // Person.unapply(baba) => Option((n, a))
    // this does not work with classes without unapply
    // Person cannot be used as an extractor in a pattern
    // because it lacks an unapply or unapplySeq method
    case Person(n, a) => s"Hi! $n, you are $a years old!"
  }
  println(babaPM)

  val babaStatus = baba.age match {
    case Person(status) => s"Baba's status is $status"
  }
  println(babaStatus)

  // boolean patterns
  object even {
    def unapply(arg: Int): Boolean = arg % 2 == 0
  }

  object singleDigit {
    def unapply(arg: Int): Boolean = arg > -10 && arg < 10
  }

  val aNumber: Int = 43

  val mathProperty = aNumber match {
    case even() => "An even number"
    case singleDigit() => "Single digit number"
    case _ => "No special property"
  }
  println(mathProperty)

  // infix patterns
  // Similar to Either type in Scala
  infix case class Or[A, B](a: A, b: B)

  val anEither = Or(2, "two")
  val humanDescriptionEither = anEither match {
    case number Or string => s"$number is written as $string"
  }
  println(humanDescriptionEither)

  // Something we have used before is `::`
  val someList = List(1, 2, 3)
  val listPM = someList match {
    case 1 :: rest =>  "List starting with 1"
    case _ => "Some List"
  }
  println(listPM)

  // decomposing sequences
  val vararg = someList match {
    case List(1, _*) => "List starting with 1"
    case _ => "Some List"
  }
}
