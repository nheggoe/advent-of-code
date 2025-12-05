package dev.nheggoe
package util

object Benchmark {

  def run[A](label: String, runs: Int = 10, warmupMs: Int = 2000)(
      block: => A
  ): A = {
    val result = block

    val start = System.nanoTime()
    var warmupCount = 0
    while ((System.nanoTime() - start) / 1e6 < warmupMs && warmupCount < 50) {
      block
      warmupCount += 1
    }

    val times = (1 to runs).map { _ =>
      val t0 = System.nanoTime()
      block
      (System.nanoTime() - t0) / 1e6
    }

    println(
      f"$label: min=${times.min}%.2f ms, avg=${times.sum / runs}%.2f ms, max=${times.max}%.2f ms (warmup=$warmupCount)"
    )
    result
  }

}
