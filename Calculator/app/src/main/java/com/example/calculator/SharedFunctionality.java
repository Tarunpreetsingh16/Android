package com.example.calculator;

import android.widget.TextView;

import java.util.Stack;

public final class SharedFunctionality {
    //stacks to handle redo and undo operations
    protected static Stack undoOperators ;
    protected static Stack redoOperators ;
    protected static Stack undoResults;
    protected static Stack redoResults;
    protected static StringBuilder resultString;
    protected static StringBuilder inputNumberString;

    /*****setters and getters*****/

    /****setters****/
    public static void setResultString(String value){
        if(resultString == null){
            resultString = new StringBuilder();
        }
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

    public static void pushUndoOperators(String value){
        if(undoOperators == null){
            undoOperators = new Stack();
        }
        undoOperators.push(value);
    }

    public static void pushRedoOperators(String value){
        if(redoOperators == null){
            redoOperators = new Stack();
        }
        redoOperators.push(value);
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
    /*****getters****/
    public static String getResultString(){
        if(resultString == null)
            return "0";
        else
            return resultString.toString();
    }
    public static String getInputNumberString(){
        if(inputNumberString == null)
            return "0";
        else
            return inputNumberString.toString();
    }

    public static String popUndoOperators(){
        if(undoOperators == null || undoOperators.empty())
            return "0";
        else
            return String.valueOf(undoOperators.pop());
    }
    public static String popRedoOperators(){
        if(redoOperators == null || redoOperators.isEmpty())
            return "0";
        else
            return String.valueOf(redoOperators.pop());
    }

    public static String popUndoResults(){
        if(undoResults == null || undoResults.isEmpty())
            return "0";
        else
            return String.valueOf(undoResults.pop());
    }
    public static String popRedoResults(){
        if(redoResults == null || redoResults.isEmpty())
            return "0";
        else
            return String.valueOf(redoResults.pop());
    }

    //clear variables
    public static void clear(){
        if( undoResults != null)
            undoResults.empty();
        if( undoOperators != null)
            undoOperators.empty();
        if( redoOperators != null )
            redoOperators.empty();
        if( redoResults != null)
            redoResults.empty();
        inputNumberString = new StringBuilder();
        resultString = new StringBuilder();
    }
}
