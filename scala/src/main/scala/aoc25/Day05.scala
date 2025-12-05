package dev.nheggoe
package aoc25

import util.{Benchmark, InputFetcher}

object Day05 {

  def parseInput(
      input: String
  ): (Seq[(Long, Long)], Seq[Long]) = {
    val ranges = input.linesIterator
      .takeWhile(_.nonEmpty)
      .map(_.split("-", 2))
      .collect { case Array(start, end) =>
        (start.toLong, end.toLong)
      }
      .toSeq
    val ingredientIds = input.linesIterator
      .dropWhile(_.nonEmpty)
      .drop(1)
      .map(_.toLong)
      .toSeq
    (ranges, ingredientIds)
  }

  def main(args: Array[String]): Unit = {
    val puzzleInput = InputFetcher.fetchInput(2025, 5)
    println(
      s"Day 5 part one is ${Benchmark.run("Part one")(partOne(puzzleInput))}" // avg=0.39 ms
    )
  }

  def partOne(input: String): Int = {
    val (ranges, ingredientIds) = parseInput(input)
    ingredientIds.count(id =>
      ranges.exists(range => range._1 <= id && id <= range._2)
    )
  }

}
