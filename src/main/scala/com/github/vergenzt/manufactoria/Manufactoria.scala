package com.github.vergenzt.manufactoria

import domain._
import domain.types._
import scala.scalajs.js.annotation.JSExportAll

@JSExportAll
object Manufactoria {

  /** Run an example solution. */
  def main(): Unit = {
    import Cardinals._
    import Dot._

    val solution = new Machine {
      override val size = (3, 1)
      override val components = Map(
        (1,1) -> Start(Cardinals.S),
        (2,1) -> Branch(E, R, B),
        (3,1) -> End()
      )
    }

    println(solution.toString())

    println(example.StartsWithBlue.checkMachine(solution))
  }
}
