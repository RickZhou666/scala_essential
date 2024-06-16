package com.rick.scala
package lectures.part1basics

import scala.annotation.tailrec

/**
 * @Author: Rick
 * @Date: 2024/6/12 22:42
 */
object Recursion extends App {
  // @tailrec
  private def factorial(n: Int): Int =
    if (n <= 1) 1
    else {
      println(s"Computing factorial of $n - I first need factorial of ${n - 1}")
      val result = n * factorial(n - 1) // this need extra stack frame
      println(s"Computed factorial of $n")

      result
    }

  // println(factorial(5000))

  def anotherFactorial(n: BigInt): BigInt = {
    @tailrec // use this annotation
    def factHelper(x: BigInt, accumulator: BigInt): BigInt = {
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator) // allows scala to use current stack frame instead of creating a new one
                                              // TAIL RECURSION = use recursive call as the LAST expression
    }
    factHelper(n, 1)
  }
  /*
    anotherFactorial(10) = factHelper(10, 1)
    = factHelper(9, 10 * 1)
    = factHelper(8, 9 * 10 * 1)
    = factHelper(7, 8 * 9 * 10 * 1)
    ...
    = factHelper(2, 3 * 4 * ... * 8 * 9 * 10 * 1)
    = factHelper(1, 2 * 3 * 4 * ... * 9 * 10 * 1)
    = 2 * 3 * 4 * ... * 9 * 10 * 1
    //  this works for large numbr
   */
  // println(anotherFactorial(5000)) // if 0 run out of BigInt limit


  // WHEN YOU NEED LOOPS, USE _TAIL_RECURSION

  /*
    1. concatenate a String and times
    2. IsPrime function tail recursive
    3. Fibonacci function, tail recursive
   */

    def concat(name: String, age: Int): String = {
      @tailrec
      def concatHelper(name: String, age:Int, accumulator: String): String = {
        if (age <= 1) s"$name $age $accumulator"
        else  concatHelper(name, age - 1, s"$name $age $accumulator")
      }
      concatHelper(name, age, "")
    }
    println(concat("Rick", 3))

    def isPrime(n: Int): Boolean = {
      @tailrec
      def isPrimeTailrec(t: Int, isStillPrime: Boolean): Boolean = {
        if (!isStillPrime) false
        else if (t <= 1) true
        else isPrimeTailrec(t - 1, n % t != 0 && isStillPrime)
      }
      isPrimeTailrec(n / 2, true)
    }
    println(isPrime(8))

    def fabonacci(n: Int): Int = {
      @tailrec
      def fabonacciTailrec(i: Int, last: Int, nextToLast: Int): Int = {
        if (i >= n) last
        else fabonacciTailrec(i + 1, last + nextToLast, last)
      }

      if (n <= 2) 1
      else fabonacciTailrec(2, 1, 1)
    }
  // f(4) = f(3) +        f(2)
  //      = f(2) + f(1) + f(2)
  // 1 1 2 3 5 8 13 21
  println(fabonacci(2))
}

