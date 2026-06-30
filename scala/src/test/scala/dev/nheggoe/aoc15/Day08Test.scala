package dev.nheggoe.aoc15

import dev.nheggoe.aoc.Input
import dev.nheggoe.aoc15.Day08.partOne
import munit.FunSuite

class Day08Test extends FunSuite {
  given input: Input = Input("""""
                               |"abc"
                               |"aaa\"aaa"
                               |"\x27"""".stripMargin)

  test("partOne") {
    val expected = 12
    assertEquals(partOne, expected)
  }
}
