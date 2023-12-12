package BasicLectures.part4PatternMatching

import scala.util.Random

object L1PatternMatching extends App {

  // switch on steroids
  val random = new Random
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "the one"
    case 2 => "double or nothing"
    case 3 => "third time"
    case _ => "something else" // _ is the wildcard/default case
  }
  println(x)
  println(description)

  // 1. Decompose values
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 22)

  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi $n, you are $a years old - 1"
    case Person(n, a) if a > 21 => s"Hi $n, you are $a years old - 2"
    case _ => "I don't know my age"
  }
  println(greeting)
  // cases are matched in order
  // If no cases match then we will get a match error
  // Type returned by pattern matching is unified type of all cases
  // Pattern Matching works well with case classes

  // Pattern matching on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = new Dog("Terra Nova")
  animal match {
    case Dog(someBreed) => println(s"A dog of ${someBreed}")
    case _              => println("something else")
  }

  // Exercise
  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Product(e1: Expr, e2: Expr) extends Expr

  // write a simple function that use PM and takes an expression as a parameter
  // and returns a human readable format
  // Sum(Number(2), Number(3)) => 2 + 3
  // Sum(Number(2), Number(3), Number(4)) => 2 + 3 + 4
  // Prod(Sum(Number(2), Number(3)), Number(4)) => (2 + 3) * 4 // with Parenthesis
  // Sum(Prod(Number(2), Number(3)), Number(4) => 2 * 3 + 4 // no Parenthesis

  def converted(expr: Expr): String = {
    expr match {
      case Number(n)   => s"$n"
      case Sum(e1, e2) => converted(e1) + " + " + converted(e2)
      case Product(e1, e2) =>
        def maybeParenthesis(e: Expr) = {
          e match {
            case Product(_, _) => converted(e)
            case Number(_)     => converted(e)
            case _             => "(" + converted(e) + ")"
          }
        }

        maybeParenthesis(e1) + " * " + converted(e2)
    }
  }

  println(converted(Sum(Number(2), Number(3))))
  println(converted(Product(Sum(Number(2), Number(3)), Number(4))))
  println(converted(Product(Number(4), Sum(Number(2), Number(3))))) // todo

}
