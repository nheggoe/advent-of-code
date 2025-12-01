package dev.nheggoe
package aoc24

import util.InputFetcher

object Day04 {
  private val puzzleInput = InputFetcher.fetchInput(2024, 4)

  def main(args: Array[String]): Unit = {
    println(s"Part one is ${partOne(puzzleInput)}")
    println(s"Part one is ${partTwo(puzzleInput)}")
  }

  case class Matrix(data: Seq[Seq[Char]]) {

    def diagonalsDownRight: Matrix = {
      val rows = data.size
      val cols = data.head.size

      Matrix(for { i <- -(cols - 1) until rows } yield {
        for {
          r <- data.indices
          c <- data.head.indices
          if r - c == i
        } yield data(r)(c)
      })
    }

    def diagonalsDownLeft: Matrix = {
      val rows = data.size
      val cols = data.head.size

      Matrix(for { i <- 0 until (rows + cols - 1) } yield {
        for {
          r <- data.indices
          c <- data.head.indices
          if r + c == i
        } yield data(r)(c)
      })
    }

    def countWord(target: String): Int = {
      Seq(
        countVertical(target),
        countHorizontal(target),
        countDiagonal(target)
      ).sum
    }

    def transpose: Matrix = Matrix(data.transpose)

    def countVertical(target: String): Int = {
      this.transpose.countHorizontal(target)
    }

    def countHorizontal(target: String): Int = {
      data
        .map { _.mkString("") }
        .map { line =>
          target.r.findAllIn(line).size + target.reverse.r.findAllIn(line).size
        }
        .sum
    }

    def countDiagonal(target: String): Int = {
      val left = this.diagonalsDownLeft
      val right = this.diagonalsDownRight

      left.countHorizontal(target) + right.countHorizontal(target)
    }

    def fromA(): Seq[Matrix] = {
      var matrices: Seq[Matrix] = Nil
      for (i <- data.indices) {
        for (j <- data.head.indices) {
          if (
            data(i)(j) == 'A'
            && i - 1 >= 0
            && data.indices.contains(i + 1)
            && j - 1 >= 0
            && data.head.indices.contains(j + 1)
          )
            val matrix = Matrix(
              for { a <- i - 1 to i + 1 } yield {
                for { b <- j - 1 to j + 1 } yield {
                  data(a)(b)
                }
              }
            )
            matrices = matrices ++ Seq(matrix)
        }
      }
      matrices
    }
  }

  object Matrix {
    def fromLines(lines: Seq[String]): Matrix = {
      Matrix(lines.map(_.toCharArray))
    }
  }

  def partOne(input: String): Int = {
    val matrix = Matrix.fromLines(input.split("\n"))
    matrix.countWord("XMAS")
  }

  def partTwo(input: String): Int = {
    val matrix = Matrix.fromLines(input.split("\n"))
    val target = "MAS"
    val matrices = matrix.fromA()
    matrices
      .map { m =>
        val left = m.diagonalsDownLeft.countHorizontal(target)
        val right = m.diagonalsDownRight.countHorizontal(target)
        left == 1 && right == 1
      }
      .count(_ == true)
  }
}
