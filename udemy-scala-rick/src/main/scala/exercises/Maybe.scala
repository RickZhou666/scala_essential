package com.rick.scala
package exercises

/**
 * @Author: Rick
 * @Date: 2024/6/23 15:14
 */
abstract class Maybe[+T] {
  def map[B](f: T => B): Maybe[B]

  def flatMap[B](f: T => Maybe[B]): Maybe[B]

  def filter(p: T => Boolean): Maybe[T] // same type as original
}

case object MaybeNot extends Maybe[Nothing] {

  override def map[B](f: Nothing => B): Maybe[B] = MaybeNot

  // Empty return MaybeNot collection
  override def flatMap[B](f: Nothing => Maybe[B]): Maybe[B] = MaybeNot

  override def filter(p: Nothing => Boolean): Maybe[Nothing] = MaybeNot
}

/*
    class can use TypeParams
    object cannot
 */
case class Just[+T](value: T) extends Maybe[T] {

  override def map[B](f: T => B): Maybe[B] = Just(f(value))

  override def flatMap[B](f: T => Maybe[B]): Maybe[B] = f(value)

  /*
      predicate take a T return Boolean
      if function p apply this value is True, return this object
      otherwise MaybeNot
   */
  override def filter(p: T => Boolean): Maybe[T] =
    if (p(value)) this
    else MaybeNot
}

object MaybeTest extends App {
  val just3 = Just(3)
  println(just3)
  println(just3.map(_ * 2))
  println(just3.flatMap(x => Just(x % 2 == 0)))
  println(just3.filter(_ % 2 == 0))
}
