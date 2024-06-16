package com.rick.scala
package lectures.part1basics

/**
 * @Author: Rick
 * @Date: 2024/6/10 08:53
 */
object Expressions extends App {
  val x = 1 + 2 // EXPRESSION
  println(x)

  println(2 + 3 * 4)
  // math expression
  // + - * / & | ^ << >> >>> (right string with zero extension)

  println(1 == x)
  // relational expression
  // == != > >= < <=

    println(!(1 == x))
  // boolean operator
  // ! && ||

  var aVariable = 2
  aVariable += 3 // also works with -= *= /=  ....... changing a variable called side effects
  println(aVariable)

  println("\n====== Instructions (DO) vs Expressions (VALUE) ======")
  // Instructions (DO) vs Expressions (VALUE)
  // IF expression
  val aCondition = true
  val aConditionValue = if(aCondition) 5 else 3
  println(aConditionValue)

  println(if(aCondition) 5 else 3) // if is expression
  println(1 + 3)

  println("\n====== Loops ======")
  var i = 0
  val aWhile = while (i < 10) {
    println(i)
    i += 1
  }

  // NEVER WRITE THIS AGAIN

  // EVERYTHING in Scala is an Expression! Except val, class, object

  println("\n====== Unit ======")
  val aWeirdValue = (aVariable = 3) // Unit === void, dont return only thing meaningful
  println(aWeirdValue)

  // side effects: println(), whiles, reassigning

  println("\n====== Code Blocks ======")
  // Code blocks
  val aCodeBlock = { // type of last expression
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"
  }

  // val anotherValue = z + 1 // z is not visible to outside

  println("\n====== QUIZs ======")
  // 1. difference between "hello world" vs println("hello world")?
  // "hello world" - value of type String
  // println("hello world") - expression which is a side effect return Unit. has a side effect which is printing "hello world"

  // 2.
  // Boolean: true
  val someValue = {
    2 < 3
  }
  println(someValue)

  // Int: 42
  val someOtherValue = {
    if(someValue) 239 else 986
    42
  }
  println(someOtherValue)
}
