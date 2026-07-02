package dev.nheggoe.aoc15

import dev.nheggoe.aoc15.Day11.{
  allValidChar,
  containsIncreasingStraight,
  containsTwoNonOverlappingPairs,
  increment,
  isValid,
  nextPassword
}
import munit.FunSuite

class Day11Test extends FunSuite {

  test("case 1") {
    val password = "hijklmmn"
    assert(password.containsIncreasingStraight)
    assert(!password.allValidChar)
    assert(!password.containsTwoNonOverlappingPairs)
  }

  test("case 2") {
    val password = "abbceffg"
    assert(!password.containsIncreasingStraight)
    assert(password.containsTwoNonOverlappingPairs)
  }

  test("case 3") {
    val password = "abbcegjk"
    assert(!password.containsTwoNonOverlappingPairs)
  }

  test("increment password") {
    val expected = Seq("xxx", "xxy", "xxz", "xya", "xyb")
    val actual = Iterator.iterate("xxx")(increment).take(5).toSeq
    assertEquals(actual, expected)
  }

  test("nextPassword case 1") {
    val password = "abcdefgh"
    val expected = "abcdffaa"
    assert(expected.isValid)
    assertEquals(password.nextPassword, expected)
  }

  test("nextPassword case 2") {
    val password = "ghijklmn"
    val expected = "ghjaabcc"
    assertEquals(password.nextPassword, expected)
  }

}
