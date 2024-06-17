package com.rick.scala
package lectures.part2oop

import playground.{PrinceCharming, Cinderella => Princess}

import java.sql
import java.util.Date
// import playground._

import java.util.Date
import java.sql.{Date => SqlDate}

/**
 * @Author: Rick
 * @Date: 2024/6/16 19:16
 */
object PackingAndImports extends App {

  println("\n=================== 1. accessible by their simple name  ===================")
  // package members are accessible by their simple name
  val writer = new Writer("Daniel", "RockTheJVM", 2018)

  println("\n=================== 2. import the package ===================")
  // import the package
  // val princess = new Cinderella // if you dont want to import class
  val princess = new Princess  // if you dont want to import class
  // val princess = new playground.Cinderella // use fully qulified name playground.Cinderella = fully qualified name


  println("\n=================== 3. packages are in hierarchy ===================")
  // packages are in hierarchy
  // matching folder structure

  println("\n=================== 4. package object ===================")
  // package object
  // universal method, fields
  sayHello
  println(SPEED_OF_LIGHT)

  println("\n=================== 5. imports ===================")
  // imports
  val prince = new PrinceCharming


  val aDate = new Date // this will use 1st one
  // 5.1 use fully quialifed name
  // val sqlDate = new sql.Date(2024, 6, 16)

  // 5.1 use alias
  val sqlDate = new SqlDate(2024, 6, 16)

  println("\n=================== 6. default imports ===================")
  // default imports
  // java.lang - String, Object, Exception
  // scala, Int, Nothing, Function
  // scala.Predef - println, ???
}
