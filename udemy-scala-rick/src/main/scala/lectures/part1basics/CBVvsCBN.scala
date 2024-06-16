package com.rick.scala
package lectures.part1basics

/**
 * @Author: Rick
 * @Date: 2024/6/13 06:38
 */
object CBVvsCBN extends App {
  private def calledByValue(x: Long): Unit = {
    println(s"by value: $x")
    println(s"by value: $x")
  }

  private def calledByName(x: => Long): Unit = {
    println(s"by name: $x")
    println(s"by name: $x")
  }

  // current system time in nano seconds
  // ============= start of called by value =============
  calledByValue(System.nanoTime()) // by Value, this value is computed before invoke the function
  // calledByValue(1059430369483958L)
  // ============= end of called by value =============


  // ============= start of called by name =============
  calledByName(System.nanoTime()) // passed the function name, evaluated inside function, so it's two times in our case
  // => delay the function evaluation as a parameter
  // extremely useful, lazy evaluation
  // ============= start of called by name =============


  private def infinite(): Int = 1 + infinite()

  private def printFirst(x: Int, y: => Int) = println(x)


  // printFirst(infinite(), 34) // stack over flow

  // this is successful without crashing
  // second parameter is evaluate by name,
  // so infinite() wont get evaluate until pass into printFirst()
  // and its body only invoke println(x)
  // so infinite() will never get evaluated
  printFirst(34, infinite())

}
