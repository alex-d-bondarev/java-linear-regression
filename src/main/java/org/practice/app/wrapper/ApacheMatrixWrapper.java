package org.practice.app.wrapper;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class ApacheMatrixWrapper implements MatrixWrapper {
    private RealMatrix wrappedMatrix;

    private ApacheMatrixWrapper(double[][] matrixArray){
        wrappedMatrix = new Array2DRowRealMatrix(matrixArray);
    }

    private ApacheMatrixWrapper(RealMatrix matrix){
        wrappedMatrix = matrix;
    }

    public static MatrixWrapper createFrom(double[][] matrixArray) {
        return new ApacheMatrixWrapper(matrixArray);
    }

    @Override
    public MatrixWrapper createRealIdentityMatrix(int size) {
        RealMatrix newWrappedMatrix = MatrixUtils.createRealIdentityMatrix(size);
        return new ApacheMatrixWrapper(newWrappedMatrix);
    }

    @Override
    public MatrixWrapper multiply(MatrixWrapper matrix) {
        if (matrix instanceof ApacheMatrixWrapper) {
            ApacheMatrixWrapper anotherMatrix = (ApacheMatrixWrapper) matrix;
            RealMatrix result = this.wrappedMatrix.multiply(anotherMatrix.wrappedMatrix);
            return new ApacheMatrixWrapper(result);
        }
        else {
            throw new NotApacheMatrixWrapper();
        }
    }

    public static class NotApacheMatrixWrapper extends RuntimeException {}
}
