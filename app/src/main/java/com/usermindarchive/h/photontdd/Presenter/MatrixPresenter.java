package com.usermindarchive.h.photontdd.Presenter;

import com.usermindarchive.h.photontdd.Model.MatrixResultEvent;
import com.usermindarchive.h.photontdd.Model.Matrix_Calculations;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;


/**
 * MatrixPresenter is the presenter that communicate with both Matrix Fragment(View) and Matrix_Calculations (Model).
 */

public class MatrixPresenter {

    private Matrix_Calculations matrix_calculations;

    private MatrixPresenterInterface matrixPresenterInterface;

    public MatrixPresenter(MatrixPresenterInterface matrixPresenterInterface) {
        this.matrixPresenterInterface=matrixPresenterInterface;

    }

//    Method to send matrix to Model Class for calculation.
    public void sendMatrix(int[][] matrixValues){
        matrix_calculations=new Matrix_Calculations(matrixValues);
    }

//  Method to register for EventBus.
    public void onStart() {
        EventBus.getDefault().register(this);
    }

//  Method to un-register for EventBus.
    public void onStop() {
        EventBus.getDefault().unregister(this);
    }

//  Method to receive Success Result.
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMatrixResltSuccessEvent(MatrixResultEvent matrixResultEvent ){
        matrixPresenterInterface.onSendMatrixResultEvent(matrixResultEvent.getFinalResult()
                ,matrixResultEvent.getRowPath()
                ,matrixResultEvent.getFinalStatus());

    }


//  Interface to pass the received result from Model class to View class
    public interface MatrixPresenterInterface{

        public void onSendMatrixResultEvent(int finalResult, List<Integer> rowPath, String finalStatus);
    }
}
