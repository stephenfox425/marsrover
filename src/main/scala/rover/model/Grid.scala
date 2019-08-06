package rover.model

case class Grid(maxXCoord: Int, maxYCoord: Int) {
  def getWholeGrid: Set[Position] = {
    for {
      currentX <- 0 to maxXCoord
      currentY <- 0 to maxYCoord
    } yield (Position(currentX, currentY))
  }.toSet
}
