package dev.nheggoe
package aoc24

import util.InputFetcher.fetchInput

import scala.annotation.tailrec

object Day2 {
  val input: Seq[Seq[Int]] =
    fetchInput(2024, 2)
      .map(_.split("\\s+"))
      .map(_.map(Integer.parseInt).toSeq)

  def main(args: Array[String]): Unit = {
    println(s"Day 2 part one is: ${partOne(input)}")
    println(s"Day 2 part two is: ${partTwo(input)}")
  }

  def partOne(input: Seq[Seq[Int]]): Int = {
    input.map(isSafe).count(_ == true)
  }

  def partTwo(input: Seq[Seq[Int]]): Int = {
    input
      .map(isSafeWithTolerance(_))
      .count(_ == true)
  }

  def isSafeWithTolerance(levels: Seq[Int], tolerance: Int = 1): Boolean = {
    @tailrec
    def loop(levels: Seq[Int], iteration: Int = 0): Boolean =
      if iteration > tolerance then false
      else if levels.combinations(levels.size - iteration).exists(isSafe) then
        true
      else loop(levels, iteration + 1)
    loop(levels)
  }

  def isSafe(levels: Seq[Int]): Boolean = {
    def isSafeIncreasing(window: Seq[Int]): Boolean = {
      (1 to 3).contains(difference(window))
    }
    def isSafeDecreasing(window: Seq[Int]): Boolean = {
      (-3 to -1).contains(difference(window))
    }
    def difference(window: Seq[Int]): Int = {
      assert(window.size == 2)
      window.last - window.head
    }

    levels.sliding(2).forall(isSafeIncreasing)
    || levels.sliding(2).forall(isSafeDecreasing)
  }
}
