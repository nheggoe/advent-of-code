package dev.nheggoe
package aoc25

import munit.FunSuite

class Day02Test extends FunSuite {
  val puzzleInput = """11-22,95-115,998-1012,1188511880-1188511890,222220-222224,
                      |1698522-1698528,446443-446449,38593856-38593862,565653-565659,
                      |824824821-824824827,2121212118-2121212124""".stripMargin

  test("Part one example") {
    val expected = BigInt(1227775554)
    assertEquals(Day02.partOne(puzzleInput), expected)
  }

  test("Range 11-22") {
    val range = 11 to 22
    val expected = 11 + 22
    val actual = range.filter(Day02.isRepeatedTwice).sum
    assertEquals(actual, expected)
  }

  test("Range 95-115") {
    val range = 95 to 115
    val expected = 99
    val actual = range.filter(Day02.isRepeatedTwice).sum
    assertEquals(actual, expected)
  }

  test("Part two example") {
    val expected = BigInt("4174379265")
    assertEquals(Day02.partTwo(puzzleInput), expected)
  }
}
