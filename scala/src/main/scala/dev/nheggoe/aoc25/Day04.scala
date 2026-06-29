package dev.nheggoe.aoc25

import dev.nheggoe.aoc.{AocDay, Input}

import scala.annotation.tailrec

object Day04 extends AocDay(4) {

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

  def partOne(using Input): Int = {
    val rolls = parseInput(input)
    collectRemovableRolls(rolls).size
  }

  def partTwo(using Input): Int = {
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
