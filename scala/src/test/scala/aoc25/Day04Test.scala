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
 
}
