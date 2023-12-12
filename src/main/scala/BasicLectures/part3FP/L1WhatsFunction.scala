package BasicLectures.part3FP

trait MyFunction[A, B] {
  def apply(element: A): B
}

class Action {
  def execute(element: Int): String = element.toString
}

trait Action2[-A, B] {
  def execute(element: A): B
}

object L1WhatsFunction extends App {

  // Dream: use functions as 1st class elements
  // Problem: oop
  // we would use the Action class as function by instantiation
  val newAction = new Action
  println(newAction.execute(element = 1))

  // At most we can do type parameterization of a trait
  // then instantiate the trait to a val with override def
  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  // Advantage of scala is that we can call doubler of 2
  println(doubler(2))

  // Scala has built in support for function types = function1[A, B] to function24
  // so we do not need to write a trait for instantiation
  val stringToIntConverter = new Function[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }
  println(stringToIntConverter("3") + 4) // 3 will be converted to Int and then added to 4

  // scala function types can take 22 parameters
  val adder: (Int, Int) => Int = (v1: Int, v2: Int) => v1 + v2
  // we will use this (A, B) => R function type
  println(adder(1, 2))

  // IN SCALA, ALL FUNCTIONS ARE OBJECTS, MEANING INSTANCE OF SOME CLASS

  // Exercise:
  // 1. A function which takes 2 strings and concatenates them
  // 2. Define a function which takes an int and returns another function, the other function
  //    takes an int and return an int

  val stringConcat: (String, String) => String = new Function2[String, String, String] {
    override def apply(s1: String, s2: String): String = s1 + " " + s2
  }
  println(stringConcat("Hello", "World"))

  // Function1[ Int, Function1[Int, Int]] this is a higher order function
  // old way
  val superAdderOld: (Int) => (Int => Int) = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  // lambda style
//  val superAdder: (Int) => (Int => Int) = (x: Int) => (y: Int) => x + y

  // lambda + syntactic sugar, only for 1 placeholder, y
  val superAdder: (Int) => (Int => Int) = (x: Int) => x + _

  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(2)) // returns Function1
  println(superAdder(3)(4)) // curried function
  // superAdder returns a function which is then called with another Int
}
