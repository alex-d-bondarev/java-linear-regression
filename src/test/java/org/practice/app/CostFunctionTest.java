package org.practice.app;

import org.junit.Test;
import org.practice.app.wrapper.MatrixWrapper;

import static org.junit.Assert.assertEquals;
import static org.practice.app.wrapper.apache.ApacheMatrixWrapper.createFrom;

public class CostFunctionTest {

    @Test
    public void constFunctionCanPredict(){
        double expectedResult = 32.07;
        double delta = 0.01;

        LinearTestDataProvider dataProvider = new LinearTestDataProvider();
        MatrixWrapper X = createFrom(dataProvider.getXWithColumnOfOnes());
        MatrixWrapper y = createFrom(dataProvider.getYColumnAsMatrix());
        MatrixWrapper theta = createFrom(dataProvider.generateTheta());

        assertEquals(expectedResult, CostFunction.computeCost(X, y, theta), delta);
    }
}
