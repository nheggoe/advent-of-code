package dev.nheggoe.aoc15

import dev.nheggoe.aoc.Input
import dev.nheggoe.aoc15.Day16.*
import munit.FunSuite

class Day16Test extends FunSuite {
  given input: Input = "Sue 1: children: 1, cars: 8, vizslas: 7"

  test("parse id") {
    val parser: Parser[Aunt] = for {
      id <- parseId
    } yield Aunt(id = id)
    val (aunt, _) = parser.run(input.value)
    assertEquals(aunt.id, 1)
  }

  test("parse entry") {
    val parser: Parser[List[Entry]] = for {
      _ <- parseId
      entries <- parseEntries
    } yield entries
    val (entries, _) = parser.run(input.value)
    val aunt = Aunt(1, entries)
    assertEquals(aunt, Aunt(id = 1, children = 1, cars = 8, vizslas = 7))
  }

  test("partOne") {
    target = Aunt(id = 1, children = 1, cars = 8, vizslas = 7)
    assertEquals(Day16.partOne, 1)
  }
}
