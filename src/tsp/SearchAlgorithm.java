package tsp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public interface SearchAlgorithm {
    int[] solve();
    static void saveToFile(String filePath, int[] solution, int bestLongestDistance, int bestPathDistance, String time) {
        try {
            // open the file in append mode
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));

            writer.write("Best path: " + Arrays.toString(solution));
            writer.newLine();
            writer.write("Minimum longest distance: " + bestLongestDistance);
            writer.newLine();
            writer.write("Path distance: " + bestPathDistance);
            writer.newLine();
            writer.write("Time: " + time);
            writer.newLine();

            // close the file
            writer.close();
        } catch (IOException e) {
            System.err.println("ERROR! Data could not be saved inside the file. " + e.getMessage());
        }
    }
}