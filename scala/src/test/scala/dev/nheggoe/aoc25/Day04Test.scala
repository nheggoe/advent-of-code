package dev.nheggoe
package aoc25

import munit.FunSuite

class Day04Test extends FunSuite {

  val input = """..@@.@@@@.
                |@@@.@.@.@@
                |@@@@@.@.@@
                |@.@@@@..@.
                |@@.@@@@.@@
                |.@@@@@@@.@
                |.@.@.@.@@@
                |@.@@@.@@@@
                |.@@@@@@@@.
                |@.@.@@@.@.""".stripMargin

  test("part one example") {
    val expected = 13
    assertEquals(Day04.partOne(input), expected)
  }

  test("Part two example") {
    val expected = 43
    assertEquals(Day04.partTwo(input), expected)
  }

}
