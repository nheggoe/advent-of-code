package dev.nheggoe.aoc25

import dev.nheggoe.aoc.AocDay

import scala.collection.immutable.NumericRange

object Day02 extends AocDay(2) {

  def parseInput(input: String): Array[NumericRange.Inclusive[BigInt]] = {
    input.strip
      .split(",")
      .map(_.strip)
      .collect {
        case range if range.contains("-") =>
          range.split("-", 2) match {
            case Array(start, end) => BigInt(start) to BigInt(end)
          }
      }
  }

  def partOne(input: String): BigInt = {
    parseInput(input).flatMap(_.filter(isRepeatedTwice)).sum
  }

  def isRepeatedTwice(id: BigInt): Boolean = {
    val stringId = id.toString
    val length = stringId.length
    if length % 2 != 0 then false
    else {
      val group = stringId.grouped(length / 2).toSeq
      group.forall(_ == group.head)
    }
  }

  def partTwo(input: String): BigInt = {
    parseInput(input).flatMap(_.filter(isInvalidId)).sum
  }

  def isInvalidId(id: BigInt): Boolean = {
    val stringId = id.toString
    val length = stringId.length
    val range = length / 2 to 1 by -1
    range.exists { len =>
      if length % len != 0 then false
      else {
        val group = stringId.grouped(len).toSeq
        group.forall(_ == group.head)
      }
    }
  }

}
