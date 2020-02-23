package com.example.theshoppy;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //declare variables
    EditText editDate ;
    Calendar calender;
    Button btnChooseDate;
    AutoCompleteTextView editProvince;
    RadioGroup radioGrpType;
    RadioButton radioBtnDesktop;
    RadioButton radioBtnLaptop;
    CardView cardDesktops;
    CardView cardLaptops;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUp();
    }
    private void setUp(){
        //set the app bar to the custom
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //attach the custom view to the main_activity for the app label
        getSupportActionBar().setCustomView(R.layout.app_bar);
        initializeVariables();
        //attach date picker to the button using clickListener
        onClickChooseDate();
        //set the  autoComplete properties int the form
        setupAutoComplete();
        //set the listener on computer type
        setUpComputerType();

    }

    private void initializeVariables() {
        //initialize variables
        editDate = findViewById(R.id.editDate);
        btnChooseDate = findViewById(R.id.btnChooseDate);
        editProvince = findViewById(R.id.editProvince);
        radioGrpType = findViewById(R.id.radioGrpType);
        radioBtnDesktop = findViewById(R.id.radioBtnDesktop);
        radioBtnLaptop = findViewById(R.id.radioBtnLaptop);
        cardDesktops = findViewById(R.id.cardDesktops);
        cardLaptops = findViewById(R.id.cardLaptops);
    }

    private void setUpComputerType() {

        radioGrpType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                //check which radio button is checked
                if(radioBtnDesktop.isChecked()){
                    //if desktop is chosen then hide laptop cardview and make desktop visible
                    cardLaptops.setVisibility(View.GONE);
                    cardDesktops.setVisibility(View.VISIBLE);
                }
                else{
                    //if laptop is chosen then hide desktop cardview and make laptops visible
                    cardDesktops.setVisibility(View.GONE);
                    cardLaptops.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private void setupAutoComplete() {
        //create List of provinces in Canada
        List<String> provinces = new ArrayList<>();
        provinces.add("Alberta");
        provinces.add("British Columbia");
        provinces.add("Manitoba");
        provinces.add("New Brunswick");
        provinces.add("Newfoundland and Labrador");
        provinces.add("Nova Scotia");
        provinces.add("Ontario");
        provinces.add("Prince Edward Island");
        provinces.add("Quebec");
        provinces.add("Saskatchewan");
        provinces.add("Northwest Territories");
        provinces.add("Nunavut");
        provinces.add("Yukon");

        //create adapter for auto complete
        ArrayAdapter<String> provinceAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,provinces);
        //set the adapter for thr provinces editText
        editProvince.setAdapter(provinceAdapter);
    }

    private void onClickChooseDate(){
        calender = Calendar.getInstance();
        final int currentDay = calender.get(Calendar.DAY_OF_MONTH);
        final int currentMonth= calender.get(Calendar.MONTH) + 1; // month is -1
        final int currentYear = calender.get(Calendar.YEAR);

        //set today's date
        editDate.setText(currentDay + "-" + currentMonth+ "-" + currentYear);
        //set onclick for date picker
        btnChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener(){
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                //set the date when date is selected in date picker
                                editDate.setText(day + "-" + (month+1)  + "-" + year);
                            }
                        },currentYear,currentMonth-1,currentDay);
                datePickerDialog.show(); // display the datePicker to the user
            }
        });
    }
}
