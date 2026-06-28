package dev.nheggoe
package aoc15

import munit.FunSuite

class Day05Test extends FunSuite {

  // Part one

  test("Part one case 1") {
    val input = "ugknbfddgicrmopn"
    val expeced = true
    assertEquals(Day05.isNiceStringPartOne(input), expeced)
  }

  test("Part one case 1") {
    val input = "aaa"
    val expeced = true
    assertEquals(Day05.isNiceStringPartOne(input), expeced)
  }

  test("Part one case 1") {
    val input = "jchzalrnumimnmhp"
    val expeced = false
    assertEquals(Day05.isNiceStringPartOne(input), expeced)
  }

  test("Part one case 1") {
    val input = "haegwjzuvuyypxyu"
    val expeced = false
    assertEquals(Day05.isNiceStringPartOne(input), expeced)
  }

  test("Part one case 1") {
    val input = "dvszwmarrgswjxmb"
    val expeced = false
    assertEquals(Day05.isNiceStringPartOne(input), expeced)
  }

  // Part two

  test("Part two case 1") {
    val input = "qjhvhtzxzqqjkmpb"
    val expected = true
    assertEquals(Day05.isNiceStringPartTwo(input), expected)
  }

  test("Part two case 2") {
    val input = "xxyxx"
    val expected = true
    assertEquals(Day05.isNiceStringPartTwo(input), expected)
  }

  test("Part two case 3") {
    val input = "uurcxstgmygtbstg"
    val expected = false
    assertEquals(Day05.isNiceStringPartTwo(input), expected)
  }

  test("Part two case 4") {
    val input = "ieodomkazucvgmuy"
    val expected = false
    assertEquals(Day05.isNiceStringPartTwo(input), expected)
  }
}
