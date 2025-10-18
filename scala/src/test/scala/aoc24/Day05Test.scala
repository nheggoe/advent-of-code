package dev.nheggoe
package aoc24

import munit.FunSuite

class Day05Test extends FunSuite {
  val puzzleInput: String = """47|53
                              |97|13
                              |97|61
                              |97|47
                              |75|29
                              |61|13
                              |75|53
                              |29|13
                              |97|29
                              |53|29
                              |61|53
                              |97|53
                              |61|29
                              |47|13
                              |75|47
                              |97|75
                              |47|61
                              |75|61
                              |47|29
                              |75|13
                              |53|13
                              |
                              |75,47,61,53,29
                              |97,61,53,29,13
                              |75,29,13
                              |75,97,47,61,53
                              |61,13,29
                              |97,13,75,29,47""".stripMargin

  test("Parse input") {
    val rules = """47|53
                  |97|13
                  |97|61
                  |97|47
                  |75|29
                  |61|13
                  |75|53
                  |29|13
                  |97|29
                  |53|29
                  |61|53
                  |97|53
                  |61|29
                  |47|13
                  |75|47
                  |97|75
                  |47|61
                  |75|61
                  |47|29
                  |75|13
                  |53|13""".stripMargin.linesIterator.toSeq

    val pages = """75,47,61,53,29
                  |97,61,53,29,13
                  |75,29,13
                  |75,97,47,61,53
                  |61,13,29
                  |97,13,75,29,47""".stripMargin.linesIterator.toSeq

    val expected = (rules, pages)
    assertEquals(Day05.parseInput(puzzleInput), expected)
  }

  test("Part one example") {
    val expected = 143
    assertEquals(Day05.partOne(puzzleInput), expected)
  }

  test("Part two example") {
    val expected = 123
    assertEquals(Day05.partTwo(puzzleInput), expected)
  }
}
