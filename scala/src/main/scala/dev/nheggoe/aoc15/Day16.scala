package dev.nheggoe.aoc15

import dev.nheggoe.aoc.{AocDay, Input, St}

import scala.annotation.tailrec

object Day16 extends AocDay(16) {
  // Sue 1: children: 1, cars: 8, vizslas: 7
  // --  ^             ^

  type Parser[T] = St[T, String]

  type Entry = (key: Key, value: Int)
  object Entry {
    def unapply(s: String): Option[Entry] = s match
      case s"$key: $value" => value.toIntOption.map((key.strip(), _))
      case _               => None
  }

  private object Entries:
    def unapply(s: String): Option[List[Entry]] = {
      @tailrec def recur(x: String, acc: List[Entry] = Nil): List[Entry] = {
        x match {
          case s"${Entry(pair)}, $rest" => recur(rest, pair :: acc)
          case s"${Entry(pair)}"        => pair :: acc
          case _                        => acc
        }
      }
      Option(recur(s)).filter(_.nonEmpty)
    }

  def parseEntries: Parser[List[Entry]] = St:
    case s"${Entries(entries)}" => (entries, "")

  def parseId: Parser[Int] = St { s =>
    val (prefix, rest) = s.span(_ != ':')
    (prefix.dropWhile(_ != ' ').strip().toInt, rest.tail.strip())
  }

  into enum Key {
    case Children
    case Cats
    case Samoyeds
    case Pomeranians
    case Akitas
    case Vizslas
    case Goldfish
    case Trees
    case Cars
    case Perfumes
  }

  object Key {
    given Conversion[String, Key] = {
      case "children"    => Children
      case "cats"        => Cats
      case "samoyeds"    => Samoyeds
      case "pomeranians" => Pomeranians
      case "akitas"      => Akitas
      case "vizslas"     => Vizslas
      case "goldfish"    => Goldfish
      case "trees"       => Trees
      case "cars"        => Cars
      case "perfumes"    => Perfumes
    }
  }

  case class Aunt(
      id: Int,
      children: Option[Int] = None,
      cats: Option[Int] = None,
      samoyeds: Option[Int] = None,
      pomeranians: Option[Int] = None,
      akitas: Option[Int] = None,
      vizslas: Option[Int] = None,
      goldfish: Option[Int] = None,
      trees: Option[Int] = None,
      cars: Option[Int] = None,
      perfumes: Option[Int] = None,
  ) {
    def toMap: Map[Key, Option[Int]] = Map(
      Key.Children -> children,
      Key.Cats -> cats,
      Key.Samoyeds -> samoyeds,
      Key.Pomeranians -> pomeranians,
      Key.Akitas -> akitas,
      Key.Vizslas -> vizslas,
      Key.Goldfish -> goldfish,
      Key.Trees -> trees,
      Key.Cars -> cars,
      Key.Perfumes -> perfumes,
    )
    def matches: Boolean = {
      val tMap = target.toMap
      toMap.forall { case (key, value) =>
        value.isEmpty || value == tMap(key)
      }
    }

    def matchFn(key: Key): (Option[Int], Option[Int]) -> Boolean = {
      val fn: (Option[Int], Option[Int]) -> Boolean = key match {
        case Key.Cats | Key.Trees => { case (Some(a), Some(b)) =>
          a > b
        }
        case Key.Pomeranians | Key.Goldfish => { case (Some(a), Some(b)) =>
          a < b
        }
        case _ => _ == _
      }
      (a, b) => a.isEmpty || fn(a, b)
    }

    def outdatedRetroencabulatorMatches: Boolean =
      val tMap = target.toMap
      toMap.forall { case (key, value) =>
        matchFn(key)(value, tMap(key))
      }
  }

  object Aunt {
    def apply(s: String): Aunt =
      val parser: Parser[Aunt] = for {
        id <- parseId
        entries <- parseEntries
      } yield Aunt(id, entries)
      parser.run(s).value

    def apply(id: Int, xs: List[Entry]): Aunt = {
      xs.foldLeft(Aunt(id)) { case (aunt, (key, value)) =>
        key match {
          case Key.Children    => aunt.copy(children = Some(value))
          case Key.Cats        => aunt.copy(cats = Some(value))
          case Key.Samoyeds    => aunt.copy(samoyeds = Some(value))
          case Key.Pomeranians => aunt.copy(pomeranians = Some(value))
          case Key.Akitas      => aunt.copy(akitas = Some(value))
          case Key.Vizslas     => aunt.copy(vizslas = Some(value))
          case Key.Goldfish    => aunt.copy(goldfish = Some(value))
          case Key.Trees       => aunt.copy(trees = Some(value))
          case Key.Cars        => aunt.copy(cars = Some(value))
          case Key.Perfumes    => aunt.copy(perfumes = Some(value))
        }
      }
    }
  }

  var target = Aunt(
    id = -1,
    children = Some(3),
    cats = Some(7),
    samoyeds = Some(2),
    pomeranians = Some(3),
    akitas = Some(0),
    vizslas = Some(0),
    goldfish = Some(5),
    trees = Some(3),
    cars = Some(2),
    perfumes = Some(1),
  )

  def partOne(using Input): Int = {
    lines.map(Aunt(_)).find(_.matches).map(_.id).getOrElse(-1)
  }

  def partTwo(using Input): Int = {
    lines
      .map(Aunt(_))
      .find(_.outdatedRetroencabulatorMatches)
      .map(_.id)
      .getOrElse(-1)
  }
}
