package com.rick.scala
package lectures.part3fp

/**
 * @Author: Rick
 * @Date: 2024/6/22 05:41
 */
object AnonymousFunctions extends App {

  // equivalent
  val doubler = new Function1[Int, Int] {
    override def apply(x: Int): Int = x * 2
  }

  // anonymous function (LAMBDA)
  val doublerAnonymous = (x: Int) => x * 2
  // val doublerAnonymous: Int => Int = (x: Int) => x * 2
  // val doublerAnonymous: Int => Int = x => x * 2


  // multiple params in a lambda
  // val adder = (a: Int, b: Int) => a + b
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no params
  val justDoSomething: () => Int = () => 3
  // careful
  println(justDoSomething)    // function itself
  println(justDoSomething())  // actual call

  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MORE syntactic sugar
  // val niceIncrementer: Int => Int = (x: Int) => x + 1
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b

  /*
      1. MyList: replace all FunctionX calls with lambdas
      2. Rewrite the "special" adder as an anonymous function
   */

  val superAdd = (x: Int) => (y: Int) => x + y
  println(superAdd(3)(4))

}
