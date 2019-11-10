package org.practice.app;

import org.practice.app.file.FileLoader;

public class LinearTestDataProvider {
    private String testInputFilePath;
    private double[][] testInput;

    public LinearTestDataProvider() {
        testInputFilePath = "src/test/resources/org/practice/app/testInput.txt";
        testInput = FileLoader.arrayFromFile(testInputFilePath);
    }

    public double[][] getXWithColumnOfOnes(){
        double[][] column = new double[testInput.length][2];

        for (int i = 0; i < testInput.length; i++) {
            column[i][0] = 1;
            column[i][1] = testInput[i][0];
        }
        return column;
    }

    public double[][] getYColumnAsMatrix(){
        double[][] column = new double[testInput.length][1];

        for (int i = 0; i < testInput.length; i++) {
            column[i][0] = testInput[i][1];
        }
        return column;
    }

    public double[][] generateTheta(){
        double[][] theta = new double[2][1];
        theta[0][0] = 0;
        theta[1][0] = 0;
        return theta;
    }
}
