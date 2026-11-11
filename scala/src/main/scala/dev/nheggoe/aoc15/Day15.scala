package dev.nheggoe.aoc15

import dev.nheggoe.aoc.{AocDay, Input, St}

object Day15 extends AocDay(15) {

  case class Ingredient(
      capacity: Int,
      durability: Int,
      flavor: Int,
      texture: Int,
      calories: Int
  ) {
    def merge(that: Ingredient): Ingredient = {
      Ingredient(
        capacity = capacity + that.capacity,
        durability = durability + that.durability,
        flavor = flavor + that.flavor,
        texture = texture + that.texture,
        calories = calories + that.calories,
      )
    }
    def scale(n: Int): Ingredient = Ingredient(
      capacity * n,
      durability * n,
      flavor * n,
      texture * n,
      calories * n
    )

  }

  object Ingredient {
    // example input: Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3
    //                ^^^^^^^^^^^^^^^^^^^
    private type Parser[A] = St[A, String]

    private val number: Parser[Int] = St[Int, String] { s =>
      val rest = s.dropWhile(c => c != '-' && !c.isDigit)
      val (digits, after) = rest.span(c => c == '-' || c.isDigit)
      (digits.toInt, after)
    }

    def apply(s: String): Ingredient = {
      val parser: Parser[Ingredient] = for {
        capacity <- number
        durability <- number
        flavor <- number
        texture <- number
        calories <- number
      } yield Ingredient(capacity, durability, flavor, texture, calories)

      parser.run(s).value
    }
  }

  extension (ingredients: List[Ingredient])
    def score(amounts: List[Int]): Long = {
      val total = ingredients
        .zip(amounts)
        .map((ingredient, amount) => ingredient.scale(amount))
        .reduce(_.merge(_))
      math.max(0L, total.capacity)
        * math.max(0L, total.durability)
        * math.max(0L, total.flavor)
        * math.max(0L, total.texture)
    }

  def splits(total: Int, parts: Int): Iterator[List[Int]] = {
    if parts == 1 then Iterator(List(total))
    else
      (0 to total).iterator.flatMap: i =>
        splits(total - i, parts - 1).map(i :: _)

  }

  def partOne(using Input): Long =
    val ingredients = lines.map(Ingredient(_)).toList
    splits(100, ingredients.size).map(ingredients.score).max

  def partTwo(using Input): Long = ???
}
