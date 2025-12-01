package dev.nheggoe
package aoc25

import util.InputFetcher

import scala.util.matching.Regex

object Day01 {

  private val pattern: Regex = """(\w)(\d+)""".r

  def updatePosition(startingPosition: Int, rotation: Int): Int = {
    var result = startingPosition + rotation
    result %= 100
    if result < 0
    then result + 100
    else result
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
  }

  def partOne(input: String): Int = {
    val nums = stringToSeq(input)
    nums.scan(50)(updatePosition).count(_ == 0)
  }
}
