package BasicLectures.part2OOP

object L8CaseClasses extends App {

  case class Person(name: String, age: Int)

  // 1. Class parameters are promoted to fields
  val jim = new Person("Jim", 34)
  println(jim.name) // this wont be valid for normal classes

  // 2. A sensible toString factory method
  println(jim.toString)
  println(jim) // same as println(instance.toString), syntactic sugar

  // 3. Equals and hashCode implemented
  val jim2 = new Person("Jim", 34)
  println(jim == jim2) // won't be true for normal class

  // 4. Handy copy methods
  val jim3 = jim.copy()
  println(jim3)
  val jim4 = jim.copy(age = 45) // we can change certain parameters while copying
  println(jim4)

  // 5. Have companion objects automatically created
  val thePerson = Person
  val mary = Person("Mary", 23) // using the apply method as constructor
  println(mary) // In practice we don't use 'new' with case classes

  // 6. They are serializer-able
  // We can send instances of case classes through the network and
  // in between JVMs, this is used in distributed frameworks like AKKA

  // 7. They have extractor patterns
  // They can be used in pattern matching

  // 8. We also have case objects
  // They have the same properties as case classes except
  // they don't get companion objects as they are themselves objects
  case object UnitedKingdom {
    def name: String = "The United Kingdom of Great Britain"
  }

}
