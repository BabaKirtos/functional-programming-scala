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
  // scala 3 does not allow parameter less function calls
  // scala 2 does
  println(aParameterLessFunction)

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) // think about the end condition
      n.toString + " " + aString
    else // think about iteration
      n.toString + " " + aString + " " + aRepeatedFunction(aString, n - 1)
  }

  println(aRepeatedFunction("baba", 5))
  // Use loops instead of recursion as they avoid variable mutation,
  // this is fundamental to functional programming
  // recursive functions need a return type

  def sideEffect(aString: String): Unit = {
    println(aString)
  }

  sideEffect("Side Effects are bad, but necessary")

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
    if (n <= 0) 1
    else n * aFact(n - 1)
  }

  println(aFact(10))

  def aFibonnaci(n: Int): Int = {
    if (n <= 2) 1
    else aFibonnaci(n - 1) + aFibonnaci(n - 2)
  }

  println(aFibonnaci(8))

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
