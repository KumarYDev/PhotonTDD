package com.usermindarchive.h.photontdd.Model;

import android.support.v4.content.res.TypedArrayUtils;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Hassain on 2/14/2018.
 */

public class Matrix_Calculations {

    private  int pMatrixColumns;
    private int pMatrixRows;
    private int pMaxCost=50;
    private int pFinalResult;
    private String pFinalStatus;
    private List<Integer> pFinalRowPath=new ArrayList<>();
    private Map<Integer,Integer> matrixList=new HashMap<>();
    private List<Integer> Path=new ArrayList<>();

    public List<PathListModel> getPathListModels() {
        return pathListModels;
    }

    private List<PathListModel> pathListModels=new ArrayList<>();
    private String errorMessage;
    
//  Constructor for Integer Array Matrix.
    public Matrix_Calculations(int[][] pMatrix) {

        pMatrixRows=pMatrix.length;
        if(checkInputMatrix(pMatrixRows)) {
            pMatrixColumns=pMatrix[0].length;
            convertToMatrixList(pMatrix);
        }
    }

//  Constructor for String Array Matrix.
    public Matrix_Calculations(String[][] pMatrix) {
        pMatrixRows=pMatrix.length;

        if(checkInputMatrix(pMatrixRows)) {
            pMatrixColumns=pMatrix[0].length;
            convertToIntMatrix(pMatrix);
        }
    }

//  Method to check the matrix has values or not.
    private boolean checkInputMatrix(int pMatrixRows) {
        if(pMatrixRows==0){
            sendErrorMessage(1);
            return false;
        }else{
            return true;
        }
    }

//  Method to convert the String Matrix to Integer Matrix
    private void convertToIntMatrix(String[][] pMatrix) {
        int[][] tempIntMatrix=new int[pMatrixRows][pMatrixColumns];

        entry:
        for(int i=0;i<pMatrixRows;i++){
            for(int j=0;j<pMatrixColumns;j++){
                String tempCellValue=pMatrix[i][j].trim().toString();
                try{
                    if(checkStringMatrixCell(tempCellValue)) {
                        int z = Integer.valueOf(tempCellValue);
                        tempIntMatrix[i][j] = z;
                    }else{
                        sendErrorMessage(1);
                        break entry;
                    }
                }catch(NumberFormatException e){
                    System.out.println("Value is not a number");
                    sendErrorMessage(1);
                    break entry;
                }

            }
        }

        convertToMatrixList(tempIntMatrix);
    }

//  Method to check the String matrix Cell has value or is the cell Empty.
    private boolean checkStringMatrixCell(String tempCellValue) {
        if(tempCellValue.length()==0)
            return false;
        else
            return true;
    }

//  Method to send the required error message.
    private void sendErrorMessage(int i) {
        if(i==1){
            setErrorMessage("Invalid Matrix");
        }
    }

//  Method to convert the matrix to List.
    private void convertToMatrixList(int[][] matrix){

        for(int i=0;i<pMatrixRows;i++){
            for(int j=0;j<pMatrixColumns;j++){
                matrixList.put(getPositionValue(i,j), matrix[i][j]);
            }
        }
        beginCalculation();

    }

//  Method to begin the Calculation.
    private void beginCalculation() {
        entry(leftMostColumnList(matrixList));
        sortList(pathListModels);
        setFinalResults(pathListModels);
    }

//   Method to set Final Results.
    private void setFinalResults(List<PathListModel> pathListModels) {
        PathListModel pathListModelTemp=pathListModels.get(0);

        if(pathListModelTemp.getPathCost()<=pMaxCost){
            setFinalCostResult(pathListModelTemp.getPathCost());
            setpFinalRowPath(pathListModelTemp.getRowsOfPath());
            setpFinalStatus("Yes");

            sendResult();

        }else{
            updateMinimumValues(pathListModelTemp.getPathCost(),pathListModelTemp.getPath(),pathListModelTemp.getRowsOfPath());

            sendResult();
        }


    }

//    Method to Calculate the path that has minimum value in the Given Result Path.
    private void updateMinimumValues(int pathCost, List<Integer> path, List<Integer> valuesPath) {
        int tempCost=0;
        List<Integer> tempRowPath=new ArrayList<>();

        for(int i=0;i<valuesPath.size();i++){
            tempCost = tempCost + matrixList.get(path.get(i));
            if(tempCost>pMaxCost){
                tempCost = tempCost - matrixList.get(path.get(i));
                break;
            }else {
                tempRowPath.add(valuesPath.get(i));
            }
        }

        setFinalCostResult(tempCost);
        setpFinalRowPath(tempRowPath);
        setpFinalStatus("No");
    }

//    Method to get the position in matrix list with Row and Column Value.
    private int getPositionValue(int row,int column){
        return row*pMatrixColumns+column;
    }

//    Method to get the row value of matrix with the position value in Matrix List.
    private int getRowValue(int positionValue){
        if(positionValue<pMatrixColumns)
            return 0;
        else
        return positionValue/pMatrixColumns;

    }

//    Method to get the column value of matrix with the position value in Matrix List.
    private int getColumnValue(int positionValue){
        if(positionValue<pMatrixColumns)
            return positionValue;
        else
            return positionValue%pMatrixColumns;

    }

//    Method to get the Array to start the path in the matrix.
    private int[] leftMostColumnList(Map<Integer, Integer> matrixList){
        int columnLeftMost[]=new int[pMatrixRows];
        for(int j=0;j<pMatrixRows;j++)
        {
            columnLeftMost[j]=j*pMatrixColumns;
        }
        return columnLeftMost;
    }

//    Method to check the column of current Position.
    private int[] nextPathPositionValues(int positionValue){
        int[] nextPathPositionValues=new int[3];

        int rowValue=getRowValue(positionValue);
        int columnValue=getColumnValue(positionValue);

        if(columnValue==pMatrixColumns-1){
            nextPathPositionValues[0]=-1;

        }else{

            nextPathPositionValues=nextPositionValues(positionValue,rowValue,columnValue);
        }
        return nextPathPositionValues;
    }

//    Method to get the possible positions in path as Array for the current Position.
    private int[] nextPositionValues(int positionValue,int rowValue,int columnValue){
        int[] nextPositionValues=new int[3];
        Set<Integer> tempPostionSaver= new HashSet<>();
        int i=0;
        while(i<3){
            if(i==0){
                if(rowValue==0){
                    tempPostionSaver.add(getPositionValue(pMatrixRows-1,columnValue+1));
                    nextPositionValues[i]=getPositionValue(pMatrixRows-1,columnValue+1);
                }
                else{
                    tempPostionSaver.add(positionValue-pMatrixColumns+1);
                    nextPositionValues[i]=positionValue-pMatrixColumns+1;
                }
            }
            if(i==1){

                tempPostionSaver.add(positionValue+1);
                nextPositionValues[i]=positionValue+1;
            }

            if(i==2){
                if(rowValue==(pMatrixRows-1)){
                    tempPostionSaver.add(columnValue+1);
                    nextPositionValues[i]=columnValue+1;
                }
                else{
                    tempPostionSaver.add(pMatrixColumns+1+positionValue);
                    nextPositionValues[i]=pMatrixColumns+1+positionValue;
                }

            }
            i++;
        }
        int[] temp=new int[tempPostionSaver.size()];
        int j=0;
        for (int tempInt:tempPostionSaver){
            temp[j++]=tempInt;
        }
        if(pMatrixRows!=2)
        return nextPositionValues;
        else
        return temp;
    }

//  Method Start the Calculation with Left most of matrix Positions as Array.
    private void entry(int[] entryPositionList){

        for(int i=0;i<entryPositionList.length;i++) {
            int matrixListKey=entryPositionList[i];
            updatePathList(matrixListKey);
            moveToNext(matrixListKey);
        }
    }

//  Method that recursively works to create the paths in the Matrix Grid.
    private void moveToNext(int currentPosition) {
        int[] nextPathList=nextPathPositionValues(currentPosition);

        if(nextPathPositionValues(currentPosition)[0]!=-1){
            for(int matrixListKey:nextPathPositionValues(currentPosition)) {
                updatePathList(matrixListKey);
                moveToNext(matrixListKey);
           }
        }else{
            Log.e("Test","reached"+Path.toString()+"  cost:"+getPathCost(Path));
            addToPathList(Path);
        }

    }

//  Method to add the created path to Path List
    private void addToPathList(List<Integer> Path) {
        List<Integer> tempPath= new ArrayList<>(Path);
        pathListModels.add(new PathListModel(tempPath,getPathCost(Path),getRowValuesPath(Path)));
    }

//  Method to sort the Path List created with all possible paths.
    private void sortList(List<PathListModel> pathListModels) {
        Collections.sort(pathListModels);
    }

//  Method to get the Row number list for the Path.
    private List<Integer> getRowValuesPath(List<Integer> path) {
        List<Integer> temp=new ArrayList<>();
        for (int i=0;i<path.size();i++){
            temp.add(getRowValue(path.get(i))+1);
        }
        return temp;
    }

//  Method to update path in Recursion Process.
    private void updatePathList(int currentPositionValue){
        int nextPositionValue=getPositionStatus(currentPositionValue);

        if(Path.contains(nextPositionValue)){
            int index=Path.indexOf(nextPositionValue);
            int removeCount=Path.size()-index;
            for(int j=0;j<removeCount;j++){
                Path.remove(Path.size()-1);
            }
            Path.add(currentPositionValue);
        }else{
            Path.add(currentPositionValue);
        }

    }

//  Method used to update the path as required.
    private int getPositionStatus(int currentPositionValue){
        int rowValue=getRowValue(currentPositionValue);
        int columnValue=getColumnValue(currentPositionValue);
            if(rowValue==0){
            return getPositionValue(pMatrixRows-1,columnValue);
        }
        else return currentPositionValue-pMatrixColumns;
    }

//  Method to send Success Result.
    private void sendResult() {
//        update UI

        EventBus.getDefault().post(new MatrixResultEvent(getFinalResult(),getpFinalRowPath(),getpFinalStatus()));

    }

//  Method to calculate the cost for the Path.
    private int getPathCost(List<Integer> path){
        int tempCost=0;
        for (int pathPosition:path){
            tempCost=tempCost+matrixList.get(pathPosition);
        }
        return tempCost;
    }

//  Method to get final Result cost.
    public int getFinalResult() {
        return pFinalResult;
    }

//  Method to set final Result cost.
    private void setFinalCostResult(int pFinalResult) {
        this.pFinalResult = pFinalResult;
    }

//  Method to get Row list of final Path.
    public List<Integer> getpFinalRowPath() {
        return pFinalRowPath;
    }

//  Method to set Row list of final Path.
    private void setpFinalRowPath(List<Integer> pFinalRowPath) {
        this.pFinalRowPath = pFinalRowPath;
    }

//  Method to get Result Success or Failure status.
    public String getpFinalStatus() {
        return pFinalStatus;
    }

//  Method to set Result Success or Failure status.
    public void setpFinalStatus(String pFinalStatus) {
        this.pFinalStatus = pFinalStatus;
    }

//  Method to get Error Message.
    public String getErrorMessage() {
        return errorMessage;
    }

//  Method to set Error Message.
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
