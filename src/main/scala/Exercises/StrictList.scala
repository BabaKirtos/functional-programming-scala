package Exercises

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
  def lastElement[A](elem: StrictList[A]): A = {
    if (elem == EmptyStrict())
      throw new NoSuchElementException
    else if (elem.tail == EmptyStrict())
      elem.head
    else
      lastElement(elem.tail)
  }

  val listOfIntegers: StrictList[Int] =
    new ConsStrict(1, new ConsStrict(2, new ConsStrict(3, new ConsStrict(4, EmptyStrict()))))
  val listOfStrings: StrictList[String] = new ConsStrict(
    "Hi",
    new ConsStrict("Hello", new ConsStrict("new", new ConsStrict("back", EmptyStrict()))))

  println(lastElement(listOfStrings))
  println(lastElement(listOfIntegers))
}
