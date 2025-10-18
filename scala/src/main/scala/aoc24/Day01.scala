package dev.nheggoe
package aoc24

import util.InputFetcher.fetchInput

/** @see `https://adventofcode.com/2024/day/1` */
object Day01 {

  val input: String = fetchInput(2024, 1)

  def main(args: Array[String]): Unit = {
    println(s"Part one is $partOne")
    println(s"Part two is $partTwo")
  }

  private def leftAndRight: (Seq[Int], Seq[Int]) = {
    val (a, b) = input
      .split('\n')
      .map(_.split("\\s+", 2))
      .map(token => (Integer.parseInt(token(0)), Integer.parseInt(token(1))))
      .unzip
    (a.sorted, b.sorted)
  }

  private val (left, right) = leftAndRight

  private def partOne: Int =
    left
      .zip(right)
      .map { case (left, right) =>
        math.abs(left - right)
      }
      .sum

  private def partTwo: Int =
    left.map(n => right.count(_ == n) * n).sum
}
