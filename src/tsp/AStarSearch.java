package tsp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import java.util.*;

public class AStarSearch implements SearchAlgorithm {
    // variable that holds the number of cities
    private int numberOfCities;
    // the distance matrix that holds the distances between the cities
    private int[][] distanceMatrix;
    // array that holds the best found path
    private int[] bestPath;
    // variable that holds the minimum of the longest distance between two consecutive cities in the path
    private int bestLongestDistance;
    // variable that holds the distance of the bestPath
    private int bestPathDistance;
    // a priority queue to hold the nodes
    PriorityQueue<Node> priorityQueue;

    // constructor for the AStarSearch class
    // it takes as parameter the distanceMatrix
    AStarSearch(int[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
        this.numberOfCities = distanceMatrix.length;
        this.bestPath = new int[numberOfCities + 1];
        Arrays.fill(bestPath, -1);
        this.bestLongestDistance = Integer.MAX_VALUE;
        this.bestPathDistance = Integer.MAX_VALUE;
        priorityQueue = new PriorityQueue<>();
    }

    // public method for starting the solving of TSP using A*
    // the method returns the best path found
    @Override
    public int[] solve() {

        // start with an initial node at the origin city
        // mark it as visited, and add it to the priority queue
        Node startNode = new Node(0, new boolean[numberOfCities], new int[numberOfCities + 1], 0, 0, 1);
        startNode.visited[0] = true;
        priorityQueue.add(startNode);

        a_star_search();

        return bestPath;
    }

    private void a_star_search() {
        // while the priority queue is not empty
        while (!priorityQueue.isEmpty()) {

            // extract the node with the least f value from the priority queue
            Node currentNode = priorityQueue.poll();

            // if all the cities have been visited, calculate the total cost including the return to the starting city
            if (currentNode.count == numberOfCities) {
                int returnDist = distanceMatrix[currentNode.currentCity][0];
                int finalMaxDist = Math.max(currentNode.maxDist, returnDist);
                int totalDist = currentNode.currentPathDist + returnDist;

                // update the best path if a smaller bestLongestDistance has been found
                if (finalMaxDist < bestLongestDistance) {
                    bestLongestDistance = finalMaxDist;
                    bestPathDistance = totalDist;
                    System.arraycopy(currentNode.currentPath, 0, bestPath, 0, numberOfCities);
                    bestPath[numberOfCities] = 0;
                }
                return;
            }

            // for each unvisited adjacent city, create a new node with updated path and cost
            // and add it to the priority queue
            for (int nextCity = 0; nextCity < numberOfCities; nextCity++) {
                if (!currentNode.visited[nextCity] && distanceMatrix[currentNode.currentCity][nextCity] != 0) {

                    boolean[] visited = Arrays.copyOf(currentNode.visited, numberOfCities);
                    visited[nextCity] = true;

                    int[] currentPath = Arrays.copyOf(currentNode.currentPath, numberOfCities + 1);
                    currentPath[currentNode.count] = nextCity;

                    int newMaxDist = Math.max(currentNode.maxDist, distanceMatrix[currentNode.currentCity][nextCity]);
                    int newPathDist = currentNode.currentPathDist + distanceMatrix[currentNode.currentCity][nextCity];

                    priorityQueue.add(new Node(nextCity, visited, currentPath, newMaxDist, newPathDist, currentNode.count + 1));
                }
            }
        }
    }

    // getter for the bestLongestDistance
    public int getBestLongestDistance() {
        return bestLongestDistance;
    }

    // getter for the bestPathDistance
    public int getRouteDistance() {
        return bestPathDistance;
    }

    // intern class for the node used in the priority queue
    private class Node implements Comparable<Node> {
        int currentCity;
        boolean[] visited;
        int[] currentPath;
        int maxDist;
        int currentPathDist;
        int count;

        // constructor for the Node class
        Node(int currentCity, boolean[] visited, int[] currentPath, int maxDist, int currentPathDist, int count) {
            this.currentCity = currentCity;
            this.visited = visited;
            this.currentPath = currentPath;
            this.maxDist = maxDist;
            this.currentPathDist = currentPathDist;
            this.count = count;
        }

        // override the compareTo method for the PriorityQueue
        @Override
        public int compareTo(Node other) {
            // the value of f is the sum between the current distance of the path and the heuristic function
            // f(n) = g(n) + h(n)
            // g(n) - the value of the distance of the current path
            // h(n) - the minimum of the distance to the nearest unvisited node
            int thisF = this.currentPathDist + heuristic();
            int otherF = other.currentPathDist + other.heuristic();
            return Integer.compare(thisF, otherF);
        }

        // the heuristic method returns the minimum of the distance from the last node in the path to the nearest unvisited node
        private int heuristic() {
            int minDistance = Integer.MAX_VALUE;
            for (int i = 0; i < numberOfCities; i++) {
                if (!visited[i]) {
                    minDistance = Math.min(minDistance, distanceMatrix[currentCity][i]);
                }
            }
            return minDistance;
        }
    }

    public static void saveToFile(String filePath, int[] solution, int bestLongestDistance, int bestPathDistance) {
        try {
            // open the file in append mode
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));

            writer.write("---------------------A* SEARCH---------------------------");
            writer.newLine();

            // close the file
            writer.close();
        } catch (IOException e) {
            System.err.println("ERROR! Data could not be saved inside the file. " + e.getMessage());
        }

        SearchAlgorithm.saveToFile(filePath, solution, bestLongestDistance, bestPathDistance);

        try {
            // open the file in append mode
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));

            writer.newLine();
            writer.newLine();

            // close the file
            writer.close();
        } catch (IOException e) {
            System.err.println("ERROR! " + e.getMessage());
        }
    }
}