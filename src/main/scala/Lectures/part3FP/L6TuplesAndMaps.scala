package Lectures.part3FP

import scala.annotation.tailrec

object L6TuplesAndMaps extends App {

  // tuples = finite ordered "lists"
  // type is Tuple2[Int, String] = (Int, String)
  // can be grouped with 22 types , like function types
  val aTuple = (2, "hello scala")
  // 0 indexed is written with _1
  println(aTuple._1)
  println(aTuple(0))
  println(aTuple(1))
  // copy method
  println(aTuple.copy(_2 = "goodbye java"))
  // swap
  println(aTuple.swap)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()
  // phonebook
  val phonebook =
    Map(("Jim", 555), "Daniel" -> 789, "JIM" -> 999).withDefaultValue(-1) // a -> b syntactic sugar
  println(phonebook)
  // basic map ops
  println(phonebook.contains("Jim"))
  println(phonebook("Jim")) // returns the value for key
  // calling the apply method above with a key that does not exist will throw an exception
  // to counter this we use 'withDefaultValue' while creating the map
  println(phonebook("Mary"))
  // add a pairing
  val newParing = "Mary" -> 678
  val newPhonebook = phonebook + newParing // maps are immutable
  println(newPhonebook)

  // functional on maps
  // map, flatMap, filter
  // !!!! careful with mapping keys
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  println(phonebook.view.filterKeys(_.startsWith("J")).toMap)
  // mapValues
  println(phonebook.view.mapValues(number => number * 10).toMap)
  println(phonebook.view.mapValues(number => "0771-" + number).toMap)

  // conversions to other collection
  println(phonebook.toList)
  println(List(("Jim", 555), ("Daniel", 789)).toMap)

  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

  // Exercise - simple social networking map
  // string to list of strings

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    // mapping between person to an empty list
    network + (person -> Set())

  def friend(
      network: Map[String, Set[String]],
      a: String,
      b: String): Map[String, Set[String]] = {
    val friendsA = network(a) // retrieve friends list of person A
    val friendsB = network(b) // retrieve friends list of person B
    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def unfriend(
      network: Map[String, Set[String]],
      a: String,
      b: String): Map[String, Set[String]] = {
    val friendsA = network(a) // retrieve friends set of person A
    val friendsB = network(b) // retrieve friends set of person B
    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    @tailrec
    def removeAux(
        friends: Set[String],
        networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  def nFriends(network: Map[String, Set[String]], person: String): Int = {
    if (!network.contains(person)) 0
    else network(person).size
  }

  def mostFriends(network: Map[String, Set[String]]): String = {
    network.maxBy(pair => pair._2.size)._1
  }

  def peopleWithNoFriends(network: Map[String, Set[String]]): Int = {
    network.count(_._2.isEmpty)
  }

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    @tailrec
    def bfs(
        target: String,
        consideredPeople: Set[String],
        discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person))
          bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))

      }
    }
    bfs(b, Set(), network(a) + a)
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))
  // for a better implementation make network a class

  // Jim, Bob, Mary
  // Bob and Mary are friends
  // Bob and Jim are friends
  val people = add(add(add(empty, "Bob"), "Jim"), "Mary")
  val testNet = friend(friend(people, "Bob", "Jim"), "Bob", "Mary")
  println(testNet)
  println(nFriends(testNet, "Bob"))
  println(mostFriends(testNet))
  println(peopleWithNoFriends(testNet))
  println(socialConnection(testNet, "Mary", "Jim"))
  println(socialConnection(network, "Mary", "Bob"))

}
