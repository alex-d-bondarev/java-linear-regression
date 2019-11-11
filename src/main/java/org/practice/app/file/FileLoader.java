package org.practice.app.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileLoader {
    public static double[][] arrayFromFile(String filePath) {
        double[][] doubles;
        String fileLineDelimeter = ",";

        doubles = convertListOfStringsToDoublesArray(getListOfFileLineStrings(filePath),
                fileLineDelimeter);

        return doubles;
    }

    private static double[][] convertListOfStringsToDoublesArray(List<String> lines, String delimeter) {
        int rows;
        int columns;
        double[][] doubles;
        rows = lines.size();
        columns = lines.get(0).split(delimeter).length;

        doubles = new double[rows][columns];

        for (int i = 0; i < lines.size(); i++) {
            doubles[i] = Arrays.
                    stream(lines.get(i).split(delimeter)).
                    mapToDouble(Double::parseDouble).
                    toArray();
        }
        return doubles;
    }

    private static List<String> getListOfFileLineStrings(String filePath) {
        try {
            return readFileLines(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> readFileLines(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        String readLine;
        BufferedReader bReader = new BufferedReader(new FileReader(filePath));

        while ((readLine = bReader.readLine()) != null) {
            lines.add(readLine);
        }

        return lines;
    }
}
