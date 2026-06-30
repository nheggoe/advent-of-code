package dev.nheggoe.aoc15

import dev.nheggoe.aoc.{AocDay, Input}

object Day08 extends AocDay(8) {

  extension (s: String)
    private def memLen: Int =
      def recur(chars: List[Char]): Int =
        chars match
          case Nil                           => 0
          case '\\' :: '\\' :: rest          => 1 + recur(rest)
          case '\\' :: '"' :: rest           => 1 + recur(rest)
          case '\\' :: 'x' :: _ :: _ :: rest => 1 + recur(rest)
          case _ :: rest                     => 1 + recur(rest)
      recur(s.drop(1).dropRight(1).toList)

  override def partOne(using Input): Any =
    val rawLength = lines.map(_.length).sum
    val memLength = lines.map(_.memLen).sum
    rawLength - memLength

  override def partTwo(using Input): Any = ???

}
