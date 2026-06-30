package dev.nheggoe.aoc15

import dev.nheggoe.aoc.{AocDay, Input, InputFetcher}
import dev.nheggoe.aoc15.Day07.Source.*

import scala.Conversion.into
import scala.collection.mutable

object Day07 extends AocDay(7):

  private val register: mutable.Map[Key, Value] = mutable.Map.empty

  extension (m: mutable.Map[Key, Value])(using Map[Destination, Source])
    def value(k: Key): Value = m.getOrElseUpdate(k, Source(k).eval)

  override def main(args: Array[String]): Unit =
    given Input = InputFetcher.fetchInput
    logPartOne(partOne("a"))
    logPartOne(partTwo("a"))

  override def partOne(using Input): Map[Key, Value] =
    given connections: Map[Destination, Source] = lines.toConnections
    simulate

  override def partTwo(using Input): Map[Key, Value] =
    given connections: Map[Destination, Source] =
      (lines :+ s"${partOne("a")} -> b").toConnections
    simulate

  private def simulate(using
      connections: Map[Destination, Source]
  ): Map[Key, Value] = {
    register.clear()
    for (k, v) <- connections.toVector
    yield register.put(k, v.eval)
    register.toMap
  }

  enum Gate:
    case Or(x: Source, y: Source)
    case And(x: Source, y: Source)
    case LShift(x: Source, n: Int)
    case RShift(x: Source, n: Int)
    case Not(x: Source)
    def eval(using connections: Map[Destination, Source]): Int = {
      this match
        case Or(x, y)     => x.eval | y.eval
        case And(x, y)    => x.eval & y.eval
        case LShift(x, n) => x.eval << n
        case RShift(x, n) => x.eval >> n
        case Not(x)       => ~x.eval
    }.normalize

  end Gate
  object Gate:
    private val NotR = """NOT (\w+)""".r
    private val OrR = """(\w+) OR (\w+)""".r
    private val AndR = """(\w+) AND (\w+)""".r
    private val LShiftR = """(\w+) LSHIFT (\d+)""".r
    private val RShiftR = """(\w+) RSHIFT (\d+)""".r

    def apply(s: String): Gate = s.strip() match
      case NotR(x)       => Not(Source(x))
      case OrR(x, y)     => Or(Source(x), Source(y))
      case AndR(x, y)    => And(Source(x), Source(y))
      case LShiftR(x, n) => LShift(Source(x), n.toInt)
      case RShiftR(x, n) => RShift(Source(x), n.toInt)

    given Conversion[String, Gate] = Gate(_)
  end Gate

  enum Source:
    case GateS(g: into[Gate])
    case Num(n: Int)
    case Var(name: String)

    def eval(using connections: Map[Destination, Source]): Int =
      this match
        case Num(n)   => n
        case GateS(g) => g.eval
        case Var(k)   => register.getOrElseUpdate(k, connections(k).eval)
  end Source

  object Source:
    private val NumR = """(\d+)""".r
    private val VarR = """(\w+)""".r

    def apply(s: String): Source =
      s.strip() match
        case NumR(n)    => Num(n.toInt)
        case VarR(name) => Var(name)
        case s          => GateS(s)

    given Conversion[String, Source] = Source(_)
  end Source

  private type Key = String
  private type Value = Int
  private type Destination = Key

  extension (int: Int) private def normalize: Int = int & 0xffff

  extension (lines: Vector[String])
    private def toConnections: Map[Destination, Source] = {
      for
        line <- lines
        Vector(source, destination) = line.split("->").toVector.map(_.strip())
      yield destination -> Source(source)
    }.toMap

end Day07
