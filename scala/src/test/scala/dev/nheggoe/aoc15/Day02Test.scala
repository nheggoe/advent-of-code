package dev.nheggoe
package aoc15

import dev.nheggoe.aoc.Input
import munit.FunSuite

class Day02Test extends FunSuite {

  test("Part one case 1") {
    given Input = "2x3x4"
    val expected = 58
    assertEquals(Day02.partOne, expected)
  }

  test("Part one case 2") {
    given Input = "1x1x10"
    val expected = 43
    assertEquals(Day02.partOne, expected)
  }

  test("Part two case 1") {
    given Input = "2x3x4"
    val expected = 34
    assertEquals(Day02.partTwo, expected)
  }

  test("Part two case 2") {
    given Input = "1x1x10"
    val expected = 14
    assertEquals(Day02.partTwo, expected)
  }
}
