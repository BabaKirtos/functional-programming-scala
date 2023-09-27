package playground

import PrintableInstances._
import PrintableSyntax._

trait Printable[A] {
  def print(value: A): String
}

object PrintableInstances {
  implicit val printableString: Printable[String] = (value: String) => value
  implicit val printableInt: Printable[Int] = (value: Int) => value.toString
  implicit val printableBoolean: Printable[Boolean] = (value: Boolean) => if (value) "yes" else "no"
}

object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {
    def print(implicit printable: Printable[A]): String =
      printable.print(value)
  }
}

object TypeClass extends App {

  val printableString: String = "Hello, Scala!"
  val printableInt: Int = 42
  val printableBoolean: Boolean = true

  println(printableString.print) // Output: Hello, Scala!
  println(printableInt.print) // Output: 42
  println(printableBoolean.print) // Output: yes

}
