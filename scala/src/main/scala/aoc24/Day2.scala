package dev.nheggoe
package aoc24

import util.InputFetcher.fetchInput

object Day2 {

  val input: String = fetchInput(2024, 2)

  def parseLine(line: String): Report =
    val levels = line.split("\\s+").map(_.toInt)
    Report(levels)

  case class Report(levels: Seq[Int]):
    def isSafe: Boolean =
      (allIncreasing || allDecreasing) && allInRange
    def isDampenedSafe: Boolean =
      isSafe || dampenedReport
    private val pairs: Seq[(Int, Int)] = levels.init.zip(levels.tail)
    private val allIncreasing: Boolean = pairs.forall(_ < _)
    private val allDecreasing: Boolean = pairs.forall(_ > _)
    private val allInRange: Boolean =
      pairs.forall: pair =>
        val difference = math.abs(pair._1 - pair._2)
        (1 to 3).contains(difference)
    private def dampenedReport: Boolean =
      levels.indices.exists: index =>
        val newLevels = levels.take(index) ++ levels.drop(index + 1)
        Report(newLevels).isSafe

  def main(args: Array[String]): Unit = {
    val reports = input.split("\n").map(parseLine)
    val partOne = reports.count(_.isSafe)
    val partTwo = reports.count(_.isDampenedSafe)
    println(s"Day 2 part one is: $partOne")
    println(s"Day 2 part two is: $partTwo")
  }

}
