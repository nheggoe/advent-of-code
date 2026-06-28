package dev.nheggoe
package aoc24

import aoc24.Day03.{partOne, partTwo}

class Day03Test extends munit.FunSuite:

  val exampleInput =
    "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"

  test("Day 3 Part One") {
    val expected: Long = 161
    assertEquals(partOne(exampleInput), expected)
  }

  test("Day 3 Part Two") {
    val expected: Long = 48
    assertEquals(partTwo(exampleInput), expected)
  }
