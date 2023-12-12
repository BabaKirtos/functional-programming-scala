package BasicLectures.part1Basics

import scala.annotation.tailrec

object L4Recursion extends App {

  def aFact(n: Int): Int = {
    // call stack maintains partial results so that it can get desired result at end
    if (n <= 1)
      1
    else {
      println("Computing factorial of " + n + " - I first need factorial of " + (n - 1))
      val result = n * aFact(n - 1)
      println("Computed factorial of " + n + ", result = " + result)
      result
    }
  }
  println(aFact(10))
  //println(aFact(5000))
  // Stack overflow as the intermediate results are too many

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt = {
      if (x <= 1) {
        accumulator
      } else {
        println(f"x = $x accumulator = $accumulator")
        factHelper(x - 1, x * accumulator)
        // keeps on computing and storing the result, scala doesn't need to save intermediate results
        // This is called TAIL RECURSION = use recursive call as its LAST expression
        // When you need loops use TAIL RECURSION
      }
    }
    factHelper(n, 1)
  }
  println(anotherFactorial(10))
  // huge number, so use BigInt
  // factHelper can run by itself, just replace n with required number and print

  def stringTail(word: String, n: Int): String = {
    @tailrec
    def stringHelper(word: String, n: Int, result: String): String = {
      if (n < 1) result
      else stringHelper(word: String, n - 1, word + " " + result)
    }
    stringHelper(word, n, "")
  }
  println(stringTail("Baba", 10))

  def anotherFibbo(n: Int): Int = {
    @tailrec
    def aFibboHelper(n: Int, result: Int): Int = {
      if (n <= 1) result
      else aFibboHelper(n - 2, result + (n - 1) + (n - 2))
    }
    aFibboHelper(n, result = 0)
  }
  println(anotherFibbo(10))
}
