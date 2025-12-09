package dev.nheggoe
package aoc25

import munit.FunSuite

class Day09Test extends FunSuite {
  private val input = """7,1
                        |11,1
                        |11,7
                        |9,7
                        |9,5
                        |2,5
                        |2,3
                        |7,3""".stripMargin

  test("Part one example") {
    val expected = 50L
    assertEquals(Day09.partOne(input), expected)
  }
 
}
