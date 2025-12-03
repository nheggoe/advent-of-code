package dev.nheggoe
package aoc15

import util.InputFetcher

object Day03 {

  case class Position(row: Int, column: Int) {
    infix def next(direction: Char): Position = {
      direction match {
        case '<' => copy(column = column - 1)
        case '>' => copy(column = column + 1)
        case '^' => copy(row = row - 1)
        case 'v' => copy(row = row + 1)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val puzzleInput = InputFetcher.fetchInput(2015, 3)
    println(s"Day 3 part one is ${partOne(puzzleInput)}")
    println(s"Day 3 part two is ${partTwo(puzzleInput)}")
  }

  def partOne(input: String): Int = {
    input.scanLeft(Position(0, 0))(_ next _).toSet.size
  }

  def partTwo(input: String): Int = {
    val (santa, roboSanta) = input.zipWithIndex.partition(_._2 % 2 == 0)
    val santaPosition = santa.map(_._1).scanLeft(Position(0, 0))(_ next _)
    val roboPosition = roboSanta.map(_._1).scanLeft(Position(0, 0))(_ next _)
    (santaPosition ++ roboPosition).toSet.size
  }

}
