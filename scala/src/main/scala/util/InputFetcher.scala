package dev.nheggoe
package util

import zio.*
import zio.http.*

import java.nio.file.{Files, Path, Paths}
import scala.jdk.CollectionConverters.*

object InputFetcher:

  private val cacheDir = "cache"

  def fetchInput(year: Int, day: Int): Seq[String] =
    def load: String =
      val cacheFile = Path.of(cacheDir, year.toString, s"day$day.txt")
      if Files.exists(cacheFile) then Files.readString(cacheFile)
      else
        val input = fetchFromInternet(year, day)
        println("Fetched puzzle input from internet")
        saveToCache(cacheFile, input)
        input

    load.split("\n").map(_.trim).toSeq

  private def fetchFromInternet(year: Int, day: Int): String =
    val program = ZIO
      .scoped:
        for
          env <- readDotEnv()
          token <- ZIO
            .fromOption(env.get("SESSION_TOKEN"))
            .orElseFail(new RuntimeException("Missing SESSION_TOKEN in .env"))
          url <- ZIO
            .fromOption(
              URL
                .decode(s"https://adventofcode.com/$year/day/$day/input")
                .toOption
            )
            .orElseFail(new RuntimeException("Invalid URL"))
          response <- Client.batched(
            Request
              .get(url)
              .addHeader("Cookie", s"session=$token")
              .addHeader("User-Agent", "Scala ZIO HTTP Client")
          )
          body <- response.body.asString
        yield body.trim
      .provide(Client.default)

    Unsafe.unsafe:
      implicit unsafe =>
        Runtime.default.unsafe.run(program).getOrThrowFiberFailure()

  private def saveToCache(cacheFile: Path, content: String): Unit =
    Files.createDirectories(cacheFile.getParent)
    Files.writeString(cacheFile, content)

  private def readDotEnv(
      path: String = "../.env"
  ): ZIO[Any, Throwable, Map[String, String]] =
    ZIO.attempt:
      Files
        .readAllLines(Paths.get(path))
        .asScala
        .filter(_.contains("="))
        .map(_.split("=", 2))
        .collect:
          case Array(k, v) => k.trim -> v.trim
        .toMap
