package dev.nheggoe
package aoc24

import aoc24.Day2.{isSafe, isSafeWithTolerance}

import zio.test.*

import scala.List

object Day2Test extends ZIOSpecDefault:
  def spec =
    suite("Day2Spec")(
      test("returns true for safe list #1") {
        assertTrue(isSafe(List(7, 6, 4, 2, 1)))
      },
      test("returns false for unsafe list #2") {
        assertTrue(!isSafe(List(1, 2, 7, 8, 9)))
      },
      test("returns false for unsafe list #3") {
        assertTrue(!isSafe(List(9, 7, 6, 2, 1)))
      },
      test("returns false for unsafe list #4") {
        assertTrue(!isSafe(List(1, 3, 2, 4, 5)))
      },
      test("returns false for unsafe list #5") {
        assertTrue(!isSafe(List(8, 6, 4, 4, 1)))
      },
      test("returns true for safe list #6") {
        assertTrue(isSafe(List(1, 3, 6, 7, 9)))
      },

      // part two
      test("Safe without removing any level") {
        assertTrue(isSafeWithTolerance(List(7, 6, 4, 2, 1)))
      },
      test("Unsafe regardless of which level is removed") {
        assertTrue(!isSafeWithTolerance(List(1, 2, 7, 8, 9)))
      },
      test("Unsafe regardless of which level is removed") {
        assertTrue(!isSafeWithTolerance(List(9, 7, 6, 2, 1)))
      },
      test("Safe by removing the second level, 3") {
        assertTrue(isSafeWithTolerance(List(1, 3, 2, 4, 5)))
      },
      test("Safe by removing the third level, 4") {
        assertTrue(isSafeWithTolerance(List(8, 6, 4, 4, 1)))
      },
      test("Safe without removing any level") {
        assertTrue(isSafeWithTolerance(List(1, 3, 6, 7, 9)))
      }
    )
