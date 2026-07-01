package dev.nheggoe.aoc15

import dev.nheggoe.aoc.{AocDay, Input}

object Day09 extends AocDay(9) {

  type Location = String
  case class Path(from: Location, to: Location, distance: Int)
  object Path:
    def apply(s: String): Path =
      val pattern = """(\w+) to (\w+) = (\d+)""".r
      s match
        case pattern(from, to, weight) => new Path(from, to, weight.toInt)

    given Conversion[String, Path] = Path(_)

  extension (ps: Set[Path])
    def undirected: Set[Path] = {
      for p @ Path(from, to, _) <- ps
      yield Set(p, p.copy(from = to, to = from))
    }.flatten

    def uniqueLocations: Set[Location] = {
      for Path(from, to, _) <- ps
      yield Set(from, to)
    }.flatten

  override def partOne(using Input): Any = {
    val paths = lines.map(Path(_)).toSet.undirected
    val table = paths.groupBy(_.from)
    val locations = paths.uniqueLocations

    def recur(current: Location, remaining: Set[Location]): List[List[Path]] = {
      if remaining.isEmpty then List(Nil)
      else
        for
          next <- table.getOrElse(current, Nil).toList
          if remaining.contains(next.to)
          rest <- recur(next.to, remaining - next.to)
        yield next :: rest
    }

    {
      for
        start <- locations.toList
        route <- recur(start, locations - start)
      yield route.map(_.distance).sum
    }.sorted.headOption
  }

  override def partTwo(using Input): Any = ???
}
