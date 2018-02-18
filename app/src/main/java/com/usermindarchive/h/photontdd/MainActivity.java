package com.usermindarchive.h.photontdd;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.usermindarchive.h.photontdd.Model.Matrix_Calculations;

import com.usermindarchive.h.photontdd.View.MatrixCreateFragment;


/**
 * MainActivity is the Entry Activity of PhotonTDD application.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadMatrixCreateFragment();

    }


//    Method that will load the fragment to Enter the Required Rows and Columns to Create Matrix.
    private void loadMatrixCreateFragment() {
        MatrixCreateFragment matrixCreateFragment= new MatrixCreateFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.maincontainer,matrixCreateFragment);
        fragmentTransaction.commit();
    }

}
