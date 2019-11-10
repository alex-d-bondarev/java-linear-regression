package org.practice.app;

import org.junit.Test;
import org.practice.app.file.FileLoader;
import org.practice.app.wrapper.MatrixWrapper;

import static org.junit.Assert.assertEquals;
import static org.practice.app.wrapper.apache.ApacheMatrixWrapper.createFrom;

public class CostFunctionTest {

    @Test
    public void constFunctionCanPredict(){
        String testInputFilePath = "src/test/resources/org/practice/app/testInput.txt";
        double[][] testInput = FileLoader.arrayFromFile(testInputFilePath);
        MatrixWrapper X = createFrom(getXWithColumnOfOnes(testInput));
        MatrixWrapper y = createFrom(getYColumnAsMatrix(testInput));
        MatrixWrapper theta = createFrom(generateTheta());
        double expectedResult = 32.07;
        double delta = 0.01;

        assertEquals(expectedResult, CostFunction.computeCost(X, y, theta), delta);
    }

    private double[][] getXWithColumnOfOnes(double[][] matrix){
        double[][] column = new double[matrix.length][2];

        for (int i = 0; i < matrix.length; i++) {
            column[i][0] = 1;
            column[i][1] = matrix[i][0];
        }
        return column;
    }

    private double[][] getYColumnAsMatrix(double[][] matrix){
        double[][] column = new double[matrix.length][1];

        for (int i = 0; i < matrix.length; i++) {
            column[i][0] = matrix[i][1];
        }
        return column;
    }

    private double[][] generateTheta(){
        double[][] theta = new double[2][1];
        theta[0][0] = 0;
        theta[1][0] = 0;
        return theta;
    }
}
