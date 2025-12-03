package dev.nheggoe
package aoc25

import munit.FunSuite

class Day03Test extends FunSuite {
  val puzzleInput = """987654321111111
                      |811111111111119
                      |234234234234278
                      |818181911112111""".stripMargin

  test("Part one example") {
    val expected = 357L
    assertEquals(Day03.partOne(puzzleInput), expected)
  }

  test("Part two example") {
    val expected = 3121910778619L
    assertEquals(Day03.partTwo(puzzleInput), expected)
  }
}
