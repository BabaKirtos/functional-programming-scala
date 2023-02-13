package Lectures.part2OOP

object L10Exceptions extends App {

  val x: String = null
  //  println(x.length)

  // throwing and catching exceptions
  // throwing an exception is an expression
  // the value of the below expression is Nothing
  // we use new keyword because exceptions are instance of classes
  //  val aWierdValue = throw new NullPointerException

  // throwable classes extends the throwable class
  // exceptions and errors are the major subtypes
  // exceptions is for code issues and error is for system issues (jvm)

  // catching exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int received")
    else 42

  val potentialFail =
    try {
      // code that might throw an exception
      getInt(true)
    } catch {
      case e: RuntimeException => println("caught a runtime exception")
    } finally {
      // code that will get executed
      // it is optional and does not influence the return type of this expression
      println("finally")
    }

  potentialFail

  // how to create your own exception
  //  class MyException extends Exception
  //  val exception = new MyException
  //
  //  throw exception

  /*
  1. Crash a program with OutOfMemoryError
  2. Crash a program with StackOverflowError
  3. Pocket Calculator:
     - add
     - subtract
     - multiply
     - divide
    Custom exceptions:
      - throw overflow exception is add exceeds int.MAX_VALUE
      - throw underflow exception if subtract exceeds int.MIN_VALUE
      - MathCalculationException if divide by zero
   */

  //  val array = Array.ofDim[Int](Int.MaxValue) // OutOfMemoryError
  //  def infiniteRecursion: Int = 1 + infiniteRecursion // Stack overflow error
  //  val noLimit = infiniteRecursion

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by 0")

  object PocketCalculator {
    def add(a: Int, b: Int): Int = {
      val result = a + b
      if (a > 0 && b > 0 && result < 0) throw new OverflowException
      else if (a < 0 && b < 0 && result > 0) throw new UnderflowException
      else result
    }
    def sub(a: Int, b: Int): Int = {
      val result = a - b
      if (a > 0 && b < 0 && result < 0) throw new OverflowException
      else if (a < 0 && b > 0 && result > 0) throw new UnderflowException
      else result
    }
    def multiply(a: Int, b: Int): Int = {
      val result = a * b
      if (a > 0 && b > 0 && result < 0) throw new OverflowException
      else if (a < 0 && b < 0 && result < 0) throw new OverflowException
      else if (a > 0 && b < 0 && result > 0) throw new UnderflowException
      else if (a < 0 && b > 0 && result > 0) throw new UnderflowException
      else result
    }
    def divide(a: Int, b: Int): Int = {
      if (b == 0) throw new MathCalculationException
      else a / b
    }
  }

//  println(PocketCalculator.add(Int.MaxValue, 4))
//  println(PocketCalculator.divide(2, 0))

}
