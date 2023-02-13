package Lectures.part4PatternMatching

import Exercises.{Cons, Empty, MyList}

object L2AllThePatterns extends App {

//  // 1. constants:
//  val x: Any = "Scala"
//  val constants = x match {
//    case 1                => "a number"
//    case "Scala"          => "The scala"
//    case true             => "The truth"
//    case L2AllThePatterns => "A singleton object"
//  }
//
//  // 2. Match anything:
//  // 2.1 Wildcards:
//  val matchAnything = x match {
//    case _ =>
//  }
//  // 2.2 Variable:
//  val aVariable = x match {
//    case something => s"I've found $something"
//  }
//
//  // 3. Tuple:
//  val aTuple = (1, 2)
//  val matchATuple = aTuple match {
//    case (1, 1)         =>
//    case (something, 2) => s"I've found $something"
//  }
//  // nested tuple
//  val nestedTuple = (1, (2, 3))
//  val matchANestedTuple = nestedTuple match {
//    case (_, (2, v)) =>
//  }
//  // Pattern matching can be nested
//
//  // 4. Case classes = constructor pattern
//  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
//  val matchAList = aList match {
//    case Empty                              =>
//    case Cons(head, tail)                   =>
//    case Cons(head, Cons(subhead, subtail)) =>
//  }
//
//  // 5. List Patterns:
//  val aStandardList = List(1, 2, 3, 42)
//  val standardListMatching = aStandardList match {
//    case List(1, _, _, _)    => // extractor = advanced
//    case List(1, _*)         => // list of arbitrary length - advanced
//    case 1 :: List(_)        => // infix pattern
//    case List(1, 2, 3) :+ 42 => // another infix pattern
//  }
//
//  // 6. Type specifiers:
//  val unknown: Any = 2
//  val unknownMatch = unknown match {
//    case list: List[Int] => // explicit type specifier
//    case _               =>
//  }
//
//  // 7. Name binding:
//  val nameBindingMatch = aList match {
//    case nonEmptyList @ Cons(_, _)  => // use the name later here
//    case Cons(1, rest @ Cons(2, _)) => // name binding inside nested patterns
//  }
//
//  // 8. Multi Patterns:
//  val multiPattern = aList match {
//    case Empty | Cons(0, _) => // use '|' operator
//  }
//
//  // 9. if guards:
//  val secondElementSpecial = aList match {
//    case Cons(_, Cons(speicalElement, _)) if speicalElement % 2 == 0 =>
//  }

  // Question
  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfNumbers: List[Int]    => "a list of numbers"
    case _                           => "empty list"
  }
  println(numbersMatch)
  // JVM trick question
  // This is because JVM is backward compatible
  // After type checking the JVM removes type parameters
  // therefore List[String] and List[Int] becomes List
  // the 1st condition will match
  // This is called type erasure

}
