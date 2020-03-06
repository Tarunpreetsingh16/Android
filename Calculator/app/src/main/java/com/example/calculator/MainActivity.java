package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //**********declare variables**************

    //*******Declare view variables***********

    //buttons
    Button btnNum1,btnNum2,btnNum3,btnNum4,btnNum5,btnNum6,btnNum7,btnNum8,btnNum9,btnDecimal,
            btnUndo,btnRedo,btnMultiply,btnAdd,btnSubtract,btnDivide,btnEqual,btnBackspace,btnNum0,btnClear;

    //textViews
    TextView txtResult,txtInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize variables on start up
        initializeViewData();
        //initialize click operations of the button
        initializeClickOperations();
    }

    //initialize click operations of the button
    private void initializeClickOperations() {

        final CalculatorAbstraction calculator = Calculator.getInstance();

        //when number 0 is clicked
        btnNum0.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //set add operation and compute it
                calculator.setOperation(new ConcatNumber());
                calculator.doOperation("0");
                UpdateView();
            }
        });
        //when number 1 is clicked
        btnNum1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //set add operation and compute it
                calculator.setOperation(new ConcatNumber());
                calculator.doOperation("1");
                UpdateView();
            }
        });

        //when number 2 is clicked
        btnNum2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //set add operation and compute it
                calculator.setOperation(new ConcatNumber());
                calculator.doOperation("2");
                UpdateView();
            }
        });

        //when number 3 is clicked
        btnNum3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //set add operation and compute it
                calculator.setOperation(new ConcatNumber());
                calculator.doOperation("3");
                UpdateView();
            }
        });

        //when number 4 is clicked
        btnNum4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //set add operation and compute it
                calculator.setOperation(new ConcatNumber());
                calculator.doOperation("4");
                UpdateView();
            }
        });

        //when number 5 is clicked
        btnNum5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //set add operation and compute it
                calculator.setOperation(new ConcatNumber());
                calculator.doOperation("5");
                UpdateView();
            }
        });
        //when number 6 is clicked
        btnNum6.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //set add operation and compute it
                calculator.setOperation(new ConcatNumber());
                calculator.doOperation("6");
                UpdateView();
            }
        });
        //when number 7 is clicked
        btnNum7.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //set add operation and compute it
                calculator.setOperation(new ConcatNumber());
                calculator.doOperation("7");
                UpdateView();
            }
        });
        //when number 8 is clicked
        btnNum8.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //set add operation and compute it
                calculator.setOperation(new ConcatNumber());
                calculator.doOperation("8");
                UpdateView();
            }
        });
        //when number 9 is clicked
        btnNum9.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //set add operation and compute it
                calculator.setOperation(new ConcatNumber());
                calculator.doOperation("9");
                UpdateView();
            }
        });
        //when decimal "." is clicked
        btnDecimal.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //set add operation and compute it
                calculator.setOperation(new ConcatNumber());
                calculator.doOperation(".");
                UpdateView();
            }
        });
        //when decimal "." is clicked
        btnClear.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //set add operation and compute it
                calculator.setOperation(new ClearOperation());
                calculator.doOperation(".");
                UpdateView();
            }
        });
        //when add is clicked
        btnAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //set add operation and compute it
                calculator.setOperation(new AddOperation());
                calculator.doOperation(null);
            }
        });
        //when multiply is clicked
        btnMultiply.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //set multiply operation and compute it
                calculator.setOperation(new MultiplyOperation());
                calculator.doOperation(null);
            }
        });
        //when divide is clicked
        btnDivide.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //set divide operation and compute it
                calculator.setOperation(new DivideOperation());
                calculator.doOperation(null);
            }
        });
        //when subtract is clicked
        btnSubtract.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //set subtract operation and compute it
                calculator.setOperation(new SubtractOperation());
                calculator.doOperation(null);
            }
        });
    }

    private void UpdateView() {
        txtInput.setText(SharedFunctionality.getInputNumberString());
        txtResult.setText(SharedFunctionality.getResultString());
    }

    //initialize variables
    private void initializeViewData() {
        btnNum0 = findViewById(R.id.btnNum0);
        btnNum1 = findViewById(R.id.btnNum1);
        btnNum2 = findViewById(R.id.btnNum2);
        btnNum3 = findViewById(R.id.btnNum3);
        btnNum4 = findViewById(R.id.btnNum4);
        btnNum5 = findViewById(R.id.btnNum5);
        btnNum6 = findViewById(R.id.btnNum6);
        btnNum7 = findViewById(R.id.btnNum7);
        btnNum8 = findViewById(R.id.btnNum8);
        btnNum9 = findViewById(R.id.btnNum9);
        btnDecimal = findViewById(R.id.btnDecimal);
        btnUndo = findViewById(R.id.btnUndo);
        btnRedo = findViewById(R.id.btnRedo);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        btnSubtract= findViewById(R.id.btnSubtract);
        btnAdd = findViewById(R.id.btnAdd);
        btnEqual = findViewById(R.id.btnEqual);
        btnBackspace = findViewById(R.id.btnBackspace);
        btnClear = findViewById(R.id.btnClear);
        txtResult = findViewById(R.id.txtResult);
        txtInput = findViewById(R.id.txtInput);

    }
}
