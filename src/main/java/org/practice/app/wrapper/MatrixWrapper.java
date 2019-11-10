package org.practice.app.wrapper;

public interface MatrixWrapper {
    MatrixWrapper createRealIdentityMatrix(int size);
    MatrixWrapper multiply(MatrixWrapper matrix);
    MatrixWrapper multiplyEachElement(double multiplier);
    MatrixWrapper transpose();
    MatrixWrapper subtract(MatrixWrapper matrix);
    MatrixWrapper squareEachELementOfMatrix();
    int getAmountOfRows();
    double getColumnSum(int columnIndex);
}
