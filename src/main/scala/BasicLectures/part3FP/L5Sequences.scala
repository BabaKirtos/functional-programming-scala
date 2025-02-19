package BasicLectures.part3FP

import scala.util.Random

object L5Sequences extends App {

  // Seq
  // they have a well-defined order
  // they are indexed
  val aSeq = Seq(1, 3, 2, 4)
  println(aSeq)
  println(aSeq.indices)
  println(aSeq.reverse)
  println(aSeq(2))
  println(aSeq ++ Seq(5, 6, 7))
  println(aSeq.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 to 10
  val aNewRange: Seq[Int] = 1 until 10
  // 'to' is inclusive whereas 'until' is non-inclusive
  aRange.foreach(println)
  aNewRange.foreach(println)
  // iterate using ranges
  (1 to 10).foreach(x => println("Hello"))

  // Lists
  val aList = List(1, 2, 3, 4)
  println(aList)
  // prepend a value
  val prepended = 42 :: aList
  val anotherPrepend = 42 +: aList :+ 89 // +: prepend :+ append
  println(prepended)
  println(anotherPrepend)
  // fill method
  val apples5 = List.fill(5)("apple")
  println(apples5)
  // mkString method
  println(aList.mkString("-|-"))

  // Arrays - are mutable
  val numbers = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[Int](3)
  println(threeElements.mkString("Array(", ", ", ")"))
  threeElements.foreach(println)
  //mutation
  println(numbers.mkString(" "))
  numbers(2) = 0 // syntactic sugar for number.update(2, 0)
  println(numbers.mkString(" "))

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers // array to ArraySeq => implicit conversions
  println(numbersSeq)

  // vectors
  val aVector: Vector[Int] = Vector(1, 2, 3, 4)
  println(aVector)

  // performance test: Vectors vs. Lists
  val maxRuns = 1000 // number of runs
  val maxCapacity = 1000000 // max index of the collect

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      itr <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // keeps reference to tails
  // updating an element in the middle takes a long time
  println(getWriteTime(numbersList))
  // the depth of the tree is small
  // needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector))

}
