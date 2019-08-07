"# Mars Rover" 

A program that simulates a pars rover attempting to move around a grid. It can only move on the cardinal directions NESW. Each movement is considered to be the same distance. The rover can only execute the manouvers of:
- go forwards (1 square)
- turn clockwise (90 degrees)
- turn anticlockwise (90 degrees)

Also included is an attempt to implement an autopilot that implements Dijkstra's algorithm to calculate the shortest route.
It does this by:
- getting a set of all available nodes
- creating sets of visited nodes and unvisited nodes.
- from a start point, find all the (invisited) neighbours, add them to a set
- iterate over the neighbours, check if they are the end, if not add them to visited with the "source" node and add their (unvisited) neighbours to the unvisited set
- continue until the endpoint is found, then stop
- once the endpoint is found, it and all visited nodes (and where they are sourced from) are used to reverse engineer the fastest route, by taking the endpoint's source node, then its source node, etc.
- Number of instructions for the rover have not so far been considered. These would be added in a future step.

This is an sbt project, with some test coverage. To run them, check out the project and run 

`sbt test`
