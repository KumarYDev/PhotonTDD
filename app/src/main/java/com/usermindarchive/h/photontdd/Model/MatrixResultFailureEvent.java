package com.usermindarchive.h.photontdd.Model;

import java.util.List;

/**
 * MatrixResultFailureEvent will send failure result of the calculation.
 */

public  class MatrixResultFailureEvent {
    private final List<Integer> rowPath;

    private final int finalResult;

    public MatrixResultFailureEvent(int finalResult, List<Integer> rowPath) {
        this.finalResult=finalResult;
        this.rowPath=rowPath;
    }

    public List<Integer> getRowPath() {
        return rowPath;
    }

    public int getFinalResult() {
        return finalResult;
    }
}
