ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.2"

lazy val root = (project in file("."))
  .settings(
    name := "functional-programming-scala")

libraryDependencies += "com.github.ben-manes.caffeine" % "caffeine" % "2.9.0"

