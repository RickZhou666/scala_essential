package com.rick.scala
package lectures.part3fp

/**
 * @Author: Rick
 * @Date: 2024/6/16 20:57
 */
object WhatsAFunction extends App {
  // DREAM: use functions as first class elements
  // problem: oop

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println("\n================== 1. MyFunction ==================")
  // doubler is an instance of MyFunction
  // and can be called like a function
  println(doubler(2))

  println("\n================== 2. Function1, 2, 3 ==================")
  // function types = Function1[A, B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }
  println(stringToIntConverter("3") + 4)

  println("\n================== 3. up to 24 params ==================")
  // scala support this up to 24 params
  // val adder = new Function2[Int, Int, Int] {
  //   override def apply(a: Int, b: Int): Int = a + b
  // }
  // syntactic sugar for Function2
  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }
  println(adder(1, 2))

  println("\n================== 4. Function types ==================")
  // Function types Function2[A, B, R] === (A, B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS or INSTANCE OF CLASSES DERIVED FROM Function1, Function2, Function3...

  /*
      1. a function which takes 2 strings and concatenates them
      2. transform the MyPredicate and MyTransformer into function types
      3. define a function which takes an argument Int and returns another function which takes an Int and returns an Int
          - what's the type of this function
          - how to do it
   */
  println("\n================== 5. QUIZ ==================")
  println("\n================== 5.1  ==================")
  val concat: (String, String) => String = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + " " + v2
  }
  println(concat("cat", "dog"))


  println("\n================== 5.2  ==================")
  // trait MyPredicate[-T] {
  //   def test(elem: T): Boolean
  // }

  val myPredicate: (Int => Boolean) = new Function[Int, Boolean] {
    override def apply(v1: Int): Boolean = v1 % 2 == 0
  }
  println(myPredicate(2))


  // trait MyTransformer[-A, B] {
  //   def transform(elem: A): B
  // }

  val myTransformer: (Int => Int) = new Function[Int, Int] {
    override def apply(v1: Int): Int = v1 * 2
  }
  println(myTransformer(2))

  println("\n================== 5.3  ==================")
  // val innerFunction: (Int => Int) = new Function[Int, Int] {
  //   override def apply(v1: Int): Int = v1 * 2
  // }

  // Function1[Int, Function1[Int, Int]]
  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(v1: Int): Function1[Int, Int] = new Function[Int, Int] {
      override def apply(v2: Int): Int = v1 + v2
    }
  }

  // adder3 is a Function, Function1[Int, Int]
  val adder3 = superAdder(3)
  println(adder3)
  println(adder3(4))
  println(superAdder(3)(4)) // curried function


}

trait MyFunction[A, B] {
  def apply(element: A): B
}
