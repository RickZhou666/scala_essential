package com.rick.scala
package lectures.part2oop

/**
 * @Author: Rick
 * @Date: 2024/6/15 21:55
 */
object Inheritance extends App {

  // single class inheritance
  sealed class Animal {
    val creatureType = "wild"
    // private def eat = println("nomnom") // cannot be access by subclass
    // protected def eat = println("nomnom") // allow access by subclass, not outside the subclass
    def eat = println("nomnom") // allow access by subclass, not outside the subclass
  }

  println("\n================== extends ==================")
  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  // only access non-private
  cat.crunch


  println("\n================== constructors ==================")

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
              // class signature
  // class Audit(name: String, age: Int, idCard: String) extends Person // JVM need instantiate super class first, use below CORRECT way
  // class Audit(name: String, age: Int, idCard: String) extends Person(name, age)

  class Audit(name: String, age: Int, idCard: String) extends Person(name) // if you define as above super class with constructor, this will be valid

  println("\n================== overriding ==================")
  // class Dog extends Animal {
  //   override val creatureType = "domestic"
  //   override def eat: Unit = println ("crunch, crunch")
  // }

  // override in class signature
  // class Dog(override val creatureType: String) extends Animal {
  //   override def eat: Unit = println ("crunch, crunch")
  // }
  //
  class Dog(dogType: String) extends Animal {
    override val creatureType = dogType
    override def eat: Unit = {
      println("==== super ====")
      super.eat
      println ("crunch, crunch")
    }
  }
  val dog = new Dog("Domestic")
  dog.eat
  println(dog.creatureType)

  println("\n================== type substitution ==================")
  // type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("K9") // it's like java polymorphism, but method in superclass cannot be private or protected
  unknownAnimal.eat

  // overRIDING vs overLOADING

  // super - reference to the parent class

  // preventing overrides
  // 1 - use final on member // prevent derived class to override members
  // 2 - user final on entire class
  // 3 - seal the class = extend classes in THIS FILE, prevent extension in other files (current file extends if valid, outside this file is not)
            // e.g. the Cat and Dog is the only type from this file, you dont want to be extends by other class
}
