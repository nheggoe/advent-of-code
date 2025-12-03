package dev.nheggoe
package aoc25

import util.InputFetcher

object Day03 {

  def parseInput(input: String): Seq[Seq[Char]] = {
    input.linesIterator.collect(_.toSeq).toSeq
  }

  def findTheLargestJoltage(seq: Seq[Char]): Int = {
    val largestDigit = seq.max
    val index = seq.indexOf(largestDigit)
    if index != seq.indices.last then {
      val next = seq.slice(index + 1, seq.size).max
      Seq(largestDigit, next).mkString.toInt
    } else {
      val first = seq.takeWhile(_ != largestDigit).max
      Seq(first, largestDigit).mkString.toInt
    }
  }

  def main(args: Array[String]): Unit = {
    val puzzleInput = InputFetcher.fetchInput(2025, 3)
    println(s"Day 3 part one is ${partOne(puzzleInput)}")
  }

  def partOne(input: String): Int = {
    val lines = parseInput(input)
    lines.map(findTheLargestJoltage).sum
  }

}
