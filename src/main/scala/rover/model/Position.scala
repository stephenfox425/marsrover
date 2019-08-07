package rover.model

case class Position(xCoord: Int, yCoord: Int) {
  def getNeighbours(): Set[Position] = {
    Set(Position(xCoord + 1, yCoord),
      Position(xCoord -1, yCoord),
      Position(xCoord, yCoord + 1),
      Position(xCoord, yCoord))
  }

  def correctPosition(grid: Grid): Position =
    if(isStayingOnGrid(grid)) this
    else findNewWrapAroundPosition(grid)

  private def findNewWrapAroundPosition(grid: Grid): Position =
    if (xCoord < 0 ||
      xCoord >= grid.maxXCoord) {
      val newX = if (xCoord < 0) grid.maxXCoord else 0
      Position(newX, yCoord)
    } else {
      val newY = if (yCoord < 0) grid.maxYCoord else 0
      Position(xCoord, newY)
    }

  private def isStayingOnGrid(grid: Grid): Boolean =
    xCoord >= 0 &&
      xCoord < grid.maxXCoord &&
      yCoord >= 0 &&
      yCoord < grid.maxYCoord
}

case class PositionWithPrevious(thisPosition: Position, previousPosition: Option[Position])
