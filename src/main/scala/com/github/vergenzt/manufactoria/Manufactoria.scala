package com.github.vergenzt.manufactoria

import types._

object Manufactoria {
  /** Run an example solution. */
  def main(args: Array[String]): Unit = {
    import Cardinals._
    import Dot._

    val solution = new Machine {
      override val size = (3, 1)
      override val components = Map(
        (1,1) -> Start(S),
        (2,1) -> Branch(E, R, B),
        (3,1) -> End()
      )
    }

    println(solution.toString())

    println(Examples.StartsWithBlue.checkMachine(solution))
  }
}
