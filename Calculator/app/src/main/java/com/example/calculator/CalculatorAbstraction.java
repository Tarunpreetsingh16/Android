package com.example.calculator;
import java.util.*;

class CalculatorAbstraction {
    //stacks to handle redo and undo operations
    protected Stack undoOperators;
    protected Stack redoOperators;
    protected Stack undoResults;
    protected Stack redoResults;
    //opeartion object to store operation to be done
    protected Operation operation;
    //setters
    public void setOperation(Operation operation){
        this.operation = operation;
    }
    //provide the functionality to do any operation
    public void doOperation(){
        operation.doOperation();
    }
}
