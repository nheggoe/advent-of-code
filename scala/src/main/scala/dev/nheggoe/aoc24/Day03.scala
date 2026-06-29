package dev.nheggoe.aoc24

import dev.nheggoe.aoc.{AocDay, Input}

import scala.util.matching.Regex

/** @see `https://adventofcode.com/2024/day/3` */
object Day03 extends AocDay(3) {
  private val mulPattern: Regex = """mul\((\d+),(\d+)\)""".r
  private val allPattern: Regex =
    """(mul\((\d+),(\d+)\)|do\(\)|don't\(\))""".r

  def partOne(using Input): Long =
    mulPattern
      .findAllIn(input)
      .map { case mulPattern(a, b) =>
        a.toLong * b.toLong
      }
      .sum

  def partTwo(using Input): Long = {
    val (_, res2) = allPattern
      .findAllIn(input)
      .foldLeft((true, 0: Long)) {
        case ((true, sum), mulPattern(a, b)) =>
          (true, sum + a.toLong * b.toLong)
        case ((_, sum), "don't()") => (false, sum)
        case ((_, sum), "do()")    => (true, sum)
        case ((flag, sum), _)      => (flag, sum)
      }
    res2
  }
}
