package BasicLectures.part3FP

import scala.annotation.tailrec

object L3HofAndCurries extends App {

  // below is an example of HOF (Higher order function)
  // val superFunction: (Int, (String, (Int => Boolean) => Int) => (Int => Int)) = ???

  // map, flatMap, filter in MyList are examples of HOF

  // a function that applies a function n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) => f(f(f(x))) => if f(x) = 2 * x, n = 2 and x = 2 => 8 , this is a HOF
  // this function applies f before recursion
  def nTimes[A](f: A => A, n: Int): A => A = {
    if (n <= 0) (x: A) => x
    else (x: A) => nTimes(f, n - 1)(f(x))
  }

  println(nTimes[Int](_ * 2, 4)(2))
  println(nTimes[String](x => x + " TailRec " + x, 3)("A"))

  // this function applies f after recursion
  // https://chatgpt.com/share/66fa8b0c-be40-8013-b4ec-3863c099f3a8
  def nTimesRec[A](f: A => A, n: Int): A => A = {
    if (n <= 0) (x: A) => x
    else (x: A) => f(nTimes(f, n-1)(x))
  }

  println(nTimesRec[Int](_ * 2, 4)(2))
  println(nTimesRec[String](x => x + " TailRec " + x, 3)("A"))

  @tailrec
  def nTimesTailRec[A](f: A => A, n: Int, acc: A => A = identity[A]): A => A = {
    if (n <= 0) acc
    else nTimesTailRec(f, n - 1, (x: A) => f(acc(x)))
  }

  println(nTimesTailRec[Int](_ * 2, 4)(2))
  println(nTimesTailRec[String](x => x + " TailRec " + x, 3)("A"))

  @tailrec
  def nTimesValue(f: Int => Int, n: Int, x: Int): Int = {
    if (n <= 0) x
    else nTimesValue(f, n - 1, f(x))
  }

  println(nTimesValue(_ * 2, 4, 2))

  println(List(1, 2, 3).flatMap(List(_)))

  val plusOne = (x: Int) => x + 1
  println(nTimesValue(plusOne, 10, 2))

  // curried functions
  val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3) // y => 3 + y
  println(add3(5))
  println(superAdder(8)(9))

  // Kunal's question
  def functionAdder(f: Int => Int)(x: Int)(y: Int): Int = f(x) + f(y)
  //  val functionAdder: (Int => Int) => Int => Int => Int = (f: Int => Int) => (x: Int) => (y: Int) => f(x) + f(y)
  println(functionAdder(x => x * x * x)(2)(3) + " <---")

  // currying
  // functions with multiple parameters list
  def doubleToString(c: String)(x: Double): String = c.format(x)

  // standard format will format double number passed to it
  val standardFormat: Double => String = doubleToString("%4.2f")
  val preciseFormat: Double => String = doubleToString("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  def compose(f: Int => Int, g: Int => Int): Int => Int = {
    (x: Int) => f(g(x))
  }

  val addMultiply = compose(_ * 2, _ + 2)
  println(addMultiply(2))

  def antiCompose(f: Int => Int, g: Int => Int): Int => Int = {
    (x: Int) => g(f(x))
  }

  val multiplyAdd = antiCompose(_ * 2, _ + 2)
  println(multiplyAdd(2))

  def toCurry[A](f: (A, A) => A): A => A => A = {
    (x: A) => (y: A) => f(x, y)
  }

  println(toCurry[Int](_ * _)(4)(5))
  println(toCurry[String](_ + " " + _)("Hello")("World"))

  def fromCurry[A](f: A => A => A): (A, A) => A = {
    (x: A, y: A) => f(x)(y)
  }

  println(fromCurry[Int]((x: Int) => (y: Int) => x * y)(7, 8))
  println(fromCurry[String]((x: String) => (y: String) => x + " " + y)("Hello", "World"))

  /*
    1. Expand MyList
      - foreach method A => Unit - done
        - ex: [1,2,3].foreach(x => println(x))

      - sort function ((A, A) => Int) => MyList
        - ex: [1,2,3].sort((x, y) => y - x) => [3,2,1]

      - zipWith function (list, (A, A) => B) => MyList[B] - done
        - ex: [1,2,3].zipWith([4,5,6], (x,y) => x * y) => [1 * 4, 2 * 5, 3 * 6] = [4,10,18]

      - fold(start)(function) => value - done
        - ex:[1,2,3].fold(0)(x + y) => 6

    2. toCurry(f: ((Int, Int) => Int) => (Int => Int => Int) - done
       fromCurry(f: (Int => Int => Int) => ((Int, Int) => Int) - done

    3. compose(f,g) => x => f(g(x)) - done
       andThen(g,f) => x => g(f(x)) - done

   */
}
