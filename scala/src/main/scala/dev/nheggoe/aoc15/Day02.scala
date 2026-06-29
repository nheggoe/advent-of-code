package dev.nheggoe.aoc15
import dev.nheggoe.aoc.{AocDay, Input}

object Day02 extends AocDay(2) {

  def surfaceArea(l: Int, w: Int, h: Int): Int = {
    val sides = Seq(l * w, w * h, h * l)
    sides.map(_ * 2).sum + sides.min
  }

  def ribbonLength(l: Int, w: Int, h: Int): Int = {
    val ribbon = l * w * h
    val wrap = Seq(l, w, h).sorted.take(2).map(_ * 2).sum
    ribbon + wrap
  }

  def parseDimentions(line: String): (Int, Int, Int) = {
    val pattern = """(\d+)x(\d+)x(\d+)""".r
    line match {
      case pattern(l, w, h) => (l.toInt, w.toInt, h.toInt)
    }
  }

  def partOne(using Input): Int = {
    input.linesIterator.map(parseDimentions).map(surfaceArea).sum
  }

  def partTwo(using Input): Int = {
    input.linesIterator.map(parseDimentions).map(ribbonLength).sum
  }

}
