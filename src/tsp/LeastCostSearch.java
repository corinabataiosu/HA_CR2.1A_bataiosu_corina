package tsp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LeastCostSearch implements SearchAlgorithm {
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

    // constructor for the LeastCostSearch class
    // it takes as parameter the distanceMatrix
    public LeastCostSearch(int[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
        this.numberOfCities = distanceMatrix.length;
        this.bestPath = new int[numberOfCities + 1];
        Arrays.fill(bestPath, -1);
        this.bestLongestDistance = Integer.MAX_VALUE;
        this.bestPathDistance = 0;
        // the comparator is used to order the nodes in the queue based on the maxDist value
        // it makes sure that the node with the smallest maxDist value will be at the front of the queue
        this.priorityQueue = new PriorityQueue<>(Comparator.comparingInt(n -> n.currentPathDist));
    }

    // public method for starting the solving of TSP using uniform cost/least cost search
    // the method returns the best path found
    @Override
    public int[] solve() {

        // start with an initial node at the starting city
        // mark it as visited, and add it to the priority queue
        Node startNode = new Node(0, new boolean[numberOfCities], new int[numberOfCities + 1], 0, 0, 1);
        startNode.visited[0] = true;
        priorityQueue.add(startNode);

        least_cost_search();

        return bestPath;
    }

    private void least_cost_search() {
        // while the priority queue is not empty
        while (!priorityQueue.isEmpty()) {

            // extract the node with the least path cost from the priority queue
            Node currentNode = priorityQueue.poll();

            // if all the cities are visited, calculate the total cost including the return to the starting city
            if (currentNode.count == numberOfCities) {
                // returnDist is the distance from the last city to be visited to the origin city
                int returnDist = distanceMatrix[currentNode.currentCity][0];
                // finalMaxDist holds the maximum between the minimization of the longest distance
                // between 2 consecutive cities and the returnDist
                int finalMaxDist = Math.max(currentNode.maxDist, returnDist);
                // add the return distance to the path's distance value
                int totalDist = currentNode.currentPathDist + returnDist;

                // update the best path if this cost is lower and add the origin city to the final path
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
            for (int nextCity = 1; nextCity < numberOfCities; nextCity++) {
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
    private static class Node {
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
    }

    public static void saveToFile(String filePath, int[] solution, int bestLongestDistance, int bestPathDistance) {
        try {
            // open the file in append mode
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));

            writer.write("---------------------LEAST COST SEARCH---------------------------");
            writer.newLine();

            // close the file
            writer.close();
        } catch (IOException e) {
            System.err.println("ERROR! Data could not be saved inside the file. " + e.getMessage());
        }

        SearchAlgorithm.saveToFile(filePath, solution, bestLongestDistance, bestPathDistance);
    }
}