package playground

import Identifiable.toIdentifiableOps

import scala.language.implicitConversions

trait Identifiable[A] {
  def id(value: A, ctx: Any): String
}

object Identifiable {
  implicit def toIdentifiableOps[A: Identifiable](value: A): IdentifiableOps[A] =
    new IdentifiableOps(value)
}

class IdentifiableOps[A: Identifiable](value: A) {
  def id: String = implicitly[Identifiable[A]].id(value, ())
  def idFor(ctx: Any): String = implicitly[Identifiable[A]].id(value, ctx)
}

case class Metric(name: String)
case class Task(name: String)

object Check {

  def main(args: Array[String]): Unit = {

    implicit val identifiableMetric: Identifiable[Metric] =
      (value: Metric, ctx: Any) => ctx match {
        case "metrics" => "m_" + value.name
        case "metrics_summary" => "ms_" + value.name
        case other => throw new RuntimeException("Oops!")
      }

    implicit val identifiableTask: Identifiable[Task] =
      (value: Task, _: Any) => value.name

    println(Metric("a").idFor("metrics"))
    println(Metric("b").idFor("metrics_summary"))
    println(Task("c").id)
  }
}
