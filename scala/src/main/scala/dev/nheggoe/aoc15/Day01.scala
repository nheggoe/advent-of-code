package dev.nheggoe.aoc15

import dev.nheggoe.aoc.{AocDay, Input}

object Day01 extends AocDay(1) {

  override def partOne(using Input): Int =
    val (increase, decrease) = input.partition(_ == '(')
    increase.length - decrease.length

  override def partTwo(using Input): Int = {
    for char <- input
    yield if char == '(' then 1 else -1
  }.scan(0)(_ + _).takeWhile(_ != -1).length

}
