package com.example.calculator;

import android.util.Log;

public class ClearOperation extends Operation {
    @Override
    public void doOperation(String value) {
        //reinitialize everything
        SharedFunctionality.clear();
    }
}
