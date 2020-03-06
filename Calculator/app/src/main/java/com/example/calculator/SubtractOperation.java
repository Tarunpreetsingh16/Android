package com.example.calculator;


public class SubtractOperation extends Operation {
    @Override
    public void doOperation(String value) {
        //check if there is a number
        if(!SharedFunctionality.getInputNumberString().trim().equals(".") && SharedFunctionality.getComputedCheck()){
            //Set the operator
            SharedFunctionality.setOperator('-');

            SharedFunctionality.setFirsNumber(Double.parseDouble(SharedFunctionality.getInputNumberString()));
            //clear the number entered from the view
            SharedFunctionality.clearInputString();
            //set the computed check as false
            SharedFunctionality.setComputedCheck(false);
        }
    }
}
