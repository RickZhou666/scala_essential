package com.rick.scala
package lectures.part1basics

/**
 * @Author: Rick
 * @Date: 2024/6/13 07:01
 */
object StringOps extends App {

  val str: String = "Hello, I am learning Scala"

  println(str.charAt(2))
  println(str.substring(7, 11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", "--"))
  println(str.toLowerCase())
  println(str.toUpperCase())
  println(str.length)

  // ============== below scala function ==============

  val aNumberString = "45"
  val aNumber = aNumberString.toInt
  // +: prepend, "a" +: param
  // :+ append,  param := "a"
  println('a' +: aNumberString :+ 'z')
  println('a' + aNumberString + 'z')
  println(Array('a') ++ aNumberString ++ Array('z'))


  println(str.reverse)
  println(str.take(2))

  // ============== Scala-specific: String interpolates ==============
  // S-interpolates
  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name and I am $age years old" // inject $name
  val anotherGreeting = s"Hello, my name is $name and I will be turning ${age + 1} years old"
  println(anotherGreeting)


  // F-interpolates
  val speed = 1.5f
  val myth = f"$name can eat $speed%2.3f burgers per minuets" // %2.3f printlnf (print with format) 2 characters minimal, 3 decimal precision
  println(myth)

  // raw-interpolates
  println(raw"This is a \n newline") // \ (backslash) won't be escaped
  val escaped = "This is a \n newline"
  println(raw"$escaped")
}
