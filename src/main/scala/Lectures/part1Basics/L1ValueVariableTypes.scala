package Lectures.part1Basics

object L1ValueVariableTypes extends App {

  val x: Int = 42
  println(x)

  // VALs ARE IMMUTABLE
  // vals are like to const in C, but they are not be used in different parts of the code
  // they are to be used in intermediate computations which would then be used in bigger computations
  // x = 55 will throw a compiler error
  // Compiler can infer types
  // type of variable is written after colon

  val aString: String = "Hello"
  println(aString)
  println(aString.map(_.toUpper))
  println(aString.flatMap(_ + " "))
  println(aString.filter(_ == 'H'))
  aString.foreach(println(_))

  val aNewString: Unit = for {
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

  val aLong: Long = 999999999999999999L // 8 bytes
  println(aLong)

  val aFloat: Float = 2.0f
  println(aFloat)

  // we need to define long number with L, and float with f

  val aDouble: Double = 3.14
  println(aFloat)

  // variables are important as they give us side effects
  // side effects lets us see what our program is doing
  // functional programming involves working with vals than vars
  // vars are not preferred as they are mutable and introduce side effects

  var aVariable: Int = 5 // can be reassigned
  println(aVariable)

  aVariable = 10 // variables are used for side effects
  println(aVariable)

}
