package com.rick.scala
package lectures.part1basics

/**
 * @Author: Rick
 * @Date: 2024/6/12 21:02
 */
object Functions extends App {
  // function name(parameter_name: parameter_type) return_type
  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }
  println (aFunction("hello", 3))

  def aParameterlessFunction(): Int = 42
  println(aParameterlessFunction())
  // println(aParameterlessFunction) // sbt 3 forbid this


  def aRepatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + " " + aRepatedFunction(aString, n - 1)
  }

  println(aRepatedFunction("hello", 3))
  // WHEN YOU NEED LOOPS, USE RECURSION.

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)
  }

  /*
    1. A greeting function (name, age) => "Hi, my name is $name and I am $age years old."
    2. Factorial function 1 * 2 * 3 * ... * n
    3. A fibonacci function
        f(1) = 1
        f(2) = 1
        f(n) = f(n - 1) + f(n - 2)
    4. Test if a number is prime
   */

  def greetingFunction(name: String, age: Int): String = {
    // use string interpolation s"$name $age"
    s"Hi, my name is $name and I am $age years old."
  }
  println(greetingFunction("rick", 31))

  def factorialFunction(n: Int): Int = {
    if (n == 1) n
    else n * factorialFunction(n - 1)
  }
  println(factorialFunction(5))

  def fibonacciFunction(n: Int): Int = {
    if (n == 1) 1
    else if (n == 2) 1
    else fibonacciFunction(n - 1) + fibonacciFunction(n - 2)
  }
  println(fibonacciFunction(8))

  /*
    1 and itself
   */
  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(cur: Int): Boolean = {
      if (cur <= 1) true
      else n % cur != 0 && isPrimeUntil(cur - 1)
    }

    isPrimeUntil(n / 2)
  }

  println(isPrime(3))
  println(isPrime(3*21))

}
