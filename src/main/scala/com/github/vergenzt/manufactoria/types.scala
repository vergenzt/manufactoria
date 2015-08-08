package com.github.vergenzt.manufactoria

package object types {
  type Vec = (Int, Int)

  implicit class VecOps(rc: Vec) {
    @inline private def r: Int = rc._1
    @inline private def c: Int = rc._2

    def +(uv: Vec): Vec = (r + uv._1, c + uv._2)
    def *(i: Int): Vec = (r * i, c * i)
    def dot(uv: Vec): Int = (r * uv._1) + (c * uv._2)
    def perpendicularTo(that: Vec): Boolean = (this dot that) == 0
    def left: Vec = (-c, r)
    def right: Vec = (c, -r)
    def isZero: Boolean = (r == 0) && (c == 0)

    def boundedBy(size: (Int,Int)): Boolean =
      (0 < r) && (r <= size._1) &&
      (0 < c) && (c <= size._2)
  }

  object Cardinals {
    object N extends Vec(-1, 0)
    object S extends Vec( 1, 0)
    object E extends Vec( 0, 1)
    object W extends Vec( 0,-1)
  }

  sealed trait Dot
  object Dot {
    case object B extends Dot
    case object R extends Dot
    case object G extends Dot
    case object Y extends Dot
  }

  type Code = Seq[Dot]

  type Decision = Boolean
  val Accept: Decision = true
  val Reject: Decision = false

  case class State(code: Code, pos: Vec, vel: Vec, decision: Option[Decision]) {
    def moved(direction: Vec): State = this.copy(pos = pos + direction, vel = direction)
  }
}
