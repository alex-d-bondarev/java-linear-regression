package org.practice.app.wrapper.apache;

import org.apache.commons.math3.linear.RealMatrixChangingVisitor;

public class SquareEachElementMatrixChangingVisitor implements RealMatrixChangingVisitor {
    @Override
    public void start(int i, int i1, int i2, int i3, int i4, int i5) {
    }

    @Override
    public double visit(int i, int i1, double v) {
        return v * v;
    }

    @Override
    public double end() {
        return 0;
    }
}
