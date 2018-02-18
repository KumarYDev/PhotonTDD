package com.usermindarchive.h.photontdd;

import com.usermindarchive.h.photontdd.Model.MatrixResultFailureEvent;
import com.usermindarchive.h.photontdd.Model.MatrixResultSuccessEvent;
import com.usermindarchive.h.photontdd.Presenter.MatrixPresenter;

import junit.framework.Assert;

import org.greenrobot.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Created by Hassain on 2/18/2018.
 */
public class MatrixPresenterTest {

    MatrixPresenter matrixPresenter;

    @Mock
    MatrixPresenter.MatrixPresenterInterface matrixPresenterInterface;

    @Captor
    private ArgumentCaptor<Integer> cost;

    @Captor
    private ArgumentCaptor<List<Integer>> rowPath;

    @Before
    public void setUp() throws Exception{

        MockitoAnnotations.initMocks(this);
        matrixPresenter = new MatrixPresenter(matrixPresenterInterface);
    }

    @Test
    public void testThatPresenterIsNotNull(){
        Assert.assertNotNull(matrixPresenter);
        Assert.assertNotNull(matrixPresenterInterface);
    }

    @Test
    public void testOnMatrixResltSuccessEvent(){
        matrixPresenter.onStart();
        List<Integer> sampleRowPath=new ArrayList<>(Arrays.asList(1,2,3,3));
        int sampleCost=10;
        MatrixResultSuccessEvent matrixResultSuccessEvent=new MatrixResultSuccessEvent(sampleCost,sampleRowPath);

        EventBus.getDefault().post(matrixResultSuccessEvent);
        matrixPresenter.onStop();
        verify(matrixPresenterInterface).onSendMatrixResultSuccessEvent(cost.capture(),rowPath.capture(), matrixResultEvent.getFinalStatus());
        Assert.assertEquals("10",cost.getValue().toString());
        Assert.assertEquals("[1, 2, 3, 3]",rowPath.getValue().toString());
    }

    @Test
    public void testOnMatrixResltFailureEvent(){
        matrixPresenter.onStart();
        List<Integer> sampleRowPath=new ArrayList<>(Arrays.asList(1,2,3,3));
        int sampleCost=51;
        MatrixResultFailureEvent matrixResultSuccessEvent= new MatrixResultFailureEvent(sampleCost,sampleRowPath);

        EventBus.getDefault().post(matrixResultSuccessEvent);
        matrixPresenter.onStop();
        verify(matrixPresenterInterface).onSendMatrixResultFailureEvent(cost.capture(),rowPath.capture());
        Assert.assertEquals("51",cost.getValue().toString());
        Assert.assertEquals("[1, 2, 3, 3]",rowPath.getValue().toString());
    }

}