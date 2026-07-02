package dev.nheggoe.aoc15

import dev.nheggoe.aoc.{AocDay, Input}

import scala.annotation.tailrec

object Day11 extends AocDay(11) {

  private lazy val validChars: Set[Char] =
    ('a' to 'z').toSet -- Set('i', 'o', 'l')

  extension (c: Char) def increment: Char = (c.toInt + 1).toChar

  extension (s: String) {
    private def isValid: Boolean =
      s.containsIncreasingStraight && s.allValidChar && s.containsTwoNonOverlappingPairs

    private def containsPair: Option[Char] = {
      @tailrec
      def recur(xs: List[Char]): Option[Char] = {
        xs match
          case Nil                   => None
          case x :: y :: _ if x == y => Some(x)
          case _ :: rest             => recur(rest)
      }
      recur(s.toList)
    }

    def containsTwoNonOverlappingPairs: Boolean = {
      for
        n <- LazyList.from(2 to s.length - 2)
        (fst, snd) = s.splitAt(n)
      yield (fst.containsPair, snd.containsPair)
    }.exists {
      case (Some(x), Some(y)) if x != y => true
      case (_, _)                       => false
    }

    def containsIncreasingStraight: Boolean = s
      .sliding(3)
      .exists(str => str(2) - str(1) == 1 && str(1) - str(0) == 1)

    def allValidChar: Boolean = s.forall(validChars.contains)

    def increment: String = {
      def recur(str: List[Char]): List[Char] = {
        str match
          case Nil         => Nil
          case 'z' :: rest => 'a' :: recur(rest)
          case c :: rest   => c.increment :: rest
      }
      recur(s.toList.reverse).reverse.mkString
    }

    def nextPassword: String =
      @tailrec
      def go(acc: String): String =
        if acc.isValid then acc else go(acc.increment)

      go(s)
  }

  override def partOne(using Input): String = input.nextPassword

  override def partTwo(using Input): Any = ???
}
