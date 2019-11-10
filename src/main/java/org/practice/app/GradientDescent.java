package org.practice.app;

import org.practice.app.wrapper.MatrixWrapper;

public class GradientDescent {
    public static MatrixWrapper calculateNewTheta(
            MatrixWrapper X, MatrixWrapper y, MatrixWrapper theta, double alpha, int iterations){

        int numOfTrainingExamples = y.getLargestDimensionSize();

        if(numOfTrainingExamples == 0){
            throw new GradientDescent.NoTrainingExamples();
        } else {
            for (int i = 0; i < iterations; i++) {

                MatrixWrapper hypothesisVector = X.multiply(theta);
                MatrixWrapper errorsVector = hypothesisVector.subtract(y);

                MatrixWrapper thetaChange = errorsVector.transpose().multiply(X).transpose().multiplyEach(alpha / numOfTrainingExamples);
                theta = theta.subtract(thetaChange);
            }
        }

        return theta;
    }

    private static class NoTrainingExamples extends RuntimeException {}
}
