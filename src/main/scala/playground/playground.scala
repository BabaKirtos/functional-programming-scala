package playground

import Lectures.part2OOP.Counter

import javax.xml.transform.Result
import scala.language.postfixOps

object playground {
  def main(args: Array[String]): Unit = {

    println("Hello World!")

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
      def helper(nums: Array[Int], i1: Int, i2: Int, t: Int, inc: Int): Array[Int] = {
        if (nums(i1) + nums(i2) == t) Array(i1, i2)
        else if (i2 == nums.length - 1) helper(nums, i1 + 1, i1 + 2, t, 1)
        else helper(nums, i1, i1 + inc, t, inc + 1)
      }
      helper(nums, 0, 1, target, 1)
    }
    println(twoSum(nums, target).mkString("Array(", ", ", ")"))

    // LeetCode question 2: Palindrome number
    def isPalindrome(x: Int): Boolean = {
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

      def minLetters(strs: Array[String], index: Int = 0, acc: Int): Int = {
        if (index == strs.length) acc
        else if (strs(index).length < acc) minLetters(strs, index + 1, strs(index).length)
        else minLetters(strs, index + 1, acc)
      }

      val minimumLetters = minLetters(strs, acc = strs(0).length)

      def compare(
          strs: Array[String],
          outer: Int = 0,
          inner: Int,
          result: Boolean = false): Boolean = {
        if (outer == strs.length - 1) result
        else if (strs(outer)(inner) != strs(outer + 1)(inner)) false
        else compare(strs, outer + 1, inner, strs(outer)(inner) == strs(outer + 1)(inner))
      }

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


//    first page
//      Map("AREA" ***> Vector(MetricView(can_ag_5,AREA,num,Some(CP),Some(Engine),Some(AREA),None,last,Some(num),Some(HA),Some(1),Some(Area),false,Some(0.0),Some(1.0),None,None,Some(PCM),Some(List(a, b, c)),Some(0.0),Some(0.0))), "ENG_SPEED" ***> Vector(MetricView(can_ag_1,ENG_SPEED,num,Some(CP),Some(Engine),Some(ENG_SPEED),None,last,Some(num),Some(RPM),Some(1),Some(Engine Speed),false,Some(0.0),Some(1.0),None,None,Some(PCM),Some(List(a, b, c)),Some(0.0),Some(0.0)), MetricView(can_mh_1,ENG_SPEED,num,Some(MH),Some(Engine),Some(ENG_SPEED),None,last,Some(num),Some(RPM),Some(1),Some(Engine Speed),false,Some(0.0),Some(1.0),None,None,Some(PCM),Some(List(a, b, c)),Some(0.0),Some(0.0))), "FUEL RATE 2" ***> Vector(MetricView(can_ag_4,FUEL RATE 2,num,Some(MH),Some(Engine),Some(FUEL_RATE),None,none,Some(num),Some(L/H),Some(3),Some(Fuel rate),false,Some(0.0),Some(0.5),None,None,Some(PCM),Some(List(a, b, c)),Some(0.0),Some(0.0)))) was not equal to
//      Map("AREA" ***> List(MetricView(can_ag_5,AREA,num,Some(CP),Some(Engine),Some(AREA),None,last,Some(num),Some(HA),Some(1),Some(Area),false,Some(0.0),Some(1.0),None,None,Some(PCM),Some(List(a, b, c)),Some(0.0),Some(0.0))), "ENG_SPEED" ***> List(MetricView(can_ag_1,ENG_SPEED,num,Some(CP),Some(Engine),Some(ENG_SPEED),None,last,Some(num),Some(RPM),Some(1),Some(Engine Speed),false,Some(0.0),Some(1.0),None,None,Some(PCM),Some(List(a, b, c)),Some(0.0),Some(0.0)), MetricView(can_mh_1,ENG_SPEED,num,Some(MH),Some(Engine),Some(ENG_SPEED),None,last,Some(num),Some(RPM),Some(1),Some(Engine Speed),false,Some(0.0),Some(1.0),None,None,Some(PCM),Some(List(a, b, c)),Some(0.0),Some(0.0)), MetricView(can_ag_1_2,ENG_SPEED,num,Some(CP),Some(Engine),Some(ENG_SPEED),None,last,Some(num),Some(RPM),Some(1),Some(Engine Speed),false,Some(0.0),Some(1.0),None,None,Some(AM53),Some(List(b, c, e)),Some(0.0),Some(0.0))), "FUEL RATE 2" ***> List(MetricView(can_ag_4,FUEL RATE 2,num,Some(MH),Some(Engine),Some(FUEL_RATE),None,none,Some(num),Some(L/H),Some(3),Some(Fuel rate),false,Some(0.0),Some(0.5),None,None,Some(PCM),Some(List(a, b, c)),Some(0.0),Some(0.0)))) (PgMetricRepoSpec.scala:64)

//    by name
//      Map("ENG_SPEED" ***> Vector(MetricView(can_ag_1,ENG_SPEED,num,Some(CP),Some(Engine),Some(ENG_SPEED),None,last,Some(num),Some(RPM),Some(1),Some(Engine Speed),false,Some(0.0),Some(1.0),None,None,Some(PCM),Some(List(a, b, c)),Some(0.0),Some(0.0)), MetricView(can_mh_1,ENG_SPEED,num,Some(MH),Some(Engine),Some(ENG_SPEED),None,last,Some(num),Some(RPM),Some(1),Some(Engine Speed),false,Some(0.0),Some(1.0),None,None,Some(PCM),Some(List(a, b, c)),Some(0.0),Some(0.0))), "GROUND_SPEED" ***> Vector(MetricView(can_ag_2,GROUND_SPEED,num,Some(CP),Some(Engine),Some(GROUND_SPEED),None,last,Some(num),Some(KM/H),Some(2),Some(Ground Speed),false,Some(0.0),Some(1.0),None,None,Some(PCM),Some(List(a, b, c)),Some(0.0),Some(0.0)))) was not equal to
//      Map("ENG_SPEED" ***> List(MetricView(can_ag_1,ENG_SPEED,num,Some(CP),Some(Engine),Some(ENG_SPEED),None,last,Some(num),Some(RPM),Some(1),Some(Engine Speed),false,Some(0.0),Some(1.0),None,None,Some(PCM),Some(List(a, b, c)),Some(0.0),Some(0.0)), MetricView(can_mh_1,ENG_SPEED,num,Some(MH),Some(Engine),Some(ENG_SPEED),None,last,Some(num),Some(RPM),Some(1),Some(Engine Speed),false,Some(0.0),Some(1.0),None,None,Some(PCM),Some(List(a, b, c)),Some(0.0),Some(0.0)), MetricView(can_ag_1_2,ENG_SPEED,num,Some(CP),Some(Engine),Some(ENG_SPEED),None,last,Some(num),Some(RPM),Some(1),Some(Engine Speed),false,Some(0.0),Some(1.0),None,None,Some(AM53),Some(List(b, c, e)),Some(0.0),Some(0.0))), "GROUND_SPEED" ***> List(MetricView(can_ag_2,GROUND_SPEED,num,Some(CP),Some(Engine),Some(GROUND_SPEED),None,last,Some(num),Some(KM/H),Some(2),Some(Ground Speed),false,Some(0.0),Some(1.0),None,None,Some(PCM),Some(List(a, b, c)),Some(0.0),Some(0.0)))) (PgMetricRepoSpec.scala:79)

  }
}
