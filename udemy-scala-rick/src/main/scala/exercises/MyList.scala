package com.rick.scala
package exercises

/**
 * @Author: Rick
 * @Date: 2024/6/15 23:00
 */
abstract class MyList[+A] { // covariant +A, invariant A, contravariant -A

  /*
      head = first element of the list
      tail = remainder of the list
      isEmpty = is this list empty
      add(int) => new List with this element added
      toString => a string representation of the list
   */
  def head: A

  def tail: MyList[A]

  def isEmpty(): Boolean

  def add[B >: A](element: B): MyList[B]

  def printElements: String

  // polymorphic call
  override def toString: String = "[" + printElements + "]"
}

// Empty list should be anything
object Empty extends MyList[Nothing] {

  override def head: Nothing = throw new NoSuchElementException // expression return nothing, throw NotImplemented error

  // override def tail: MyList = throw new NoSuchElementException
  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty(): Boolean = true

  override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  override def printElements: String = ""


}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty(): Boolean = false

  override def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  override def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements // this only works in Scala 2. Scala 3 use a String Interpolator
}

object ListTest extends App {
  // println("\n=============== test head & tail ===============")
  // val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  // println(list.head)
  // println(list.tail.head)
  //
  // println("\n=============== test add ===============")
  // println(list.add(4).head)
  //
  // println("\n=============== test empty ===============")
  // println(list.isEmpty)
  //
  // println("\n=============== test toString ===============")
  // println(list.toString)


  val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", new Cons("Rick", Empty)))
  println(listOfIntegers.toString)
  println(listOfStrings.toString)

}