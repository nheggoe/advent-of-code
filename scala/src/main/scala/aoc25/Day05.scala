package dev.nheggoe
package aoc25

import util.{Benchmark, InputFetcher}

import scala.annotation.tailrec

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
      s"Day 5 part one is ${Benchmark.run("Part one")(partOne(puzzleInput))}" // avg=132.05 μs
    )
    println(
      s"Day 5 part one is ${Benchmark.run("Part two")(partTwo(puzzleInput))}" // avg=62.59 μs
    )
  }

  def partOne(input: String): Int = {
    val (ranges, ingredientIds) = parseInput(input)
    val uniqueRanges = removeRedundantRanges(ranges)
    ingredientIds.count(id =>
      uniqueRanges.exists(range => range._1 <= id && id <= range._2)
    )
  }

  def removeRedundantRanges(ranges: Seq[(Long, Long)]): Seq[(Long, Long)] = {
    @tailrec
    def loop(
        remaining: List[(Long, Long)],
        acc: List[(Long, Long)]
    ): List[(Long, Long)] = (remaining, acc) match {
      case (Nil, _)                        => acc.reverse
      case (head :: tail, Nil)             => loop(tail, head :: Nil)
      case (head :: tail, last :: restAcc) =>
        if overlaps(head, last)
        then loop(tail, merge(head, last) :: restAcc)
        else loop(tail, head :: acc)
    }
    val sorted = ranges.sortBy(_._1).toList
    loop(sorted, Nil)
  }

  def overlaps(a: (Long, Long), b: (Long, Long)): Boolean = {
    val (aStart, aEnd) = a
    val (bStart, bEnd) = b
    bStart <= aEnd && aStart <= bEnd
  }

  def merge(a: (Long, Long), b: (Long, Long)): (Long, Long) = {
    val (aStart, aEnd) = a
    val (bStart, bEnd) = b
    (math.min(aStart, bStart), math.max(aEnd, bEnd))
  }

  def partTwo(input: String): Long = {
    val (ranges, _) = parseInput(input)
    removeRedundantRanges(ranges)
      .map(range => range._2 - range._1 + 1)
      .sum
  }

}
