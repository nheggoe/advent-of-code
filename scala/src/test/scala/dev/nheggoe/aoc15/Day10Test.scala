package dev.nheggoe.aoc15

import dev.nheggoe.aoc15.Day10.{iter, lookAndSay}
import munit.FunSuite

class Day10Test extends FunSuite {

  test("first iteration") {
    val input = "1"
    val after = "11"
    assertEquals(lookAndSay(input), after)
  }

  test("second iteration") {
    val input = "11"
    val after = "21"
    assertEquals(lookAndSay(input), after)
  }

  test("third iteration") {
    val input = "21"
    val after = "1211"
    assertEquals(lookAndSay(input), after)
  }

  test("fourth iteration") {
    val input = "1211"
    val after = "111221"
    assertEquals(lookAndSay(input), after)
  }

  test("fifth iteration") {
    val input ="111221"
    val after = "312211"
    assertEquals(lookAndSay(input), after)
  }

  test("iter 5 times == fifth iteration") {
    val input ="1"
    val expected = "312211"
    assertEquals(lookAndSay.iter(5, input), expected)
  }
}
