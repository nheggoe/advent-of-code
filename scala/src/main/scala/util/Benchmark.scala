package dev.nheggoe
package util

object Benchmark {

  def run[A](label: String, runs: Int = 64, warmupMs: Int = 4096)(
      block: => A
  ): A = {
    val result = block

    val start = System.nanoTime()
    var warmupCount = 0
    while ((System.nanoTime() - start) / 1e6 < warmupMs) {
      block
      warmupCount += 1
    }

    val times = (1 to runs).map { _ =>
      val t0 = System.nanoTime()
      block
      (System.nanoTime() - t0) / 1e3 // µs
    }

    println(
      f"$label: min=${times.min}%.2f µs, avg=${times.sum / runs}%.2f μs, max=${times.max}%.2f µs"
    )
    result
  }

}
