package dev.nheggoe
package aoc24

import aoc24.Day04.{partOne, partTwo}

import munit.FunSuite
import dev.nheggoe.aoc.Input

class Day04Test extends FunSuite:

  private val exampleInput = """MMMSXXMASM
                               |MSAMXMSMSA
                               |AMXSXMAAMM
                               |MSAMASMSMX
                               |XMASAMXAMM
                               |XXAMMXXAMA
                               |SMSMSASXSS
                               |SAXAMASAAA
                               |MAMMMXMMMM
                               |MXMXAXMASX""".stripMargin
  given Input = exampleInput

  test("Day 4 Part One") {
    val expected = 18
    assertEquals(partOne, expected)
  }

  test("Day 4 part two") {
    val expected = 9
    assertEquals(partTwo, expected)
  }
