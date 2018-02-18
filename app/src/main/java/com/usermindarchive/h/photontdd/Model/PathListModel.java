package com.usermindarchive.h.photontdd.Model;

import java.util.List;

/**
 * PathListModel is used to save the grid path , cost of the path and rows of the path.
 */

public class PathListModel implements Comparable<PathListModel>{
    private int pathCost;
    private final List<Integer> path;
    private final List<Integer> rowsOfPath;

    public List<Integer> getRowsOfPath() {
        return rowsOfPath;
    }



    public List<Integer> getPath() {
        return path;
    }

    public int getPathCost() {
        return pathCost;
    }



    public PathListModel(List<Integer> path, int pathCost, List<Integer> rowsOfPath) {
        this.path=path;
        this.pathCost=pathCost;
        this.rowsOfPath=rowsOfPath;
    }

    @Override
    public int compareTo(PathListModel pathListModel) {
        return (this.getPathCost()<pathListModel.getPathCost()?-1:
                (this.getPathCost()==pathListModel.getPathCost()?0:1));
    }
}
