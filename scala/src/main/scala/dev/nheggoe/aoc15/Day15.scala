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

    def score: Long = {
      math.max(0L, capacity)
        * math.max(0L, durability)
        * math.max(0L, flavor)
        * math.max(0L, texture)
    }

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
    def mix(amounts: List[Int]): Ingredient = {
      ingredients
        .zip(amounts)
        .map((ingredient, amount) => ingredient.scale(amount))
        .reduce(_.merge(_))
    }

  def splits(total: Int, parts: Int): Iterator[List[Int]] = {
    if parts == 1 then Iterator(List(total))
    else
      (0 to total).iterator.flatMap: i =>
        splits(total - i, parts - 1).map(i :: _)

  }

  def partOne(using Input): Long =
    val ingredients = lines.map(Ingredient(_)).toList
    splits(100, ingredients.size).map(ingredients.mix).map(_.score).max

  def partTwo(using Input): Long =
    val ingredients = lines.map(Ingredient(_)).toList
    val scores = for {
      combination <- splits(100, ingredients.size)
      mixed = ingredients.mix(combination)
      if mixed.calories == 500
    } yield mixed.score
    scores.max
}
