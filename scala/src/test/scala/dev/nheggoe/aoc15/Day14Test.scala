package dev.nheggoe.aoc15

import dev.nheggoe.aoc.Input
import dev.nheggoe.aoc15.Day14.{Reindeer, parseReindeer, race}
import munit.ScalaCheckSuite
import org.scalacheck.*
import org.scalacheck.Prop.*

class Day14Test extends ScalaCheckSuite {

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

  lazy val genReindeer: Gen[Reindeer] =
    for
      name <- Gen.identifier
      speed <- Gen.choose(1, 1_000)
      flyDuration <- Gen.choose(1, 1_000)
      restDuration <- Gen.choose(1, 1_000)
    yield Reindeer(name, speed, flyDuration, restDuration)
  given Arbitrary[Reindeer] = Arbitrary(genReindeer)

  test("parsing") {
    new TestData:
      assert(reindeer.contains(comet))
      assert(reindeer.contains(dancer))
  }

  property("during first flying phase, distance is speed * seconds"):
    forAll: (r: Reindeer) =>
      forAll(Gen.choose(0, r.flyDuration)): seconds =>
        r.totalDistance(seconds) == r.speed * seconds

  property("during resting phase, distance will not change"):
    forAll: (r: Reindeer) =>
      forAll(Gen.choose(r.flyDuration + 1, r.flyDuration + r.restDuration)):
        seconds => r.totalDistance(seconds) == r.speed * r.flyDuration

  test("part one") {
    new TestData:
      assertEquals(comet.totalDistance(raceDuration), 1120)
      assertEquals(dancer.totalDistance(raceDuration), 1056)
  }

  test("part two") {
    new TestData:
      assertEquals(
        race(raceDuration, comet, dancer),
        Vector((comet.name, 312), (dancer.name, 689))
      )
  }

}
