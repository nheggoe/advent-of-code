package dev.nheggoe
package aoc15

import util.InputFetcher

object Day03 {

  case class Position(row: Int, column: Int) {
    infix def next(direction: Char): Position = {
      direction match {
        case '<' => this.copy(column = column - 1)
        case '>' => this.copy(column = column + 1)
        case '^' => this.copy(row = row - 1)
        case 'v' => this.copy(row = row + 1)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val puzzleInput = InputFetcher.fetchInput(2015, 3)
    println(s"Day 3 part one is ${partOne(puzzleInput)}")
  }

  def partOne(input: String): Int = {
    input.scanLeft(Position(0, 0))(_ next _).toSet.size
  }

}
