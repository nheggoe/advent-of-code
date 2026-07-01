package dev.nheggoe.aoc15

import dev.nheggoe.aoc.Input
import dev.nheggoe.aoc15.Day09.{Path, partOne, uniqueLocations}
import munit.FunSuite

class Day09Test extends FunSuite {
  given input: Input = Input("""London to Dublin = 464
                        |London to Belfast = 518
                        |Dublin to Belfast = 141""".stripMargin)

  val paths: Set[Path] = {
    for line <- input.value.linesIterator.toSeq
    yield Path(line)
  }.toSet

  test("unique locations") {
    val expected = Set("London", "Dublin", "Belfast")
    assertEquals(paths.uniqueLocations, expected)
  }

  test("all paths") {
    val allPaths = Set(
      Path("London", "Dublin", 464),
      Path("Dublin", "London", 464),
      Path("London", "Belfast", 518),
      Path("Belfast", "London", 518),
      Path("Dublin", "Belfast", 141),
      Path("Belfast", "Dublin", 141)
    )
    assertEquals(paths.undirected, allPaths)
  }

  test("partOne") {
    val expected = Some(605)
    assertEquals(partOne, expected)
  }
}
