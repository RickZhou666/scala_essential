package com.rick.scala
package lectures.part3fp

/**
 * @Author: Rick
 * @Date: 2024/6/23 11:56
 */
object mapFlatmapFilterFor extends App {
  val list = List(1, 2, 3)
  println(list.head)
  println(list.tail)

  println("\n======================== map ========================")
  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))


  println("\n======================== filter ========================")
  // filter
  // based on predicate function to filter element
  println(list.filter(_ % 2 == 0))

  println("\n======================== flat map ========================")
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))


  println("\n======================== Quiz - pair elements ========================")
  // print all combinations between two lists
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("BLACK", "WHITE")
  // List("a1", "a2", ... "a4")
  // DONT USE FOR
  // for (char <- chars) {
  //   for (num <- numbers) {
  //     println(s"$char$num")
  //   }
  // }

  // function code, recursive
  val combinations = numbers.filter(_ % 2 == 0)
                            .flatMap(n => chars.flatMap(c => colors.map(color => s"$c$n-$color"))) // each number generate a list
  println(combinations)


  println("\n======================== foreach ========================")
  // foreach
  // print all element in each line
  list.foreach(println)


  println("\n======================== for-comprehensions - 1 ========================")
  val forCombinations = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  } yield s"$c$n-$color"
  println(forCombinations)

  println("\n======================== for-comprehensions - 2 ========================")
  for {
    n <- numbers
  } println(n)

  println("\n======================== syntax overload ========================")
  list.map { x =>
    x * 2
  }

  println("\n======================== Quiz ========================")
  /*
      1. MyList supports for comprehensions? if you want to use setup below
          map(f: A => B) => MyList[B]
          filter(p: A => Boolean) => MyList[A]
          flatMap(f: A => MyList[B]) => MyList[B]
      2. A small collection of at most ONE element - Maybe[+T]
          - map, flatMap, filter
   */

}
