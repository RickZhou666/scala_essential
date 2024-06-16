package com.rick.scala
package lectures.part1basics

/**
 * @Author: Rick
 * @Date: 2024/6/13 06:47
 */
object DefaultArgs extends App {

  // if I dont pass acc value, it will be default as 1
  def trFact(n: Int, acc: Int = 1): Int =
    if (n <= 1) acc
    else trFact(n - 1, n * acc)

  val fact10 = trFact(10, 2) // acc would be override
  println(fact10)

  def savaPicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("saving picture")

  savaPicture()
  // savaPicture(800, 600) // compiler will think 800 is the first parameter which is String
  savaPicture(height = 600) // compiler will override height and take default value for other parameters
  /*
    1. pass in every leading argument
    2. name the arguments
   */

  savaPicture(height = 600, width = 800, format = "png")

}
