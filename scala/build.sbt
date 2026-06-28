name := "advent-of-code"
scalaVersion := "3.8.4"

scalacOptions ++= Seq(
  "-deprecation"
)

libraryDependencies ++= Seq(
  "org.scala-lang" %% "toolkit" % "0.9.2",
  "org.scala-lang" %% "toolkit-test" % "0.9.2" % Test
)
