package com.rick.scala
package lectures.part2oop

/**
 * @Author: Rick
 * @Date: 2024/6/15 21:11
 */
// object Objects extends App {
object Objects {

  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")
  object Person { // type + its only instance
    // "static"/"class" - level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    // factory method
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }

  class Person(val name: String) {
    // instance-level functionality
  }
  // COMPANIONS

  def main (args: Array[String]): Unit = {
    println(Person.N_EYES)
    println(Person.canFly)

    // Scala object = SINGLETON INSTANCE
    println("\n============= object - SINGLETON =============")
    val mary = Person
    val john = Person
    println(mary == john)

    println("\n============= class =============")

    // if we use class, it will instantiate two different instances
    val mary_1 = new Person("Mary")
    val john_2 = new Person("John")
    println(mary_1 == john_2)

    println("\n============= object factory method =============")
    val bobbie = Person.apply(mary_1, john_2)
    // val bobbie = Person(mary_1, john_2)
    print(bobbie.name)


    println("\n============= Scala Application =============")
    // Scala Applications = Scala object with (reason why this code is runnable)
    // def main(args: Array[String]): Unit (as Scala is using JVM, entry must be public static void main(String[] args))
  }






}
