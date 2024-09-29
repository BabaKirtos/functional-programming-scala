package BasicLectures.part1Basics

object L1ValueVariableTypes extends App {

  // Below is a perfectly good example of functions as first class citizens and
  // how to use higher order functions to create a new list
  val addFunction = (x: Int, y: Int) => x + y // with syntactic sugar
  val multiplyFunction: (Int, Int) => Int = (x, y) => x * y // without syntactic sugar
  val fucntionList: List[(Int, Int) => Int] = List(addFunction, multiplyFunction)
  val inputList = List((1, 2), (3, 4), (5, 6), (7, 8))
  val partialFunction: PartialFunction[(Int, Int), List[Int]] = {
    case (a, b) => fucntionList.map(_(a, b))
  }
  val ouputMapList = inputList.map {
    partialFunction
  }
  val ouputFlatMapList = inputList.flatMap {
    partialFunction
  }
  println(fucntionList) // You get a List with 2 elements which are pointers to a lambda function (addNew, multiplyNew)
  println(ouputMapList) // Each element of inputList will return a List
  println(ouputFlatMapList) // We flatten the inner elements to get one outer List

  val y = (a: Int) => a * 2 // Inferred type of Int => Int
  println(y)
  println(y(2)) // think in terms of pointer, like pointer to a function

  val x: Int = 42
  println(x)

  // VALs ARE IMMUTABLE
  // vals are like to const in C, but they are not be used in different parts of the code
  // they are to be used in intermediate computations which would then be used in bigger computations
  // x = 55 will throw a compiler error
  // Compiler can infer types
  // The type of variable is written after colon
  val aString: String = "Hello"
  println(aString)
  println(aString.map(_.toUpper))
  println(aString.flatMap(_ + " "))
  println(aString.filter(_ == 'H'))
  aString.foreach(println(_))

  // for-comprehension
  for {
    a <- aString
  } println(a.toUpper)

  //semicolon can be used at end, but not necessary
  val aBool: Boolean = true
  println(aBool)

  val aChar: Char = 'a'
  println(aChar.toUpper)

  val newInt: Int = 999999999 // 4 bytes
  println(newInt)

  val add: Int = 4 + 5

  val aShort: Short = 9999 // 2 bytes
  println(aShort)

  // we need to define long number with L, and float with f
  val aLong: Long = 999999999999999999L // 8 bytes
  println(aLong)

  val aFloat: Float = 2.0f
  println(aFloat)

  val aDouble: Double = 3.14
  println(aFloat)

  // variables are important as they give us side effects
  // The side effects lets us see what our program is doing
  // functional programming involves working with vals than vars
  // The vars are not preferred as they are mutable and introduce side effects
  var aVariable: Int = 5 // can be reassigned
  println(aVariable)

  aVariable = 10 // variables are used for side effects
  println(aVariable)
}
