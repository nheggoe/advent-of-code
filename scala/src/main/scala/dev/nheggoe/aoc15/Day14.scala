package dev.nheggoe.aoc15

import dev.nheggoe.aoc.{AocDay, Input, St}

import scala.util.matching.Regex

object Day14 extends AocDay(14) {

  enum Phase:
    case Flying(left: Int)
    case Resting(left: Int)

  case class Reindeer(
      name: String,
      speed: Int,
      flyDuration: Int,
      restDuration: Int
  ) {
    val step: St[Int, Phase] = St {
      case Phase.Flying(1)  => (speed, Phase.Resting(restDuration))
      case Phase.Flying(n)  => (speed, Phase.Flying(n - 1))
      case Phase.Resting(1) => (0, Phase.Flying(flyDuration))
      case Phase.Resting(n) => (0, Phase.Resting(n - 1))
    }

    def totalDistance(seconds: Int): Int =
      val program = (1 to seconds).foldLeft(St[Int, Phase](s => (0, s))) {
        (acc, _) =>
          for
            sum <- acc
            km <- step
          yield sum + km
      }
      program.run(Phase.Flying(flyDuration)).value
  }
  object Reindeer {
    val pattern: Regex =
      """(\w+) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds.""".r

    def apply(s: String): Reindeer = s match
      case pattern(name, speed, flyDuration, restDuration) =>
        new Reindeer(name, speed.toInt, flyDuration.toInt, restDuration.toInt)
  }

  extension (s: String) def parseReindeer: Reindeer = Reindeer(s)

  private val raceDuration: Int = 2503

  override def partOne(using Input): Int =
    lines.map(_.parseReindeer).map(_.totalDistance(raceDuration)).max

  override def partTwo(using Input): Any = ???
}
