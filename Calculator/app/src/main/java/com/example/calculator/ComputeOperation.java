package com.example.calculator;

import android.widget.Toast;

public class ComputeOperation extends Operation {
    @Override
    public void doOperation(String value) {
        double newResult;
        //check if there is a number
        if(SharedFunctionality.getInputNumberString() != "0" &&
                !SharedFunctionality.getInputNumberString().trim().equals(".") && SharedFunctionality.getOperator() != ' ') {

            //create result to be displayed and stored
            String oldResultString = SharedFunctionality.peekUndoResults();
            //get the number entered from the view
            String newNumberString = SharedFunctionality.getInputNumberString();
            double oldResult = Double.parseDouble(oldResultString);
            switch (SharedFunctionality.getOperator()){
                case '+':
                    if(SharedFunctionality.lengthUndoResults() == 0)
                        newResult = SharedFunctionality.getFirstNumber() + Double.parseDouble(newNumberString);
                    else
                        newResult = oldResult + Double.parseDouble(newNumberString);
                    break;
                case '-':

                    if(SharedFunctionality.lengthUndoResults() == 0)
                        newResult = SharedFunctionality.getFirstNumber() - Double.parseDouble(newNumberString);
                    else
                        newResult = oldResult - Double.parseDouble(newNumberString);
                    break;
                case '*':

                    if(SharedFunctionality.lengthUndoResults() == 0)
                        newResult = SharedFunctionality.getFirstNumber() * Double.parseDouble(newNumberString);
                    else
                        newResult = oldResult * Double.parseDouble(newNumberString);
                    break;
                case '/':

                    if(SharedFunctionality.lengthUndoResults() == 0)
                        newResult = SharedFunctionality.getFirstNumber() / Double.parseDouble(newNumberString);
                    else
                        newResult = oldResult / Double.parseDouble(newNumberString);
                    break;
                default:
                    return;
            }

            //push the new result into the stack for future use

            SharedFunctionality.pushUndoResults(String.valueOf(newResult));
            //create new result string to be displayed on the screen
            SharedFunctionality.setResultString(String.valueOf(newResult));
            SharedFunctionality.setComputedCheck(true);
            SharedFunctionality.setOperator(' ');

            //clear the number entered from the view
            SharedFunctionality.clearInputString();
        }
    }
}
