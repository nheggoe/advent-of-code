package dev.nheggoe
package aoc25

import munit.FunSuite
import dev.nheggoe.aoc.Input

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
  given Input = input

  test("part one example") {
    val expected = 13
    assertEquals(Day04.partOne, expected)
  }

  test("Part two example") {
    val expected = 43
    assertEquals(Day04.partTwo, expected)
  }

}
