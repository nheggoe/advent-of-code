package dev.nheggoe.aoc15

import dev.nheggoe.aoc.{AocDay, Input}

import java.security.MessageDigest

object Day04 extends AocDay(4) {

  extension (s: String) {
    def md5: String = {
      MessageDigest
        .getInstance("MD5")
        .digest(s.getBytes("UTF-8"))
        .map("%02x".format(_))
        .mkString
    }
  }

  def partOne(using Input): Int = {
    findKey(input, "00000")
  }

  def partTwo(using Input): Int = {
    findKey(input, "000000")
  }

  private def findKey(input: String, prefix: String) = {
    val naturalNumbers = LazyList.iterate(1)(_ + 1)
    naturalNumbers.find(num =>
      (input + num.toString).md5.startsWith(prefix)
    ) match {
      case Some(key) => key
      case None      => -1
    }
  }
}
