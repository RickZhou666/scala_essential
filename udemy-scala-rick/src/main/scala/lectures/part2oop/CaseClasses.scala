package com.rick.scala
package lectures.part2oop

/**
 * @Author: Rick
 * @Date: 2024/6/16 16:44
 */
object CaseClasses extends App {
  /*
    we want to solve is that often for lightweight data structures
    for class file, we need re-implement all sorts of boilerplate
    equals, hashCode, toString

   */
  case class Person(name: String, age: Int)

  println(s"\n============= 1. class parameters are all promoted to fields =============")
  // 1. class parameters are fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  println(s"\n============= 2. sensible toString =============")
  // 2. sensible toString
  // println(jim.toString)
  // println(instance) = println(instance.toString) // syntactic sugar
  println(jim)


  println(s"\n============= 3. equals + hashCode =============")
  // 3. equals and hashCode implemented OOTB (Out of the box)
  val jim2 = new Person("Jim", 34)
  println(jim == jim2)
  // normal classes generate different instances, but equals method was not implemented, hence return false

  println(s"\n============= 4. copy method =============")
  // 4. CCs have handy copy method
  // generate a new instance which is copy of jim and set a new value to age
  val jim3 = jim.copy(age = 45)
  println(jim3)

  println(s"\n============= 5. Companion Objects =============")
  // 5. CCs have companion objects
  // use 'case' it will automatically generate object of class Person
  val thePerson = Person
  val mary = Person("Mary", 18) // companion use apply()
  println(mary)


  println(s"\n============= 6. Serializable =============")
  // 6. CCs are serializable
  // especially useful when dealing with distributed system
  // send instance of case classes through network and in between JVMs
  // Akka deal with serializable msgs through the network and our msgs are in general in practice case classes


  println(s"\n============= 7. Pattern Matching =============")
  // 7. CCs have extractor patterns = CCs can be used in PATTERN MATCHING

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }
  /*
    Expand MyList - use case classes and case objects
   */
}
