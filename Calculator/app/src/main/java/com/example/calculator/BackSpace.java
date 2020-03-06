package com.example.calculator;

public class BackSpace extends Operation {
    @Override
    public void doOperation(String value) {
        try {
            String input = SharedFunctionality.getInputNumberString();
            String newInput = input.substring(0, input.length() - 1);
            SharedFunctionality.clearInputString();
            SharedFunctionality.setInputNumberString(newInput);
        }
        catch(Exception e){
            return ;
        }
    }
}
