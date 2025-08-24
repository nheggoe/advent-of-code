package dev.nheggoe
package aoc24

import util.InputFetcher

import scala.util.matching.Regex

/** @see `https://adventofcode.com/2024/day/3` */
object Day3 {
  val input: String = InputFetcher.fetchInput(2024, 3)

  case class Memory(data: String):
    private val pattern: Regex = "mul\\((\\d+),(\\d+)\\)".r
    private val enable: Regex = "do\\(\\)".r
    private val disable: Regex = "don't\\(\\)".r
    def readInstruction(input: String = data): Long =
      val matches = pattern.findAllMatchIn(input)
      matches
        .map:
          case pattern(a, b) =>
            a.toLong * b.toLong
        .sum
    def readInstructionWithConditional: Long =
      val disabledIndex = disable
        .findAllMatchIn(data)
        .map: matched =>
          matched.end + 1
        .toSeq
      val enabledIndex = enable
        .findAllMatchIn(data)
        .map: matched =>
          matched.start
        .toSeq

      val newList = 0 +: enabledIndex
      val result = newList
        .zipAll(disabledIndex, 0, 0)
        .flatMap:
          case (a, b) if b == 0 => Seq(data.substring(a))
          case (a, b) if b > a  => Seq(data.substring(a, b))
          case (_, _)           => Nil
        .map:
          readInstruction

      result.sum

  def main(args: Array[String]): Unit =
    val memory = Memory(input.mkString)
    val partOne: Long = memory.readInstruction()
    println(s"Day 3 part one is $partOne")
    val partTwo = memory.readInstructionWithConditional
    println(s"Day 3 part two is $partTwo")
}
