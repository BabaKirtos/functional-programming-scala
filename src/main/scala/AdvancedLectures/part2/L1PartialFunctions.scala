package AdvancedLectures.part2

object L1PartialFunctions extends App {

  // Whenever we need to perform specific operations
  // based on some condition of the argument being passed,
  // we can use a partial function, or we want to apply the
  // function only on a subset of the argument's range
  val sampleFunction: Int => Int = x => x + 1

  val toEven: PartialFunction[Int, Int] = {
    case x if x % 2 == 0 => x
    case y => sampleFunction(y)
  }
  println(toEven(3))

  // We have already seen partial functions before
  // They are defined with Pattern Matching
  val aPartialFunction: PartialFunction[Int, String] = {
    case 1 => "I'm the one"
    case 2 => "Last of us"
  }
  println(aPartialFunction(2))

  // Below are some additional methods available on PartialFunctions
  // isDefined method returns a boolean but will return if a default case is defined
  println(aPartialFunction.isDefinedAt(4))

  // We can return an Option on the PartialFunction using the lift method
  // It transforms it into a total function
  val liftedPF: Int => Option[String] = aPartialFunction.lift
  println(liftedPF(4))

  // Chaining PartialFunctions


}
