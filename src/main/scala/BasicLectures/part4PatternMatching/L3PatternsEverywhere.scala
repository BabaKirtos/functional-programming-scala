package BasicLectures.part4PatternMatching

object L3PatternsEverywhere extends App {
  // big idea #1
  try {
    // code
  } catch {
    case e: RuntimeException      => "runtime"
    case np: NullPointerException => "null"
    case _                        => "something else"
  }
  // catches are actually matches

  // big idea #2
  val list = List(1, 2, 3, 4)
  val even = for {
    x <- list if x % 2 == 0
  } yield 10 * x

  // generators are also based on pattern matching
  val tuples = List((1, 2), (3, 4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second
  // case classes, :: operator etc

  // big idea #3
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple
  println(b)
  // multiple val definitions based on pattern matching

  val head :: tail = list
  println(head)
  println(tail)

  // big idea #4
  // partial function are based on pattern matching
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1               => "the one"
    case _               => "something else"
  } // partial function literal

  val mappedList2 = list.map { x =>
    x match {
      case v if v % 2 == 0 => v + " is even"
      case 1               => "the one"
      case _               => "something else"
    }
  }
  println(mappedList)
}
