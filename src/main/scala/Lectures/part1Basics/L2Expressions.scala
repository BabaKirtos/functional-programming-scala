package Lectures.part1Basics

object L2Expressions extends App {

  // compiler knows the type of expression
  // like the below expression will return int
  val x = 1 + 2 // Expression
  println(x)

  // math -> + add - minus * multiply
  // bitwise -> & and | or ^ xor << >> >>> right shift with 0
  println(2 + 3 * 4)

  // relational -> == equality <= >= < >
  println(1 == x)

  // logical operators-> && || == !
  println(!(1 == x))
  println(true && false)
  println(true || false)

  def add(a: Int, b: Int): Unit = {
    a + b
  }

  val added = add(2, 3)

  // operations with a variable
  var aVariable = 2
  aVariable /= 2 // also works with += -= *= /= .. side effects
  println(aVariable)

  // Instructions vs Expressions
  // an instruction is something we tell the computer to do
  // an expression is something that has a value or a type (evaluated)

  // IF expression
  val aCondition = true
  val aConditionedValue = if (aCondition) 5 else 3
  println(aConditionedValue)
  // unlike in other languages
  // where we say if a condition is true do this
  // in scala we return a value
  // therefore we call it if expression
  println(if (aCondition) 5 else 3)

  // loops are discouraged in scala
  // as they only execute side effects
  var a = 1
  while (a <= 10) {
    println(a)
    a += 1
  }
  // NEVER WRITE LOOPS IN SCALA

  // EVERYTHING in scala is an EXPRESSION, ie everything will return something
  val aWeirdValue = aVariable = 3 // Unit = void ****
  // unit is a special type in scala which does not return anything
  println(aWeirdValue)

  // side effects in scala are expression returning unit
  println((aVariable = 6)) // reassignment of a var is also unit type
  // the while loop will also return unit
  // side effects -> println(), whiles, reassignment of vars
  // these are all expressions returning unit

  // Code blocks
  val aCodeBlock = {
    val y = 2
    val z = y + 1
    if (z > 2) "hi" else 5
    9 // this will be returned
  }
  println(aCodeBlock)
  // code block is an expression
  // the value of the block is the last expression

  // Takeaway -> Instructions are executed, Expressions are evaluated
  // every line of code in scala returns something
  // we need to think in terms of expressions
}
