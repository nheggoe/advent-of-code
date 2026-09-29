package dev.nheggoe.aoc

object Benchmark {

  def run[A](label: String, runs: Int = 64, warmupMs: Int = 4096)(
      block: => A
  ): A = {
    val result = block

    val start = System.nanoTime()
    while (System.nanoTime() - start) / 1e6 < warmupMs do {
      val _ = block
    }

    val times = (1 to runs).map { _ =>
      val t0 = System.nanoTime()
      block
      (System.nanoTime() - t0) / 1e3 // µs
    }

    println(
      f"[bench $label] min=${times.min}%.2f µs, avg=${times.sum / runs}%.2f μs, max=${times.max}%.2f µs"
    )
    result
  }

}
