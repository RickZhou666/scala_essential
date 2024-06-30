package com.rick.scala
package lectures.part3fp

import scala.annotation.tailrec

/**
 * @Author: Rick
 * @Date: 2024/6/23 17:41
 */
object TuplesAndMaps extends App {

  println("\n======================== Tuples ========================")
  // tuples = finite ordered "lists"
  // val aTuple = new Tuple2(2, "hello, Scala") // Tuple2[Int, String] = (Int, String)
  val aTuple = (2, "hello, Scala") // Tuple2[Int, String] = (Int, String)
  println(aTuple._1)
  println(aTuple._2)

  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap) // ("hello, Scala", 2)

  println("\n======================== Maps ========================")
  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()
  println("\n======================== Maps - -> ========================")
  val phonebook = Map(("Jim", 555), ("Daniel", 789)).withDefaultValue(-1)
  val phonebook2 = Map(("Jim", 555), "Daniel" -> 789)

  // a -> b is sugar for (a, b)
  println(phonebook2)

  println("\n======================== Maps - ops ========================")
  println(phonebook.contains("Jim"))
  println(phonebook("Jim")) // return the value associate with the key
  println(phonebook("Rick")) // throw exception if no such key, you can set default value for each key

  println("\n======================== Maps - adding a pairing ========================")
  val newPariring = "Mary" -> 678
  val newPhonebook = phonebook + newPariring // maps are immutable
  println(newPhonebook)

  println("\n======================== Maps - functionals ========================")
  // functionals on maps
  // map, flatMap, filter
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2)) // pair for each tuple

  println("\n======================== Maps - filterKeys ========================")
  // filterKeys
  println(phonebook.filterKeys(x => x.startsWith("J")))

  println("\n======================== Maps - mapValues ========================")
  // mapValues
  println(phonebook.mapValues(number => number * 10)) // lambda take value
  println(phonebook.mapValues(number => "0245-" + number)) // lambda take value

  println("\n======================== Maps - conversions to other collections ========================")

  println(phonebook.toList)
  println(List(("Daniel", 555), ("Rick", 666)).toMap) // convert a list to map

  val names = List("Bob", "James", "Angela", "Rick", "Daniel", "Jim", "Mary")
  println(names.groupBy(name => name.charAt(0))) // group name that has same char at index 0


  println("\n======================== Exercises ========================")
  /*
        1. what would happen if I had two original entries "Jim" -> 555 and "JIM" -> 900
            !!! CAREFUL with mapping key


        2. Overly simplified social network based on maps
            Person = String
            - add a person to the network
            - remove
            - friend (mutual)
            - unfriend

            - number of friends of a person
            - person with most friends
            - how many people have NO friends
            - if there is a social network between two people (direct or not)
   */

  println("\n======================== Exercises - same key  ========================")
  // you will lose data
  val phonebookTest = Map("Jim" -> 555, "JIM" -> 789).withDefaultValue(-1)
  println(phonebookTest)
  println(phonebookTest.map(pair => pair._1.toLowerCase -> pair._2))

  println("\n======================== Exercises - Social Network  ========================")

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    // remove ele b from Set
    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
    // + create a new pair in map, if there is old pairing, it will be replaced
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person // remove person key entirely
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))


  println("\n======================== Exercises - Social Network - II. 3 people  ========================")
  // Jim, Bob, Mary
  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")
  println(testNet)


  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String =
    // pair     => Bob -> Set(Jim, Mary)
    // pair._1  => Bob
    // pair._2  => Set(Jim, Mary).size => 2
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int = {
    // filter pair
    network.filter(pair => pair._2.size == 0).size

    // get count with given condition
    // network.count(pair => pair._2.size == 0)

    // filtering the key, get size 0, this create a new Map, return the size
    // network.filterKeys(k => network(k).size == 0).size
  }

  println(nPeopleWithNoFriends(testNet))
  println(nPeopleWithNoFriends(people))

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }
    bfs(b, Set(), network(a) + a)
  }

  println(socialConnection(testNet, "Mary", "Jim"))
  println(socialConnection(network, "Mary", "Bob"))
  println(socialConnection(network, "Mary", "Mary"))

}
