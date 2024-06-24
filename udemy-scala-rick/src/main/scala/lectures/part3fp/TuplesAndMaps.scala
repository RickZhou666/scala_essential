package com.rick.scala
package lectures.part3fp

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

  println("\n======================== Maps - conversions to other colelctions ========================")
  println(phonebook.toList)
  println(List(("Daniel", 555), ("Rick", 666)).toMap) // convert a list to map

  val names = List("Bob", "James", "Angela", "Rick", "Daniel", "Jim", "Mary")
  println(names.groupBy(name => name.charAt(0))) // group name that has same char at index 0
}
