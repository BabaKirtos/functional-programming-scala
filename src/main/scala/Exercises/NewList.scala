package Exercises

object NewList extends App {

  abstract class NewList {
    def head: Int
    def tail: NewList
    def isEmpty: Boolean
    def add(element: Int): NewList
    def addBack(element: Int): NewList
    def printElements: String
    def newStr: String = "[" + printElements + "]"
  }

  object emptyList extends NewList { // singleton instance of empty pointer
    def head: Int = throw new NoSuchElementException
    def tail: NewList = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add(element: Int): NewList = new ConsList(element, emptyList)
    def addBack(element: Int): NewList = new ConsList(element, emptyList)
    def printElements: String = ""
  }

  class ConsList(h: Int, t: NewList) extends NewList {
    def head: Int = h
    def tail: NewList = t
    def isEmpty: Boolean = false
    def add(element: Int): NewList = new ConsList(element, this) // adds in the front of the list
    def addBack(element: Int): NewList = {
      if (t.isEmpty) new ConsList(h, new ConsList(element, emptyList))
      else new ConsList(h, t.addBack(element))
    }
    def printElements: String =
      if (t.isEmpty) "" + h
      else h + " " + t.printElements
  }

  val list = new ConsList(1, new ConsList(2, new ConsList(3, new ConsList(4, emptyList))))
  println(list.addBack(5).newStr)
  println(list.newStr)
  println(list) // pointer to the instance
  println(list.head) // head of instance
  println(list.tail) // pointer to next instance
  println(list.tail.head) // head of next instance
  println(list.tail.tail)
  println(list.tail.tail.head)
  println(list.tail.tail.tail)
  println(list.add(4).head)
}
