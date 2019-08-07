package rover.controller

import org.scalatest.{FunSpec, Matchers}
import rover.model.{Grid, Position, PositionWithPrevious}


class AutopilotSpec extends FunSpec with Matchers {

  describe("An Autopilot") {
    it("should reverse engineer a route of 1 if endNode is next to start node") {
      val startPosition = Position(0,0)
      val endPosition = Position(0, 1)
      val endNodeWithVisitedSet: EndNodeWithVisitedSet = EndNodeWithVisitedSet(PositionWithPrevious(endPosition, Some(startPosition)),
        Set(PositionWithPrevious(startPosition, None)))
      val autopilot = new Autopilot()
      val route = autopilot.reverseEngineerRoute(endNodeWithVisitedSet, startPosition)
      route shouldEqual List(startPosition, endPosition)
    }

    it("should calculate the correct route given a grid and start and end position") {
      val startPosition = Position(0,0)
      val endPosition = Position(0, 1)
      val sampleGrid = Grid(3,3)
      val autopilot = new Autopilot()
      val route = autopilot.calculate(sampleGrid, startPosition, endPosition)
      route shouldEqual List(startPosition, endPosition)
    }
  }

}
