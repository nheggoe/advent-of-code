package dev.nheggoe.aoc15

import dev.nheggoe.aoc.Input
import dev.nheggoe.aoc15.Day12.{partOne, partTwo}
import munit.FunSuite

class Day12Test extends FunSuite {
  test("case 1") {
    assertEquals(partOne(using Input("[1,2,3]")), 6)
    assertEquals(partOne(using Input("""{"a":2,"b":4}""")), 6)
  }

  test("case 2") {
    assertEquals(partOne(using Input("[[[3]]]")), 3)
    assertEquals(partOne(using Input("""{"a":{"b":4},"c":-1}""")), 3)
  }

  test("case 3") {
    assertEquals(partOne(using Input("""{"a":[-1,1]}""")), 0)
    assertEquals(partOne(using Input("""[-1,{"a":1}]""")), 0)
  }

  test("case 4") {
    assertEquals(partOne(using Input("[]")), 0)
    assertEquals(partOne(using Input("{}")), 0)
  }
  // ===========  partTwo  ==============

  test("case 5") {
    assertEquals(partTwo(using Input("[1,2,3]")), 6)
  }

  test("case 6") {
    assertEquals(partTwo(using Input("""[1,{"c":"red","b":2},3]""")), 4)
  }

  test("case 7") {
    assertEquals(partTwo(using Input("""{"d":"red","e":[1,2,3,4],"f":5}""")), 0)
  }

  test("case 8") {
    assertEquals(partTwo(using Input("""[1,"red",5]""")), 6)
  }

}
