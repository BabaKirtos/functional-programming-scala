package BasicLectures.part2OOP

object L4Inheritance extends App {

  // single class inheritance
  class Animal {
    val creatureType: String = "Wild"
    protected def eat: Unit = println("eating")
    def newEat: Unit = println("new eat")
  }

  class Cat extends Animal {
    def crunch = {
      eat // allowed to use here but not outside this scope if protected
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch
//  cat.eat // as eat is protected it is not accessible from this scope

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  // Overriding
  class Dog(override val creatureType: String) extends Animal {
    override def eat: Unit = {
      // super refers to the super class
      super.eat
      println("crunch")
    }
    override def newEat: Unit = {
      // super refers to the super class
      super.newEat
      println("crunch")
    }
  }

  val dog = new Dog("K9")
  dog.eat

  // type substitution (broad: polymorphism), it is only possible as Dog extends Animal
  // the most override function will be evaluated
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.newEat

  // Preventing Overrides:
  // 1. use final keyword for def in super class
  // 2. use final keyword in class definition
  // 3. seal the class  = extends classes in this file but prevents extension in other files

}
