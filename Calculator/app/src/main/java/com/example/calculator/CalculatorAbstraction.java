package com.example.calculator;

class CalculatorAbstraction {

    //operation object to store operation to be done
    protected Operation operation;
    //setters
    public void setOperation(Operation operation){
        this.operation = operation;
    }
    //provide the functionality to do any operation
    public void doOperation(String value){
        operation.doOperation(value);
    }
}
