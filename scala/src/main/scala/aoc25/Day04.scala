package dev.nheggoe
package aoc25

import util.InputFetcher

import scala.annotation.tailrec

object Day04 {

  case class Position(row: Int, column: Int) {
    def adjacentPositions: Set[Position] = {
      Set(
        Position(row - 1, column - 1),
        Position(row - 1, column),
        Position(row - 1, column + 1),
        Position(row, column - 1),
        Position(row, column + 1),
        Position(row + 1, column - 1),
        Position(row + 1, column),
        Position(row + 1, column + 1)
      )
    }
  }

  def parseInput(input: String): Set[Position] = {
    input.linesIterator.zipWithIndex.flatMap { case (line, row) =>
      line.zipWithIndex.collect {
        case (char, column) if char == '@' =>
          Position(row, column)
      }
    }.toSet
  }

  def main(args: Array[String]): Unit = {
    val puzzleInput = InputFetcher.fetchInput(2025, 4)
    println(s"Day 4 part one is ${partOne(puzzleInput)}")
    println(s"Day 4 part two is ${partTwo(puzzleInput)}")
  }

  def partOne(input: String): Int = {
    val rolls = parseInput(input)
    collectRemovableRolls(rolls).size
  }

  def partTwo(input: String): Int = {
    @tailrec
    def loop(rolls: Set[Position], acc: Int = 0): Int = {
      val removableRolls = collectRemovableRolls(rolls)
      if removableRolls.isEmpty then acc
      else loop(rolls -- removableRolls, acc + removableRolls.size)
    }
    loop(parseInput(input))
  }

  private def collectRemovableRolls(rolls: Set[Position]): Set[Position] = {
    rolls.collect {
      case position if rolls.intersect(position.adjacentPositions).size < 4 =>
        position
    }
  }
}
