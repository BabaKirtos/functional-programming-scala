ThisBuild / version := "0.1.0-SNAPSHOT"

val scala3Version = "3.1.2"

lazy val root = (project in file("."))
  .settings(
    name := "functional-programming-scala",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      "com.github.ben-manes.caffeine" % "caffeine" % "2.9.0",
      "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.3"))
