package org.practice.app;

import org.practice.app.wrapper.MatrixWrapper;

public class CostFunction {
    public static double computeCost(MatrixWrapper X, MatrixWrapper y, MatrixWrapper theta){
        int numOfTrainingExamples = y.getLargestDimensionSize();

        if(numOfTrainingExamples == 0){
            throw new NoTrainingExamples();
        } else {

            MatrixWrapper hypothesisVector = X.multiply(theta);
            double featuresSum = hypothesisVector.subtract(y).squareEachELementOfMatrix().getColumnSum(0);

            return 1 / ((double )2 * numOfTrainingExamples) * featuresSum;
        }
    }

    public static class NoTrainingExamples extends RuntimeException {}
}
