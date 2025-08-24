import sbt.Keys.libraryDependencies

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.7.2"

lazy val root = (project in file("."))
  .settings(
    name := "advent-of-code",
    idePackagePrefix := Some("dev.nheggoe"),
    libraryDependencies ++= Seq(
      "org.scala-lang" %% "toolkit" % "0.7.0",
      "org.scala-lang" %% "toolkit-test" % "0.7.0" % Test
    )
  )
