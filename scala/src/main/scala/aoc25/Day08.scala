package dev.nheggoe
package aoc25

import util.InputFetcher

object Day08 {

  def parseInput(input: String): Seq[Point] = {
    input.linesIterator.collect { case s"$x,$y,$z" =>
      Point(x.toInt, y.toInt, z.toInt)
    }.toSeq
  }

  case class Point(x: Int, y: Int, z: Int) {
    override def toString: String = s"($x, $y, $z)"
    override def equals(obj: Any): Boolean = obj match {
      case Point(otherX, otherY, otherZ) =>
        x == otherX && y == otherY && z == otherZ
      case _ => false
    }
    def euclideanDistance(other: Point): Double = {
      val p = Seq(x, y, z)
      val q = Seq(other.x, other.y, other.z)
      math.sqrt(p.zip(q).map(_ - _).map(math.pow(_, 2)).sum)
    }
  }

  def main(args: Array[String]): Unit = {
    val puzzleInput = InputFetcher.fetchInput(2025, 8)
    println(s"Day 8 part one is ${partOne(puzzleInput, 1000)}")
  }

  def selections[A](xs: List[A]): List[(A, List[A])] = xs match {
    case Nil          => Nil
    case head :: tail =>
      (head, tail) :: selections(tail).map { case (a, rest) =>
        (a, head :: rest)
      }
  }

  def partOne(input: String, connection: Int): Long = {
    val points = parseInput(input)
    val indexes = points.zipWithIndex.toMap
    val unionFind = UnionFind(points.size)
    points
      .combinations(2)
      .toSeq
      .sortBy { case Seq(p, q) => p.euclideanDistance(q) }
      .take(connection)
      .foreach { case Seq(p, q) => unionFind.union(indexes(p), indexes(q)) }
    unionFind.size.toSeq.sorted.reverse.take(3).product
  }

  private class UnionFind(n: Int) {
    private val parent = Array.tabulate(n)(identity)
    val size: Array[Int] = Array.fill(n)(1)

    private def find(x: Int): Int = {
      if parent(x) != x then parent(x) = find(parent(x))
      parent(x)
    }

    def union(x: Int, y: Int): Unit = {
      val px = find(x)
      val py = find(y)
      if px == py then return

      if size(px) < size(py) then {
        parent(px) = py
        size(py) += size(px)
      } else {
        parent(py) = px
        size(px) += size(py)
      }
    }
  }

}
