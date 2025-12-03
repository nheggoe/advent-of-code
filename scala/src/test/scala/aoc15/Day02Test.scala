package dev.nheggoe
package aoc15

import munit.FunSuite

class Day02Test extends FunSuite {

  test("Part one case 1") {
    val input = "2x3x4"
    val expected = 58
    assertEquals(Day02.partOne(input), expected)
  }

  test("Part one case 2") {
    val input = "1x1x10"
    val expected = 43
    assertEquals(Day02.partOne(input), expected)
  }
}
