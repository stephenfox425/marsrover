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
    Rover(facing, correctPosition(newPosition), grid)
  }

  def correctPosition(newPosition: Position): Position =
    if(isStayingOnGrid(newPosition)) newPosition
    else findNewWrapAroundPosition(newPosition)

  private def findNewWrapAroundPosition(newPosition: Position): Position =
    if (newPosition.xCoord < 0 ||
      newPosition.xCoord >= grid.maxXCoord) {
      val newX = if (newPosition.xCoord < 0) grid.maxXCoord else 0
      return Position(newX, newPosition.yCoord)
    } else {
      val newY = if (newPosition.yCoord < 0) grid.maxYCoord else 0
      Position(newPosition.xCoord, newY)
    }

  private def isStayingOnGrid(newPosition: Position): Boolean =
    newPosition.xCoord >= 0 &&
      newPosition.xCoord < grid.maxXCoord &&
      newPosition.yCoord >= 0 &&
      newPosition.yCoord < grid.maxYCoord

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
