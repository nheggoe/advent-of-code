package dev.nheggoe.aoc25

import dev.nheggoe.aoc.AocDay

object Day03 extends AocDay(3) {

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
