package dev.nheggoe.aoc24

import dev.nheggoe.aoc.{AocDay, Input}

/** @see `https://adventofcode.com/2024/day/1` */
object Day01 extends AocDay(1) {

  private def leftAndRight(input: String): (Seq[Int], Seq[Int]) = {
    val (a, b) = input
      .split('\n')
      .toSeq
      .map(_.split("\\s+", 2))
      .map(token => (Integer.parseInt(token(0)), Integer.parseInt(token(1))))
      .unzip
    (a.sorted, b.sorted)
  }

  def partOne(using Input): Int =
    val (left, right) = leftAndRight(input)
    left
      .zip(right)
      .map { case (left, right) =>
        math.abs(left - right)
      }
      .sum

  def partTwo(using Input): Int =
    val (left, right) = leftAndRight(input)
    left.map(n => right.count(_ == n) * n).sum
}
