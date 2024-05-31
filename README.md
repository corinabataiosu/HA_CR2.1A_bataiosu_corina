The code contains :

SearchAlgorithm.java - an interface used for abstraction

DepthFirstSearch.java - implementation for the DFS algorithm (implements the SearchAlgorithm interface)

LeastCostSearch.java - implementation for the LCS/UCS algorithm (implements the SearchAlgorithm interface)

AStarSearch.java - implementation for the A* algorithm (implements the SearchAlgorithm interface)


DistanceMatrixGenerator.java - class used for the random generation of the distance matrix based on a number of cities


DataSaver.java - class used to save the data for the distance of the paths and the time taken to find the respective path (used for charts) (saves inside data.txt and time.txt)


results.txt - file in which we save the results in a clear, easy to read way

data.txt - file for the distances (a line contains "number of nodes - path using DFS - path using LCS - path using A*")

time.txt - file for the time (a line contains "number of nodes - time using DFS - time using LCS - time using A*")


Main.java - the main method


The code should run as it is, but without writing in the specified files. For writing in the files, please uncomment the commented lines in the Main.java method and change the path
