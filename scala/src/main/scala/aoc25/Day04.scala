package dev.nheggoe
package aoc25

import util.InputFetcher

object Day04 {

  case class Position(row: Int, column: Int) {
    def adjacentPositions(rowBound: Int, columnBound: Int): Set[Position] = {
      Set(
        Position(row - 1, column - 1),
        Position(row - 1, column),
        Position(row - 1, column + 1),
        Position(row, column - 1),
        Position(row, column + 1),
        Position(row + 1, column - 1),
        Position(row + 1, column),
        Position(row + 1, column + 1)
      ).filter(_.row >= 0)
        .filter(_.row < rowBound)
        .filter(_.column >= 0)
        .filter(_.column < columnBound)
    }
  }

  def parseInput(lines: Seq[String]): Set[Position] = {
    lines.zipWithIndex.flatMap { case (line, row) =>
      line.zipWithIndex.collect {
        case (char, column) if char == '@' =>
          Position(row, column)
      }
    }.toSet
  }

  def main(args: Array[String]): Unit = {
    val puzzleInput = InputFetcher.fetchInput(2025, 4)
    println(s"Day 4 part one is ${partOne(puzzleInput)}")
  }

  def partOne(input: String): Int = {
    val lines = input.linesIterator.toSeq
    val rows = lines.length
    val columns = lines.head.length
    val rolls = parseInput(lines)
    rolls.count { position =>
      val adjacentInRolls =
        rolls.intersect(position.adjacentPositions(rows, columns))
      adjacentInRolls.size < 4
    }
  }

}
