package dev.nheggoe.aoc

import com.sun.org.apache.xalan.internal.lib.ExsltDatetime.year
import sttp.client4.Response
import sttp.client4.quick.*

import java.nio.file.{Files, Path, Paths}
import scala.jdk.CollectionConverters.*

object InputFetcher {

  def fetchInput(using date: Date): String = {
    val inputFile = date.toPath

    if Files.exists(inputFile)
    then Files.readString(inputFile)
    else
      val input = fetchFromInternet(date).body
      if input.toLowerCase().contains("please log in")
      then
        throw new IllegalStateException(
          "Please refresh the AOC cookie in `.env`"
        )
      else println("Fetched puzzle input from internet")
      saveToCache(inputFile, input)
      input
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
    Files.writeString(cacheFile, content)
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
