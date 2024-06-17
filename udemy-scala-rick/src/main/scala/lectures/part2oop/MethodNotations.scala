package com.rick.scala
package lectures.part2oop

/**
 * @Author: Rick
 * @Date: 2024/6/15 17:08
 */
object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int=18) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    // def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie, age)

    def unary_! : String = s"$name, what the hack?!"

    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
    def apply(times: Int): String = s"$name watched $favoriteMovie $times times"

    def learns(thing: String): String = s"${this.name} learns $thing"
    def learnsScala(): String = learns("Scala")
  }

  println("\n =============== infix notation ===============")
  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  //      object method param
  println(mary likes "Inception") // equivalent
  // infix notation = operator notation (syntactic sugar)

  println("\n =============== operators ===============")
  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  // println(mary hangOutWith tom)
  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2))

  // ALL OPERATORS ARE METHODS.
  // Akka actors have ! ?

  println("\n =============== prefix notation ===============")
  // prefix notation
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-
  // unary_ prefix only works with - + ~ !

  println(!mary)
  println(mary.unary_!)

  println("\n =============== postfix notation ===============")
  // postfix notation
  // only works with variable without parameters
  println(mary.isAlive) // dot notation
  // println(mary isAlive) // this only works for Scala 2

  println("\n =============== apply ===============")
  // apply
  println(mary.apply())
  println(mary()) // equivalent

  println("\n =============== Quiz ===============")
  /*
    1.  Overload the + operator
        mary + "the rockstar" => new person "Mary (the rockstar)"

    2.  Add an age to the Person class
        Add a unary + operator => new person with the age + 1
        +mary => mary with the age incrementer

    3.  Add a "learns" method in the Person class => "Mary learns Scala"
        Add a learnScala method, calls learns method wiht "Scala"
        Use it in postfix notation

    4.  Overload the apply method
        mary.apply(2) => "Mary watched Inception 2 times"
   */

  println("\n =============== Quiz #1 ===============")
  println((mary + "the Rockstar")()) // 1. create a new Person object and invoke apply()
  println((mary + "the Rockstar").apply())

  println("\n =============== Quiz #2 ===============")
  println(mary.unary_+.age)
  println((+mary).age)

  println("\n =============== Quiz #3 ===============")
  println(mary learns "Scala")
  // println(mary learnsScala) // this only works for Scala 2

  println("\n =============== Quiz #4 ===============")
  println(mary apply(2))
  println(mary(4))
}
