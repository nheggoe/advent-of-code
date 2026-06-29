package dev.nheggoe.aoc15

import dev.nheggoe.aoc.{AocDay, Input}

import scala.util.chaining.scalaUtilChainingOps

object Day07 extends AocDay(7):
  def partOne(using Input): Any = input.linesIterator.tokens
  def partTwo(using Input): Any = ???

  enum Gates:
    case And, Or, Not
    case LShift(n: Int)
    case RShit(n: Int)
  end Gates

  case class State(x: Int)

  var state = State(0)

  extension (ss: Iterator[String])
    private def tokens: Iterator[(String, String)] =
      ss.map(s => s.split("->").toSeq)
        .tap(seq => { println(seq.mkString); assert(seq.length == 2) })
        .map:
          case Seq(l, r) => (l, r)

  extension (tokens: Seq[String]) private def state = ???

end Day07
