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

  protected lazy val input: String = InputFetcher.fetchInput

  def partOne(input: String): Any
  def partTwo(input: String): Any

  def main(args: Array[String]): Unit =
    val day = f"Day$n%02d"
    println(s"[$year $day.partOne] ${partOne(input)}")
    println(s"[$year $day.partTwo] ${partTwo(input)}")
