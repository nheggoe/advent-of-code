package dev.nheggoe
package aoc25

import util.InputFetcher

object Day07 {

  def parseInput(input: String): (Int, Seq[Seq[Char]]) = {
    val start = input.linesIterator.flatMap { line =>
      line.zipWithIndex.collectFirst {
        case (symbol, column) if symbol == 'S' => column
      }
    }.next
    (start, input.linesIterator.map(_.toSeq).toSeq)
  }

  private def simulateBeam(
      lines: Seq[Seq[Char]],
      initial: Vector[Long]
  ): (Seq[Long], Long) = {
    val width = lines.head.size
    lines.foldLeft(initial, 0L) { case ((rays, counter), line) =>
      val splitters = line.zipWithIndex.collect {
        case ('^', idx) if rays(idx) > 0L => idx
      }
      val res = splitters.foldLeft(rays) { (r, idx) =>
        val indexes = Seq(idx - 1, idx, idx + 1)
        val Seq(left, cur, right) = indexes
        val Seq(leftVal, curVal, rightVal) = indexes.map(r(_))
        r.updated(left, leftVal + curVal)
          .updated(right, rightVal + curVal)
          .updated(cur, 0)
      }
      (res, counter + splitters.size)
    }
  }

  def main(args: Array[String]): Unit = {
    val puzzleInput = InputFetcher.fetchInput(2025, 7)
    println(s"Day 7 part one is ${partOne(puzzleInput)}")
    println(s"Day 7 part two is ${partTwo(puzzleInput)}")
  }

  def partOne(input: String): Long = {
    val (start, lines) = parseInput(input)
    val width = lines.head.size
    val initial = Vector.fill(width)(0L).updated(start, 1L)
    simulateBeam(lines, initial)._2
  }

  def partTwo(input: String): Long = {
    val (start, lines) = parseInput(input)
    val width = lines.head.size
    val initial = Vector.fill(width)(0L).updated(start, 1L)
    simulateBeam(lines, initial)._1.sum
  }
}
