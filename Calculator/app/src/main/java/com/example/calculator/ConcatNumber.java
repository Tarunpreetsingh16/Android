package com.example.calculator;

public class ConcatNumber extends Operation {
    @Override
    public void doOperation(String value) {
        //check if first input is zero
        if(!(value.equals("0") && SharedFunctionality.getInputNumberString().equals("0"))) {
            //check if the number already contains a decimal
            if(!(SharedFunctionality.getInputNumberString().contains(".") && value.equals(".")))
                SharedFunctionality.setInputNumberString(value);
        }

    }
}
