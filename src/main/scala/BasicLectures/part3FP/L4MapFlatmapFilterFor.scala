package BasicLectures.part3FP

object L4MapFlatmapFilterFor extends App {

  val list = List(1, 2, 3, 4)
  println(list)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  println(list.flatMap((x: Int) => List(x, x + 2)))
  println(list.flatMap((x: Int) => List(x, x * 2)))

  // exercise
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("Black", "White")
  // print ("a1","a2","a3"..."d4")

  // iterations in FP
  val combinations =
    numbers.filter(_ % 2 == 0).flatMap(n =>
      chars.flatMap(c => colors.map(color => "" + c + n + "-" + color)))
  println(combinations)

  // for comprehensions
  val forCombinations = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  } yield ("" + c + n + "-" + color)

  println(forCombinations)

  // similar to using foreach
  for {
    n <- numbers
  } println(n)

  // exercise - implement a small collection with AT MOST 1 element
  // equivalent to the Option type in scala

}
