package FunctionalProgramming

import scala.annotation.tailrec

object P1C1Introduction {

  // A comment!
  /* Multi-line
 comment */

  /** A documentation comment */

  // polymorphic HOFs are where we use types to guide us toward an implementation
  /* The object keyword creates a new singleton type,
  which is like a class that only has a single named instance.*/
  def abs(n: Int): Int =
    if (n < 0) -n
    else n

  private def formatAbs(x: Int) = {
    // everything in scala is an object, including the val msg below
    // which is a String object
    val msg = "The absolute of %d is %d"
    msg.format(x, abs(x))
  }

  def factorial(n: Int): Int = {
    @tailrec
    def go(n: Int, acc: Int): Int =
      if (n <= 0) acc
      else go(n - 1, n * acc)

    go(n, 1)
  }

  private def formatFactorial(n: Int) = {
    val msg = "The factorial of %d is %d."
    msg.format(n, factorial(n))
  }

  // Fibonacci series
  // F0 = 0, F1 = 1, F2 = 1, F3 = 2, F4 = 3, F5 = 5
  def fibonacci(n: Int): Int = {
    @tailrec
    def fibTailRec(n: Int, a: Int, b: Int): Int = {
      if (n == 0) a
      else fibTailRec(n - 1, b, a + b)
    }

    fibTailRec(n, 0, 1)
  }

  private def formatFibonacci(n: Int) = {
    val msg = "The fibonacci of %d is %d."
    msg.format(n, fibonacci(n))
  }

  // All the above format methods are similar
  // So we can replace them with a HoF
  private def formatResult(s: String, n: Int, f: Int => Int) = {
    val msg = "The %s of %d is %d."
    msg.format(s, n, f(n))
  }

  // our main method is an outer shell that calls into our purely functional core
  def main(args: Array[String]): Unit = {

    println(formatAbs(-42))
    println(formatFactorial(7))
    println(formatFibonacci(10))

    println(formatResult("absolute", -42, abs))
    println(formatResult("factorial", 7, factorial))
    println(formatResult("fibonacci", 10, fibonacci))
  }

  /*
  * The implementations of members within an object can refer to
  * each other unqualified (without prefixing the object name), but
  * if needed they have access to their enclosing object using a special name: this
  * */

  /*
  * The first new idea is this: functions are values.
  * And just like values of other types—such as integers, strings,
  * and lists—functions can be assigned to variables, stored in data structures,
  * and passed as arguments to functions.
  * */
}
