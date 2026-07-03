package dev.nheggoe.aoc15
import dev.nheggoe.aoc.{AocDay, Input}

object Day13 extends AocDay(13) {

  type People = String
  case class Rule(source: People, nextTo: People, value: Int)
  object Rule:
    def apply(s: String): Rule =
      val gainR =
        """(\w+) would gain (\d+) happiness units by sitting next to (\w+).""".r
      val loseR =
        """(\w+) would lose (\d+) happiness units by sitting next to (\w+).""".r
      s match
        case gainR(source, value, nextTo) =>
          new Rule(source, nextTo, value.toInt)
        case loseR(source, value, nextTo) =>
          new Rule(source, nextTo, -value.toInt)

  extension (s: String) def parseRule: Rule = Rule(s)
  extension (ps: List[People])
    def toHappiness(f: (People, People, People) => Int): List[Int] = {
      val wrapAround = ps.last :: ps ::: ps.head :: Nil
      wrapAround
        .sliding(3)
        .map:
          case Seq(left, source, right) => f(source, left, right)
          case _                        => sys.error("unreachable")
        .toList
    }

  def calculateHappiness(
      table: Map[People, Vector[Rule]]
  )(source: People, left: People, right: People): Int = {
    val rules = table.getOrElse(source, Nil)
    val l = rules.find(_.nextTo == left).map(_.value).getOrElse(0)
    val r = rules.find(_.nextTo == right).map(_.value).getOrElse(0)
    l + r
  }

  def allPeople(rules: Seq[Rule]): Set[People] = {
    for Rule(x, y, _) <- rules.toSet
    yield Set(x, y)
  }.flatten

  override def partOne(using Input): Int =
    val rules = lines.map(_.parseRule)
    val table = rules.groupBy(_.source)
    allPeople(rules).toList.permutations.toList
      .map(_.toHappiness(calculateHappiness(table)).sum)
      .max

  override def partTwo(using Input): Any = ???
}
