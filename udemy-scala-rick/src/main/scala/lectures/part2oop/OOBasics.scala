package com.rick.scala
package lectures.part2oop

/**
 * @Author: Rick
 * @Date: 2024/6/13 07:33
 */
object OOBasics extends App {
  val person = new Person("Rick", 21)
  println(person.age) // age is a parameter, not a class member
  println(person.x)
  person.greet("Eve")
  person.greet()

  val person2 = new Person("Eve")
  println(person2.age)


  val rick = new Writer("rick", "zhou", 1993)
  println(rick.fullName())

  println("\n\n============ Author Test ============ ")
  val author = new Writer("Charles", "Dickens", 1812)
  val imposter = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)
  println(novel.authorAge())
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(imposter))


  println("\n\n============ Counter Test ============ ")
  val counter = new Counter
  counter.inc.print
  counter.inc.inc.inc.print
  counter.inc(10).print
}

// constructor
class Person(name: String, val age: Int = 0) {
  // body
  val x = 2 // FIELDs

  println(1 + 3) // evaluated, every object instantiated will invoke this

  // method, if variable name same, it will reflect to method variable
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  // overloading
  def greet(): Unit = println(s"Hi, I am $name")

  def greet(age: Int): Int = 1
  // different signatures, diff # of params, diff type of params, diff return type

  // multiple constructors
  // def this(name: String) = this(name, 0) // use default value in constructor
  def this(name: String) = this(name, 0)

  def this() = this("John Doe")
}

// class parameters are NOT FIELDS
// add val in front, it will become a FIELD


/*
  Novel and a Writer

  Writer: first name, surname, year
    - method fullname

  Novel: name, year of release, author
    - authorAge
    - isWrittenBy(author)
    - copy(new year of release) = new instance of Novel
 */

class Writer(firstName: String, surname: String, val year: Int) {
  def fullName(): String =
    s"$firstName $surname"
}

class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def authorAge(): Int =
    yearOfRelease - author.year

  def isWrittenBy(author: Writer): Boolean =
    author == this.author

  def copy(newYearOfRelease: Int): Novel =
    new Novel(name, newYearOfRelease, author)
}



/*
  Counter class
    - receives an int value
    - method current count
    - method to increment/decrement => new Counter
    - overload inc/dec to receive an amount => new Counter
 */

class Counter(val count: Int = 0) {
  // in above way, it's a field member, you can access like getCount()
  // def currentCount(): Int =
  //   this.number

  // def inc(): Int =
  //   new Counter(this.number + 1)

  // def dec(): Int =
  //   new Counter(this.number - 1)

  // instance is fixed, and cannot be modified
  def inc = {
    println("incrementing")
    new Counter(count + 1) // immutability
  }


  def dec = {
    println("decrementing")
    new Counter(count - 1)
  }

  // this is return type, as we return Counter, it cannot be Int
  // def incrementBy(cnt: Int): Int =
  //   new Counter(this.number + cnt)
  //
  // def decrementBy(cnt: Int): Int =
  //   new Counter(this.number - cnt)

  // overload
  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n - 1)
    // new Counter(count + n)
  }

  def dec(n: Int): Counter = {
    if (n <= 0) this
    else dec.dec(n - 1)
  }

  def print = println(count)
}
