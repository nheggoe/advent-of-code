package dev.nheggoe
package aoc25

import util.InputFetcher

import scala.annotation.tailrec
import scala.util.chaining.scalaUtilChainingOps

object Day08 {

  def parseInput(input: String): Seq[Point] = {
    input.linesIterator.collect { case s"$x,$y,$z" =>
      Point(x.toInt, y.toInt, z.toInt)
    }.toSeq
  }

  case class Point(x: Int, y: Int, z: Int)

  def euclideanDistance(p: Point, q: Point): Double = {
    val pCord = p.productIterator.collect { case int: Int => int }
    val qCord = q.productIterator.collect { case int: Int => int }
    pCord
      .zip(qCord)
      .map(_ - _)
      .map(math.pow(_, 2))
      .sum
      .pipe(math.sqrt)
  }

  def main(args: Array[String]): Unit = {
    val puzzleInput = InputFetcher.fetchInput(2025, 8)
    println(s"Day 8 part one is ${partOne(puzzleInput, 1000)}")
    println(s"Day 8 part two is ${partTwo(puzzleInput)}")
  }

  def partOne(input: String, connection: Int): Long = {
    val points = parseInput(input)
    val indexes = points.zipWithIndex.toMap
    val unionFind = UnionFind(points.size)
    points
      .combinations(2)
      .map(c => (c.head, c.last))
      .toSeq
      .sortBy(euclideanDistance)
      .take(connection)
      .map((a, b) => (indexes(a), indexes(b)))
      .foreach(unionFind.union)
    unionFind.size.toSeq.sorted.reverse.take(3).product
  }

  def partTwo(input: String): Long = {
    val points = parseInput(input)
    val indexes = points.zipWithIndex.toMap
    val unionFind = UnionFind(points.size)
    val connections = points
      .combinations(2)
      .map(c => (c.head, c.last))
      .toSeq
      .sortBy(euclideanDistance)

    def allConnected: Boolean = {
      val head = unionFind.find(0)
      (1 until points.size).forall(unionFind.find(_) == head)
    }

    @tailrec
    def loop(remaining: List[(Point, Point)], idx: Int = 0): Int =
      remaining match {
        case (p_1, p_2) :: tail =>
          unionFind.union(indexes(p_1), indexes(p_2))
          if allConnected then idx
          else loop(tail, idx + 1)
        case Nil => -1
      }

    val ans = loop(connections.toList)
    connections(ans) match {
      case (Point(x_1, _, _), Point(x_2, _, _)) => x_1.toLong * x_2.toLong
    }
  }

  private class UnionFind(n: Int) {
    private val parent = Array.tabulate(n)(identity)
    val size: Array[Int] = Array.fill(n)(1)

    def find(x: Int): Int = {
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
