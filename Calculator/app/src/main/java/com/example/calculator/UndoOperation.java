package com.example.calculator;

import android.util.Log;

public class UndoOperation extends Operation {
    @Override
    public void doOperation(String value) {
        if(SharedFunctionality.lengthUndoResults() <= 0) {
            return;
        }
        //pop the top value/recent calculated result
        String result = SharedFunctionality.popUndoResults();
        //push it to the other stack
        SharedFunctionality.pushRedoResults(result);
        //peek the top value that should be displayed
        SharedFunctionality.setResultString(SharedFunctionality.peekUndoResults());

    }
}
