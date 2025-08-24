package dev.nheggoe
package util

import sttp.client4.Response
import sttp.client4.quick.*

import java.nio.file.{Files, Path, Paths}
import scala.concurrent.Future
import scala.jdk.CollectionConverters.*

object InputFetcher:

  private val cacheDir = "cache"

  def fetchInput(year: Int, day: Int): String =
    val cacheFile = Path.of(cacheDir, year.toString, s"day$day.txt")
    if Files.exists(cacheFile) then Files.readString(cacheFile)
    else
      val input = fetchFromInternet(year, day).body
      println("Fetched puzzle input from internet")
      saveToCache(cacheFile, input)
      input

  private def fetchFromInternet(
      year: Int,
      day: Int
  ): Response[String] =
    val token: String = readDotEnv().getOrElse("SESSION_TOKEN", "")
    quickRequest
      .get(uri"https://adventofcode.com/$year/day/$day/input")
      .cookie("session", token)
      .send()

  private def saveToCache(cacheFile: Path, content: String): Unit =
    Files.createDirectories(cacheFile.getParent)
    Files.writeString(cacheFile, content)

  private def readDotEnv(path: String = "../.env") =
    Files
      .readAllLines(Paths.get(path))
      .asScala
      .filter(_.contains("="))
      .map(_.split("=", 2))
      .collect:
        case Array(k, v) => k.trim -> v.trim
      .toMap
