package dev.nheggoe
package aoc25

import util.InputFetcher

object Day03 {

  def parseInput(input: String): Seq[Seq[Char]] = {
    input.linesIterator.collect(_.toSeq).toSeq
  }

  def maxPossibleStart(seq: Seq[Char], n: Int): (Char, Int) = {
    seq.zipWithIndex
      .sortBy(_._1)(using Ordering[Char].reverse)
      .collectFirst {
        case (value, index) if index + n <= seq.size => (value, index)
      }
      .get
  }

  def findTheLargestJoltage(seq: Seq[Char], n: Int): Seq[Char] = {
    if n == 0 then return Nil
    val (value, index) = maxPossibleStart(seq, n)
    Seq(value) ++ findTheLargestJoltage(seq.slice(index + 1, seq.size), n - 1)
  }

  def main(args: Array[String]): Unit = {
    val puzzleInput = InputFetcher.fetchInput(2025, 3)
    println(s"Day 3 part one is ${partOne(puzzleInput)}")
    println(s"Day 3 part two is ${partTwo(puzzleInput)}")
  }

  def partOne(input: String): Long = {
    val lines = parseInput(input)
    lines
      .map(line => findTheLargestJoltage(line, 2).mkString.toLong)
      .sum
  }

  def partTwo(input: String): Long = {
    val lines = parseInput(input)
    lines
      .map(line => findTheLargestJoltage(line, 12).mkString.toLong)
      .sum
  }

}
