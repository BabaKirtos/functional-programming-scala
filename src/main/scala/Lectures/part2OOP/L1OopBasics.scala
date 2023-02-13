package Lectures.part2OOP

object test extends App {

  val person = new Person("John", 29)
  println(person.x)
  person.greet("Baba")
  person.greet()
  val defaultPerson = new Person()
  defaultPerson.greet()
  println(person)
  person.me
  println(defaultPerson)
  defaultPerson.me

}

// constructor
// class parameters are NOT FIELDS unless used with val/var
class Person(name: String, val age: Int) {

  // body is not an expression but defines the class
  val x = 2

  println(1 + 3) // this side effect will be evaluated during instantiation

  def greet(name: String): Unit = {
    println(s"${this.name} says: Hi $name")
  }

  // name will be picked from class parameter in below code
  // 'this' keyword is implied in the below case
  // repeating function names with different parameter is called overloading
  def greet(): Unit = println(s"Hi, I'm $name, and I'm $age years old")

  // multiple constructors
  // auxiliary constructor
  def this(name: String) = this(name, 0)
  // auxiliary constructors can be replaced by defining default parameters in
  // the main constructor
  def this() = this("John Doe")

  def me: Unit = println(this)

}

/*
  Exercise:
  1. Implement a Novel and Writer class
  2. Writer: first name, surname, age
    - return full name
  3. Novel: name, year of release, author(instance of class writer)
    - authorAge (at time of release)
    - isWrittenBy (author)
    - copy (new year of release) = new instance of novel with new year
 */
/*
  Counter Class
    - receives an int value
    - method current count
    - method to increment/decrement => new Counter
    - overload inc/dec to receive an amount
 */

object L1OopBasics extends App {

  val author = new Writer("Abhishek", "Roy", 1993)
  val book = new Novel("Scala Book", 2016, author)

  book.author.printFullName() // is this inheritance?
  book.authorName()
  book.authorAge()
  println(book.isWrittenBy(author))
  val newBook = book.copy(2018)
  newBook.authorAge()

  val counter = new Counter
  counter.print()
  counter.inc(5).print()

}

class Writer(firstName: String, surName: String, val birthYear: Int) {
  def printFullName(): Unit = {
    println(s"Author Name: $firstName $surName")
  }
  def fullName(): String = {
    firstName + " " + surName
  }
}

class Novel(name: String, releaseYear: Int, val author: Writer) {
  def authorAge(): Unit = {
    println(s"Author age at time of release: ${releaseYear - author.birthYear}")
  }
  def isWrittenBy(author: Writer): Boolean = {
    author == this.author
  }
  def authorName(): Unit = {
    println(author.fullName())
  }
  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}

class Counter(val count: Int = 0) {

  def inc: Counter = {
    println("incrementing")
    new Counter(count + 1) // immutability
  }
  def dec: Counter = {
    println("decrementing")
    new Counter(count - 1) // we return a new instant
  }
  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n - 1)
  }
  def dec(n: Int): Counter = {
    if (n <= 0) this
    else dec.dec(n - 1)
  }

  def print(): Unit = println(s"Current count: $count")
}
