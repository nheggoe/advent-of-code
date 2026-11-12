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
      children: Int = 0,
      cats: Int = 0,
      samoyeds: Int = 0,
      pomeranians: Int = 0,
      akitas: Int = 0,
      vizslas: Int = 0,
      goldfish: Int = 0,
      trees: Int = 0,
      cars: Int = 0,
      perfumes: Int = 0,
  ) {
    def matches(other: Aunt): Boolean = List(
      children -> other.children,
      cats -> other.cats,
      samoyeds -> other.samoyeds,
      pomeranians -> other.pomeranians,
      akitas -> other.akitas,
      vizslas -> other.vizslas,
      goldfish -> other.goldfish,
      trees -> other.trees,
      cars -> other.cars,
      perfumes -> other.perfumes,
    ).forall { case (a, b) =>
      a == 0 || a == b
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
          case Key.Children    => aunt.copy(children = value)
          case Key.Cats        => aunt.copy(cats = value)
          case Key.Samoyeds    => aunt.copy(samoyeds = value)
          case Key.Pomeranians => aunt.copy(pomeranians = value)
          case Key.Akitas      => aunt.copy(akitas = value)
          case Key.Vizslas     => aunt.copy(vizslas = value)
          case Key.Goldfish    => aunt.copy(goldfish = value)
          case Key.Trees       => aunt.copy(trees = value)
          case Key.Cars        => aunt.copy(cars = value)
          case Key.Perfumes    => aunt.copy(perfumes = value)
        }
      }
    }
  }

  var target = Aunt(
    id = -1,
    children = 3,
    cats = 7,
    samoyeds = 2,
    pomeranians = 3,
    akitas = 0,
    vizslas = 0,
    goldfish = 5,
    trees = 3,
    cars = 2,
    perfumes = 1,
  )

  def partOne(using Input): Int = {
    lines.map(Aunt(_)).find(_.matches(target)).map(_.id).getOrElse(-1)
  }

  def partTwo(using Input): Any = ???
}
