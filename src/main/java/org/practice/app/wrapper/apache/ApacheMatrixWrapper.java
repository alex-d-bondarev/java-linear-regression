package org.practice.app.wrapper.apache;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.practice.app.wrapper.MatrixWrapper;

import java.util.stream.DoubleStream;

public class ApacheMatrixWrapper implements MatrixWrapper {
    private final RealMatrix wrappedMatrix;

    private ApacheMatrixWrapper(double[][] matrixArray) {
        wrappedMatrix = new Array2DRowRealMatrix(matrixArray);
    }

    private ApacheMatrixWrapper(RealMatrix matrix) {
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
        throwExceptionIfNotApacheMatrixWrapper(matrix);

        ApacheMatrixWrapper anotherMatrix = (ApacheMatrixWrapper) matrix;
        RealMatrix result = this.wrappedMatrix.multiply(anotherMatrix.wrappedMatrix);

        return new ApacheMatrixWrapper(result);
    }

    @Override
    public MatrixWrapper multiplyEachElement(double multiplier) {
        RealMatrix newMatrix = wrappedMatrix.copy();
        return new ApacheMatrixWrapper(newMatrix.scalarMultiply(multiplier));
    }

    @Override
    public MatrixWrapper subtract(MatrixWrapper matrix) {
        throwExceptionIfNotApacheMatrixWrapper(matrix);

        ApacheMatrixWrapper anotherMatrix = (ApacheMatrixWrapper) matrix;
        RealMatrix result = this.wrappedMatrix.subtract(anotherMatrix.wrappedMatrix);

        return new ApacheMatrixWrapper(result);
    }

    private void throwExceptionIfNotApacheMatrixWrapper(MatrixWrapper matrix) {
        if (!(matrix instanceof ApacheMatrixWrapper))
            throw new NotApacheMatrixWrapper();
    }

    @Override
    public MatrixWrapper squareEachELementOfMatrix() {
        RealMatrix squaredMatrix = wrappedMatrix.copy();
        squaredMatrix.walkInOptimizedOrder(new SquareEachElementMatrixChangingVisitor());
        return new ApacheMatrixWrapper(squaredMatrix);
    }

    @Override
    public int getAmountOfRows() {
        return wrappedMatrix.getRowDimension();
    }

    @Override
    public double getColumnSum(int columnIndex) {
        double[] column = wrappedMatrix.getColumn(columnIndex);
        return DoubleStream.of(column).sum();
    }

    @Override
    public MatrixWrapper transpose() {
        return new ApacheMatrixWrapper(wrappedMatrix.transpose());
    }

    @SuppressWarnings("WeakerAccess")
    public static class NotApacheMatrixWrapper extends RuntimeException {
    }
}
