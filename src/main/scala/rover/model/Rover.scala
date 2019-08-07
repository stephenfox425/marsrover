package rover.model

import rover.model.Facing._

case class Rover(facing: Facing, position: Position, grid: Grid) {
  def moveForward() = {
    val newPosition = facing match {
      case North => Position(position.xCoord, position.yCoord + 1)
      case East => Position(position.xCoord + 1, position.yCoord)
      case South => Position(position.xCoord, position.yCoord - 1)
      case West => Position(position.xCoord -1, position.yCoord)
    }
    Rover(facing, newPosition.correctPosition(grid), grid)
  }



  def rotateClockWise(): Rover = {
    val newFacing = facing match {
      case North => East
      case East => South
      case South => West
      case West => North
    }
    Rover(newFacing, position, grid)
  }

  def rotateAntiClockWise(): Rover = {
    val newFacing = facing match {
      case North => West
      case East => North
      case South => East
      case West => South
    }
    Rover(newFacing, position, grid)
  }
}
