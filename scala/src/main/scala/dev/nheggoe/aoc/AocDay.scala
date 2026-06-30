package dev.nheggoe.aoc

import java.util.concurrent.*

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
object AocDay:
  /** Per-part wall-clock budget; parts exceeding this print `timed out`. */
  private val timeoutSeconds = 4

trait AocDay(n: Int)(using year: Year):
  given Date(year, Day(n))

  protected def input(using i: Input): String = i.value
  protected def lines(using i: Input): Vector[String] =
    input.linesIterator.toVector

  def partOne(using Input): Any
  def partTwo(using Input): Any

  def main(args: Array[String]): Unit =
    given Input = InputFetcher.fetchInput
    logPartOne(partOne)
    logPartTwo(partTwo)

  def logPartOne(ans: => Any): Unit = tryLog(prefix("partOne"), ans)

  def logPartTwo(ans: => Any): Unit = tryLog(prefix("partTwo"), ans)

  private def prefix(label: String) = f"[$year Day$n%02d.$label] "

  /** Evaluates `x` on a daemon thread, aborting after
    * [[AocDay.timeoutSeconds]].
    *
    * A timed-out computation can't be forcibly killed on the JVM (cancellation
    * is cooperative), but the worker is a daemon so it never blocks JVM exit;
    * `shutdownNow` interrupts it on a best-effort basis.
    */
  private def tryLog(prefix: String, x: => Any): Unit =
    print(prefix)
    val executor = Executors.newSingleThreadExecutor: r =>
      val t = Thread(r, f"$year-Day$n%02d")
      t.setDaemon(true)
      t
    try
      val task: Callable[Any] = () => x
      println(
        executor.submit(task).get(AocDay.timeoutSeconds, TimeUnit.SECONDS)
      )
    catch
      case _: TimeoutException =>
        println(s"timed out (> ${AocDay.timeoutSeconds}s)")
      case e: ExecutionException =>
        e.getCause match
          case _: NotImplementedError => println("???")
          case cause                  => throw cause
    finally executor.shutdownNow()
