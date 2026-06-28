package dev.nheggoe.aoc24

import dev.nheggoe.aoc.AocDay

object Day02 extends AocDay(2) {

  def parseLine(line: String): Report = {
    val levels = line.split("\\s+").map(_.toInt)
    Report(levels.toSeq)
  }

  case class Report(levels: Seq[Int]) {
    def isSafe: Boolean =
      (allIncreasing || allDecreasing) && allInRange
    def isDampenedSafe: Boolean =
      isSafe || dampenedReport
    private val pairs: Seq[(Int, Int)] = levels.init.zip(levels.tail)
    private val allIncreasing: Boolean = pairs.forall(_ < _)
    private val allDecreasing: Boolean = pairs.forall(_ > _)
    private val allInRange: Boolean =
      pairs.forall { case (left, right) =>
        val difference = math.abs(left - right)
        (1 to 3).contains(difference)
      }
    private def dampenedReport: Boolean =
      levels.indices.exists { index =>
        val newLevels = levels.take(index) ++ levels.drop(index + 1)
        Report(newLevels).isSafe
      }
  }

  def partOne(input: String): Int =
    input.split("\n").map(parseLine).count(_.isSafe)

  def partTwo(input: String): Int =
    input.split("\n").map(parseLine).count(_.isDampenedSafe)

}
