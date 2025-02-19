package playground

import javax.xml.transform.Result
import scala.annotation.tailrec
import scala.collection.mutable
import scala.language.postfixOps

object playground {
  def main(args: Array[String]): Unit = {

    println("Hello World!")

    @tailrec
    def loop(i: Int = 0): Unit = {
      println("Hi") // executing a side effect
      if (i < 10)
        loop(i + 1)
    }

    loop()

    val aList: List[Int] = List(1, 2, 3, 4)
    val appendedList: List[Int] = aList :+ 5
    println(appendedList)

    // LeetCode question 1: twoSums
    val nums = Array(1, 3, 4, 2)
    val target = 6

    def twoSum(nums: Array[Int], target: Int): Array[Int] = {
      @tailrec
      def helper(nums: Array[Int], i1: Int, i2: Int, counter: Int = 1): Array[Int] = {
        if (nums(i1) + nums(i2) == target) Array(i1, i2)
        else if (i2 == nums.length - 1) helper(nums, i1 + 1, i1 + 2)
        else helper(nums, i1, i1 + counter, counter + 1)
      }

      helper(nums, 0, 1)
    }

    println(twoSum(nums, target).mkString("Array(", ", ", ")"))

    // LeetCode question 2: Palindrome number
    def isPalindrome(x: Int): Boolean = {
      @tailrec
      def reverse(y: Int, num: Int = 0): Int = {
        if (y < 1) num
        else reverse(y / 10, (y % 10) + (num * 10))
      }

      if (x < 0) false
      else if (reverse(x) == x) true
      else false
    }

    println(isPalindrome(121))

    // LeetCode question 3: Longest common prefix
    def longestCommonPrefix(strs: Array[String]): String = {

      @tailrec
      def minLetters(strs: Array[String], index: Int = 0, acc: Int): Int = {
        if (index == strs.length) acc
        else if (strs(index).length < acc) minLetters(strs, index + 1, strs(index).length)
        else minLetters(strs, index + 1, acc)
      }

      val minimumLetters = minLetters(strs, acc = strs(0).length)

      @tailrec
      def compare(
                   strs: Array[String],
                   outer: Int = 0,
                   inner: Int,
                   result: Boolean = false): Boolean = {
        if (outer == strs.length - 1) result
        else if (strs(outer)(inner) != strs(outer + 1)(inner)) false
        else compare(strs, outer + 1, inner, strs(outer)(inner) == strs(outer + 1)(inner))
      }

      @tailrec
      def helper(strs: Array[String], accu: String = "", index: Int = 0): String = {
        if (index == minimumLetters || !compare(strs, inner = index)) accu
        else helper(strs, accu + strs(0)(index), index + 1)
      }

      if (strs.length == 1 && strs(0).length == 1) strs(0) else helper(strs)
    }

    val strs: Array[String] = Array("flower", "flow", "flowing", "flood")
    println(longestCommonPrefix(strs))

    val newArray: List[String] = strs.toList
    val minSize = newArray.map(_.length).min
    println(minSize)

    // print all combinations between 2 lists
    val numbers = List(1, 2, 3, 4)
    val chars = List('a', 'b', 'c', 'd')
    val colors = List("Red", "Green", "Blue", "Violet")

    // output -> List('a1Red','a2Red',....,'d4Violet')

    val test = chars.flatMap(x => numbers.map(y => "" + x + y))
    println(test)

    val combinations =
      colors.flatMap(col => chars.flatMap(c => numbers.map(n => "" + c + n + "-" + col)))
    println(combinations)

    val forCombinations = for {
      c <- chars
      col <- colors
      n <- numbers
    } yield "" + c + n + "-" + col
    println(forCombinations)

    val device = Set("PCM", "AM53")
    device.foreach(println)

    val test2 = Set("", "1")

    println("".isEmpty)

    // Question: Find square root using Newton's method
    // sqrt(x) = mean(y and x/y) start with y = 1 and iterate
    // test with -> 0.001, 0.1e-20, 1.0e20, 1.0e50
    // isGoodEnough is not very precise for small numbers and can lead to non-termination for large numbers
    // Explain why? https://chat.openai.com/share/e773ec6a-e411-44d6-8d21-a5fb48b8177f
    // It has to do with how computer deal with floating point numbers
    def square(x: Double): Double = x * x

    def abs(x: Double): Double = if (x < 0.0) -x else x

    def sqrt(x: Double): Double = {
      def isGoodEnough(guess: Double): Boolean = abs((square(guess) - x) / x) < 0.001

      def improve(guess: Double): Double = (guess + (x / guess)) / 2

      @tailrec
      def sqreIter(x: Double, guess: Double = 1.0): Double =
        if isGoodEnough(guess) then guess
        else sqreIter(x, improve(guess))

      sqreIter(x)
    }

    println(sqrt(4))
    println(sqrt(2))
    println(sqrt(1.0e-20))
    println(sqrt(1.0e50))

    // Question: Get an element in pascal's triangle with rows and columns as input starting at 0
    // 1
    // 1 1
    // 1 2 1
    // 1 3 3 1
    // 1 4 6 4 1
    // 1 5 10 10 5 1
    // 1 6 15 20 15 6 1

    //    def pascal(row: Int, column: Int): Int = {
    //      def pascalElement(r: Int, c: Int, e: Int = 1): Int =
    //        if (column == 0 || column == row) 1
    //        else pascalElement(row - 1, column - 1) + pascalElement(row - 1, column)
    //
    //      pascalElement(row, column)
    //    }
    //
    //    for (row <- 0 to 10) {
    //      for (col <- 0 to row)
    //        print(s"${pascal(col, row)} ")
    //      println()
    //    }

    def moveZeroes(nums: Array[Int]): Unit = {
      @tailrec
      def HelperZ(nums: Array[Int], i: Int, k: Int): Unit = {
        if (i < nums.length) {
          if (nums(i) != 0) {
            val temp = nums(k)
            nums(k) = nums(i)
            nums(i) = temp
            HelperZ(nums, i + 1, k + 1)
          } else HelperZ(nums, i + 1, k)
        }
      }

      HelperZ(nums, 0, 0)
    }

    def myFunc(ele: List[Any]): (List[Int], List[Float], List[String]) = {

      @tailrec
      def Helper(
                  elem: List[Any],
                  intA: List[Int] = Nil,
                  floatA: List[Float] = Nil,
                  stringA: List[String] = Nil): (List[Int], List[Float], List[String]) = {
        elem match {
          case Nil => (intA.reverse, floatA.reverse, stringA.reverse)
          case head :: tail => head match
            case x: Int => Helper(tail, x :: intA, floatA, stringA)
            case x: Float => Helper(tail, intA, x :: floatA, stringA)
            case x: String => Helper(tail, intA, floatA, x :: stringA)
            case _ => Helper(tail, intA, floatA, stringA)
        }
      }

      Helper(ele, Nil, Nil, Nil)
    }

    val myList = List(1, 2.5f, "hello", 3.14f, "world", 42)
    val result2 = myFunc(myList)
    println(result2)

    case class A(value: Int) {
      override def hashCode(): Int = 1

      override def equals(obj: Any): Boolean = true
    }

    val a1 = A(1)
    val a2 = A(2)
    val a3 = A(3)

    val newMap = Map(a1 -> 1, a2 -> 2, a3 -> 3)

    println(newMap)
    println(a1 == a2)

    val listOfTuple: List[(Int, String)] = List((1, "Hi"), (2, "My"), (3, "Name"), (4, "is"), (5, "SlimShady"))
    val (intList, stringList) = listOfTuple.unzip
    val reducedIntList = intList.reduce((s1, s2) => s1 + s2)
    println(stringList)
    println(reducedIntList)

    // Define a partial function that checks if a number is prime
    val isPrime: PartialFunction[Int, Boolean] = {
      case n if n > 1 && (2 until n).forall(i => n % i != 0) => true
    }

    // Define a function that calculates the sum of prime numbers in a list
    def sumOfPrimes(numbers: List[Int]): Int = {
      numbers.collect {
        case n if isPrime.isDefinedAt(n) && isPrime(n) => n
      }.sum
    }

    val inputList = List(2, 3, 4, 5, 6, 7, 8, 9, 10)
    val sum = sumOfPrimes(inputList)
    println(s"Input List: $inputList")
    println(s"Sum of Prime Numbers: $sum")

    // Cursor sample, if the condition is true then even, else odd
    def someList(input: List[Int], condition: Boolean, limit: Int, cursor: Option[Int] = None): (List[Int], Option[Int]) = {
      import scala.util.Random

      @tailrec
      def helper(acc: List[Int], currentIndex: Int = 0): (List[Int], Int) = {
        if (acc.size == limit || currentIndex >= input.size)
          (acc, currentIndex + 1)
        else {
          val slicedElements = input.slice(currentIndex, currentIndex + limit)
          val filteredElements =
            if (condition) slicedElements.filter(_ % 2 == 0)
            else slicedElements.filterNot(_ % 2 == 0)
          helper(acc ++ filteredElements, currentIndex + limit)
        }
      }

      val (resultList, currentIndex) = helper(Nil, cursor.getOrElse(0))

      if (currentIndex >= input.size) (resultList, None)
      else (resultList, Some(currentIndex))
    }

    // Example usage:
    val numList = (1 to 50).toList
    println(someList(numList, condition = true, limit = 5))
    println(someList(numList, condition = true, 5, Some(11)))
    println(someList(numList, condition = true, 5, Some(46)))

    def concatenateN(n: Int, s: String): String =
      if (n <= 0) "!!"
      else s + concatenateN(n - 1, s)

    println(concatenateN(3, "Hi"))

    val a = List(1, 2, 3, 4)

    val add = (x: Int, y: Int) => x + y

    val multi = (y: Int) => y * 2

    val b = List(add, multi)

    //    val result = a.map(add(_))

    //    println(result)

    // implement calculate, it takes a function as an argument, also int as an argument and prints after applying
    def calculate(f: (Int, Int) => Int)(y: Int)(z: Int): Unit =
      println(f(y, z))

    val add2 = calculate(add)(2)

    add2(7)

    // implement a queue using a stack ds, use pop push, enqueue - insert(push), dequeue - removes (pop)
    // this should be a fifo lifo
    case class MyQueue(a: mutable.Stack[Int]) {

      def enqueue(x: Int) = a.push(x) // appends at the end

      def dequeue: Int = {

        val temp: mutable.Stack[Int] = mutable.Stack()

        val count = a.length

        @tailrec
        def transfer(x: mutable.Stack[Int], acc: mutable.Stack[Int] = mutable.Stack(), counter: Int = 0): mutable.Stack[Int] =
          if (temp.size == counter) acc
          else transfer(x, acc.push(temp.pop()), counter + 1)

        @tailrec
        def helper(a: mutable.Stack[Int], counter: Int = 0): Int = {
          if (counter == count) {
            transfer(temp)
            a.pop()
          }
          else {
            val b = a.pop()
            temp.push(b)
            helper(a, counter + 1)
          }
        }

        helper(a)
      } // returns the last element of MyQueue
    }

    // table 1-10 records, table 2-50 records, table 1 left join on table 2

    // Employee
    //EID	MID
    //1	2
    //2	5
    //3	2
    //4	5
    //5	NULL
    //select a.*,b.* from employee as a left join employee as b on a.mid=b.eid

    // EID	MID EID MID
    // 1	2 2 5
    // 2	5 5 NULL
    // 3	2 2 5
    // 4	5 5 NULL
    // 5	NULL NULL NULL

    // Table1
    //Policy Number	Coverage No.	Premium
    //P1	C1	100
    //P1	C2	100
    //P2	C1	100

    // Policy Number - Number of Coverages	C1	C2	Total Premium
    //P1	2	1	1	200
    //P2	1	1	0	100

    // given a List of strings use tail recursion to search for a String

    val input = List("Apple", "Mango", "Grapes")

    def stringFinder(in: List[String], target: String): Boolean = {
      @tailrec
      def helper(str: List[String], acc: Boolean = false): Boolean = {
        if (str.tail == Nil || acc) acc
        else helper(str.tail, str.head == target)
      }

      helper(in)
    }

    println(stringFinder(input, "Mango"))

    // List(2, 1, 2, 2, 2, 3, 7, 5, 4, 4) -> List(1, 2, 3, 4, 5, 7, 2, 4)

    //    def sortedList(input: List[Int]): List[Int] = {
    //
    //      def sorter(in: List[Int], size: Int, value: Int, counter: Int = 0, acc: List[Int] = List.empty): List[Int] = {
    //        if (in.tail == Nil && counter == size) acc
    //        else if (value < in.head) sorter(in.tail, size, value, counter + 1, acc ++ List(value))
    //        else sorter(in.tail, size, value, counter + 1, acc)
    //      }
    //
    //      sorter(input, input.size, input.head)
    //    }
    //
    //    println(sortedList(List(2, 1, 2, 2, 2, 3, 7, 5, 4, 4)))

    // Array[0,0,1,0,1,1,2,2,1,1,2,0,0] -> only 3 elements 0,1,2. Sort it with low time and space complexity.

    // List(1,2,0) List(3,6,7,8) -> List(1,3,2,6,0,7,8)

    // Q. Write a program to count the frequency of each element in a list.
    // Nums = [1, 2, 3, 2, 1, 3, 2, 4, 5, 4]
    // List((1, 2), (2, 3) .. )

    def frequency1(in: List[Int]): List[(Int, Int)] = {
      in.groupBy(identity).map { case (k, v) => (k, v.size) }.toList
    }

    def frequency(in: List[Int]): List[(Int, Int)] = {
      @tailrec
      def counter(input: List[Int], target: Int, acc: Int = 0): Int = {
        if (input.tail == Nil) acc
        else counter(input.tail, target, if (input.head == target) acc + 1 else acc)
      }

      @tailrec
      def helper(inList: List[Int], acc: List[(Int, Int)] = List.empty): List[(Int, Int)] = {
        if (inList.tail == Nil) acc
        else if (acc.map(_._1).contains(inList.head)) helper(inList.tail, acc)
        else helper(inList.tail, acc ++ List((inList.head, counter(inList, inList.head))))
      }

      helper(in)
    }

    val inNums = List(1, 2, 3, 2, 1, 3, 2, 4, 5, 4)
    println(frequency(inNums))

    // String -> palindrome or not
    // val x = "abcddcba" -> true

    def palindromeCheck(input: String): Boolean = {

      @tailrec
      def helper(in: String, counter: Int = 0, acc: Boolean = false): Boolean = {
        if (counter == (in.length / 2)) acc
        else helper(in, counter + 1, in(counter) == in((in.length - 1) - counter))
      }

      helper(input)
    }

    val x = "malayalam"
    println(palindromeCheck(x))

    // List of numbers, we want to find the last element

    @tailrec
    def lastElement(in: List[Int]): Int = {
      if (in.tail == Nil) in.head
      else lastElement(in.tail)
    }

    val listNums = List(1, 2, 3, 4, 6, 7, 8)
    println(lastElement(listNums))

    //Q. we need to find the list where we need to compare the elements right
    // to that, and the largest of all elements should come on the place of that element.
    //
    // list1 = [1,2,3,4,5]
    // output = [5,5,5,5,5]

    def listReplacer(input: List[Int]): List[Int] = {

      @tailrec
      def findMax(in: List[Int], acc: Int = -1): Int = {
        if (in.tail == Nil) if (in.head > acc) in.head else acc
        else findMax(in.tail, if (in.head > acc) in.head else acc)
      }

      @tailrec
      def helper(in: List[Int], size: Int, max: Int, acc: List[Int] = List.empty): List[Int] = {
        if (in.tail == Nil) acc ++ List(max)
        else helper(in.tail, size, max, acc ++ List(max))
      }

      helper(input, input.size, findMax(input))
    }

    val list1 = List(1, 2, 3, 4, 5)

    println(listReplacer(list1))

    //Input = "hi hello how are you"
    //Output = ["ih","olleh","woh" , "era" ,"uoy"];

    val newInput = "hi hello how are you"

    println(newInput.split(" ").map(customReverse).mkString("Array(", ", ", ")"))

    def customReverse(input: String): String = {

      @tailrec
      def helper(in: String, size: Int, counter: Int = 0, acc: String = ""): String = {
        if (counter == size) acc
        else helper(in, size, counter + 1, acc + in((size - 1) - counter))
      }

      helper(input, input.length)
    }

    println(customReverse("hello"))

    // Generate the next numeric palindrome
    // Ex: input = 121, output = 131
    // 999? -> 1001; xyzyx -> 0 == n, 1 == n-1, ...
    // brute force
    // TODO: Solve using a lower time complexity
    def generateNextPalindrome(input: Int): Int = {

      @tailrec
      def convertToDigits(in: Int, acc: List[Int] = Nil): List[Int] = {
        if (in <= 0) acc
        else convertToDigits(in / 10, in % 10 +: acc)
      }

      @tailrec
      def checkPalindrome(in: List[Int], n: Int = 0, acc: Boolean = true): Boolean = {
        if (!acc || n == in.length / 2) acc
        else checkPalindrome(in, n + 1, in(n) == in(in.length - 1 - n))
      }

      @tailrec
      def helper(nextNumber: Int): Int = {
        if (checkPalindrome(convertToDigits(nextNumber))) nextNumber
        else helper(nextNumber + 1)
      }

      helper(input + 1)
    }

    println(generateNextPalindrome(999))
  }
}
