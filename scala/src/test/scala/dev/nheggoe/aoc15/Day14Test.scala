package dev.nheggoe.aoc15

import dev.nheggoe.aoc.Input
import dev.nheggoe.aoc15.Day14.{Reindeer, parseReindeer}
import munit.FunSuite

class Day14Test extends FunSuite {

  trait TestData:
    given input: Input = Input(
      """Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
        |Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.""".stripMargin
    )
    val reindeer: Seq[Reindeer] =
      input.value.linesIterator.map(_.parseReindeer).toSeq

    val raceDuration: Int = 1000

    val comet: Reindeer = Reindeer("Comet", 14, 10, 127)
    val dancer: Reindeer = Reindeer("Dancer", 16, 11, 162)

  test("parsing") {
    new TestData:
      assert(reindeer.contains(comet))
      assert(reindeer.contains(dancer))
  }

  test("total distance after n seconds") {
    new TestData:
      assertEquals(comet.totalDistance(1), comet.speed)
      assertEquals(dancer.totalDistance(1), dancer.speed)

      assertEquals(
        comet.totalDistance(comet.flyDuration),
        comet.flyDuration * comet.speed
      )
      assertEquals(
        dancer.totalDistance(dancer.flyDuration),
        dancer.flyDuration * dancer.speed
      )

      assertEquals(
        comet.totalDistance(comet.flyDuration + comet.restDuration),
        comet.flyDuration * comet.speed
      )
      assertEquals(
        dancer.totalDistance(dancer.flyDuration + dancer.restDuration),
        dancer.flyDuration * dancer.speed
      )

      assertEquals(comet.totalDistance(raceDuration), 1120)
      assertEquals(dancer.totalDistance(raceDuration), 1056)
  }
}
