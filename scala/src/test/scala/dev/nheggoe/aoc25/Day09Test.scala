package dev.nheggoe
package aoc25

import dev.nheggoe.aoc.Input
import dev.nheggoe.aoc25.Day09.{partOne, partTwo}
import munit.FunSuite

class Day09Test extends FunSuite {
  given Input = """7,1
                  |11,1
                  |11,7
                  |9,7
                  |9,5
                  |2,5
                  |2,3
                  |7,3""".stripMargin

  test("Part one example") {
    val expected = 50L
    assertEquals(partOne, expected)
  }

  test("Part two example".ignore) {
    val expected = 24L
    assertEquals(partTwo, expected)
  }

}
