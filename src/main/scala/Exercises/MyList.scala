package Exercises

abstract class MyList[+A] { // like an empty template, telling its child what to do
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String // polymorphic call
  def toStr: String = "[" + printElements + "]"

  // higher order functions
  // they either receive functions as parameters or return functions as result
  def map[B](f: A => B): MyList[B]
  def flatMap[B](f: A => MyList[B]): MyList[B]
  def filter(f: A => Boolean): MyList[A]
  def ++[B >: A](list: MyList[B]): MyList[B] // concatenation
  def foreach(f: A => Unit): Unit
  def sort(f: (A, A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], f: (A, B) => C): MyList[C]
  def fold[B](start: B)(f: (A, B) => B): B
}

case object Empty extends MyList[Nothing] { // defines an empty list
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[A >: Nothing](element: A): MyList[A] = new Cons(h = element, t = Empty)
  def printElements: String = ""

  def map[B](f: Nothing => B): MyList[B] = Empty
  def flatMap[B](f: Nothing => MyList[B]): MyList[B] = Empty
  def filter(f: Nothing => Boolean): MyList[Nothing] = Empty
  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
  def foreach(f: Nothing => Unit): Unit = ()
  def sort(f: (Nothing, Nothing) => Int): MyList[Nothing] = Empty
  def zipWith[B, C](list: MyList[B], f: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("Lists do not have same length")
    else Empty
  def fold[B](start: B)(f: (Nothing, B) => B): B = start
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  def printElements: String =
    if (t.isEmpty) "" + h
    else s"${h} ${t.printElements}"

  def map[B](f: A => B): MyList[B] =
    new Cons(f(h), t.map(f))

  def flatMap[B](f: A => MyList[B]): MyList[B] =
    f(h) ++ t.flatMap(f)

  def filter(f: A => Boolean): MyList[A] =
    if (f(h)) new Cons(h, t.filter(f))
    else t.filter(f)

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(f: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] = {
      if (sortedList.isEmpty) new Cons(x, Empty)
      else if (f(h, sortedList.head) <= 0) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))
    }
    val sortedTail = t.sort(f)
    insert(h, sortedTail)
  }

  def zipWith[B, C](list: MyList[B], f: (A, B) => C): MyList[C] =
    if (list.isEmpty) throw new RuntimeException("Lists do not have same length")
    else new Cons(f(h, list.head), t.zipWith(list.tail, f))

  def fold[B](start: B)(f: (A, B) => B): B = {
    t.fold(f(h, start))(f)
  }
  
}

object Cons {
  def apply[A](elems: A*): MyList[A] = {
    def creator(elems: Seq[A], counter: Int = 0, list: MyList[A] = Empty): MyList[A] = {
      if (counter == elems.length) list
      else creator(elems, counter + 1, list ++ new Cons[A](h = elems(counter), t = Empty))
    }
    creator(elems)
  }
}

//trait MyPredicate[-T] {
//  def test(elem: T): Boolean
//}
//
//trait MyTransformer[-A, B] {
//  def transform(elem: A): B
//}

object ListTest extends App {

  val newList = List(1,2,3)
  val b = newList.map(x => x + " Hi")
  println(b)

  val listOfIntegers: MyList[Int] = Cons(1,2,3,4)
  val listOfIntegers1: MyList[Int] = Cons(5,6,7,8)
  val listOfStrings: MyList[String] = Cons("Hi","Hello","World","Now")

  // hof - foreach
  listOfIntegers.foreach((x: Int) => println(x))
  // hof - sort
  println(listOfIntegers.sort((x, y) => y - x).toStr)

  // hof - zipWith
  println(listOfIntegers.zipWith(listOfIntegers, _ * _).toStr)
  println(listOfIntegers.zipWith(listOfStrings, (x: Int, y: String) => x + "-" + y).toStr)

  // hof - fold
  println(listOfIntegers.fold(2)(_ * _))

  // old way
//  println(listOfIntegers.map(new Function1[Int, Int] {
//    override def apply(elem: Int): Int = elem * 2
//  }).toStr)

  // lambda style
//  println(listOfIntegers.map((elem: Int) => elem * 2).toStr)

  // lambda + syntactic sugar
  println(listOfIntegers.map(_ * 2).toStr)

  // old way
//  println((listOfIntegers.filter(new Function1[Int, Boolean] {
//    override def apply(elem: Int): Boolean = elem % 2 == 0
//  })).toStr)

  // lambda style
//  println((listOfIntegers.filter((elem: Int) => elem % 2 == 0).toStr)

  // lambda + syntactic sugar
  println(listOfIntegers.filter(_ % 2 == 0).toStr)

  // old way
//  println(listOfIntegers.flatMap(new Function1[Int, MyList[Int]] {
//    override def apply(elem: Int): MyList[Int] = new Cons(elem, new Cons(elem + 1, Empty))
//  }).toStr)

  // lambda style
  println(listOfIntegers.flatMap((elem: Int) => new Cons(elem, new Cons(elem + 1, Empty))).toStr)

  // lambda + syntactic sugar
//  println(listOfIntegers.flatMap(new Cons(_, new Cons(_ + 1, Empty))).toStr)

  println((listOfIntegers ++ listOfIntegers1).toStr)

  // true because they are case classes
  println(listOfIntegers == listOfIntegers1)

  // for comprehensions works for MyList as
  // function signatures for map,flatMap and filter match
  // with the actual def for scala list
  for {
    n <- listOfIntegers
  } println(n)

  val forMyList = for {
    n <- listOfIntegers
    s <- listOfStrings
  } yield (n + "-" + s)

  println(forMyList.toStr)

}

/*
Implement a singly linked list
1. The method head = 1st element of the list
2. tail = remainder of list
3. isEmpty = boolean, is this list empty
4. add = receives an integer and returns a new list with this element added
5. toString = string representation
 */
