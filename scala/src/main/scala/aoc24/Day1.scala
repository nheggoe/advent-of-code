package dev.nheggoe
package aoc24

import util.InputFetcher.fetchInput

/** @see `https://adventofcode.com/2024/day/1` */
object Day1:

  val input: String = fetchInput(2024, 1)

  def main(args: Array[String]): Unit =
    println(s"Part one is $partOne")
    println(s"Part two is $partTwo")

  private def leftAndRight: (Seq[Int], Seq[Int]) =
    val (a, b) = input
      .split('\n')
      .map(_.split("\\s+", 2))
      .map(token => (Integer.parseInt(token(0)), Integer.parseInt(token(1))))
      .unzip
    (a.sorted, b.sorted)

  private val (left, right) = leftAndRight

  private type TotalDistance = Int
  private def partOne: TotalDistance =
    left
      .zip(right)
      .map(tuple => math.abs(tuple._1 - tuple._2))
      .sum

  private type SimilarityScore = Int

  private def partTwo: SimilarityScore =
    left.map(n => right.count(_ == n) * n).sum
