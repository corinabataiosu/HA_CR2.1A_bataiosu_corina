package tsp;

import java.util.Arrays;

import static tsp.DistanceMatrixGenerator.saveMatrixToFile;

public class Main {
    public static void main(String[] args) {

        //String fileName = new String("D:/an2sem2/AI/HA_CR2.1A_bataiosu_corina/src/tsp/results.txt");

        int[][] distanceMatrix = DistanceMatrixGenerator.generateRandomMatrix(4);
        //saveMatrixToFile(distanceMatrix, fileName);

        DepthFirstSearch dfs = new DepthFirstSearch(distanceMatrix);
        int[] solutionDFS = dfs.solve();;
        System.out.println("---------------------DEPTH FIRST SEARCH---------------------------");
        System.out.println("Best route using depth first search: " + Arrays.toString(solutionDFS));
        System.out.println("Minimum longest distance: " + dfs.getBestLongestDistance());
        System.out.println("Path distance: " + dfs.getRouteDistance());
        //DepthFirstSearch.saveToFile(fileName, solutionDFS, dfs.getBestLongestDistance(), dfs.getRouteDistance());

        LeastCostSearch lcs = new LeastCostSearch(distanceMatrix);
        int[] solutionLCS = lcs.solve();
        System.out.println("---------------------LEAST COST SEARCH---------------------------");
        System.out.println("Best route using least cost search: " + Arrays.toString(solutionLCS));
        System.out.println("Minimum longest distance: " + lcs.getBestLongestDistance());
        System.out.println("Path distance: " + lcs.getRouteDistance());
        //LeastCostSearch.saveToFile(fileName, solutionLCS, lcs.getBestLongestDistance(), lcs.getRouteDistance());

        AStarSearch ass = new AStarSearch(distanceMatrix);
        int[] solutionASS = ass.solve();
        System.out.println("---------------------A* SEARCH---------------------------");
        System.out.println("Best route using a* search: " + Arrays.toString(solutionASS));
        System.out.println("Minimum longest distance: " + ass.getBestLongestDistance());
        System.out.println("Path distance: " + ass.getRouteDistance());
        //AStarSearch.saveToFile(fileName, solutionASS, ass.getBestLongestDistance(), ass.getRouteDistance());
    }
}