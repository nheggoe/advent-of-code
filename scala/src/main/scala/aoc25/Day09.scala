package dev.nheggoe
package aoc25

import util.InputFetcher

object Day09 {

  case class Point(x: Long, y: Long)

  def area(p: Point, q: Point): Long = {
    val Point(x_1, y_1) = p
    val Point(x_2, y_2) = q
    (math.abs(x_2 - x_1) + 1) * (math.abs(y_2 - y_1) + 1)
  }

  def parseInput(input: String): Seq[Point] = {
    input.linesIterator.collect { case s"$x,$y" =>
      Point(x.toInt, y.toInt)
    }.toSeq
  }

  def main(args: Array[String]): Unit = {
    val puzzleInput = InputFetcher.fetchInput(2025, 9)
    println(s"Day 9 part one is ${partOne(puzzleInput)}")
  }

  def partOne(input: String): Long = {
    val points = parseInput(input)
    points
      .combinations(2)
      .map { case Seq(p, q) => area(p, q) }
      .max
  }

}
