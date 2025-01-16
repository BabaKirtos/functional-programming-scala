package Exercises

import scala.runtime.Nothing$

abstract class Maybe[+A] {

  def map[B](f: A => B): Maybe[B]
  def flatMap[B](f: A => Maybe[B]): Maybe[B]
  def filter(f: A => Boolean): Maybe[A]
}

case object MaybeNot extends Maybe[Nothing] {

  def map[B](f: Nothing => B): Maybe[Nothing] = MaybeNot
  def flatMap[B](f: Nothing => Maybe[B]): Maybe[Nothing] = MaybeNot
  def filter(p: Nothing => Boolean): Maybe[Nothing] = MaybeNot
}

case class Just[+A](value: A) extends Maybe[A] {

  def map[B](f: A => B): Maybe[B] = Just(f(value))
  def flatMap[B](f: A => Maybe[B]): Maybe[B] = f(value)
  def filter(f: A => Boolean): Maybe[A] =
    if (f(value)) this
    else MaybeNot
}

object TestMaybe extends App {

  val maybeList = List(
    new Just[Int](3),
    MaybeNot,
    new Just[Int](4),
    MaybeNot,
    new Just[Int](5),
    MaybeNot,
    new Just[Int](6))

  println(maybeList.map(_.map(_ * 2)))
  println(maybeList.map(_.flatMap((x: Int) => new Just[Boolean](x % 2 == 0))))
  println(maybeList.map(_.filter(_ % 2 == 0)))
  println(maybeList.filter(_ != MaybeNot))
}
