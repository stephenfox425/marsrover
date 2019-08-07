package rover.controller

import rover.model.{Grid, Position, PositionWithPrevious}

class Autopilot {

  def calculate(grid: Grid, startPosition: Position, endPosition: Position): Seq[Position] = {
    val unvisitedSet: Set[Position] = grid.getWholeGrid
    val visitedSet: Set[Position] = Set.empty

    val endNodeWithPrevious: EndNodeWithVisitedSet = visitNode(grid, unvisitedSet, visitedSet, PositionWithPrevious(startPosition, None),
      Set.empty, Set.empty, endPosition)

    return reverseEngineerRoute(endNodeWithPrevious, startPosition)
  }

  def visitNode(grid: Grid, unvisitedSet: Set[Position], visitedSet: Set[Position], currentPositionWP: PositionWithPrevious,
                neighboursToVisit: Set[PositionWithPrevious], visitedSetWP: Set[PositionWithPrevious], endPosition: Position): EndNodeWithVisitedSet = {
    val currentPosition = currentPositionWP.thisPosition
    if(currentPosition.equals(endPosition)) EndNodeWithVisitedSet(currentPositionWP, visitedSetWP)
    else {
      val correctedNeighbours: Set[Position] = currentPosition.getNeighbours.map(_.correctPosition(grid))
      val unvisitedNeighbours: Set[Position] = correctedNeighbours.filterNot(neighbour => visitedSet.contains(neighbour))
      val neighboursWithPrevious: Set[PositionWithPrevious] = unvisitedNeighbours.map(PositionWithPrevious(_, Some(currentPosition)))
      val newNeighboursToVisit: Set[PositionWithPrevious] = neighboursToVisit ++ neighboursWithPrevious

      //remove the current node from the unvisited set, and add it as visited
      val nextVisitedSet = visitedSet + currentPosition
      val nextUnvisitedSet: Set[Position] = unvisitedSet.filterNot(position => position equals currentPositionWP)

      val nextPositionToVisitWP: PositionWithPrevious = newNeighboursToVisit.headOption.getOrElse(currentPositionWP)
      val newNeighboursToVisitTail = newNeighboursToVisit - nextPositionToVisitWP

      visitNode(grid, nextUnvisitedSet, nextVisitedSet, nextPositionToVisitWP, newNeighboursToVisitTail, visitedSetWP + currentPositionWP, endPosition)
    }
  }

  def reverseEngineerRoute(endNodeWithVisitedSet: EndNodeWithVisitedSet, startPosition: Position): List[Position] = {
    val routeFromEnd: List[Position] = stepBackwards(endNodeWithVisitedSet, List.empty, startPosition: Position)
    routeFromEnd.reverse
  }

  def stepBackwards(nodeWithVisitedSet: EndNodeWithVisitedSet, route: List[Position], startPosition: Position): List[Position] = {
    if(nodeWithVisitedSet.endNode.thisPosition.equals(startPosition)) route.appended(nodeWithVisitedSet.endNode.thisPosition)
    else {
      val thisNode = nodeWithVisitedSet.endNode

      val nextToVisit: PositionWithPrevious = nodeWithVisitedSet.visitedSetWP.filterNot(node => node.thisPosition.equals(thisNode.previousPosition)).head
      val listWithoutNextNode = nodeWithVisitedSet.visitedSetWP.filter(node => node.thisPosition.equals(thisNode.previousPosition))

      stepBackwards(EndNodeWithVisitedSet(nextToVisit, listWithoutNextNode), route.appended(thisNode.thisPosition), startPosition)
    }
  }

}

case class EndNodeWithVisitedSet(endNode: PositionWithPrevious, visitedSetWP: Set[PositionWithPrevious])