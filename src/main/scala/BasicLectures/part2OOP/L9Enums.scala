package BasicLectures.part2OOP

object L9Enums {

  enum Permissions { // a data type with bunch of cases/constants in the body
    case READ, WRITE, EXECUTE, NONE
    // add fields/methods
    def openDocument(): Unit =
      if (this == READ) println("Opening document")
      else println("Reading not allowed")
  }

  // ENUMS can also take arguments (constructor args)
  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4) // binary - 100
    case WRITE extends PermissionsWithBits(2) // 010
    case EXECUTE extends PermissionsWithBits(1) // 001
    case NONE extends PermissionsWithBits(0) // 000
  }

  // ENUMS can have companion objects
  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits = PermissionsWithBits.NONE
  }

  val somePermissions: Permissions = Permissions.READ // instance of Permissions
  // Standard API for ENUM
  val somePermissionsOrdinal = somePermissions.ordinal // the index of case READ ie 0
  val allPermission = PermissionsWithBits.values // all values of the ENUM ?? Not working ??
  val readPermission = PermissionsWithBits.valueOf("READ")

  def main(args: Array[String]): Unit = {
    somePermissions.openDocument()
    println(somePermissionsOrdinal)
    println(allPermission)
    println(readPermission)
  }

}
