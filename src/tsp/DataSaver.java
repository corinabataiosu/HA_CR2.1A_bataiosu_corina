package tsp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class DataSaver {
    static void saveToFileDist(String filePath, int numberOfCities, int dfs, int lcs, int a) {
        try {
            // open the file in append mode
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));

            writer.write(numberOfCities + "\t " + dfs + "\t " + lcs + "\t " + a);
            writer.newLine();

            // close the file
            writer.close();
        } catch (IOException e) {
            System.err.println("ERROR! Data could not be saved inside the file. " + e.getMessage());
        }
    }

    static void saveToFileTime(String filePath, int numberOfCities, String dfs, String lcs, String a) {
        try {
            // open the file in append mode
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));

            writer.write(numberOfCities + "\t " + dfs + "\t " + lcs + "\t " + a);
            writer.newLine();

            // close the file
            writer.close();
        } catch (IOException e) {
            System.err.println("ERROR! Data could not be saved inside the file. " + e.getMessage());
        }
    }
}
