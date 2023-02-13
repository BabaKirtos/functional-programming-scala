package Lectures.part2OOP

import scala.annotation.targetName
import scala.language.postfixOps

object L2MethodNotations extends App {

  // the below class is declared inside the object that's why
  // it does not conflict with the class person declared in L1
  class Person(val name: String, favMovie: String, val age: Int = 0) {

    val movieTimes = s"Mary watched $favMovie"

    def likes(movie: String): Boolean = movie == favMovie
    def &(person: Person): String = s"${this.name} is hanging with ${person.name}"
    def &(nickName: String): Person = new Person(s"${name} (${nickName})", favMovie, age)
    def unary_! : String = s"${name}, hello!"
    def unary_+ : Person = new Person(name, favMovie, age + 1)
    def isAlive: Boolean = true
    def learns(language: String): String = s"$name is learning $language"
    def learnsScala: String = this learns "Scala"
    def apply(): String = s"Hi, my name is $name, I like $favMovie and I'm $age years old"
    def apply(number: Int): String =
      if (number > 1) s"$movieTimes $number times" else s"$movieTimes $number time"
  }

  val mary = new Person("Mary", "Inception", age = 25)
  println(mary.likes("Inception"))
  println(mary likes "Inception") // equivalent
  // infix notation = operator notation (syntactic sugar)
  // only works with 1 parameter methods

  // "operators" in scala
  val tom = new Person("Tom", "Fight Club")
  println(mary & tom)

  println(1.+(2))
  // ALL OPERATORS ARE METHODS
  // Akka actors have ! ?

  // prefix notations
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-
  // unary_ prefix only works with - + ~ !
  println(x)
  println(y)

  println(!mary)
  println(mary.unary_!)

  // postfix notations
  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary()) // equivalent
  // compiler looks for "apply" method

  println((mary & "the Rockstar")())
  println((+mary)())

  println(mary learnsScala)
  println(mary(2))

}
