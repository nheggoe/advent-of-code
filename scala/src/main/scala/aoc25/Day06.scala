package dev.nheggoe
package aoc25

import util.{Benchmark, InputFetcher}

object Day06 {

  def parseInput(input: String): Seq[Seq[String]] = {
    val spacing = input.linesIterator.toSeq.last
      .split(" (?=[*+])")
      .map(_.length + 1)

    val indices = spacing.scanLeft(0)(_ + _)

    input.linesIterator
      .map { line =>
        indices
          .zip(indices.tail)
          .map { (start, end) =>
            line.slice(start, end - 1)
          }
          .toSeq
      }
      .toSeq
      .transpose
  }

  def main(args: Array[String]): Unit = {
    val puzzleInput = InputFetcher.fetchInput(2025, 6)
    println(s"Day 6 part one is ${partOne(puzzleInput)}")
    println(s"Day 6 part two is ${partTwo(puzzleInput)}")
  }

  def partOne(input: String): Long = {
    val instructions = parseInput(input)
    instructions.map { ins =>
      val nums = ins.take(ins.size - 1).map(_.strip).map(_.toLong)
      val op = ins.last.strip
      op match {
        case "*" => nums.product
        case "+" => nums.sum
      }
    }.sum
  }

  def partTwo(input: String): Long = {
    val instructions = parseInput(input)
    instructions.map { ins =>
      val nums = ins
        .take(ins.size - 1)
        .map(_.reverse)
        .transpose
        .map(_.filter(_ != ' '))
        .map(_.mkString)
        .map(_.toLong)
      val op = ins.last.strip
      op match {
        case "*" => nums.product
        case "+" => nums.sum
      }
    }.sum
  }

}
