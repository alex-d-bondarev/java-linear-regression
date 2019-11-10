package org.practice.app;

import org.practice.app.wrapper.MatrixWrapper;

public class CostFunction {
    public static double computeCost(MatrixWrapper X, MatrixWrapper y, MatrixWrapper theta){
        if(y.getAmountOfRows() == 0){
            throw new NoTrainingExamples();
        } else {
            return computeTrainableCostFunction(X, y, theta);
        }
    }

    private static double computeTrainableCostFunction(MatrixWrapper X, MatrixWrapper y, MatrixWrapper theta) {
        int firstColumn = 0;

        MatrixWrapper hypothesisVector = X.multiply(theta);
        double featuresSum = hypothesisVector.subtract(y).squareEachELementOfMatrix().getColumnSum(firstColumn);
        return 1 / ((double )2 * y.getAmountOfRows()) * featuresSum;
    }

    private static class NoTrainingExamples extends RuntimeException {}
}
