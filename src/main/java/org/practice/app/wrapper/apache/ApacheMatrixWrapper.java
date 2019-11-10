package org.practice.app.wrapper.apache;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.practice.app.wrapper.MatrixWrapper;

import java.util.stream.DoubleStream;

public class ApacheMatrixWrapper implements MatrixWrapper {
    private final RealMatrix wrappedMatrix;

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

    @Override
    public MatrixWrapper subtract(MatrixWrapper matrix) {
        if (matrix instanceof ApacheMatrixWrapper) {
            ApacheMatrixWrapper anotherMatrix = (ApacheMatrixWrapper) matrix;
            RealMatrix result = this.wrappedMatrix.subtract(anotherMatrix.wrappedMatrix);
            return new ApacheMatrixWrapper(result);
        }
        else {
            throw new NotApacheMatrixWrapper();
        }
    }

    @Override
    public MatrixWrapper squareEachELementOfMatrix() {
        RealMatrix squaredMatrix = wrappedMatrix.copy();
        squaredMatrix.walkInOptimizedOrder(new SquareEachElementMatrixChangingVisitor());
        return new ApacheMatrixWrapper(squaredMatrix);
    }

    @Override
    public int getLargestDimensionSize() {
        int numOfRows = wrappedMatrix.getRowDimension();
        int numOfColumns = wrappedMatrix.getColumnDimension();

        return Math.max(numOfColumns, numOfRows);
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

    public static class NotApacheMatrixWrapper extends RuntimeException {}
}
