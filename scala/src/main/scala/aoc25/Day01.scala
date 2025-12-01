package dev.nheggoe
package aoc25

import util.InputFetcher

import scala.util.matching.Regex

object Day01 {

  private val pattern: Regex = """(\w)(\d+)""".r

  def updatePosition(startingPosition: Int, rotation: Int): Int = {
    val result = (startingPosition + rotation) % 100
    if result < 0
    then result + 100
    else result
  }

  def countTimesPassZero(
      startingPosition: Int,
      finalPosition: Int,
      rotation: Int
  ): Int = {
    val roundTrip = math.abs(rotation) / 100
    if (rotation < 0 && finalPosition > startingPosition && startingPosition != 0)
      || (rotation > 0 && finalPosition < startingPosition)
      || (startingPosition != 0 && finalPosition == 0)
    then roundTrip + 1
    else roundTrip
  }

  def stringToSeq(input: String): Seq[Int] = {
    input.linesIterator.collect {
      case pattern("L", num) => -num.toInt
      case pattern("R", num) => num.toInt
    }.toSeq
  }

  def main(args: Array[String]): Unit = {
    val puzzleInput = InputFetcher.fetchInput(2025, 1)
    println(s"Day 1 part one is ${partOne(puzzleInput)}")
    println(s"Day 1 part two is ${partTwo(puzzleInput)}")
  }

  def partOne(input: String): Int = {
    val nums = stringToSeq(input)
    nums.scan(50)(updatePosition).count(_ == 0)
  }

  def partTwo(input: String): Int = {
    val rotations = stringToSeq(input)
    val positions = rotations.scanLeft(50)(updatePosition)
    positions
      .sliding(2)
      .zip(rotations)
      .collect { case (Seq(start, end), rotation) =>
        countTimesPassZero(start, end, rotation)
      }
      .sum
  }
}
