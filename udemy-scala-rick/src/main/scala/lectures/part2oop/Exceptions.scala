package com.rick.scala
package lectures.part2oop

/**
 * @Author: Rick
 * @Date: 2024/6/16 17:44
 */
object Exceptions extends App {

  val x: String = null
  // println(x.length)
  // this ^^ will crash with NPE, this crash JVM


  println("\n=================== 1. throwing exceptions ===================")
  // 1. throwing exceptions

  // val aWeirdValue: String = throw new NullPointerException

  // throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtypes


  println("\n=================== 2. catching exceptions ===================")

  // 2. how to catching exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No Int for you!")
    else 42


  val potentialFail = try {
    // try block try to return an Int, but below
    getInt(false)
  } catch {
    // catch block return UNIT, so when compiler try to unify (Int and Unit) gets AnyVal
    // case e: RuntimeException => println("caught a Runtime exception")
    // case e: NullPointerException => println("caught a Runtime exception") // we are not catching RuntimeException, program will crash
    case e: RuntimeException => 44444
  } finally {
    // code will get executed NO MATTER WHAT
    // optional
    // does not influence the return type of this expression
    println("finally")
  }

  println(potentialFail)

  println("\n=================== 3. define your own exceptions ===================")

  // 3. how to define your own exceptions
  class MyException extends Exception

  val exception = new MyException
  // throw exception

  /*
      1. Crash your program with an OutOfMemoryError
      2. Crash with StackOverflowError
      3. PocketCalculator
          - add(x, y)
          - subtract(x, y)
          - multiply(x, y)
          - divide(x, y)

          Throw
          - OverflowException if add(x, y) exceeds Int.MAX_VALUE
          - UnderflowException if subtract(x, y) exceeds Int.MIN_VALUE
          - MathCalculationException for division by 0
   */

  println("\n=================== 4. Quiz ===================")
  println("\n=================== 4.1 OOM ===================")
  // throw new OutOfMemoryError
  // val array = Array.ofDim(2147483644) // initialize array too large
  // val array = Array.ofDim(Int.MaxValue) // Only works for Scala 2.12

  println("\n=================== 4.2 SOF ===================")

  // throw new StackOverflowError
  def infiniteRecursion: Int = 1 + infiniteRecursion
  // infiniteRecursion

  println("\n=================== 4.2 Customized Exception ===================")

  class OverflowException extends Exception

  class UnderflowException extends Exception

  class MathCalculationException extends Exception

  // use object instead of class, so we dont need instantiate it every time
  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      if (x.toLong + y.toLong > Int.MaxValue) throw new OverflowException
      else if (x.toLong + y.toLong < Int.MinValue) throw new UnderflowException
      else x + y
    }

    def subtract(x: Int, y: Int): Int = {
      if (x.toLong - y.toLong > Int.MaxValue) throw new OverflowException
      else if (x.toLong - y.toLong < Int.MinValue) throw new UnderflowException
      else x - y
    }

    def multiply(x: Int, y: Int): Int = {
      if (x.toLong * y.toLong > Int.MinValue && x.toLong * y.toLong < Int.MaxValue) x * y
      else if (x.toLong * y.toLong > Int.MaxValue) throw new OverflowException
      else if (x.toLong * y.toLong < Int.MinValue) throw new UnderflowException
      else throw new MathCalculationException
    }

    def divide(x: Int, y: Int): Int = {
      if (y != 0) x / y
      else throw new MathCalculationException
    }
  }

  println("\n=================== 4.2.1 with classes ===================")
  // val calculator = new PocketCalculator
  // println(calculator.add(1, Int.MaxValue))
  // println(calculator.subtract(Int.MinValue, 1))
  // println(calculator.multiply(Int.MaxValue, 2))
  // println(calculator.divide(Int.MaxValue, 0))

  println("\n=================== 4.2.2 with object ===================")
  // PocketCalculator.add(1, Int.MaxValue)
  // PocketCalculator.add(-1, Int.MinValue)

  // PocketCalculator.subtract(Int.MinValue, 1)
  // PocketCalculator.subtract(1, Int.MinValue)

  // PocketCalculator.multiply(2, Int.MaxValue)
  // PocketCalculator.multiply(2, Int.MinValue)
  // PocketCalculator.multiply(2, Int.MinValue)

  PocketCalculator.divide(Int.MaxValue, 0)

}
