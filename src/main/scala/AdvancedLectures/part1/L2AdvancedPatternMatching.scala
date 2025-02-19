package AdvancedLectures.part1

import scala.annotation.tailrec

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
    case 1 :: rest =>  s"List starting with 1 and ${rest.toString()}"
    case _ => "Some List"
  }
  println(listPM)

  // decomposing sequences
  val vararg = someList match {
    case List(1, _*) => "List starting with 1"
    case _ => "Some List"
  }

  abstract class NewList[A] {
    def head: A
    def tail: NewList[A]
  }

  object NewList {

    // This signature cannot change
    def unapplySeq[A](list: NewList[A]): Option[Seq[A]] = {

      @tailrec
      def helper(l: NewList[A], acc: Seq[A] = Seq.empty): Seq[A] = {
        if (l == NewEmpty()) acc.reverse
        else helper(l.tail, l.head +: acc)
      }

      Some(helper(list))
    }
  }

  case class NewEmpty[A]() extends NewList[A] {
    override def head: A = throw new NoSuchElementException()
    override def tail: NewList[A] = throw new NoSuchElementException()
  }

  case class NewCons[A](override val head: A, override val tail: NewList[A]) extends NewList[A]

  val newList: NewList[Int] = NewCons(1, NewCons(2, NewCons(3, NewEmpty())))

  val varargCustom = newList match {
    case NewList(1, 2, _*) => "List starting with 1 and 2"
    case _ => "Some List"
  }

  println(varargCustom)

  // We don't always need to return an Option for the unapply method
  // We only need to return a Type which has 2 methods, `isEmpty` and `get`
  // Below is a custom type with these methods
  abstract class Wrapper[T] {
    def isEmpty: Boolean
    def get: T
  }

  object CustomPerson {
    def unapply(person: Person): Wrapper[String] = new Wrapper[String] {
      override def isEmpty: Boolean = false
      override def get: String = person.name
    }
  }

  val customBabaPM = baba match {
    case CustomPerson(name) if name == "Slim Shady" => s"Hi, my name is $name"
    case _ => "Hi, my name is Slim Shady"
  }

  println(customBabaPM)
}
