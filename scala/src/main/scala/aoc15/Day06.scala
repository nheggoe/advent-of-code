package dev.nheggoe
package aoc15

import util.InputFetcher

object Day06 {

  case class Grid(
      lights: Array[Array[Int]] = Array.fill(1000)(Array.fill(1000)(0))
  ) {

    private val instructionPattern = """(.+) (\d+),(\d+) \w+ (\d+),(\d+)""".r

    def updateCell(rs: Int, cs: Int, re: Int, ce: Int)(f: Int => Int): Unit = {
      for {
        row <- rs to re
        col <- cs to ce
      } lights(row)(col) = f(lights(row)(col))
    }

    def execInsPartOne(instruction: String): Unit = instruction match {
      case instructionPattern(op, rs, cs, re, ce) =>
        val rStart = rs.toInt
        val cStart = cs.toInt
        val rEnd = re.toInt
        val cEnd = ce.toInt
        val f: Int => Int = op match {
          case "turn on"  => _ => 1
          case "turn off" => _ => 0
          case "toggle"   => state => if state == 1 then 0 else 1
          case _          => identity
        }
        updateCell(rStart, cStart, rEnd, cEnd)(f)
    }

    def execInsPartTwo(instruction: String): Unit = instruction match {
      case instructionPattern(op, rs, cs, re, ce) =>
        val rStart = rs.toInt
        val cStart = cs.toInt
        val rEnd = re.toInt
        val cEnd = ce.toInt
        val f: Int => Int = op match {
          case "turn on"  => _ + 1
          case "toggle"   => _ + 2
          case "turn off" => state => math.max(0, state - 1)
          case _          => identity
        }
        updateCell(rStart, cStart, rEnd, cEnd)(f)
    }

    def countLit: Int = lights.flatten.count(_ == 1)
    def totalBrightness: Long = lights.flatten.map(_.toLong).sum
  }

  def main(args: Array[String]): Unit = {
    val puzzleInput = InputFetcher.fetchInput(2015, 6)
    println(s"Day 6 part one is ${partOne(puzzleInput)}")
    println(s"Day 6 part two is ${partTwo(puzzleInput)}")
  }

  def partOne(input: String): Int = {
    val grid = Grid()
    input.linesIterator.foreach(grid.execInsPartOne)
    grid.countLit
  }

  def partTwo(input: String): Long = {
    val grid = Grid()
    input.linesIterator.foreach(grid.execInsPartTwo)
    grid.totalBrightness
  }

}
