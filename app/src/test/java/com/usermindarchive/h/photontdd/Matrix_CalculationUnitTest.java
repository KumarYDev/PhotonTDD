package com.usermindarchive.h.photontdd;

import com.usermindarchive.h.photontdd.Model.Matrix_Calculations;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Hassain on 2/14/2018.
 */

public class Matrix_CalculationUnitTest {
    Matrix_Calculations matrix_calculations;


    @Test
    public void sample1(){
        int costExpected=16;
        String statusExpected="Yes";

        int sample[][] = { { 3, 4, 1, 2, 8, 6 },
                { 6, 1, 8, 2, 7, 4 },
                { 5, 9, 3, 9, 9, 5 },
                { 8, 4, 1, 3, 2, 6 },
                { 3, 7, 2, 8, 6, 4 }
        };
        List<Integer> rowPathExpected=Arrays.asList(1, 2 ,3, 4, 4, 5);
        matrix_calculations=new Matrix_Calculations(sample);

        System.out.print(matrix_calculations.getFinalResult()
                +"  "+matrix_calculations.getpFinalRowPath().toString()
                +"  "+matrix_calculations.getpFinalStatus());
        assertEquals(costExpected,matrix_calculations.getFinalResult());
        assertTrue(rowPathExpected.equals(matrix_calculations.getpFinalRowPath()));
        assertTrue(statusExpected.equals(matrix_calculations.getpFinalStatus()));
    }

    @Test
    public void sample2(){
        int costExpected=11;
        String statusExpected="Yes";

        int sample[][] = { {3, 4, 1, 2, 8, 6 },
                {6, 1, 8, 2, 7, 4},
                {5, 9, 3, 9, 9, 5},
                {8, 4, 1, 3, 2, 6},
                {3, 7, 2, 1, 2, 3}
        };
        List<Integer> rowPathExpected=Arrays.asList(1, 2, 1, 5, 4 ,5);
        matrix_calculations=new Matrix_Calculations(sample);

        System.out.print(matrix_calculations.getFinalResult()
                +"  "+matrix_calculations.getpFinalRowPath().toString()
                +"  "+matrix_calculations.getpFinalStatus());
        assertEquals(costExpected,matrix_calculations.getFinalResult());
        assertTrue(rowPathExpected.equals(matrix_calculations.getpFinalRowPath()));
        assertTrue(statusExpected.equals(matrix_calculations.getpFinalStatus()));
    }

    @Test
    public void sample3(){
        int costExpected=48;
        String statusExpected="No";

        int sample[][] = { { 19, 10, 19, 10, 19 },
                { 21, 23, 20, 19, 12 },
                { 20, 12, 20, 11, 10 }
        };
        List<Integer> rowPathExpected=Arrays.asList(1, 1, 1);
        matrix_calculations=new Matrix_Calculations(sample);

        System.out.print(matrix_calculations.getFinalResult()
                +"  "+matrix_calculations.getpFinalRowPath().toString()
                +"  "+matrix_calculations.getpFinalStatus());
        assertEquals(costExpected,matrix_calculations.getFinalResult());
        assertTrue(rowPathExpected.equals(matrix_calculations.getpFinalRowPath()));
        assertTrue(statusExpected.equals(matrix_calculations.getpFinalStatus()));
    }

    @Test
    public void sample4(){
        int costExpected=26;
        String statusExpected="Yes";

        int sample[][] = { { 5, 8, 5, 3 ,5 }
        };
        List<Integer> rowPathExpected=Arrays.asList(1,1,1,1,1);
        matrix_calculations=new Matrix_Calculations(sample);

        System.out.print(matrix_calculations.getFinalResult()
                +"  "+matrix_calculations.getpFinalRowPath().toString()
                +"  "+matrix_calculations.getpFinalStatus());
        assertEquals(costExpected,matrix_calculations.getFinalResult());
        assertTrue(rowPathExpected.equals(matrix_calculations.getpFinalRowPath()));
        assertTrue(statusExpected.equals(matrix_calculations.getpFinalStatus()));
    }

    @Test
    public void sample5(){
        int costExpected=3;
        String statusExpected="Yes";

        int sample[][] = { {5},
                {8},
                {5},
                {3},
                {5}
        };
        List<Integer> rowPathExpected=Arrays.asList(4);
        matrix_calculations=new Matrix_Calculations(sample);

        System.out.print(matrix_calculations.getFinalResult()
                +"  "+matrix_calculations.getpFinalRowPath().toString()
                +"  "+matrix_calculations.getpFinalStatus());
        assertEquals(costExpected,matrix_calculations.getFinalResult());
        assertTrue(rowPathExpected.equals(matrix_calculations.getpFinalRowPath()));
        assertTrue(statusExpected.equals(matrix_calculations.getpFinalStatus()));
    }

    @Test
    public void sample6() {

        String errorExpected = "Invalid Matrix";

        String sample[][] = {{"5,", "4","H"},
                {"8","M"," 7"},
                {"5"," 7"," 5"}
    };

        matrix_calculations=new Matrix_Calculations(sample);

        System.out.print(matrix_calculations.getErrorMessage());
        assertEquals(errorExpected,matrix_calculations.getErrorMessage());
    }

    @Test
    public void sample7(){

        String errorExpected="Invalid Matrix";

        int sample[][]=new int[0][0];

        matrix_calculations=new Matrix_Calculations(sample);

        System.out.print(matrix_calculations.getErrorMessage());
        assertEquals(errorExpected,matrix_calculations.getErrorMessage());
    }

    @Test
    public void sample8(){
        int costExpected=0;
        String statusExpected="No";

        int sample[][] = { {69,10, 19, 10, 19},
                {51 ,23 ,20 ,19 ,12},
                {60 ,12 ,20 ,11 ,10}
        };
        List<Integer> rowPathExpected=Arrays.asList();
        matrix_calculations=new Matrix_Calculations(sample);

        System.out.print(matrix_calculations.getFinalResult()
                +"  "+matrix_calculations.getpFinalRowPath().toString()
                +"  "+matrix_calculations.getpFinalStatus());
        assertEquals(costExpected,matrix_calculations.getFinalResult());
        assertTrue(rowPathExpected.equals(matrix_calculations.getpFinalRowPath()));
        assertTrue(statusExpected.equals(matrix_calculations.getpFinalStatus()));
    }
    @Test
    public void sample9(){
        int costExpected=14;
        String statusExpected="Yes";

        int sample[][] = { {60 ,3, 3, 6},
                {6, 3 ,7 ,9},
                {5, 6 ,8 ,3}
        };
        List<Integer> rowPathExpected=Arrays.asList(3,2, 1 ,3);
        matrix_calculations=new Matrix_Calculations(sample);

        System.out.print(matrix_calculations.getFinalResult()
                +"  "+matrix_calculations.getpFinalRowPath().toString()
                +"  "+matrix_calculations.getpFinalStatus());
        assertEquals(costExpected,matrix_calculations.getFinalResult());
        assertTrue(rowPathExpected.equals(matrix_calculations.getpFinalRowPath()));
        assertTrue(statusExpected.equals(matrix_calculations.getpFinalStatus()));
    }
    @Test
    public void sample10(){
        int costExpected=0;
        String statusExpected="Yes";

        int sample[][] = { {6,3,-5,9},
                {-5,2,4,10},
                {3,-2,6,10},
                {6,-1,-2,10}
        };
        List<Integer> rowPathExpected=Arrays.asList(2,3, 4, 1);
        matrix_calculations=new Matrix_Calculations(sample);

        System.out.print(matrix_calculations.getFinalResult()
                +"  "+matrix_calculations.getpFinalRowPath().toString()
                +"  "+matrix_calculations.getpFinalStatus());
        assertEquals(costExpected,matrix_calculations.getFinalResult());
        assertTrue(rowPathExpected.equals(matrix_calculations.getpFinalRowPath()));
        assertTrue(statusExpected.equals(matrix_calculations.getpFinalStatus()));
    }

    @Test
    public void sample11(){
        int costExpected=10;
        String statusExpected="Yes";

        int sample[][] = { {51,51},
                {0 ,51},
                {51 ,51},
                {5 ,5}
        };
        List<Integer> rowPathExpected=Arrays.asList(4,4);
        matrix_calculations=new Matrix_Calculations(sample);

        System.out.print(matrix_calculations.getFinalResult()
                +"  "+matrix_calculations.getpFinalRowPath().toString()
                +"  "+matrix_calculations.getpFinalStatus());
        assertEquals(costExpected,matrix_calculations.getFinalResult());
        assertTrue(rowPathExpected.equals(matrix_calculations.getpFinalRowPath()));
        assertTrue(statusExpected.equals(matrix_calculations.getpFinalStatus()));
    }

    @Test
    public void sample12(){
        int costExpected=10;
        String statusExpected="No";
        int sample[][] = { {51,51,51},
                {0,51,51},
                {51, 51, 51},
                {5, 5 ,51}
        };
        List<Integer> rowPathExpected=Arrays.asList(4,4);
        matrix_calculations=new Matrix_Calculations(sample);

        System.out.print(matrix_calculations.getFinalResult()
                +"  "+matrix_calculations.getpFinalRowPath().toString()
                +"  "+matrix_calculations.getpFinalStatus());
        assertEquals(costExpected,matrix_calculations.getFinalResult());
        assertTrue(rowPathExpected.equals(matrix_calculations.getpFinalRowPath()));

        assertTrue(statusExpected.equals(matrix_calculations.getpFinalStatus()));
    }

    @Test
    public void sample14(){
        int costExpected=16;
        String statusExpected="Yes";

        String sample[][] = { { "3", "4", "1", "2", "8", "6 "},
                { "6", "1", "8", "2", "7", "4" },
                {" 5", "9", "3", "9", "9", "5 "},
                { "8", "4", "1", "3", "2", "6" },
                { "3", "7", "2", "8", "6", "4 "}
        };

        List<Integer> rowPathExpected=Arrays.asList(1, 2 ,3, 4, 4, 5);
        matrix_calculations=new Matrix_Calculations(sample);

        System.out.print(matrix_calculations.getFinalResult()
                +"  "+matrix_calculations.getpFinalRowPath().toString()
                +"  "+matrix_calculations.getpFinalStatus());
        assertEquals(costExpected,matrix_calculations.getFinalResult());
        assertTrue(rowPathExpected.equals(matrix_calculations.getpFinalRowPath()));
        assertTrue(statusExpected.equals(matrix_calculations.getpFinalStatus()));
    }

    @Test
    public void sample13(){
        int costExpected=20;
        String statusExpected="Yes";

        int sample[][] = {  { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                             { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 }
        };
        List<Integer> rowPathExpected=Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        matrix_calculations=new Matrix_Calculations(sample);

        System.out.print(matrix_calculations.getFinalResult()
                +"  "+matrix_calculations.getpFinalRowPath().toString()
                +"  "+matrix_calculations.getpFinalStatus());
        assertEquals(costExpected,matrix_calculations.getFinalResult());
        assertTrue(rowPathExpected.equals(matrix_calculations.getpFinalRowPath()));
        assertTrue(statusExpected.equals(matrix_calculations.getpFinalStatus()));
    }
    @Test
    public void sample15(){
        int costExpected=20;
        String statusExpected="Yes";

        int sample[][] = {  { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
        };

        List<Integer> rowPathExpected=Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        matrix_calculations=new Matrix_Calculations(sample);

        System.out.print(matrix_calculations.getFinalResult()
                +"  "+matrix_calculations.getpFinalRowPath().toString()
                +"  "+matrix_calculations.getpFinalStatus());
        assertEquals(costExpected,matrix_calculations.getFinalResult());
        assertTrue(rowPathExpected.equals(matrix_calculations.getpFinalRowPath()));
        assertTrue(statusExpected.equals(matrix_calculations.getpFinalStatus()));
    }


}
