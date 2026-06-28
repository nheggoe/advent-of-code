name := "advent-of-code"
scalaVersion := "3.8.4"

scalacOptions ++= Seq(
  "-deprecation"
)

val toolkitVersion = "0.9.2"

libraryDependencies ++= Seq(
  "org.scala-lang" %% "toolkit" % toolkitVersion,
  "org.scala-lang" %% "toolkit-test" % toolkitVersion % Test
)
