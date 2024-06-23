package com.rick.scala
package exercises

import lectures.part2oop.Generics.MyList

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

  // OO exercise: deciding function signature
  // higher-order functions
  //    1. receive function as parameter
  //    2. or return other function as result
  def map[B](transformer: A => B): MyList[B]

  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  def filter(predicate: A => Boolean): MyList[A]

  // Concatenation
  // B super-type of A, return type is B
  def ++[B >: A](list: MyList[B]): MyList[B]

  // HOFs
  def foreach(f: A => Unit): Unit
  /*
      receive a compare function
   */
  def sort(compare: (A, A) => Int): MyList[A]
  /*
      MyList[A] will receive some MyList[B] and a zip function convert (A, B) => C, return MyList[C]
      add Type Parameters [B, C]
      will throw exception if different length
   */
  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]

  /*
      TypeParameter as B
      take param B
      and operator with params (B, A) return B,
      then return B

   */
  def fold[B](start: B)(operator: (B, A) => B): B

}

// Empty list should be anything
case object Empty extends MyList[Nothing] {

  override def head: Nothing = throw new NoSuchElementException // expression return nothing, throw NotImplemented error

  // override def tail: MyList = throw new NoSuchElementException
  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty(): Boolean = true

  override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  override def printElements: String = ""

  // OO exercise: deciding function signature
  override def map[B](transformer: Nothing => B): MyList[B] = Empty

  override def flatMap[B](transformer: Nothing =>  MyList[B]): MyList[B] = Empty

  override def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty


  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list // just return that list

  // HOFs
  override def foreach(f: Nothing => Unit): Unit = ()

  override def sort(compare: (Nothing, Nothing) => Int) = Empty

  override def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Empty

  override def fold[B](start: B)(operator: (B, Nothing) => B): B = start // as list is empty, return 'start' value
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty(): Boolean = false

  override def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  override def printElements: String =
    if (t.isEmpty()) "" + h
    // else h + " " + t.printElements // this only works in Scala 2. Scala 3 use a String Interpolator
    else s"$h + ${t.printElements}" // this only works in Scala 2. Scala 3 use a String Interpolator

  /**
   * [1, 2, 3].filter(n % 2 == 0) =
   *  <br>= [2, 3].filter(n % 2 == 0) =
   *  <br>= new Cons(2, [3].filter(n % 2 == 0) )
   *  <br>= new Cons(2, Empty.filter(n % 2 == 0) )
   *  <br>= new Cons(2, Empty)
   *
   * @param predicate
   * @return
   */
  override def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) new Cons(h, t.filter(predicate)) // if h passed it will be included in result, and test tail
    else t.filter(predicate) // otherwise test tail

  /**
   * `[1, 2, 3].map(n * 2)`
   * <br>`= new Cons(2, [2, 3].map(n * 2) )`
   * <br>`= new Cons(2, new Cons(4, [3].map(n * 2) ) )`
   * <br>`= new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2) ) ) )`
   * <br>`= new Cons(2, new Cons(4, new Cons(6, Empty) ) ) `
   *
   * @param transformer
   * @tparam B
   * @return
   */
  override def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

  /*
      [1, 2] ++ [3, 4, 5]
      = new Cons(1, [2] ++ [3, 4, 5])
      = new Cons(1, new Cons(2, Empty ++ [3, 4, 5]))
      = new Cons(1, new Cons(2, [3, 4, 5]))
      = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5) ) ) ) )
   */
  override def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)


  /*
    [1, 2].flatMap(n => [n, n + 1])
    = [1, 2] ++ [2].flatMap(n => [n, n + 1])
    = [1, 2] ++ [2, 3] ++ Empty.flatMap(n => [n, n + 1])
    = [1, 2] ++ [2, 3] ++ Empty
    = [1, 2, 2, 3]
   */
  override def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  // HOFs
  override def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  override def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] = {
      if (sortedList.isEmpty()) new Cons(x, Empty)
      else if (compare(x, sortedList.head)<= 0) new Cons(x, sortedList)
      else  new Cons(sortedList.head, insert(x, sortedList.tail))
    }

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  override def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] =
    if (list.isEmpty()) throw new RuntimeException("Lists do not have the same length")
    else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))

  /*
    [1,2,3].fold(0)(+) =
    = [2,3].fold(1)(+) =
    = [3].fold(3)(+) =
    = [].fold(6)(+) =
    = 6


   */
  override def fold[B](start: B)(operator: (B, A) => B): B = {
    // val newStart = operator(start, h)
    // t.fold(newStart)(operator)
    // equivalent to below
    t.fold(operator(start, h))(operator)
  }
}


// =============== Start of Convert to function like ===============
// trait MyPredicate[-T] { // T => Boolean
//   def test(elem: T): Boolean
// }
//
// trait MyTransformer[-A, B] { // A => B
//   def transform(elem: A): B
// }
// =============== End of Convert to function like ===============

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


  println("================== test list trait, abstract classes, invariant, covariant, contravariant ==================")
  val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", new Cons("Rick", Empty)))
  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println("\n================== test map ==================")
  // val transformer = new Function1[Int, Int]{
  //   override def apply(elem: Int): Int = elem * 2
  // }
  // val transformer: Int => Int = (elem: Int) => elem + 2
  // val mapListOfIntegers =  listOfIntegers.map(transformer)
  // val mapListOfIntegers =  listOfIntegers.map(transformer)
  println(listOfIntegers.map(elem => elem * 2).toString)



  println("\n================== test filter ==================")
  // val predicate = new Function1[Int, Boolean] {
  //   override def apply(elem: Int): Boolean = elem % 2 == 0
  // }
  // val predicate: Int => Boolean = (elem: Int) =>  elem % 2 == 0
  // val filterListOfIntegers =  listOfIntegers.filter(predicate)
  println(listOfIntegers.filter(elem => elem % 2 == 0))


  println("\n================== test flatMap ==================")
  val anotherListOfIntegers: MyList[Int] = new Cons(1, new Cons(4, new Cons(5, Empty)))
  println(listOfIntegers ++ anotherListOfIntegers)
  // val flatMapTransformer = new Function1[Int, MyList[Int]] {
  //   override def apply(elem: Int): MyList[Int] = new Cons(elem, new Cons(elem + 1, Empty))
  // }
  // val flatMapTransformer: Int => MyList[Int] = (elem: Int) => new Cons(elem, new Cons(elem + 1, Empty))
  // println(listOfIntegers.flatMap(flatMapTransformer))
  println(listOfIntegers.flatMap(elem => new Cons(elem, new Cons(elem + 1, Empty))))

  println("\n================== test case classes & objects ==================")
  val cloneListOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(s"compare clone of list integers ${listOfIntegers == cloneListOfIntegers}")
  // if you don't use 'case' then you need define a recursive comparison method


  println("\n================== HOFs foreach ==================")
  // listOfIntegers.foreach(x => println(x))
  listOfIntegers.foreach(println)

  println("\n================== HOFs sort ==================")
  println(listOfIntegers.sort((x, y) => y - x))

  println("\n================== HOFs zip ==================")
  // missing parameter type for expanded function ((x$1: Int, x$2: <error>) => <x$1: error>.<$plus: error>("-").<$plus: error>(x$2))
  //   println(anotherListOfIntegers.zipWith(listOfStrings, _ + "-" + _))
  println(anotherListOfIntegers.zipWith[String, String](listOfStrings, _ + "-" + _))

  println("\n================== HOFs fold ==================")
  println(listOfIntegers.fold(0)(_ + _))

}