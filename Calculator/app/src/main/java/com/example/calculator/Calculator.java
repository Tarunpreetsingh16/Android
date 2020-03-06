package com.example.calculator;

public class Calculator extends CalculatorAbstraction {
    private static Calculator _calculator;

    //block the creation of objects by restricting constructor
    protected Calculator(){}

    public static Calculator getInstance(){
        //check that there is no instance already created
        if(_calculator == null){
            _calculator = new Calculator();
        }
        return _calculator;
    }

    @Override
    public void doOperation(String value) {
        operation.doOperation(value);
    }
}
