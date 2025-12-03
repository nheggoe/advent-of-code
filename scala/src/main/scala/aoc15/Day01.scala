package dev.nheggoe
package aoc15

import util.InputFetcher

object Day01 {

  def main(args: Array[String]): Unit = {
    val puzzleInput = InputFetcher.fetchInput(2015, 1)
    println(s"Day 1 part one is ${partOne(puzzleInput)}")
  }

  def partOne(input: String): Int = {
    val (increase, decrease) = input.partition(_ == '(')
    increase.length - decrease.length
  }

}
