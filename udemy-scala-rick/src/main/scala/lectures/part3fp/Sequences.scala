package com.rick.scala
package lectures.part3fp

import scala.util.Random

/**
 * @Author: Rick
 * @Date: 2024/6/23 15:49
 */
object Sequences extends App {
  println("\n======================== Seq ========================")
  // Seq
  val aSequence = Seq (4, 3, 2, 1)
  println(aSequence)
  println(aSequence.reverse)
  // apply method retrieve the value at specified index
  println(aSequence(2))
  println(aSequence.apply(2))

  println(aSequence ++ Seq(7, 5, 4))
  println(aSequence.sorted)

  println("\n======================== Ranges - to ========================")
  // Ranges
  val aRange: Seq[Int] =  1 to 10
  aRange.foreach(println)

  println("\n======================== Ranges - until (exclusive) ========================")
  val aRange2: Seq[Int] =  1 until  10
  aRange2.foreach(println)

  println("\n======================== Ranges - do something repeatively ========================")
  (1 to 10).foreach(x => println("hello"))

  println("\n======================== lists ========================")
  // lists
  println("\n======================== lists - prepend & append ========================")
  val aList = List(1, 2, 3)
  val prepended = 42 :: aList
  println(prepended)

  //              prepend     append
  // : always on the side of list
  val prepended2 = 42 +: aList :+ 89
  println(prepended2)

  println("\n======================== lists - fill ========================")
  val apples5 = List.fill(5)("apple")
  println(apples5)
  println(aList.mkString("-|-"))

  println("\n======================== Array ========================")
  // Array
  val numbers = Array(1, 2, 3, 4)
  val treeElements = Array.ofDim[Int](3)
  // println(treeElements)
  // initialized with some default value, reference type -> null, primitive type -> default
  treeElements.foreach(println)

  println("\n======================== Array - mutation ========================")
  // mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2, 0)
  println(numbers.mkString(" "))


  println("\n======================== Array - implicit conversion ========================")
  // arrays and seq
  val numbersSeq: Seq[Int] = numbers // implicit conversion
  println(numbersSeq)

  println("\n======================== Vector ========================")
  // Vector
  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  println("\n======================== List vs Vector ========================")
  // vectors vs lists
  val maxRuns = 1000
  val maxCapacity = 1000000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns // run 1000 times
    } yield {
      val currentTime = System.nanoTime()           // get current time
      // operations
      collection.updated(r.nextInt(maxCapacity), r.nextInt()) // modify the collection
      System.nanoTime() - currentTime               // return time difference
    }

    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // 1s = 1e3 ms = 1e6 microsecond = 1e9 nano seconds = 1e12 pico seconds

  // pros: keep reference to tail
  // cons: updating an element in the middle takes long time
  // if u try to update the rest of element it's not very efficient
  println(getWriteTime(numbersList))
  // pros: depth of the tree is small
  // cons: needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector))
}
