package dev.nheggoe
package aoc15

import munit.FunSuite

class Day06Test extends FunSuite {

  test("Part one case 1") {
    val input = "turn on 0,0 through 999,999"
    val expected = 1000 * 1000
    assertEquals(Day06.partOne(input), expected)
  }

  test("Part one toggle example") {
    val input = "toggle 0,0 through 999,0"
    // toggles the first row: 1000 lights
    val expected = 1000
    assertEquals(Day06.partOne(input), expected)
  }

  test("Part one turn off 4-lights example") {
    val input = "turn off 499,499 through 500,500"
    // only turns off 4 lights, but all are already off → still 0
    val expected = 0
    assertEquals(Day06.partOne(input), expected)
  }

}
