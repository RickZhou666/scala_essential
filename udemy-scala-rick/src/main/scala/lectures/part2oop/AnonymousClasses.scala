package com.rick.scala
package lectures.part2oop

import jdk.nashorn.api.tree.WithTree

/**
 * @Author: Rick
 * @Date: 2024/6/16 14:20
 */
object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }


  println("================= Anonymous Class =================")
                                // this right part is anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("hahahaha")
  }

  println("================= what really happened =================")
  // what really happened below
  // class AnonymousClasses$$anon$1 extends Animal {
  //   override def eat: Unit = println("hahahaha")
  // }
  // val funnyAnimal: Animal = new AnonymousClasses$$anon$1

  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")
  }

  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"Hi, my name is Jim, how can I help?")
  }

  // make sure you implement all abstract methods and fields

  /*
      1. Generic trait MyPredicate[T] with a little method test(T) => Boolean
      2. Generic trait MyTransformer[A, B]
      3. MyList:
        - map(transformer) => MyList
        - filter(predicate) => MyList
        - flatMap(transformer from A to MyList[B]) => MyList[B]

        class EvenPredicate extends MyPredicate[Int]
        class StringToIntTransformer extends MyTransformer[String, Int]

        [1, 2, 3].map(n*2) = [2, 4, 6]
        [1,2,3,4].filter(n%2) = [2, 4]
        [1,2,3].flatMap(n => [n, n + 1]) => [1, 2, 2, 3, 3, 4]
   */

  trait MyPredicate[-T] {
    def test(a: T): Boolean
  }

  trait MyTransformer[-A, B]

  class MyList[T, A, B] extends MyPredicate[T] with MyTransformer[A, B] {

    override def test(a: T): Boolean = ???

    def map(transformer: MyTransformer[A, B]): MyList[T, A, B] = ???

    def filter(predicate: MyPredicate[T]): MyList[T, A, B] = ???

    def flatMap(transformer: MyTransformer[A, B]): MyList[B] = ???
  }

}
