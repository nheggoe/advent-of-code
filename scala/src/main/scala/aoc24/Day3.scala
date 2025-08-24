package dev.nheggoe
package aoc24

import util.InputFetcher

import scala.util.matching.Regex

/** @see `https://adventofcode.com/2024/day/3` */
object Day3 {
  private val aocInput: String =
    InputFetcher.fetchInput(2024, 3)
  private val mulPattern: Regex = """mul\((\d+),(\d+)\)""".r
  private val allPattern: Regex =
    """(mul\((\d+),(\d+)\)|do\(\)|don't\(\))""".r

  def partOne(input: String = aocInput): Long =
    mulPattern
      .findAllIn(input)
      .map:
        case mulPattern(a, b) =>
          a.toLong * b.toLong
      .sum

  def partTwo(input: String = aocInput): Long =
    val (_, res2) = allPattern
      .findAllIn(input)
      .foldLeft((true, 0: Long)):
        case ((true, sum), mulPattern(a, b)) =>
          (true, sum + a.toLong * b.toLong)
        case ((_, sum), "don't()") => (false, sum)
        case ((_, sum), "do()")    => (true, sum)
        case ((flag, sum), _)      => (flag, sum)
    res2

  def main(args: Array[String]): Unit =
    println(s"Day 3 part one is ${partOne()}")
    println(s"Day 3 part two is ${partTwo()}")
}
