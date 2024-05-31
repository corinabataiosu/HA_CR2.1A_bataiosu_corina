package tsp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class DepthFirstSearch implements SearchAlgorithm {
    // variable that holds the number of cities
    private int numberOfCities;
    // the distance matrix that holds the distances between the cities
    private int[][] distanceMatrix;
    // an array that keeps track of the visited cities
    private boolean[] visited;
    // array that holds the best found path
    private int[] bestPath;
    // variable that holds the minimum of the longest distance between two consecutive cities in the path
    private int bestLongestDistance;
    // variable that holds the distance of the bestPath
    private int bestPathDistance;

    // constructor for the DepthFirstSearch class
    // it takes as parameter the distanceMatrix
    DepthFirstSearch(int[][] distanceMatrix) {
        // initialise the distanceMatrix and calculate the number of cities based on its size
        this.distanceMatrix = distanceMatrix;
        this.numberOfCities = distanceMatrix.length;
        this.visited = new boolean[numberOfCities];
        // initialise the bestPath array with numOfCities + 1 elements
        // because we need to return to the origin city and store it in the path
        this.bestPath = new int[numberOfCities + 1];
        // fill the bestPath with -1 as we will update the values later
        Arrays.fill(bestPath, -1);
        // set the bestLongestDistance to a high value
        this.bestLongestDistance = Integer.MAX_VALUE;
        // initially, the distance is 0
        this.bestPathDistance = 0;
    }

    // method for starting the solving of TSP using DFS
    // the method returns the best path found
    @Override
    public int[] solve() {
        // an array to store the current path
        int[] currentPath = new int[numberOfCities + 1];
        // visiting the first city and calling the dfs method
        visited[0] = true;

        depth_first_search(0, 1, currentPath, 0, 0);

        return bestPath;
    }

    // dfs method for exploring the paths
    private void depth_first_search(int currentCity, int count, int[] currentPath, int maxDist, int currentPathDist) {
        // adding the current city to the current path
        currentPath[count - 1] = currentCity;

        // we need to verify if all the cities have been visited
        // (the number of cities in the path is equal to the total number of the cities)
        if (count == numberOfCities) {
            // returnDist is the distance from the last city to be visited to the origin city
            int returnDist = distanceMatrix[currentCity][0];
            // finalMaxDist holds the maximum between the minimization of the longest distance
            // between 2 consecutive cities and the returnDist
            int finalMaxDist = Math.max(maxDist, returnDist);
            // add the return distance to the path's distance value
            int totalDist = currentPathDist + returnDist;

            // if the longest distance between 2 consecutive cities is smaller than the one found so far
            if (finalMaxDist < bestLongestDistance) {
                // update the longest distance and the total distance of the path
                bestLongestDistance = finalMaxDist;
                bestPathDistance = totalDist;
                // update the bestPath array
                // the first numOfCities elements of the currentPath will be copied to the bestPath array from index 0
                System.arraycopy(currentPath, 0, bestPath, 0, numberOfCities);
                // adding the origin city to the final of the path
                bestPath[numberOfCities] = 0;
            }
            return;
        }

        // exploring the unvisited cities
        for (int nextCity = 0; nextCity < numberOfCities; nextCity++) {
            // if the nextCity hasn't been visited and the distance is not 0
            if (!visited[nextCity] && distanceMatrix[currentCity][nextCity] != 0) {
                // mark the city as visited
                visited[nextCity] = true;
                // run dfs for the next city
                depth_first_search(nextCity, count + 1, currentPath, Math.max(maxDist, distanceMatrix[currentCity][nextCity]),
                        currentPathDist + distanceMatrix[currentCity][nextCity]);
                // after running dfs for the next city, we set it as unvisted
                // we do that, so we can visit it again later when exploring other paths
                visited[nextCity] = false;
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

    public static void saveToFile(String filePath, int[] solution, int bestLongestDistance, int bestPathDistance, String time) {
        try {
            // open the file in append mode
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));

            writer.write("---------------------DEPTH FIRST SEARCH---------------------------");
            writer.newLine();

            // close the file
            writer.close();
        } catch (IOException e) {
            System.err.println("ERROR! Data could not be saved inside the file. " + e.getMessage());
        }

        SearchAlgorithm.saveToFile(filePath, solution, bestLongestDistance, bestPathDistance, time);
    }
}