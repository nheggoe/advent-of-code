package dev.nheggoe
package aoc24

import munit.FunSuite

class Day06Test extends FunSuite {
  private val puzzleIn = """....#.....
                           |.........#
                           |..........
                           |..#.......
                           |.......#..
                           |..........
                           |.#..^.....
                           |........#.
                           |#.........
                           |......#...
                           |""".stripMargin
  test("Part one example") {
    val expected = 41
    assertEquals(Day06.partOne(puzzleIn), expected)
  }
  test("Part two example") {
    val expected = 6
    assertEquals(Day06.partTwo(puzzleIn), expected)
  }

}
