package dev.nheggoe
package aoc15

import util.InputFetcher

object Day02 {

  def surfaceArea(l: Int, w: Int, h: Int): Int = {
    val sideA = l * w
    val sideB = w * h
    val sideC = h * l
    2 * sideA + 2 * sideB + 2 * sideC + math.min(math.min(sideA, sideB), sideC)
  }

  def ribbonLength(l: Int, w: Int, h: Int): Int = {
    val ribbon = l * w * h
    val wrap = Seq(l, w, h).sorted.take(2).map(_ * 2).sum
    ribbon + wrap
  }

  private val pattern = """(\d+)x(\d+)x(\d+)""".r

  def parseDimentions(line: String): (Int, Int, Int) = {
    line match {
      case pattern(l, w, h) => (l.toInt, w.toInt, h.toInt)
    }
  }

  def main(args: Array[String]): Unit = {
    val puzzleInput = InputFetcher.fetchInput(2015, 2)
    println(s"Day 2 part one is ${partOne(puzzleInput)}")
    println(s"Day 2 part two is ${partTwo(puzzleInput)}")
  }

  def partOne(input: String): Int = {
    input.linesIterator.map(parseDimentions).map(surfaceArea).sum
  }

  def partTwo(input: String): Int = {
    input.linesIterator.map(parseDimentions).map(ribbonLength).sum
  }

}
