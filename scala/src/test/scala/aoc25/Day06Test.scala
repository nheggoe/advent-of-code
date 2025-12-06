package dev.nheggoe
package aoc25

import munit.{FunSuite, Location, TestOptions}

class Day06Test extends FunSuite {

  val input = """123 328  51 64
                | 45 64  387 23
                |  6 98  215 314
                |*   +   *   +  """.stripMargin

  test("Part one") {
    val expected = 4277556L
    assertEquals(Day06.partOne(input), expected)
  }
}
