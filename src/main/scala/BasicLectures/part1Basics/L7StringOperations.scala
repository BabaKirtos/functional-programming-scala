package BasicLectures.part1Basics

object L7StringOperations extends App {

  val str: String = "Hello, I'm learning scala"

  println(str.charAt(2))
  // pick character at 2 using charAt(2)
  println(str.substring(7, 11))
  // substring will take a part of string
  println(str.split(" ").toList)
  // splits the string based on a character
  println(str.startsWith("Hello"))
  // Returns a boolean
  println(str.replace(" ", "-"))
  // replace with a new character or string
  println(str.toLowerCase())
  println(str.toUpperCase())
  println(str.length)

  val aNumberString = "45"
  val aNumber = aNumberString.toInt

  println('a' +: aNumberString :+ 'z')
  // +: prepending a character to string
  // :+ is appending
  println(str.reverse)
  println(str.take(2))

  // Scala-Specific: String Interpolators

  // S-Interpolators
  val name = "Baba"
  val age = 29
  val greeting = s"Hello, my name is $name, and I'm $age years old"
  println(greeting)
  val anotherGreeting = s"Hello, my name is $name, and I'll be turning ${age + 1}"
  println(anotherGreeting)

  // F-Interpolator
  val speed = 1.2f
  val myth = f"$name can eat $speed%2.2f burgers per minute"
  println(myth)
  // f interpolators can check type

  // Raw Interpolator
  println("This is a\nnewline")
  // raw not working, check why
}
