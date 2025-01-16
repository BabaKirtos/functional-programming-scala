package BasicLectures.part1Basics

import scala.annotation.tailrec

object L4Recursion extends App {

  def aFact(n: Int): Int = {
    // call stack maintains partial results so that it can get the desired result at the end
    if (n <= 1) 1
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
        println(f"x = $x, accumulator = $accumulator")
        factHelper(x - 1, x * accumulator)
        // keeps on computing and storing the result, scala doesn't need to save intermediate results
        // This is called TAIL RECURSION = use recursive call as its LAST expression
        // When you need to use loops, use TAIL RECURSION instead
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
      else stringHelper(word, n - 1, word + " " + result)
    }

    stringHelper(word, n, "")
  }

  println(stringTail("Baba", 10))

  def anotherFibo(n: Int): Int = {
    @tailrec
    def aFiboHelper(x: Int, prev: Int, current: Int): Int = {
      if (x <= 0) prev
      else aFiboHelper(x - 1, current, prev + current)
    }

    aFiboHelper(n, 0, 1)
  }

  println(anotherFibo(10))
}
