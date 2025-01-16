package BasicLectures.part3FP

import scala.util.{Failure, Random, Success, Try}

object L8HandlingFailures extends App {

  // create success and failures explicitly
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("Super Failure"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("NO STRING")

  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // syntax sugar
  val anotherFailure = Try {
    // code that might throw
  }

  // Utilities
  println(potentialFailure.isSuccess)

  // orElse
  def backupMethod(): String = "A valid result"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry)

  // If you design an API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException("Failed"))
  def betterBackupMethod(): Try[String] = Success("A valid result")
  val betterFailBack = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterFailBack)

  // map, flatMap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))

  // Exercise
  val hostname = "localhost"
  val port = "8080"
  def renderHTML(page: String): Unit = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }
    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())
    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Port Unavailable")
    }
    def getSafeConnection(host: String, port: String): Try[Connection] =
      Try(getConnection(host, port))
  }

  // if you get the HTML page from the connection, 
  // print it to the console
  // call renderHTML
  //  val tryConnection = HttpService.getSafeConnection(hostname, port)
  //  val tryHTML = tryConnection.flatMap(x => x.getSafe("url"))
  //  tryHTML.foreach(renderHTML)

  // chained calls
  Try(HttpService.getConnection(hostname, port))
    .flatMap(x => Try(x.get("url")))
    .foreach(renderHTML)

  // using for-comprehension
  for {
    connection <- Try(HttpService.getConnection(hostname, port))
    html <- Try(connection.get("url"))
  } renderHTML(html)

}
