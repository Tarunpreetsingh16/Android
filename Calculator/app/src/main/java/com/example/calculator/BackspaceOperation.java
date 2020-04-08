package com.example.calculator;

import android.util.Log;

public class BackspaceOperation extends Operation {
    @Override
    public void doOperation(String value) {
        Log.i("info","backspace");
    }
}
