package dev.nheggoe
package aoc25

import util.{Benchmark, InputFetcher}

object Day06 {

  def parseInput(input: String): Seq[Seq[String]] = {
    input.strip.linesIterator.map(_.strip).map(_.split("\\s+")).toSeq.transpose
  }

  def main(args: Array[String]): Unit = {
    val puzzleInput = InputFetcher.fetchInput(2025, 6)
    val partOneRes = Benchmark.run("Part one")(partOne(puzzleInput)) // avg=188.15 μs
    println(s"Day 6 part one is $partOneRes")
  }

  def partOne(input: String): Long = {
    val instructions = parseInput(input)
    instructions.map { ins =>
      val nums = ins.take(ins.size - 1).map(_.toLong)
      val op = ins.last
      op match {
        case "*" => nums.product
        case "+" => nums.sum
      }
    }.sum
  }

}
