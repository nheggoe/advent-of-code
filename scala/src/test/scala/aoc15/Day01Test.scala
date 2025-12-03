package dev.nheggoe
package aoc15

import munit.FunSuite

class Day01Test extends FunSuite {

  test("Part one case 1") {
    val inputOne = "(())"
    val inputTwo = "()()"
    val expected = 0
    assertEquals(Day01.partOne(inputOne), expected)
    assertEquals(Day01.partOne(inputTwo), expected)
  }

  test("Part one case 2") {
    val inputOne = "((("
    val inputTwo = "(()(()("
    val expected = 3
    assertEquals(Day01.partOne(inputOne), expected)
    assertEquals(Day01.partOne(inputTwo), expected)
  }

  test("Part one case 3") {
    val inputOne = "))((((("
    val expected = 3
    assertEquals(Day01.partOne(inputOne), expected)
  }

  test("Part one case 4") {
    val inputOne = "())"
    val inputTwo = "))("
    val expected = -1
    assertEquals(Day01.partOne(inputOne), expected)
    assertEquals(Day01.partOne(inputTwo), expected)
  }

  test("Part one case 5") {
    val inputOne = ")))"
    val inputTwo = ")())())"
    val expected = -3
    assertEquals(Day01.partOne(inputOne), expected)
    assertEquals(Day01.partOne(inputTwo), expected)
  }

}
