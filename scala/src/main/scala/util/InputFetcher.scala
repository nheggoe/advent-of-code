package dev.nheggoe
package util

import zio.*
import zio.http.*

import java.nio.file.{Files, Path, Paths}
import scala.jdk.CollectionConverters.*

object InputFetcher:

  private val cacheDir = "cache"

  def fetchInput(year: Int, day: Int): String =
    val cacheFile = Path.of(cacheDir, year.toString, s"day$day.txt")

    // Check if cached file exists
    if Files.exists(cacheFile) then
      // println(s"Reading from cache: $cacheFile")
      Files.readString(cacheFile)
    else
      val input = fetchFromInternet(year, day)
      // println(s"Fetching from internet for year $year, day $day")
      saveToCache(cacheFile, input)
      input

  private def fetchFromInternet(year: Int, day: Int): String =
    val program = ZIO
      .scoped {
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
          response <- Client.request(
            Request
              .get(url)
              .addHeader("Cookie", s"session=$token")
              .addHeader("User-Agent", "Scala ZIO HTTP Client")
          )
          body <- response.body.asString
        yield body.trim
      }
      .provide(Client.default)

    Unsafe.unsafe { implicit unsafe =>
      Runtime.default.unsafe.run(program).getOrThrowFiberFailure()
    }

  private def saveToCache(cacheFile: Path, content: String): Unit =
    // Create parent directories if they don't exist
    Files.createDirectories(cacheFile.getParent)
    Files.writeString(cacheFile, content)
    println(s"Saved to cache: $cacheFile")

  private def readDotEnv(
      path: String = "../.env"
  ): ZIO[Any, Throwable, Map[String, String]] =
    ZIO.attempt {
      Files
        .readAllLines(Paths.get(path))
        .asScala
        .filter(_.contains("="))
        .map(_.split("=", 2))
        .collect { case Array(k, v) => k.trim -> v.trim }
        .toMap
    }
