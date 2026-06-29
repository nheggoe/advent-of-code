package dev.nheggoe.aoc15
import dev.nheggoe.aoc.{AocDay, Input}

object Day03 extends AocDay(3) {

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

  def partOne(using Input): Int = {
    input.scanLeft(Position(0, 0))(_ next _).toSet.size
  }

  def partTwo(using Input): Int = {
    val (santa, roboSanta) = input.zipWithIndex.partition(_._2 % 2 == 0)
    val santaPosition = santa.map(_._1).scanLeft(Position(0, 0))(_ next _)
    val roboPosition = roboSanta.map(_._1).scanLeft(Position(0, 0))(_ next _)
    (santaPosition ++ roboPosition).toSet.size
  }

}
