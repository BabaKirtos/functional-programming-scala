package playground

object algo extends App {

  import scala.concurrent.Future
  import scala.concurrent.ExecutionContext.Implicits.global

  case class Emp(id: Int, salary: String)

  case class Response(field1: Option[String], field2: Option[String])

  case class Alarm(id: Int, title: Option[String], description: Option[String])

  val aa: Option[Response] = Some(Response(Some("Hey"), Some("Yo")))
  val aa1: Option[Response] = None

  val emp = Emp(1, "")
  val alarm = Alarm(1, Some("No idea"), Some("Don't know"))
  val bb = alarm.copy(title = aa1.flatMap(_.field1), description = aa1.flatMap(_.field2))

  val a = Future.successful(10)
  val b = Future.successful(20)

  val finalRes = for {
    i <- a if emp.salary.nonEmpty
    b <- b
  } yield i + b

  println(finalRes)
  println (bb)

  /*

    // for faster read times use array, for faster insertions use linked list
    // operations are 4 - crud
    // arrays are not referentially transparent
    // if I use list, how can I solve this problem using maps/filters/for-comprehensions/pattern matching

  class A
  (
  protected def display = ???
  )

  class B extends A
  (
  display is visible in B
  a = create an instance of A
  a.display // this will work as B is a child class
  )

  class C
  (
  create an instance of B
  B.display wil not work
  )

  object x // not a child class of A
  (
  a.display
  )
   */

}
