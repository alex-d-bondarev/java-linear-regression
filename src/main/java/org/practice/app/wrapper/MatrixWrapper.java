package org.practice.app.wrapper;

public interface MatrixWrapper {
    MatrixWrapper createRealIdentityMatrix(int size);
    MatrixWrapper multiply(MatrixWrapper matrix);
    MatrixWrapper multiplyEach(double multiplier);
    MatrixWrapper transpose();
    MatrixWrapper subtract(MatrixWrapper matrix);
    MatrixWrapper squareEachELementOfMatrix();
    int getLargestDimensionSize();
    double getColumnSum(int columnIndex);
}
