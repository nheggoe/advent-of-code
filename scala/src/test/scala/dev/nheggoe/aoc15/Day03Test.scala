package dev.nheggoe
package aoc15

import munit.FunSuite

class Day03Test extends FunSuite {

  test("Part one case 1") {
    val input = ">"
    val expected = 2
    assertEquals(Day03.partOne(input), expected)
  }

  test("Part one case 2") {
    val input = "^>v<"
    val expected = 4
    assertEquals(Day03.partOne(input), expected)
  }

  test("Part one case 3") {
    val input = "^v^v^v^v^v"
    val expected = 2
    assertEquals(Day03.partOne(input), expected)
  }

  test("Part two case 1") {
    val input = "^v"
    val expected = 3
    assertEquals(Day03.partTwo(input), expected)
  }

  test("Part two case 2") {
    val input = "^>v<"
    val expected = 3
    assertEquals(Day03.partTwo(input), expected)
  }

  test("Part two case 3") {
    val input = "^v^v^v^v^v"
    val expected = 11
    assertEquals(Day03.partTwo(input), expected)
  }

}
