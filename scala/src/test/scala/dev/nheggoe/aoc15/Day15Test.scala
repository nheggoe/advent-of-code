package dev.nheggoe.aoc15

import dev.nheggoe.aoc.Input
import munit.FunSuite

class Day15Test extends FunSuite {
  given Input = Input(
    """Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
      |Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3""".stripMargin
  )

  test("part1") {
    assertEquals(Day15.partOne, 62842880L)
  }

}
