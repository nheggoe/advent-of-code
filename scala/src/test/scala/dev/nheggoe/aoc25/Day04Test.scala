package dev.nheggoe
package aoc25

import dev.nheggoe.aoc.Input
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
