package rover.model

import org.scalatest.{FunSpec, Matchers}

class RoverSpec extends FunSpec with Matchers {

  import Facing._

  describe("The Mars Rover") {
    val basicGrid = Grid(5, 5)
    it("should move 1 step North when given the instruction") {
      val rover = Rover(North, Position(1, 1), basicGrid)
      rover.moveForward() shouldEqual Rover(North, Position(1, 2), basicGrid)
    }

    it("should move 1 step East when given the instruction") {
      val rover = Rover(East, Position(1, 1), basicGrid)
      rover.moveForward() shouldEqual Rover(East, Position(2, 1), basicGrid)
    }

    it("should move 1 step South when given the instruction") {
      val rover = Rover(South, Position(1, 1), basicGrid)
      rover.moveForward() shouldEqual Rover(South, Position(1, 0), basicGrid)
    }

    it("should move 1 step West when given the instruction") {
      val rover = Rover(West, Position(1, 1), basicGrid)
      rover.moveForward() shouldEqual Rover(West, Position(0, 1), basicGrid)
    }

    it("should rotate clockwise when given the instruction") {
      val rover = Rover(North, Position(1, 1), basicGrid)
      rover.rotateClockWise() shouldEqual Rover(East, Position(1, 1), basicGrid)
    }

    it("should rotate anticlockwise when given the instruction") {
      val rover = Rover(West, Position(1, 1), basicGrid)
      rover.rotateAntiClockWise() shouldEqual Rover(South, Position(1, 1), basicGrid)
    }

    it("should appear on the bottom of the grid when moving off the top") {
      val rover = Rover(North, Position(1, 5), basicGrid)
      rover.moveForward() shouldEqual Rover(North, Position(1, 0), basicGrid)
    }

    it("should appear on the top of the grid when moving off the bottom") {
      val rover = Rover(South, Position(1, 0), basicGrid)
      rover.moveForward() shouldEqual Rover(South, Position(1, 5), basicGrid)
    }

    it("should appear on the east of the grid when moving off the west") {
      val rover = Rover(West, Position(0, 1), basicGrid)
      rover.moveForward() shouldEqual Rover(West, Position(5, 1), basicGrid)
    }

    it("should appear on the west of the grid when moving off the east") {
      val rover = Rover(East, Position(5, 1), basicGrid)
      rover.moveForward() shouldEqual Rover(East, Position(0, 1), basicGrid)
    }
  }

}
