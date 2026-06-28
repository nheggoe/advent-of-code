package dev.nheggoe.aoc15

import dev.nheggoe.aoc.AocDay

object Day01 extends AocDay(1) {

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
