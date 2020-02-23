package com.example.theshoppy;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
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
    Spinner spinnerBrand;
    LinearLayout peripheralsDesktop;
    LinearLayout peripheralsLaptop;
    TextView txtPeripherals;
    Button btnCalculate;
    CheckBox chkSSD;
    CheckBox chkPrinter;
    RadioButton radioBtnCoolingPad;
    RadioButton radioBtnUSB;
    RadioButton radioBtnStand;
    RadioButton radioBtnWebcam;
    RadioButton radioBtnExternalHD;
    EditText editName;
    TextView txtInvoice;
    List<String> provinces;
    CardView cardInvoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUp();
    }
    private void setUp() {
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
        //set up spinner functionality
        setUpBrandSpinner();
        //set up cost calculation using a button
        setUpCalculation();
    }

    private void setUpCalculation() {
        //add listener to the button to start calculating the price
        btnCalculate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                //clear old invoice data
                clearInvoice();

                String name = editName.getText().toString().trim();
                String province = editProvince.getText().toString().trim();

                //check if user has entered the data
                if( name.isEmpty() ||
                    province.isEmpty()){
                    //give feedback to the user
                    Toast.makeText(MainActivity.this,"Fill out the name and province first",Toast.LENGTH_LONG).show();
                }
                else if(!provinces.contains(province)){
                    //give feedback to the user
                    Toast.makeText(MainActivity.this,"Choose correct province",Toast.LENGTH_LONG).show();
                }
                else {
                    //add config to invoice
                    addToInvoice();
                    //show invoice card
                    cardInvoice.setVisibility(View.VISIBLE);
                    //print invoice
                    Invoice.printInvoice(name,
                            province,
                            editDate.getText().toString(),
                            (radioBtnDesktop.isChecked()?"Desktop":"Laptop"), txtInvoice);
                }
            }
        });
    }

    private void clearInvoice(){
        //clear the text view
        txtInvoice.setText("");
        //hide the card
        cardInvoice.setVisibility(View.GONE);
        Invoice.clear();
    }

    private void addToInvoice(){
        //initialize products list with prices
        Products.initializeProducts(MainActivity.this);
        //check if desktop is selected
        if(radioBtnDesktop.isChecked()){
            //check brand of the desktop

            checkAddBrand(R.string.desktops);
        }
        else if(radioBtnLaptop.isChecked()){ //laptop is checked
            //check brand of laptop
            checkAddBrand(R.string.laptops);
        }

        //check for add ons
        if(chkSSD.isChecked()){
            Invoice.addItem("Additional",getString(R.string.ssd),Products.getPrice(getString(R.string.ssd)));
        }
        if(chkPrinter.isChecked()){
            Invoice.addItem("Additional",getString(R.string.printer),Products.getPrice(getString(R.string.printer)));
        }
        //check for added peripherals of Desktop
        if(radioBtnWebcam.isChecked()){
            Invoice.addItem("Added Peripherals",getString(R.string.webcam),Products.getPrice(getString(R.string.webcam)));
        }
        else if(radioBtnExternalHD.isChecked()){
            Invoice.addItem("Added Peripherals",getString(R.string.hard_drive),Products.getPrice(getString(R.string.hard_drive)));
        }
        //check for added peripherals of Laptops
        if(radioBtnCoolingPad.isChecked()){
            Invoice.addItem("Added Peripherals",getString(R.string.cooling_mat),Products.getPrice(getString(R.string.cooling_mat)));
        }
        else if(radioBtnUSB.isChecked()){
            Invoice.addItem("Added Peripherals",getString(R.string.usb),Products.getPrice(getString(R.string.usb)));
        }
        else if(radioBtnStand.isChecked()){
            Invoice.addItem("Added Peripherals",getString(R.string.laptop_stand),Products.getPrice(getString(R.string.laptop_stand)));
        }
    }

    private void checkAddBrand(int check){
        if(check == R.string.desktops){
            //check the brand of desktop
            if(spinnerBrand.getSelectedItem().toString() == getString(R.string.dell)){
                //get the price and add to the list
                Invoice.addItem("Brand",getString(R.string.dell),Products.getPrice(getString(R.string.dell_desktop)));
            }
            else if(spinnerBrand.getSelectedItem().toString() == getString(R.string.hp)){
                //get the price and add to the list
                Invoice.addItem("Brand",getString(R.string.hp),Products.getPrice(getString(R.string.hp_desktop)));
            }
            else if(spinnerBrand.getSelectedItem().toString() == getString(R.string.lenova)){
                //get the price and add to the list
                Invoice.addItem("Brand",getString(R.string.lenova),Products.getPrice(getString(R.string.lenova_desktop)));
            }
        }
        else{
            //check the brand of the laptop
            if(spinnerBrand.getSelectedItem().toString() == getString(R.string.dell)){
                //get the price and add to the list
                Invoice.addItem("Brand",getString(R.string.dell),Products.getPrice(getString(R.string.dell_laptop)));
            }
            else if(spinnerBrand.getSelectedItem().toString() == getString(R.string.hp)){
                //get the price and add to the list
                Invoice.addItem("Brand",getString(R.string.hp),Products.getPrice(getString(R.string.hp_laptop)));
            }
            else if(spinnerBrand.getSelectedItem().toString() == getString(R.string.lenova)){
                //get the price and add to the list
                Invoice.addItem("Brand",getString(R.string.lenova),Products.getPrice(getString(R.string.lenova_laptop)));
            }
        }

    }

    private void setUpBrandSpinner() {
        //create list of brands
        List<String> brands = new ArrayList<>();
        brands.add(getString(R.string.dell));
        brands.add(getString(R.string.hp));
        brands.add(getString(R.string.lenova));
        //create adapter for spinner
        ArrayAdapter<String> brandsAdapter =
                new ArrayAdapter<String>(this,R.layout.spinner_text,brands);
        //set the adapter for thr provinces editText
        spinnerBrand.setAdapter(brandsAdapter);
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
        spinnerBrand = findViewById(R.id.spinnerBrand);
        peripheralsDesktop = findViewById(R.id.peripheralsDesktop);
        peripheralsLaptop = findViewById(R.id.peripheralsLaptop);
        txtPeripherals = findViewById(R.id.txtPeripherals);
        btnCalculate = findViewById(R.id.btnCalculate);
        chkSSD = findViewById(R.id.chkSSD);
        chkPrinter = findViewById(R.id.chkPrinter);
        radioBtnCoolingPad = findViewById(R.id.radioBtnCoolingPad);
        radioBtnExternalHD = findViewById(R.id.radioBtnExternalHD);
        radioBtnStand = findViewById(R.id.radioBtnStand);
        radioBtnUSB = findViewById(R.id.radioBtnUSB);
        radioBtnWebcam = findViewById(R.id.radioBtnWebcam);
        editName = findViewById(R.id.editName);
        txtInvoice = findViewById(R.id.txtInvoice);
        cardInvoice = findViewById(R.id.cardInvoice);
        provinces = new ArrayList<>();
    }

    private void setUpComputerType() {

        radioGrpType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                //check which radio button is checked
                if(radioBtnDesktop.isChecked()){
                    //if desktop is chosen then hide some UI elements and some visible
                    cardLaptops.setVisibility(View.GONE);
                    cardDesktops.setVisibility(View.VISIBLE);
                    peripheralsDesktop.setVisibility(View.VISIBLE);
                    peripheralsLaptop.setVisibility(View.GONE);
                    txtPeripherals.setVisibility(View.VISIBLE);
                    //uncheck the additional peripherals of other computer type
                    radioBtnCoolingPad.setChecked(false);
                    radioBtnUSB.setChecked(false);
                    radioBtnStand.setChecked(false);
                }
                else{
                    //if laptop is chosen then hide some UI elements and some visible
                    cardDesktops.setVisibility(View.GONE);
                    cardLaptops.setVisibility(View.VISIBLE);
                    peripheralsDesktop.setVisibility(View.GONE);
                    peripheralsLaptop.setVisibility(View.VISIBLE);
                    txtPeripherals.setVisibility(View.VISIBLE);

                    //uncheck the additional peripherals of other computer type
                    radioBtnWebcam.setChecked(false);
                    radioBtnExternalHD.setChecked(false);
                }
            }
        });

    }

    private void setupAutoComplete() {
        //create List of provinces in Canada
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
