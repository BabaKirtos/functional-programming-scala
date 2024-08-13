package playground

/*
## Coding Exercise (30 minutes)

Please implement a Concurrent Digit Sum solution with the following requirements:

1. Create a function that takes a large number (as a string) and the number of partitions as input.
2. Divide the number string into the specified number of partitions.
3. Calculate the sum of digits in each partition concurrently.
4. Combine the partial sums to get the total sum of all digits.
5. Compare the execution time with a sequential digit sum operation.

Use Scala's built-in concurrency tools (e.g., Future, ExecutionContext) to implement the solution.
Below a starting point for your code.

-----
Sample output with largeNumber = "12345", partitions = 2:

Parallel digit sum: 15
Sequential digit sum: 15
Parallel execution time: ?
Sequential execution time: ?
*/

import scala.concurrent.{Future, ExecutionContext}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Success, Failure}

object ParallelDigitSum {
  def main(args: Array[String]): Unit = {
    val largeNumber = "1234567890" * 100000 // 1 million digits
    val partitions = 4

    println(largeNumber.substring(0, 3))
    println(largeNumber.splitAt(1000))

    //     Your implementation here


    def parallelDigitSum(number: String, partitions: Int): Future[Long] = {
      // Implement the parallel digit sum logic here
      def splitter(num: String, part: Int): List[String] = {

        def helper(n: String, p: Int, size: Int, counter: Int = 0, acc: List[String] = List.empty): List[String] = {
          if (counter >= size) acc
          else helper(n, p, size, counter + (size / p), acc ++ List(n.substring(counter, size / p)))
        }

        helper(num, part, num.length)
      }

      val aListString = List("12345", "456767", "6567778")
      
      val a = Future(aListString.map(_.toLong).sum)
      
      println(a)
      
      a
    }

//    def sequentialDigitSum(number: String): Long = {
//      // Implement the sequential digit sum logic here
//      Future
//    }

    //     You may add more helper methods as needed
  }
}
