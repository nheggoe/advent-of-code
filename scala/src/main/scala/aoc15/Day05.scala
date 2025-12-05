package dev.nheggoe
package aoc15

import util.InputFetcher

object Day05 {

  def main(args: Array[String]): Unit = {
    val puzzleInput = InputFetcher.fetchInput(2015, 5)
    println(s"Day 5 part one is ${partOne(puzzleInput)}")
    println(s"Day 5 part two is ${partTwo(puzzleInput)}")
  }

  def partOne(input: String): Int = {
    input.linesIterator.count(isNiceStringPartOne)
  }

  def partTwo(input: String): Int = {
    input.linesIterator.count(isNiceStringPartTwo)
  }

  def isNiceStringPartOne(input: String): Boolean = {
    val vowels = "aeiou".toSet
    val naughty = Set("ab", "cd", "pq", "xy")

    input.count(vowels.contains) >= 3
    && input.sliding(2).exists(s => s(0) == s(1))
    && !input.sliding(2).exists(naughty.contains)
  }

  def isNiceStringPartTwo(input: String): Boolean = {
    def containsOneLetterBetween = input.sliding(3).exists(s => s(0) == s(2))
    def containsDoublePair = input.sliding(2).zipWithIndex.exists { (pair, i) =>
      input.indexOf(pair, i + 2) >= 0
    }
    containsOneLetterBetween && containsDoublePair
  }

}
