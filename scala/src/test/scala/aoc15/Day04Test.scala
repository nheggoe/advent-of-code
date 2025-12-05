package dev.nheggoe
package aoc15

import munit.FunSuite

class Day04Test extends FunSuite {

  test("Part one example") {
    val key = "abcdef"
    val expected = 609043
    assertEquals(Day04.partOne(key), expected)
  }

  test("Part one case 2") {
    val key = "pqrstuv"
    val expected = 1048970
    assertEquals(Day04.partOne(key), expected)
  }
}
