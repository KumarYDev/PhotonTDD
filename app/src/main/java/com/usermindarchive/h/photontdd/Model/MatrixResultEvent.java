package com.usermindarchive.h.photontdd.Model;

import java.util.List;

/**
 * MatrixResultSuccessEvent will send success result of the calculation.
 */

public class MatrixResultEvent {


    private final List<Integer> rowPath;
    private final int finalResult;


    private final String finalStatus;

    public MatrixResultEvent(int finalResult, List<Integer> rowPath, String s) {
        this.finalResult=finalResult;
        this.rowPath=rowPath;
        this.finalStatus=s;
    }

    public List<Integer> getRowPath() {
        return rowPath;
    }

    public int getFinalResult() {
        return finalResult;
    }

    public String getFinalStatus() {
        return finalStatus;
    }
}
