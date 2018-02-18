package com.usermindarchive.h.photontdd.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.usermindarchive.h.photontdd.R;

/**
 *  MatrixCreateFragment will get the required value of Row and Column to Create Matrix.
 */

public class MatrixCreateFragment extends Fragment {
    private int rowMaxLimit=10,columMaxLimit=100,rowMinLimit=1,columMinLimit=5;
    private EditText rowCount,columnCount;
    private Button createMatrix;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_matrix_create,container,false);
        rowCount=(EditText)view.findViewById(R.id.rowcount);
        columnCount=(EditText)view.findViewById(R.id.columncount);
        createMatrix=(Button)view.findViewById(R.id.creatematrix);


//        Button Click event to pass the row and column value to Matrix Fragment
        createMatrix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Checking the Entered data
                if(checkDataEntered()&&checkCountLimit()){

                    loadMatrixFragment();
                }
            }
        });
        return view;
    }
//      Method that passes the row and column length to Matrix Fragment.
    private void loadMatrixFragment() {
        MatrixFragment matrixFragment= new MatrixFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("row", Integer.parseInt(rowCount.getText().toString()));
        bundle.putInt("column", Integer.parseInt(columnCount.getText().toString()));
        matrixFragment.setArguments(bundle);
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.maincontainer,matrixFragment);
        fragmentTransaction.addToBackStack("matrix create fragment");
        fragmentTransaction.commit();
    }


//  Checking the row and column limit.
    private boolean checkCountLimit() {
        int rowCountTemp= Integer.parseInt(rowCount.getText().toString());
        int columnCountTemp=Integer.parseInt(columnCount.getText().toString());

        if(rowCountTemp<rowMinLimit||rowCountTemp>rowMaxLimit){
            rowCount.setError("The Row Value \nMinimum Required:"+rowMinLimit+"\nMaximum Required:"+rowMaxLimit);
            return false;
        }else if(columnCountTemp<columMinLimit||columnCountTemp>columMaxLimit){
            columnCount.setError("The Column Value \nMinimum Required:"+columMinLimit+"\nMaximum Required:"+columMaxLimit);
            return false;
        }
        else {
            return true;
        }
    }
//  Method to check whether value entered or not in Row and Column cells.
    private boolean checkDataEntered() {
        if(checkView(rowCount)&&checkView(columnCount)){
            return true;
        }else{
            return false;
        }
    }
    //  Method to check whether value entered or not in View.
    private boolean checkView(EditText editText) {
        if(editText.getText().toString().trim().length()==0){
            editText.setError("Please Enter the Required Value");
            return false;
        }else {
            return true;
        }
    }
}
