package playground

import scala.annotation.tailrec

object LeetCodeQues extends App {

  // Q1.
  // https://leetcode.com/problems/roman-to-integer/description/
  def romanToInt(s: String): Int = {

    val romanToIntMap: Map[Char, Int] = Map(
      'I' -> 1,
      'V' -> 5,
      'X' -> 10,
      'L' -> 50,
      'C' -> 100,
      'D' -> 500,
      'M' -> 1000
    )

    require(s.forall(romanToIntMap.contains), "Input contains invalid Roman numeral characters")

    val strLength = s.length - 1

    def getValue(index: Int): Int = romanToIntMap(s(index))

    @tailrec
    def helper(str: String, acc: Int = 0, counter: Int = 0): Int = {
      if (counter > strLength) acc
      else if (counter < strLength && getValue(counter) < getValue(counter + 1))
        helper(str, getValue(counter + 1) - getValue(counter) + acc, counter + 2)
      else helper(str, getValue(counter) + acc, counter + 1)
    }

    helper(s)
  }

  // M = 1000, CM = 900, XC = 90 and IV = 4 -> 1994.
  println(romanToInt("MCMXCIV"))

  // Q2.
  // https://leetcode.com/problems/valid-parentheses/description/
  def isValid(s: String): Boolean = {

    val charMap: Map[Char, Char] = Map(
      ')' -> '(',
      '}' -> '{',
      ']' -> '['
    )

    if (charMap.contains(s.head)) return false

    @tailrec
    def helper(remaining: Seq[Char], stack: List[Char] = Nil): Boolean = {
      remaining match {
        case Nil => stack.isEmpty
        case head +: tail => head match {
          case '{' | '[' | '(' => helper(remaining.tail, head :: stack)
          case '}' | ']' | ')' => stack match {
            case top :: rest if charMap(head) == top => helper(remaining.tail, rest)
            case _ => false
          }
          case _ => false
        }
      }
    }

    helper(s)
  }

  println(isValid("()[]{}"))
  println(isValid("(]()"))
}
