package org.practice.app.wrapper;

public interface MatrixWrapper {
    MatrixWrapper createRealIdentityMatrix(int size);
    MatrixWrapper multiply(MatrixWrapper matrix);
}
