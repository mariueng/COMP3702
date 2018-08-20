package comp3702.p1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class modify {
    public static void main(String[] args) throws IOException {
        // Initialize the file reader and writer
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]))) {
                // Keep looping until the end of the file is reached.
                while (true) {
                    String line = reader.readLine();
                    // Line will be null when the file ends.
                    if (line == null) {
                        break;
                    }
                    // Replace the chosen character with nothing.
                    String newLine = line.replace(args[2], "");
                    // Write to the output
                    writer.write(newLine);
                    writer.newLine();
                }
                // Close the writer
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }
}
