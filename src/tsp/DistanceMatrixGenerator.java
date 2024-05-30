package tsp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DistanceMatrixGenerator {
    // static method to generate a random distance matrix
    // the method has one parameter : the number of cities
    // it returns a matrix that holds the distances between every two cities
    public static int[][] generateRandomMatrix(int numberOfCities) {
        // initialize an array to hold the distance matrix
        int[][] distanceMatrix = new int[numberOfCities][numberOfCities];
        // create a Random object to generate random numbers
        Random random = new Random();

        // fill the matrix with random distances
        for (int i = 0; i < numberOfCities; i++) {
            for (int j = 0; j < numberOfCities; j++) {
                if (i == j) {
                    // distance from a city to itself is always 0
                    distanceMatrix[i][j] = 0;
                } else {
                    // generate a random distance between 1 and (bound + 1)
                    // we add 1 so that the distance is never 0
                    distanceMatrix[i][j] =  distanceMatrix[j][i] = random.nextInt(5) + 1;
                }
            }
        }

        printMatrix(distanceMatrix);

        // return the generated distance matrix
        return distanceMatrix;
    }

    // method to print the distance matrix
    public static void printMatrix(int[][] matrix) {
        // iterate over the rows and columns of the matrix and print the matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // static method to save the distanceMatrix inside a specified file
    public static void saveMatrixToFile(int[][] distanceMatrix, String filePath) {
        try {
            // open the file in append mode
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));

            // write the matrix in the file
            for (int[] row : distanceMatrix) {
                for (int element : row) {
                    writer.write(element + " ");
                }
                writer.newLine();
            }

            writer.newLine();
            // close the file
            writer.close();
        } catch (IOException e) {
            System.err.println("ERROR! Matrix could not be saved inside the file. " + e.getMessage());
        }
    }
}