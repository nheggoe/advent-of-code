package dev.nheggoe.aoc25

import dev.nheggoe.aoc.AocDay

object Day09 extends AocDay(9) {

  case class Point(x: Long, y: Long)

  def area(p: Point, q: Point): Long = {
    val (Point(x_1, y_1), Point(x_2, y_2)) = (p, q)
    (math.abs(x_2 - x_1) + 1) * (math.abs(y_2 - y_1) + 1)
  }

  def parseInput(input: String): Seq[Point] = {
    input.linesIterator.collect { case s"$x,$y" =>
      Point(x.toInt, y.toInt)
    }.toSeq
  }

  def partOne(input: String): Long = {
    val points = parseInput(input)
    points
      .combinations(2)
      .map(c => (c.head, c.last))
      .map(area)
      .max
  }

  def partTwo(input: String): Long = {
    val points = parseInput(input)
    val isInHexagonPred = isInHexagon(points)
    ???
  }

  def raycast(point: Point): LazyList[Point] = {
    LazyList.iterate(point)(p => p.copy(x = p.x + 1))
  }

  def isInHexagon(points: Seq[Point])(point: Point): Boolean = {
    points.zip(points.tail :+ points.head).count { (start, end) =>
      val (Point(x_1, y_1), Point(x_2, y_2)) = (start, end)
      val Point(x, y) = point
      math.min(y_1, y_2) <= y && y <= math.max(y_1, y_2)
      // && raycast(point).takeWhile(_.x <= math.max(x_1, x_2)).count(_.)
    } % 2 != 0
    ???
  }

}
