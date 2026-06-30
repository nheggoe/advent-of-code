package dev.nheggoe
package aoc15

import dev.nheggoe.aoc.Input
import dev.nheggoe.aoc15.Day01.{partOne, partTwo}
import munit.FunSuite

class Day01Test extends FunSuite {

  test("Part one case 1") {
    val inputOne = "(())"
    val inputTwo = "()()"
    val expected = 0
    assertEquals(partOne(using inputOne), expected)
    assertEquals(partOne(using inputTwo), expected)
  }

  test("Part one case 2") {
    val inputOne = "((("
    val inputTwo = "(()(()("
    val expected = 3
    assertEquals(partOne(using inputOne), expected)
    assertEquals(partOne(using inputTwo), expected)
  }

  test("Part one case 3") {
    val inputOne = "))((((("
    val expected = 3
    assertEquals(partOne(using inputOne), expected)
  }

  test("Part one case 4") {
    val inputOne = "())"
    val inputTwo = "))("
    val expected = -1
    assertEquals(partOne(using inputOne), expected)
    assertEquals(partOne(using inputTwo), expected)
  }

  test("Part one case 5") {
    val inputOne = ")))"
    val inputTwo = ")())())"
    val expected = -3
    assertEquals(partOne(using inputOne), expected)
    assertEquals(partOne(using inputTwo), expected)
  }

  test("Part two case 1") {
    val input = ")"
    val expected = 1
    assertEquals(partTwo(using input), expected)
  }

  test("Part two case 2") {
    val input = "()())"
    val expected = 5
    assertEquals(partTwo(using input), expected)
  }

}
