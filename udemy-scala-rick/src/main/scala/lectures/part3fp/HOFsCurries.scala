package com.rick.scala
package lectures.part3fp

/**
 * @Author: Rick
 * @Date: 2024/6/22 06:26
 */
object HOFsCurries extends App {

  /* right side: return type is another Function takes Int return Int
   left side: params
            - Int
            - Function, (String, (Int => Boolean)) => Int
                  params
                  - String
                  - Function, Int => Boolean
                  return Int
                        params
                        - Int
                        return Boolean
  */
  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null

  // either takes function as param or return function as result
  // higher-order-function (HOF)

  // map, flatMap, filter in MyList

  println("======================== apply function n times ========================")
  // function that applies a function n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x))) = nTimes(f, 2, f(x)) = f(f(f(x)))
  // nTimes(f, n, x) = f(f(...(x))) = nTimes(f, n - 1, f(x))
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))

  val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 10, 1))

  println("\n======================== HOFs ========================")
  // ntb(f, n) = x => f(f(f...(x))))
  // increment10 = ntb(plusOne, 10) = x => plusOne(plusOne...(x)), return a function that applied PlusOne 10 times, then u can use this function
  // val y = increment10(1)
  def nTimesBetter(f: Int => Int, n: Int): Int => Int =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x)) // nTimesBetter(f, n - 1) return a function that will apply f(x)

  val increment10 = nTimesBetter(plusOne, 10)
  println (increment10(1))

  println("\n======================== curries functions ========================")
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3) // y => 3 + y, store the 3 into add3, the apply y
  println(add3(10))
  println(superAdder(3)(10))

  println("\n======================== functions with multiple parameter lists ========================")
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))


  println("\n======================== Quiz ========================")
  /*
      1. Expand MyList
          - foreach method A => Unit
              [1, 2, 3].foreach(x => println(x))

          - sort function ((A, A) => Int) => MyList
              [1, 2, 3].sort( (x, y) => y - x ) => [3, 2, 1]

          - zipWith (list, (A, A) => B) => MyList[B]
              [1, 2, 3].zipWith([4, 5, 6], x * y) => [1 * 4, 2 * 5, 3 * 6] = [4, 10, 18]

          - fold(start)(function) => a value
              [1, 2, 3].fold(0)(x + y) = 6

      2.  toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
          fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int

      3.  compose(f, g) => x => f(g(x))
          andThen(f, g) => x => g(f(x))
   */

  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) =
    x => y => f(x, y)

  def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int =
    (x, y) => f(x)(y)

  // FunctionX
  // def compose(f: Int => Int, g: Int => Int): Int => Int =
  //   x => f(g(x))
  /*
    g(x): T => A
                    T => A => B: T => B
    f(x): A => B
   */
  def compose[A, B, T](f: A => B, g: T => A): T => B =
    x => f(g(x))

  // def andThen(f: Int => Int, g: Int => Int): Int => Int =
  //   x => g(f(x))

  /*
    f(x): A => B
                    A => B => C: A => C
    g(x): B => T
   */
  def andThen[A, B, C](f: A => B, g: B => C): A => C =
    x => g(f(x))

  println("\n======================== Quiz - adder ========================")
  def superAdder2 : (Int => Int => Int) = toCurry(_ + _)
  def add4 = superAdder2(4)
  println(add4(17))

  val simpleAddr = fromCurry(superAdder)
  println(simpleAddr(4, 17))

  println("\n======================== Quiz - compose ========================")
  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3

  val composed = compose(add2, times3)
  val ordered = andThen(add2, times3)

  println(composed(4))
  println(ordered(4))

}
