package BasicLectures.part1Basics

object L5CallByNameVsValue extends App {

  def callByValue(x: Long): Unit = {
    println("by value : " + x)
    println("by value : " + x)
  }

  // the arrow => tells the compiler that the function will be called by name
  def callByName(x: => Long): Unit = {
    println("by name : " + x)
    println("by name : " + x)
  }

  // System.nanotime() tells current system time in nano seconds
  // that why we choose long as parameter input
  // when calling by name the expression is passed, whereas in value the value is passed
  // that's why we see a change in time for call by name
  callByValue(System.nanoTime())
  callByName(System.nanoTime())

  def infinite(): Int = {
    1 + infinite()
  }

  def printFirstByValue(x: 34, y: Int): Unit = {
    println(x)
  }

  def printFirstByName(x: Int, y: => Int): Unit = {
    println(x)
  }
  // printFirstByValue(infinite(), 34) // this will crash the system as it tries to evaluate infinity
  printFirstByName(34, infinite())
  // the by name call delays the evaluation of the function till it is used
  // that's why the system does not crash

  // example of closure in scala
  // Function that takes an integer and returns a closure
  def multiplier(factor: Int): Int => Int = {
    // Closure: Takes an integer x and multiplies it by the factor
    (x: Int) => x * factor
  }

  // Create closures with different factors
  val multiplyByTwo = multiplier(2)
  val multiplyByThree = multiplier(3)

  // Use closures
  val result1 = multiplyByTwo(5) // 5 * 2 = 10
  val result2 = multiplyByThree(5) // 5 * 3 = 15

  // Print results
  println(s"Result 1: $result1")
  println(s"Result 2: $result2")
}
