package Lectures.part3FP

object L2AnonFunctions extends App {

  // old way
  val helloOld: String => String = new Function1[String, String] {
    override def apply(name: String): String = s"Hi, $name"
  }
  println(helloOld("Old"))

  // lambda
  val helloLambda: String => String = (name: String) => s"Hi, $name"
  println(helloLambda("Lambda"))

  // lambda + syntactic sugar, basically (x: Int) => x * 2 can be changed to _ * 2
  val helloSugar: String => String = s"Hi, " + _
  println(helloSugar("Sugar"))

  // previous method to instantiate a function
  val doubleOld: Int => Int = new Function1[Int, Int] {
    override def apply(x: Int): Int = x * 2
  }
  println(doubleOld(3))

  // this is an anonymous function (LAMBDA functions)
  // with type annotation we do not need to specify type of x
  val doublerNew: Int => Int = x => x * 2
  println(doublerNew(3))

  // even better way to define doubler, but we need type annotation here
  val doublerSugar: Int => Int = _ * 2

  // Old way
  val adderOld: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(x: Int, y: Int): Int = x + y
  }
  println(adderOld(2, 3))

  // multiple parameters in lambda
  val adderNew: (Int, Int) => Int = (x: Int, y: Int) => x + y
  println(adderNew(2, 3))

  // old way
  val justDoSomethingOld = new Function0[Int] {
    override def apply(): Int = 3
  }
  println(justDoSomethingOld)
  println(justDoSomethingOld())

  // no param
  val justDoSomethingNew: () => Int = () => 3
  // careful
  println(justDoSomethingNew) // function itself
  println(justDoSomethingNew()) // calling the function

  // curly braces with lambda, not commonly used
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // more syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to (x: Int) => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (x: Int, y: Int) => x + y

}
