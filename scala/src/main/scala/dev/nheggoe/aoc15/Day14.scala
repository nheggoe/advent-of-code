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

    def distances(seconds: Int): Vector[Int] =
      val program = (1 to seconds).foldLeft(
        St[Vector[Int], Phase](s => (Vector.empty, s))
      ) { (acc, _) =>
        for
          trace <- acc
          km <- step
        yield trace :+ (trace.lastOption.getOrElse(0) + km)
      }
      program.run(Phase.Flying(flyDuration)).value

    def totalDistance(seconds: Int): Int =
      distances(seconds).lastOption.getOrElse(0)
  }
  object Reindeer {
    val pattern: Regex =
      """(\w+) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds.""".r

    def apply(s: String): Reindeer = s match
      case pattern(name, speed, flyDuration, restDuration) =>
        new Reindeer(name, speed.toInt, flyDuration.toInt, restDuration.toInt)

    given Conversion[String, Reindeer] = Reindeer(_)
  }

  extension (s: String) def parseReindeer: Reindeer = Reindeer(s)

  private val raceDuration: Int = 2503

  def race(
      seconds: Int,
      rs: Reindeer*
  ): Vector[(name: String, score: Int)] = {
    val traces = rs.map(_.distances(seconds))
    val scores = traces.transpose.foldLeft(Vector.fill(rs.length)(0)) {
      case (scores, distances) =>
        val lead = distances.max
        scores.zip(distances).map {
          case (score, distance) if distance == lead => score + 1
          case (score, _)                            => score
        }
    }
    rs.toVector
      .zip(scores)
      .map:
        case (r, score) => (r.name, score)
  }

  override def partOne(using Input): Int =
    lines.map(_.parseReindeer).map(_.totalDistance(raceDuration)).max

  override def partTwo(using Input): Int = {
    val reindeer = lines.map(_.parseReindeer)
    race(raceDuration, reindeer*).map(_.score).max
  }
}
