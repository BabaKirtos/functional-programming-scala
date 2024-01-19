package BasicLectures.part3FP

import scala.language.implicitConversions
import scala.util.Random

object L7Options extends App {

  // Options are used to avoid NullPointerException
  val myFirstOption: Option[Int] = Some(4)
  val noValue: Option[Int] = None

  println(myFirstOption)
  // doesn't throw an exception
  // But points to None object which extends Option[Nothing]
  println(noValue)

  // unsafe APIs
  // calling this API would throw an exception
  def unsafeMethod(): String = null

  //  val results = Some(null) // WRONG

  // this will build Some or None
  val results: Option[String] = Option(unsafeMethod())
  println(results)

  // chained methods
  def backupMethod(): String = "A valid value"

  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // DESIGN unsafe APIs
  // this will return None object
  def betterUnsafeMethod(): Option[String] = None

  def betterBackupMethod(): Option[String] = Some("A valid result")

  // better chained result
  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterChainedResult)

  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get)
  //  println(noValue.get) // VERY DANGEROUS ON Option - DO NOT USE, Instead:
  println(noValue.getOrElse(-1))

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.flatMap((x: Int) => Option(x + 2)))
  println(myFirstOption.filter(_ % 2 == 0))
  println(myFirstOption.filter(_ > 10)) // will return None
  println(myFirstOption.filter(_ < 10))

  // for-comprehensions
  // Exercise
  class Connection {
    def connect = "Connected" // would connect to some server
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  // try to establish a connection
  val config: Map[String, String] = Map(
    // fetched from somewhere else, host and port values might not be available
    "host" -> "176.45.36.1",
    "port" -> "80")

  val host = config.get("host") // get method on Map returns an Option
  val port = config.get("port")

  val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))
  val connectionStatus = connection.map(c => c.connect)
  println(connectionStatus)
  connectionStatus.foreach(println)

  // chaining above operations
  config.get("host")
    .flatMap(h =>
      config.get("port")
        .flatMap(p =>
          Connection(h, p)
            .map(c => c.connect)))
    .foreach(println)

  // for-comprehensions
  val forConnectionStatus = for {
    h <- config.get("host")
    p <- config.get("port")
    connection <- Connection(h, p)
  } yield connection.connect

  forConnectionStatus.foreach(println)
}
