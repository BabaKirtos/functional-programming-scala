package BasicLectures.part1Basics

import scala.annotation.tailrec

object L6DefaultArgs extends App {

  @tailrec
  def trFact(accumulator: Int = 1, n: Int): Int = {
    if (n <= 1) accumulator
    else trFact(n = n - 1, accumulator = n * accumulator)
  }
  println(trFact(n = 10))
  // always pass arguments with name
  // else we won't be able to omit leading arguments
}
