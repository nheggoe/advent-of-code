package dev.nheggoe.aoc24

import zio.*
import zio.http.*

import java.nio.file.{Files, Paths}
import scala.jdk.CollectionConverters.*

object InputFetcher extends ZIOAppDefault:

  private val url =
    URL.decode("https://adventofcode.com/2024/day/1/input").toOption.get

  def readDotEnv(
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

  val program: ZIO[Client & Scope, Throwable, Unit] = for
    env <- readDotEnv()
    token <- ZIO
      .fromOption(env.get("SESSION_TOKEN"))
      .orElseFail(new RuntimeException("Missing AOC_SESSION in .env"))
    response <- Client.request(
      Request
        .get(url)
        .addHeader("Cookie", s"session=$token")
        .addHeader("User-Agent", "Scala ZIO HTTP Client")
    )
    body <- response.body.asString
    _ <- Console.printLine(body)
  yield ()

  override def run: ZIO[Any, Throwable, Unit] =
    program.provide(Client.default, Scope.default)
