package BasicLectures.part2OOP

object L6Generics extends App {

  class Person() {
    val name = ""
    var age = 0
  }

  val newPerson = new Person
  newPerson.age = 10
  println(newPerson.age + newPerson.name)

  class MyList[+A] {
    // use the type A
    // A is a generic type
    // traits can also be generic
    def add[B >: A](element: B): MyList[B] = ???
  }

  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyList = MyList.empty[Int]

  class MyMap[Key, Value] // multiple generic parameters

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. YES, list[cat] extends list[animal], COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) would this be okay, as we have declared this list to be of type cat
  // this is a HARD question, as adding a dog to a list of cat will pollute the list of cat

  // 2. NO, list[animal] cannot extend list[cat], INVARIANCE
  class InvariantList[A]

  // val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat] // compiler throws error
  // invariant classes cannot be substituted one for another

  // 3. HELL NO, CONTRA VARIANCE, opposite relationship, would work for trainer
  class Trainer[-A]
  val contravariantListAnimal: Trainer[Cat] = new Trainer[Animal]

  // bounded type
  // allows us to use generics
  // which are subtype or supertype of a type
  // cage is upper bounded type
  class Cage[A <: Animal](animal: A) // cage accepts a Type which is a subtype of Animal
  val cage = new Cage(new Dog)
  class Car
  // val newCage = new Cage(new Car) // this wont work
  // as car is not a subtype of animal

  // lower bounded type
  class Loki[A >: Dog](dog: A)
  val dog1 = new Loki(new Cat)

}
