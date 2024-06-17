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

  // OO exercise: deciding function signature
  def map[B](transformer: MyTransformer[A, B]): MyList[B]

  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]

  def filter(predicate: MyPredicate[A]): MyList[A]

  // Concatenation
  // B super-type of A, return type is B
  def ++[B >: A](list: MyList[B]): MyList[B]
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
  override def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty

  override def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty

  override def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty


  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list // just return that list
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
  override def filter(predicate: MyPredicate[A]): MyList[A] =
    if (predicate.test(h)) new Cons(h, t.filter(predicate)) // if h passed it will be included in result, and test tail
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
  override def map[B](transformer: MyTransformer[A, B]): MyList[B] =
    new Cons(transformer.transform(h), t.map(transformer))

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
  override def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)
}

trait MyPredicate[-T] {
  def test(elem: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(elem: A): B
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


  println("================== test list trait, abstract classes, invariant, covariant, contravariant ==================")
  val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", new Cons("Rick", Empty)))
  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println("\n================== test map ==================")
  val transformer = new MyTransformer[Int, Int]{
    override def transform(elem: Int): Int = elem * 2
  }
  val mapListOfIntegers =  listOfIntegers.map(transformer)
  println(mapListOfIntegers.toString)



  println("\n================== test filter ==================")
  val predicate = new MyPredicate[Int] {
    override def test(elem: Int): Boolean = elem % 2 == 0
  }
  val filterListOfIntegers =  listOfIntegers.filter(predicate)
  println(filterListOfIntegers)


  println("\n================== test flatMap ==================")
  val anotherListOfIntegers: MyList[Int] = new Cons(1, new Cons(4, new Cons(5, Empty)))
  println(listOfIntegers ++ anotherListOfIntegers)
  val flatMapTransformer = new MyTransformer[Int, MyList[Int]] {
    override def transform(elem: Int): MyList[Int] = new Cons(elem, new Cons(elem + 1, Empty))
  }
  println(listOfIntegers.flatMap(flatMapTransformer))

  println("\n================== test case classes & objects ==================")
  val cloneListOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(s"compare clone of list integers ${listOfIntegers == cloneListOfIntegers}")
  // if you don't use 'case' then you need define a recursive comparison method
}