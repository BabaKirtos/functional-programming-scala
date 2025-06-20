package AdvancedLectures.part2

object L1PartialFunctions extends App {

  // Whenever we need to perform specific operations
  // based on some condition of the argument being passed,
  // we can use a partial function, or we want to apply the
  // function only on a subset of the argument's range
  val addOne: Int => Int = x => x + 1

  val isEvenNormal: Int => String = (x: Int) => {
    if (x % 2 == 0) s"$x is even, adding one: ${addOne(x)}"
    else s"$x is not even"
  }

  // Looks cleaner if there are more conditions
  val isEvenPartial: PartialFunction[Int, String] = {
    case x if x % 2 == 0 => s"$x is even, adding 1: ${addOne(x)}"
    case y => s"$y is not even"
  }

  println(isEvenPartial(3))
  println(isEvenPartial(4))

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
  println(liftedPF(4)) // returns None

  // Chaining PartialFunctions
  val anotherPF: PartialFunction[Int, String] = {
    case 3 => "I'm the link"
  }

  val aPfChain: PartialFunction[Int, String] =
    aPartialFunction
      .orElse(anotherPF)
      .orElse({
        case _ => "No match found"
      })

  println(aPfChain(3))
  println(aPfChain(4))
}
