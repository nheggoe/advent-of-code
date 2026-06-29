package dev.nheggoe.aoc25

import dev.nheggoe.aoc.{AocDay, Benchmark, Input, InputFetcher}

import scala.annotation.tailrec

object Day05 extends AocDay(5) {

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

  override def main(args: Array[String]): Unit = {
    given Input = InputFetcher.fetchInput
    logPartOne(Benchmark.run("Part one")(partOne))
    logPartTwo(Benchmark.run("Part two")(partTwo))
  }

  def partOne(using Input): Int = {
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
        acc: List[(Long, Long)] = Nil
    ): List[(Long, Long)] = (remaining, acc) match {
      case (Nil, _)                        => acc.reverse
      case (head :: tail, Nil)             => loop(tail, head :: Nil)
      case (head :: tail, last :: restAcc) =>
        if overlaps(head, last)
        then loop(tail, merge(head, last) :: restAcc)
        else loop(tail, head :: acc)
    }
    val sorted = ranges.sortBy(_._1).toList
    loop(sorted)
  }

  def overlaps(a: (Long, Long), b: (Long, Long)): Boolean = {
    val (aStart, aEnd) = a
    val (bStart, bEnd) = b
    bStart <= aEnd + 1 && aStart <= bEnd + 1
  }

  def merge(a: (Long, Long), b: (Long, Long)): (Long, Long) = {
    val (aStart, aEnd) = a
    val (bStart, bEnd) = b
    (math.min(aStart, bStart), math.max(aEnd, bEnd))
  }

  def partTwo(using Input): Long = {
    val (ranges, _) = parseInput(input)
    removeRedundantRanges(ranges)
      .map(range => range._2 - range._1 + 1)
      .sum
  }

}
