package org.practice.app;

import org.junit.Test;
import org.practice.app.wrapper.MatrixWrapper;

import static org.junit.Assert.assertTrue;
import static org.practice.app.wrapper.apache.ApacheMatrixWrapper.createFrom;

public class GradientDescentTest {

    @Test
    public void gradientDescentOptimizesCostFunction(){
        int iterations = 1500;
        double alpha = 0.01;
        double minimumDifference = 25;

        LinearTestDataProvider dataProvider = new LinearTestDataProvider();
        MatrixWrapper X = createFrom(dataProvider.getXWithColumnOfOnes());
        MatrixWrapper y = createFrom(dataProvider.getYColumnAsMatrix());
        MatrixWrapper theta = createFrom(dataProvider.generateTheta());
        double costFunctionBeforeGradientDescent = CostFunction.computeCost(X, y, theta);

        MatrixWrapper optimizedTheta = GradientDescent.calculateNewTheta(X, y, theta, alpha, iterations);
        double costFunctionAfterGradientDescent = CostFunction.computeCost(X, y, optimizedTheta);

        assertTrue(costFunctionAfterGradientDescent < costFunctionBeforeGradientDescent - minimumDifference);
    }
}
