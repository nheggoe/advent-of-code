package dev.nheggoe.aoc

import sttp.client4.Response
import sttp.client4.quick.*

import java.nio.file.{Files, Path, Paths}
import scala.jdk.CollectionConverters.*

opaque type Input = String

object Input:
  def apply(s: String): Input = s

  extension (i: Input) def value: String = i

  given Conversion[String, Input] = Input(_)

object InputFetcher {

  def fetchInput(using date: Date): Input = {
    val inputFile = date.toPath

    if Files.exists(inputFile)
    then Input(Files.readString(inputFile))
    else
      val input = fetchFromInternet(date).body.strip()
      if input.toLowerCase().contains("please log in")
      then
        throw IllegalStateException("Please refresh the AOC cookie in `.env`")
      else println("Fetched puzzle input from internet")
      saveToCache(inputFile, input)
      Input(input)
  }

  extension (date: Date)
    private def toPath: Path =
      Path.of("cache", date.year.toDirectory, s"${date.day.toFileName}.txt")

  extension (year: Year)
    private def toDirectory: String =
      year.value.toString

  extension (day: Day)
    private def toFileName: String =
      f"day${day.value}%02d"

  private def fetchFromInternet(
      date: Date
  ): Response[String] = {
    val token: String = readDotEnv().get("SESSION_TOKEN") match
      case Some(t) => t
      case None    =>
        throw new IllegalStateException("SESSION_TOKEN not found in .env file")

    val Date(year, day) = date
    quickRequest
      .get(uri"https://adventofcode.com/${year.value}/day/${day.value}/input")
      .cookie("session", token)
      .send()
  }

  private def saveToCache(cacheFile: Path, content: String): Unit = {
    Files.createDirectories(cacheFile.getParent)
    val _ = Files.writeString(cacheFile, content)
  }

  private def readDotEnv(path: String = "../.env") = {
    Files
      .readAllLines(Paths.get(path))
      .asScala
      .filter(_.contains("="))
      .map(_.split("=", 2))
      .collect { case Array(k, v) =>
        k.trim -> v.trim
      }
      .toMap
  }
}
