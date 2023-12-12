package BasicLectures.part1Basics

import scala.annotation.tailrec

object L3Functions extends App {

  // defining a function
  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }
  // calling a function
  println(aFunction("hello", 3))

  // parameter less function can be called by their name
  def aParameterLessFunction = {
    "no parameters"
  }
  // scala 3 does not allow to call parameter less functions with
  // scala 2 does
  println(aParameterLessFunction)

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) // think about the end condition
      aString
    else // think about iteration
      aString + " " + aRepeatedFunction(aString, n - 1)
  }
  println(aRepeatedFunction("baba", 5))
  // When you need loops use recursion
  // this is fundamental to functional programming
  // recursive functions need a return type

  def sideEffect(aString: String): Unit = {
    println(aString)
  }
  sideEffect("Side Effects are bad")

  // we can define auxiliary functions within functions
  def aBigFunc(a: Int): Int = {
    def aSmallFunc(m: Int, n: Int): Int = {
      m + n
    }
    aSmallFunc(a, a - 1)
  }
  println(aBigFunc(10))

  // Exercises:

  def aGreeting(name: String, age: Int): Unit = {
    println(f"Hi, my name is $name, and I'm $age years old!")
  }
  aGreeting("Baba", 10)

  def aFact(n: Int): Int = {
    if (n <= 0)
      1
    else
      n * aFact(n - 1)
  }
  println(aFact(10))

  def aFibo(n: Int): Int = {
    if (n <= 2)
      1
    else
      aFibo(n - 1) + aFibo(n - 2)
  }
  println(aFibo(8))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeUntil(t: Int): Boolean = { // does n have any divisors until t
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)
    }
    isPrimeUntil(n / 2)
  }
  println(isPrime(13))
}
