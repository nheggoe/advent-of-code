package dev.nheggoe
package aoc25

import munit.FunSuite

class Day01Test extends FunSuite {
  val puzzleInput: String = """L68
                              |L30
                              |R48
                              |L5
                              |R60
                              |L55
                              |L1
                              |L99
                              |R14
                              |L82""".stripMargin

  test("Update Position") {
    val nums = Seq(-68, -30, 48, -5, 60, -55)
    val res = nums.fold(50)(Day01.updatePosition)
    val expected = 0
    assertEquals(res, expected)
  }

  test("Part one") {
    val expected = 3
    assertEquals(Day01.partOne(puzzleInput), expected)
  }

  test("Part two") {
    val expected = 6
    assertEquals(Day01.partTwo(puzzleInput), expected)
  }
}
