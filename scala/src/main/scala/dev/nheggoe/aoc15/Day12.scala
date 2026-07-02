package dev.nheggoe.aoc15

import dev.nheggoe.aoc.{AocDay, Input}

object Day12 extends AocDay(12) {

  extension (json: ujson.Value)
    private def readNum: List[Int] =
      json match
        case ujson.Num(n) => n.toInt :: Nil
        case ujson.Obj(m) => m.values.readNum
        case ujson.Arr(a) => a.toList.flatMap(_.readNum)
        case _            => Nil

  override def partOne(using Input): Int = ujson.read(input).readNum.sum

  override def partTwo(using Input): Any = ???
}
