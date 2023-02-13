package playground

object chapter1 extends App {

  var capital = Map("India" -> "Delhi")
  capital += ("Japan" -> "Tokyo")
  println(capital)

  val some: String = "1"
  val newInt = some.toInt
  println(newInt)
}
