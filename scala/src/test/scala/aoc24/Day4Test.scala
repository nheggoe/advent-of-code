package dev.nheggoe
package aoc24

import aoc24.Day4.{partOne, partTwo}

import munit.FunSuite

class Day4Test extends FunSuite:

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

  test("Day 4 Part One") {
    val expected = 18
    assertEquals(partOne(exampleInput), expected)
  }

  test("Day 4 part two") {
    val expected = 9
    assertEquals(partTwo(exampleInput), expected)
  }
