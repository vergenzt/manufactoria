package com.github.vergenzt.manufactoria

import types._

trait Problem {
  def description: String

  /** The starter machine with size constraints and fixed components. */
  def template: Machine

  /** A list of manually-specified test cases for the problem. */
  def testCases: Seq[Code]

  /** A function to compute the actual result for a test case. */
  def acceptFn(code: Code): Decision

  /**
   * Check whether the given solution solves the problem for all provided test cases.
   * @return Some(Reject) if any test cases fail, Some(Accept) if all pass, or None otherwise
   */
  def checkMachine(grid: Machine, maxIterations: Int = 10000): Decision =
    testCases.forall(code =>
      checkTestCase(grid, code, maxIterations) == Some(acceptFn(code))
    )

  /**
   * Check a solution on a given code sequence.
   * @return the decision, if one is made within the iteration limit, or None otherwise
   */
  def checkTestCase(grid: Machine, code: Code, maxIterations: Int = 10000): Option[Decision] = {
    val start = State(code, grid.startPos, (0,0), None)
    Iterator
      .iterate(start)(state => {
        grid(state.pos).step(state)
      })
      .map(state => {
        println(s"State: $state")
        state
      })
      .take(maxIterations)
      .collectFirst {
        case State(_, _, _, Some(decision)) => decision
      }
  }
}
