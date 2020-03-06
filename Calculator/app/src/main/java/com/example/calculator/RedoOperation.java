package com.example.calculator;

import android.util.Log;

public class RedoOperation extends Operation {
    @Override
    public void doOperation(String value) {
        if(SharedFunctionality.lengthRedoResults() <= 0) {
            return;
        }
            //pop the top value/recent calculated result

            String result = SharedFunctionality.popRedoResults();
            //push it to the other stack
            SharedFunctionality.pushUndoResults(result);
            //peek the top value that should be displayed
            SharedFunctionality.setResultString(result);
    }
}
