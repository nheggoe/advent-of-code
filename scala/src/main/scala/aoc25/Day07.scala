package dev.nheggoe
package aoc25

import util.InputFetcher

object Day07 {

  case class TachyonManifold(diagram: Seq[Seq[Char]]) {

    def withInBounds(point: Point): Boolean = {
      point.row >= 0 && point.row < diagram.length
      && point.column >= 0 && point.column < diagram.head.length
    }

    def splitters: Set[Point] = {
      val splitters = for {
        (line, row) <- diagram.zipWithIndex
        (symbol, col) <- line.zipWithIndex
        if symbol == '^'
      } yield Point(row, col)
      splitters.toSet
    }

    def next(points: Set[Point]): Set[Point] = points
      .flatMap(point => point.next(diagram(point.row)(point.column)))
      .filter(withInBounds)

  }

  case class Point(row: Int, column: Int) {
    def next(symbol: Char): Set[Point] = {
      if symbol == '^' then
        Set(Point(row + 1, column - 1), Point(row + 1, column + 1))
      else Set(Point(row + 1, column))
    }
  }

  def parseInput(input: String): (Point, TachyonManifold) = {
    val start = input.linesIterator.zipWithIndex.flatMap { (line, row) =>
      line.zipWithIndex.collectFirst {
        case (symbol, column) if symbol == 'S' => Point(row, column)
      }
    }.next
    (start, TachyonManifold(input.linesIterator.map(_.toSeq).toSeq))
  }

  def main(args: Array[String]): Unit = {
    val puzzleInput = InputFetcher.fetchInput(2025, 7)
    println(s"Day 7 part one is ${partOne(puzzleInput)}")
  }

  def partOne(input: String): Int = {
    val (start, manifold) = parseInput(input)
    val states = manifold.diagram.head.indices
      .drop(1)
      .foldLeft(Set(start))((points, _) => points ++ manifold.next(points))
    manifold.splitters.count { splitter =>
      states.contains(Point(splitter.row - 1, splitter.column))
    }
  }

}
