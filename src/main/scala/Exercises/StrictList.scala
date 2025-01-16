package Exercises

import scala.annotation.tailrec

object StrictList extends App {

  trait StrictList[A] {
    def head: A
    def tail: StrictList[A]
  }

  case class EmptyStrict[A]() extends StrictList[A] {
    def head = throw new NoSuchElementException
    def tail = throw new NoSuchElementException
  }

  case class ConsStrict[A](h: A, t: StrictList[A]) extends StrictList[A] {
    def head: A = h
    def tail: StrictList[A] = t
  }

  // we can define generic methods
  @tailrec
  def lastElement[A](elem: StrictList[A]): A = {
    if (elem == EmptyStrict()) throw new NoSuchElementException
    else if (elem.tail == EmptyStrict()) elem.head
    else lastElement(elem.tail)
  }

  val listOfIntegers: StrictList[Int] =
    ConsStrict(1, ConsStrict(2, ConsStrict(3, ConsStrict(4, EmptyStrict()))))
  val listOfStrings: StrictList[String] =
    ConsStrict("Hi",
      ConsStrict("Hello",
        ConsStrict("new",
          ConsStrict("back", EmptyStrict()))))

  println(lastElement(listOfIntegers))
  println(lastElement(listOfStrings))
}
