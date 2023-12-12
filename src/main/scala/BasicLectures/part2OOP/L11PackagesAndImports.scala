package BasicLectures.part2OOP // this is not a expression

import BasicLectures.part3FP.Action

object L11PackagesAndImports extends App {

  // package members are accessible by simple name within the package
  val daniel = new Writer("Daniel", surName = "RJVM", birthYear = 1990)

  // need to import proper package
  val action = new Action
  println(action.execute(1))
  // packages are in hierarchy
}
