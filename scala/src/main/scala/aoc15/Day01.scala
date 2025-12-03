package dev.nheggoe
package aoc15

import util.InputFetcher

object Day01 {

  def main(args: Array[String]): Unit = {
    val puzzleInput = InputFetcher.fetchInput(2015, 1)
    println(s"Day 1 part one is ${partOne(puzzleInput)}")
    println(s"Day 1 part two is ${partTwo(puzzleInput)}")
  }

  def partOne(input: String): Int = {
    val (increase, decrease) = input.partition(_ == '(')
    increase.length - decrease.length
  }

  def partTwo(input: String): Int = {
    val positions =
      input.map(char => if char == '(' then 1 else -1).scan(0)(_ + _)
    positions.takeWhile(_ != -1).size
  }

}
