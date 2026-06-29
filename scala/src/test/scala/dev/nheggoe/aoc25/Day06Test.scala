package dev.nheggoe
package aoc25

import munit.FunSuite
import dev.nheggoe.aoc.Input

class Day06Test extends FunSuite {

  val input = """123 328  51 64 
                | 45 64  387 23 
                |  6 98  215 314
                |*   +   *   +  """.stripMargin
  given Input = input

  test("Part one") {
    val expected = 4277556L
    assertEquals(Day06.partOne, expected)
  }

  test("Part two") {
    val expected = 3263827L
    assertEquals(Day06.partTwo, expected)
  }

}
