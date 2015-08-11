package com.github.vergenzt.manufactoria.domain

import scala.collection.mutable
import types._

class Solution(base: Machine) extends Machine {
  /* Use the size of the base. */
  override def size: Vec = base.size

  /* Use base components where defined, otherwise this solutions components. */
  override def components: Map[Vec,Component] =
    base.components.withDefault(modifiableComponents)

  /** This solution's modifiable map of components. */
  protected val modifiableComponents = mutable.Map[Vec,Component]()

}
