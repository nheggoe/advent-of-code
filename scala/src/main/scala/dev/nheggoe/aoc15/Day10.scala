package dev.nheggoe.aoc15

import dev.nheggoe.aoc.{AocDay, Input}

object Day10 extends AocDay(10) {

  private type Run = (char: Char, count: Int)

  private def step(state: Option[Run], c: Char): (Option[Run], Option[Run]) = {
    state match
      case None => (Some((char = c, count = 1)), None)
      case Some((char, count)) if char == c => (Some((char, count + 1)), None)
      case Some(done)                       => (Some(c, 1), Some(done))
  }

  private def emit(out: StringBuilder, run: Run): Unit =
    val (char, count) = run
    out.append(count)
    out.append(char)

  def lookAndSay(s: String): String = {
    val sb = StringBuilder(s.length * 2)

    val finalState = s.foldLeft(Option.empty[Run]) { (state, c) =>
      val (nextState, completedRun) = step(state, c)
      completedRun.foreach(emit(sb, _))
      nextState
    }

    finalState.foreach(emit(sb, _))
    sb.result()
  }

  override def partOne(using Input): Any =
    lookAndSay.iter(40, input).length

  override def partTwo(using Input): Any =
    lookAndSay.iter(50, input).length
   
}
