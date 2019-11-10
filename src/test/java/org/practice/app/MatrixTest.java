package org.practice.app;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MatrixTest {

    private double[][] firstMatrixArray;
    private double[][] secondMatrixArray;
    private double[][] expectedMatrixArray;

    @Before
    public void setUp() {
        firstMatrixArray = new double[][]{
                new double[]{1d, 5d},
                new double[]{2d, 3d},
                new double[]{1d, 7d}
        };

        secondMatrixArray = new double[][]{
                new double[]{1d, 2d, 3d, 7d},
                new double[]{5d, 2d, 8d, 1d}
        };
        expectedMatrixArray = new double[][]{
                new double[]{26d, 12d, 43d, 12d},
                new double[]{17d, 10d, 30d, 17d},
                new double[]{36d, 16d, 59d, 14d}
        };
    }

    @Test
    public void testMultiplication(){
        RealMatrix firstMatrix = new Array2DRowRealMatrix(firstMatrixArray);
        RealMatrix secondMatrix = new Array2DRowRealMatrix(secondMatrixArray);
        RealMatrix expectedMatrix = new Array2DRowRealMatrix(expectedMatrixArray);

        RealMatrix actualMatrix = firstMatrix.multiply(secondMatrix);

        assertEquals(expectedMatrix, actualMatrix);
    }
}
