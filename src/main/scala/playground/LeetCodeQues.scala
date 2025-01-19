package playground

import scala.annotation.tailrec

object LeetCodeQues extends App {

  // Difficulty - Easy
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

  // Q3.
  // https://leetcode.com/problems/merge-two-sorted-lists/description/

  /**
   * Definition for a singly linked list.
   * Class ListNode(_x: Int = 0, _next: ListNode = null) {
   * var next: ListNode = _next
   * var x: Int = _x
   * }
   */
  def mergeTwoLists(list1: ListNode, list2: ListNode): ListNode = {

    @tailrec
    def helper(l1: ListNode, l2: ListNode, acc: ListNode): ListNode = {
      if (l1 == null && l2 == null) {
        acc
      } else if (l1 == null) {
        acc.next = new ListNode(l2.x, l2.next)
        helper(l1, null, acc)
      } else if (l2 == null) {
        acc.next = new ListNode(l1.x, l1.next)
        helper(null, l2, acc)
      } else if (l1.x <= l2.x) {
        acc.next = new ListNode(l1.x)
        helper(l1.next, l2, acc.next)
      } else {
        acc.next = new ListNode(l2.x)
        helper(l1, l2.next, acc.next)
      }
    }

    @tailrec
    def helperPM(l1: ListNode, l2: ListNode, acc: ListNode): ListNode = {
      (l1, l2) match {
        case (null, null) => acc
        case (null, _) =>
          acc.next = new ListNode(l2.x, l2.next)
          helperPM(l1, null, acc)
        case (_, null) =>
          acc.next = new ListNode(l1.x, l1.next)
          helperPM(null, l2, acc)
        case (_, _) if l1.x <= l2.x =>
          acc.next = new ListNode(l1.x)
          helperPM(l1.next, l2, acc.next)
        case (_, _) =>
          acc.next = new ListNode(l2.x)
          helperPM(l1, l2.next, acc.next)
      }
    }

    if (list1 == null) list2
    else if (list2 == null) list1
    else if (list1 == null && list2 == null) null
    else {
      val dummyListNode = new ListNode(-1)
      val dummyListNode2 = new ListNode(-1)
      helper(list1, list2, dummyListNode)
      helperPM(list1, list2, dummyListNode2)
      dummyListNode2.next
    }
  }

  val list1 = new ListNode(1, new ListNode(2, new ListNode(4)))
  val list2 = new ListNode(1, new ListNode(3, new ListNode(4)))
  println(mergeTwoLists(list1, list2).stringValue())
}

class ListNode(_x: Int = 0, _next: ListNode = null) {
  var next: ListNode = _next
  var x: Int = _x

  def stringValue(acc: String = ""): String =
    if (next == null) acc + _x
    else next.stringValue(acc + _x + " ")
}

object ListNode {
  def unapply(listNode: ListNode): Option[(Int, ListNode)] = {
    Option(listNode.x, listNode.next)
  }
}
