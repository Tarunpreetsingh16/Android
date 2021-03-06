package com.example.calculator;

import android.widget.TextView;

import java.util.Stack;

public final class SharedFunctionality {
    //stacks to handle redo and undo operations
    private static Stack undoResults;
    private static Stack redoResults;
   //variables declaration
    private static StringBuilder resultString;
    private static StringBuilder inputNumberString;
    private static char operator ;
    private static boolean check;
    private static double firstNumber;
    /*****setters and getters*****/

    /****setters****/
    public static void setFirsNumber(double value){
        firstNumber = value;
    }
    public static void setResultString(String value){
        resultString = new StringBuilder();
        resultString.append(value);
    }
    public static void setInputNumberString(String value){

        if(inputNumberString == null){
            inputNumberString = new StringBuilder();
        }
        if(inputNumberString.length() == 10){
            return;
        }
        inputNumberString.append(value);
    }
    public static void pushUndoResults(String value){
        if(undoResults == null){
            undoResults = new Stack();
        }
        undoResults.push(value);
    }

    public static void pushRedoResults(String value){
        if(redoResults == null){
            redoResults = new Stack();
        }
        redoResults.push(value);
    }

    public static void setOperator(char value){
        operator = value;
    }

    public static void setComputedCheck(boolean value){
        check = value;
    }
    /*****getters****/
    public static double getFirstNumber(){
        return firstNumber;
    }
    public static String getResultString(){
        if(resultString == null)
            return "0";
        else
            return resultString.toString();
    }
    public static String getInputNumberString(){
        if(inputNumberString == null || inputNumberString.length() == 0)
            return "0";
        else
            return inputNumberString.toString();
    }

    public static char getOperator(){
        return operator;
    }

    public static String popUndoResults(){
        if(undoResults == null || undoResults.empty())
            return "0";
        else
            return String.valueOf(undoResults.pop());
    }
    public static String popRedoResults(){
        if(redoResults == null || redoResults.empty())
            return "0";
        else
            return String.valueOf(redoResults.pop());
    }
    public static boolean getComputedCheck(){
        return check;
    }
    //stack peekers

    public static String peekUndoResults(){
        if(undoResults == null || undoResults.empty())
            return "0";
        else
            return String.valueOf(undoResults.peek());
    }
    public static int lengthUndoResults(){
        if(undoResults != null)
            return undoResults.size();
        return 0;
    }
    public static int lengthRedoResults(){
        if(redoResults != null)
            return redoResults.size();
        return 0;
    }


    //clear variables
    public static void clear(){
        if( undoResults != null)
            undoResults.clear();
        if( redoResults != null)
            redoResults.clear();
        inputNumberString = new StringBuilder();
        resultString = new StringBuilder();
        setOperator(' ');
        check = true;
    }

    public static void clearInputString(){
        inputNumberString = new StringBuilder();
    }
}
