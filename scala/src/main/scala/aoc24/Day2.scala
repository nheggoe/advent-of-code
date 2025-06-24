package dev.nheggoe
package aoc24

import util.InputFetcher.fetchInput

object Day2:

  private type Level = Int
  val input: List[List[Level]] = fetchInput(2024, 2)
    .map(_.split("\\s+"))
    .map(_.map(Integer.parseInt).toList)

  def main(args: Array[String]): Unit =
    println(s"Part one is: ${partOne(input)}")

  private type SafeReports = Int
  def partOne(input: List[List[Level]]): SafeReports =
    input.map(isSafe).count(_ == true)

  def isSafe(levels: List[Level]): Boolean =
    def difference(window: List[Level]): Int =
      window.last - window.head
    def isSafeIncreasing(window: List[Level]): Boolean =
      (1 to 3).contains(difference(window))
    def isSafeDecreasing(window: List[Level]): Boolean =
      (-3 to -1).contains(difference(window))

    levels.sliding(2).forall(isSafeIncreasing)
    || levels.sliding(2).forall(isSafeDecreasing)
