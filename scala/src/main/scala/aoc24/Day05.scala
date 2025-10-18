package dev.nheggoe
package aoc24

import util.InputFetcher

import java.util.concurrent.ForkJoinPool
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

object Day05 {

  private val puzzleInput: String = InputFetcher.fetchInput(2024, 5)

  def main(args: Array[String]): Unit = {
    println(s"The answer to part one is: ${partOne(puzzleInput)}")
    println(s"The answer to part two is: ${partTwo(puzzleInput)}")
  }

  def parseInput(input: String): (Seq[String], Seq[String]) = {
    input.linesIterator.filterNot(_.isBlank).toSeq.partition(_.contains('|'))
  }

  def partOne(input: String): Int = {
    pageSeq(input)
      .filter(seq => isValid(seq, indexMap(input)))
      .map(seq => seq(seq.size / 2))
      .sum
  }

  def partTwo(input: String): Int = {
    val ruleIndex = indexMap(input)
    val pool = new ForkJoinPool(Runtime.getRuntime.availableProcessors())

    given ec: ExecutionContext = ExecutionContext.fromExecutor(pool)

    val pages = pageSeq(input).filterNot(seq => isValid(seq, ruleIndex))
    val futures = pages.map(seq => Future(fixSeq(seq, ruleIndex)))

    val fixed: Seq[Seq[Int]] =
      Await.result(Future.sequence(futures), Duration.Inf)
    fixed.map(seq => seq(seq.size / 2)).sum
  }

  private def fixSeq(
      nums: Seq[Int],
      ruleIndex: Map[Int, Set[Int]]
  ): Seq[Int] = {
    val mutableCopy = ArrayBuffer.from(nums)
    val size = nums.size

    for (i <- size - 1 to 1 by -1) {
      var mustComeBefore = ruleIndex.getOrElse(mutableCopy(i), Set.empty[Int])
      while (mutableCopy.iterator.slice(0, i).exists(mustComeBefore.contains)) {
        val temp = mutableCopy.remove(i)
        mutableCopy.prepend(temp)
        mustComeBefore = ruleIndex.getOrElse(mutableCopy(i), Set.empty[Int])
      }
    }
    mutableCopy.toSeq
  }

  private def pageSeq(input: String): Seq[Seq[Int]] = {
    val (_, pages) = parseInput(input)
    pages.map(_.split(',').map(_.toInt).toSeq)
  }

  private def indexMap(input: String): Map[Int, Set[Int]] = {
    val (rules, _) = parseInput(input)
    rules
      .map(_.split('|').map(_.toInt))
      .groupBy(_.head)
      .view
      .mapValues(_.map(_.last).toSet)
      .toMap
  }

  private def isValid(
      nums: Seq[Int],
      ruleIndex: Map[Int, Set[Int]]
  ): Boolean = {
    for { i <- nums.indices } {
      val mustComeAfter = ruleIndex.getOrElse(nums(i), Set.empty[Int])
      for { k <- 0 until i } {
        if (mustComeAfter.contains(nums(k))) {
          return false
        }
      }
    }
    true
  }
}
