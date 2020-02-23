package bubnov.max.logger.back.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class Utils {

    public static StringBuilder readFile(File file) {
        StringBuilder builder = new StringBuilder();
        try (FileReader reader = new FileReader(file.getAbsoluteFile())) {

            int c;
            while ((c=reader.read()) != -1) {
                builder.append((char) c);
            }
        } catch (IOException e) {
            System.err.println("Error in reading file");
            e.printStackTrace();
        }

        return builder;
    }
}
