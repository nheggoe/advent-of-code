package dev.nheggoe
package aoc15

import munit.FunSuite
import dev.nheggoe.aoc.Input
import dev.nheggoe.aoc15.Day07.partOne

class Day07Test extends FunSuite {
  given Input = """123 -> x
                   |456 -> y
                   |x AND y -> d
                   |x OR y -> e
                   |x LSHIFT 2 -> f
                   |y RSHIFT 2 -> g
                   |NOT x -> h
                   |NOT y -> i""".stripMargin

  test("example part one") {
    val expected = Map(
      "d" -> 72,
      "e" -> 507,
      "f" -> 492,
      "g" -> 114,
      "h" -> 65412,
      "i" -> 65079,
      "x" -> 123,
      "y" -> 456
    )
    assertEquals(partOne, expected)
  }

}
