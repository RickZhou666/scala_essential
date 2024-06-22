package com.rick.scala
package lectures.part2oop

/**
 * @Author: Rick
 * @Date: 2024/6/15 23:35
 */
object Generics extends App {

  println("\n====================== generic type ======================")

  class MyList[+A] {
    // USE THE TYPE A
    // def add(element: A): MyList[A] = ??? // Covariant type A occurs in contravariant position in type A of value element
    def add[B >: A](element: B): MyList[B] = ??? // B must be superclass of A, and return type B
    /*
        A = Cat
        B = Dog = Animal
     */
  }

  // trait MyList[A] {
  //   // USE THE TYPE A
  // }

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int] // Int will replace generic type A
  val listOfStrings = new MyList[String]

  println("\n====================== generic methods ======================")

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ??? // return nothing ???
  }
  // val emptyListOfIntegers = MyList.empty[Int]

  println("\n====================== variance problem ======================")
  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. yes List[Cat] extends List[Animal] = COVARIANCE
  // - does list of Cat extends Animal?
  class CovarianceList[+A]
  val animal: Animal = new Cat
  val animalList: CovarianceList[Animal] = new CovarianceList[Cat]

  // - can I add any animal to it?
  // animalList.add(new Dog) ??? HARD QUESTION => we return a list of Animal
  // a list of Cat by adding a new dog will turn it into animal list


  // 2. NO = INVARIANCE
  class InvarianceList[A]
  val invarianceAnimalList: InvarianceList[Animal] = new InvarianceList[Animal]


  // 3. Hell, no! CONTRAVARIANCE
  // class ContravariantList[-A]
  // val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal] // a cat can be an animal, but an animal not just cat
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal] // trainer can train cat it can also train dog, croc, dinosaur


  println("\n====================== bound types ======================")
  // bounded types
  // only allows your type to certain types either sub-class or super-class

  // (1) upper bound
  // A is subclass of Animal
  class Cage[A <: Animal](animal: A) // class Cage only accept subtypes of Animal
  val cage = new Cage(new Dog)

  // class Car
  // val newCage = new Cage(new Car) // Intellij Error: do not conform to class Cage's type parameter bounds

  // (2) lower bound
  // class Cage[A >: Animal](animal: A) // class Cage only accept supertypes of Animal

  println("\n====================== Quiz: expand MyList to be generic ======================")
  // expand MyList to be generic

}
