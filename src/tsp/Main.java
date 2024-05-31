package tsp;

import java.util.Arrays;

import static tsp.DistanceMatrixGenerator.saveMatrixToFile;

public class Main {
    public static void main(String[] args) {

        //String fileName = new String("D:/an2sem2/AI/HA_CR2.1A_bataiosu_corina/src/tsp/results.txt");

        int[][] distanceMatrix = DistanceMatrixGenerator.generateRandomMatrix(10);
        //saveMatrixToFile(distanceMatrix, fileName);

        DepthFirstSearch dfs = new DepthFirstSearch(distanceMatrix);
        long startTime1 = System.nanoTime();
        int[] solutionDFS = dfs.solve();
        long endTime1 = System.nanoTime();
        double elapsedTime1 = (double) (endTime1 - startTime1) / 1_000_000_000;
        String formattedTime1 = String.format("%.9f", elapsedTime1);
        System.out.println("---------------------DEPTH FIRST SEARCH---------------------------");
        System.out.println("Best route using depth first search: " + Arrays.toString(solutionDFS));
        System.out.println("Minimum longest distance: " + dfs.getBestLongestDistance());
        System.out.println("Path distance: " + dfs.getRouteDistance());
        System.out.println("Time: " + formattedTime1);
        //DepthFirstSearch.saveToFile(fileName, solutionDFS, dfs.getBestLongestDistance(), dfs.getRouteDistance(), formattedTime1);

        LeastCostSearch lcs = new LeastCostSearch(distanceMatrix);
        long startTime2 = System.nanoTime();
        int[] solutionLCS = lcs.solve();
        long endTime2 = System.nanoTime();
        double elapsedTime2 = (double) (endTime2 - startTime2) / 1_000_000_000;
        String formattedTime2 = String.format("%.9f", elapsedTime2);
        System.out.println("---------------------LEAST COST SEARCH---------------------------");
        System.out.println("Best route using least cost search: " + Arrays.toString(solutionLCS));
        System.out.println("Minimum longest distance: " + lcs.getBestLongestDistance());
        System.out.println("Path distance: " + lcs.getRouteDistance());
        System.out.println("Time: " + formattedTime2);
        //LeastCostSearch.saveToFile(fileName, solutionLCS, lcs.getBestLongestDistance(), lcs.getRouteDistance(), formattedTime2);

        AStarSearch ass = new AStarSearch(distanceMatrix);
        long startTime3 = System.nanoTime();
        int[] solutionASS = ass.solve();
        long endTime3 = System.nanoTime();
        double elapsedTime3 = (double) (endTime3 - startTime3) / 1_000_000_000;
        String formattedTime3 = String.format("%.9f", elapsedTime3);
        System.out.println("---------------------A* SEARCH---------------------------");
        System.out.println("Best route using a* search: " + Arrays.toString(solutionASS));
        System.out.println("Minimum longest distance: " + ass.getBestLongestDistance());
        System.out.println("Path distance: " + ass.getRouteDistance());
        System.out.println("Time: " + formattedTime3);
        //AStarSearch.saveToFile(fileName, solutionASS, ass.getBestLongestDistance(), ass.getRouteDistance(), formattedTime3);
    }
}