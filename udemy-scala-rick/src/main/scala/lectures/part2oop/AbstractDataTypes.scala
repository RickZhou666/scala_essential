package com.rick.scala
package lectures.part2oop

/**
 * @Author: Rick
 * @Date: 2024/6/15 22:36
 */
object AbstractDataTypes extends App {

  println("\n============= abstract =============")

  // abstract
  abstract class Animal {
    val creatureType: String = "wild"

    def eat: Unit
  }

  class Dog extends Animal {

    override val creatureType: String = "Canine"

    def eat: Unit = println("crunch crunch")
  }

  println("\n============= traits =============")

  // traits
  // can be implements along classes
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "fresh meat"
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {

    override val creatureType: String = "croc"

    override def eat: Unit = println("nomnomnom")

    override def eat(animal: Animal): Unit = println(s"I'm croc and I'm eating ${animal.creatureType}")
  }
  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  println("\n============= traits vs abstract classes =============")
  // abstract can have abstract or non-abstract type, trait can also have non-abstract type
  // 1 - traits do not have constructor parameters (in Scala 3, this is now possible)
  // 2 - multiple traits may be inherited by the same class
  // 3 - traits = behavior, abstract class = "thing"
}
