package AdvancedLectures.part2

object L1PartialFunctions extends App {

  /** Whenever we need to perform specific operations
   * based on some condition of the argument being passed,
   * we can use a partial function, or we want to apply the
   * function only on a subset of the argument's range
   */
  val sampleFunction: Int => Int = x => x + 1

  val toEven: PartialFunction[Int, Int] = {
    case x if x % 2 == 0 => x
    case y =>
      println(s"$y is not even, applying function:")
      sampleFunction(y)
  }
  println(toEven(3))

  /** We have already seen partial functions before,
   * They are defined with Pattern Matching.
   * Below are some additional methods available on PartialFunctions:
   * `isDefined` method returns a boolean but will return if a default case is defined
   */
  val aPartialFunction: PartialFunction[Int, String] = {
    case 1 => "I'm the one"
    case 2 => "Last of us"
  }
  println(aPartialFunction(2))
  println(aPartialFunction.isDefinedAt(4))

  /** We can return an Option on the PartialFunction
   * using the lift method, it transforms into a total function
   */
  val liftedPF: Int => Option[String] = aPartialFunction.lift
  println(liftedPF(4))
  println(liftedPF(1))

  /** Chaining PartialFunctions */
  val anotherPF: PartialFunction[Int, String] = {
    case x if x < 10 => "Input is < 10"
    case _ => "Input is > 10"
  }

  /** Note that the chaining is done on the partial functions with the same type */
  val pfChained: PartialFunction[Int, String] =
    aPartialFunction.orElse[Int, String](anotherPF)

  println(pfChained(4))
  println(pfChained(11))
  println(pfChained(1))

  /** HOFs accept partial functions as arguments,
   * because [[PartialFunction]] extends `(A => B)` or [[Function1]]
   */
  val aTestList = List(1, 2, 3, 4)
  val anChangedList = aTestList.map {
    // This is possible because of the extension
    case x if x % 2 == 0 => "Even"
    case _ => "Odd"
  }
  println(aTestList)
  println(anChangedList)

  /** Let's elaborate using the below case class
   *
   * @param name Name of the person
   * @param age  Age of the person
   */
  case class Person(name: String, age: Int)

  val somePeople = List(
    Person("Alice", 12),
    Person("John", 30),
    Person("Jane", 20)
  )

  /** Persons growing up using [[PartialFunction]] */
  val timePasses = somePeople.map {
    case Person(name, age) => Person(name, age + 1)
    case null => throw new RuntimeException("no nulls in Scala?")
  }
  println(timePasses)
}
