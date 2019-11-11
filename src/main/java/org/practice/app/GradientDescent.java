package org.practice.app;

import org.practice.app.wrapper.MatrixWrapper;

@SuppressWarnings("WeakerAccess")
public class GradientDescent {
    public static MatrixWrapper calculateNewTheta(
            MatrixWrapper X, MatrixWrapper y, MatrixWrapper theta, double alpha, int iterations) {

        if (y.getAmountOfRows() == 0) {
            throw new GradientDescent.NoTrainingExamples();
        }

        return calculateTrainableTheta(X, y, theta, alpha, iterations);
    }

    private static MatrixWrapper calculateTrainableTheta(
            MatrixWrapper X, MatrixWrapper y, MatrixWrapper theta, double alpha, int iterations) {

        for (int i = 0; i < iterations; i++) {
            MatrixWrapper hypothesisVector = X.multiply(theta);
            MatrixWrapper errorsVector = hypothesisVector.subtract(y);

            MatrixWrapper thetaChange = errorsVector.transpose().multiply(X).transpose().multiplyEachElement(
                    alpha / y.getAmountOfRows());
            theta = theta.subtract(thetaChange);
        }

        return theta;
    }

    private static class NoTrainingExamples extends RuntimeException {
    }
}
