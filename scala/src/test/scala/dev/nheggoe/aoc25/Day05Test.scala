package dev.nheggoe
package aoc25

import munit.FunSuite

class Day05Test extends FunSuite {

  val input = """3-5
                |10-14
                |16-20
                |12-18
                |
                |1
                |5
                |8
                |11
                |17
                |32""".stripMargin

  test("Part one example") {
    val expected = 3
    assertEquals(Day05.partOne(input), expected)
  }

  test("Part two example") {
    val expected = 14L
    assertEquals(Day05.partTwo(input), expected)
  }

}
