package dev.nheggoe.aoc25

import dev.nheggoe.aoc.{AocDay, Input}

object Day06 extends AocDay(6) {

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

  def partOne(using Input): Long = {
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

  def partTwo(using Input): Long = {
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
