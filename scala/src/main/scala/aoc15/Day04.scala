package dev.nheggoe
package aoc15

import java.security.MessageDigest

object Day04 {

  extension (s: String) {
    def md5: String = {
      val digest = MessageDigest.getInstance("MD5")
      digest
        .digest(s.getBytes("UTF-8"))
        .map("%02x".format(_))
        .mkString
    }
  }

  def main(args: Array[String]): Unit = {
    val puzzleInput = "ckczppom"
    println(s"Day 4 part one is ${partOne(puzzleInput)}")
    println(s"Day 4 part two is ${partTwo(puzzleInput)}")
  }

  def partOne(input: String): Int = {
    findKey(input, "00000")
  }

  def partTwo(input: String): Int = {
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
