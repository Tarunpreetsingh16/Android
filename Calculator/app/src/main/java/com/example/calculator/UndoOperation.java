package com.example.calculator;

import android.util.Log;

public class UndoOperation extends Operation {
    @Override
    public void doOperation(String value) {
        Log.i("info","undo");
    }
}
