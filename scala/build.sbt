import sbt.Keys.{libraryDependencies, testFrameworks}

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.7.1"

lazy val root = (project in file("."))
  .settings(
    name := "advent-of-code",
    idePackagePrefix := Some("dev.nheggoe"),
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.1.19",
      "dev.zio" %% "zio-http" % "3.3.3"
    ),
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio-test" % "2.1.19" % Test,
      "dev.zio" %% "zio-test-sbt" % "2.1.19" % Test,
      "dev.zio" %% "zio-test-magnolia" % "2.1.19" % Test
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )
