name := "advent-of-code"
scalaVersion := "3.8.4"

scalacOptions ++= Seq(
  "-deprecation",
  "-source:future",
  "-feature",
  "-unchecked",
  "-Wunused:all",
  "-Wvalue-discard",
  "-preview",
  "-explain"
)

val toolkitVersion = "0.9.2"

libraryDependencies ++= Seq(
  "org.scala-lang" %% "toolkit" % toolkitVersion,
  "org.scala-lang" %% "toolkit-test" % toolkitVersion % Test
)

lazy val runAll = taskKey[Unit]("Run every AoC day's main sequentially")

runAll := Def.uncached(Def.taskDyn {
  val log = streams.value.log
  val DayClass = """dev\.nheggoe\.aoc(\d+)\.Day(\d+)""".r
  val days = (Compile / discoveredMainClasses).value.collect {
    case name @ DayClass(year, day) => (year.toInt, day.toInt, name)
  }.sorted
  log.info(s"Running ${days.size} AoC days sequentially:")
  days.foreach { case (y, d, _) => log.info(f"  20$y%02d Day$d%02d") }
  Def.sequential(days.map { case (_, _, name) =>
    (Compile / runMain).toTask(s" $name")
  })
}.value)
