package dev.nheggoe.aoc

opaque type Year = Int

object Year:
  def apply(n: Int): Year = n
  extension (y: Year) def value: Int = y

opaque type Day = Int

object Day:
  def apply(n: Int): Day = n
  extension (d: Day) def value: Int = d

case class Date(year: Year, day: Day)

/** Base for every solution.
  *
  * Extending `AocDay(n)` makes the day number an ambient `given Day`, fetches
  * the puzzle [[input]] from the package's `given Year` plus that day, and
  * provides a `main` that prints both parts. Subclasses only implement
  * [[partOne]] / [[partTwo]]; override `main` for custom output (e.g. timing).
  */
trait AocDay(n: Int)(using year: Year):
  given Date(year, Day(n))

  protected def input(using i: Input): String = i.value

  def partOne(using Input): Any
  def partTwo(using Input): Any

  def main(args: Array[String]): Unit =
    given Input = InputFetcher.fetchInput
    logPartOne(partOne)
    logPartTwo(partTwo)

  val day = f"Day$n%02d"

  def logPartOne(ans: => Any): Unit =
    println(s"[$year $day.partOne] $ans")

  def logPartTwo(ans: => Any): Unit =
    println(s"[$year $day.partTwo] $ans")
