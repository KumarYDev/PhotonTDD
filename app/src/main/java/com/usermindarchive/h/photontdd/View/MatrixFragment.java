package com.usermindarchive.h.photontdd.View;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

import com.usermindarchive.h.photontdd.Presenter.MatrixPresenter;
import com.usermindarchive.h.photontdd.R;

import java.util.List;

/**
 * MatrixFragment will create teh gird to get values input for processing the shortest path.
 */

public class MatrixFragment extends Fragment implements MatrixPresenter.MatrixPresenterInterface {
    private MatrixPresenter matrixPresenter;
    private int[][] matrix;
    private int rowCount,columnCount;
    EditText[][] gridCell;
    Button check;
    int emptyCellCount=0;
    GridLayout gridContainer;


    private ProgressDialog pd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_matrix,container,false);

        loadingBar();

        gridContainer=(GridLayout)view.findViewById(R.id.matrixcontainer);
        check=(Button) view.findViewById(R.id.check);
        matrixPresenter=new MatrixPresenter(this);

//      Extracting the row and column length of the gird.
        Bundle bundle=this.getArguments();
        rowCount=bundle.getInt("row");
        columnCount=bundle.getInt("column");

        matrix=new int[rowCount][columnCount];

//      Loading the editable matrix
        loadMatrix(rowCount,columnCount,gridContainer);


//      Passing the Grid values to calculate the shortest path.
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadMatrixValues();

//                Checking every cell of grid is filled or not.
                if(emptyCellCount==0){
                    getPd().show();

                    new Thread(new Runnable(){
                        public void run() {
                            matrixPresenter.sendMatrix(matrix);
                        }
                    }).start();

                }else{
                    Toast.makeText(getContext(),"Please Enter the value In Empty Cell",Toast.LENGTH_LONG).show();

                }

            }
        });


        return view;
    }

//  Method to create grid with required row and column length
    private void loadMatrix(int rowNum, int colNum, GridLayout gridContainer) {

        gridCell = new EditText[rowNum][colNum];

        GridLayout tempGridContainer = (GridLayout) gridContainer;

        //Defining how many rows and columns to be used in the layout
        tempGridContainer.setRowCount(rowNum);
        tempGridContainer.setColumnCount(colNum);

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                gridCell[i][j] = new EditText(getContext());
                setPos(gridCell[i][j], i, j);
                tempGridContainer.addView(gridCell[i][j]);
            }
        }
    }

//  Method to create matrix from the values in the grid cell's
    private void loadMatrixValues() {
        emptyCellCount=rowCount*columnCount;

        for(int i=0;i<rowCount;i++){
            for (int j=0;j<columnCount;j++){
                if(checkMatrixCell(i,j)){
                    matrix[i][j]= Integer.parseInt(gridCell[i][j].getText().toString());
                    emptyCellCount--;
                }
            }
        }
    }

//    Method that check the value's in each cell of grid.
    private boolean checkMatrixCell(int i, int j) {
        if(gridCell[i][j].getText().toString().isEmpty()){
            gridCell[i][j].setError("Enter the value");
            return false;
        }else return true;
    }

//  Method that create's editable text  field.
    private void setPos(EditText editText, int row, int column) {

        GridLayout.LayoutParams param =new GridLayout.LayoutParams();
        param.width = ViewGroup.LayoutParams.MATCH_PARENT;
        param.height = ViewGroup.LayoutParams.MATCH_PARENT;
        param.setGravity(Gravity.CENTER);
        param.rowSpec = GridLayout.spec(row);
        param.columnSpec = GridLayout.spec(column);
        editText.setLayoutParams(param);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setEms(3);
        editText.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
    }

//  Method to register for EventBus.
    @Override
    public void onStart() {
        super.onStart();
        matrixPresenter.onStart();
    }

//  Method to un-register for EventBus.
    @Override
    public void onStop() {
        super.onStop();
        matrixPresenter.onStop();
    }


//  Method to display Received Result.
    @Override
    public void onSendMatrixResultEvent(int finalResult, List<Integer> rowPath, String finalStatus) {

        String temp=finalStatus;
        getPd().dismiss();
        showResult(temp,finalResult,rowPath);

    }


//  Dialog box to show the result.
    private void showResult(String temp, int finalResult, List<Integer> rowPath) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(temp+"\n"+finalResult+"\n"+rowPath.toString());
        builder.setTitle("Result");
        builder.setPositiveButton("OK", null);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        Button button = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

//    Method to display Progress bar for longer Calculations.
    private void loadingBar(){
        // Initialize a new instance of progress dialog
        pd = new ProgressDialog(getActivity());

        // Set progress dialog style spinner
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        // Set the progress dialog title and message
        pd.setTitle("Calculating Shortest Path.");
        pd.setMessage("Calculating.........");

        // Set the progress dialog background color
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD4D9D0")));

        pd.setIndeterminate(false);
    }
    public ProgressDialog getPd() {
        return pd;
    }
}
