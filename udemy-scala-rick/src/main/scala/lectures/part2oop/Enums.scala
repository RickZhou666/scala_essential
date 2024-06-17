package com.rick.scala
package lectures.part2oop

/**
 * @Author: Rick
 * @Date: 2024/6/16 17:13
 */
object Enums {

  // enum Permissions {
  //   case READ, WRITE, EXECUTE, NONE
  //
  //   // add fields/ methods
  //   def openDocument(): Unit =
  //     if (this == READ) println("opening document...")
  //     else println("reading not allowed.")
  // }
  //
  // val somPermissions: Permissions = Permissions.READ
  //
  // // constructor args
  // // define constant inside Enum
  // enum PermissionsWithBits(bits: Int) {
  //   case READ extends PermissionsWithBits(4) // 100
  //   case WRITE extends PermissionsWithBits(2) // 010
  //   case EXECUTE extends PermissionsWithBits(1) // 001
  //   case NONE extends PermissionsWithBits(0) // 000
  // }
  //
  // object PermissionsWithBits {
  //   def fromBit(bits: Int): PermissionsWithBits = // whatever
  //     PermissionsWithBits.NONE
  // }
  //
  // // standard API
  // // defined by the order
  // val somePermissionsOrdinal = somPermissions.ordinal
  // val allPermissions = PermissionsWithBits.values // array of all possible values of the enum
  // val readPermission: Permissions = Permissions.valueOf("READ")
  // /*
  //   In Java
  //   enum Thing {
  //     A(2),
  //     B(3)
  //   }
  //  */
  //
  // def main(args: Array[String]): Unit = {
  //   somPermissions.openDocument()
  //   println(somePermissionsOrdinal)
  //   println(allPermissions.toList)
  //   println(readPermission.toString)
  // }
}
