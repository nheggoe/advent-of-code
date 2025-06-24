package dev.nheggoe
package aoc24

import zio.test.*

import scala.List

object Day2Test extends ZIOSpecDefault:
  def spec =
    suite("Day2Spec")(
      test("returns true for safe list #1") {
        assertTrue(Day2.isSafe(List(7, 6, 4, 2, 1)))
      },
      test("returns false for unsafe list #2") {
        assertTrue(!Day2.isSafe(List(1, 2, 7, 8, 9)))
      },
      test("returns false for unsafe list #3") {
        assertTrue(!Day2.isSafe(List(9, 7, 6, 2, 1)))
      },
      test("returns false for unsafe list #4") {
        assertTrue(!Day2.isSafe(List(1, 3, 2, 4, 5)))
      },
      test("returns false for unsafe list #5") {
        assertTrue(!Day2.isSafe(List(8, 6, 4, 4, 1)))
      },
      test("returns true for safe list #6") {
        assertTrue(Day2.isSafe(List(1, 3, 6, 7, 9)))
      }
    )
