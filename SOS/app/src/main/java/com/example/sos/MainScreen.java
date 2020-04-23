package com.example.sos;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainScreen extends AppCompatActivity {
    ImageButton btnFirstAdd;
    ImageButton btnSecondAdd;
    ImageButton btnThirdAdd;
    ImageButton btnFirstRemove;
    ImageButton btnSecondRemove;
    ImageButton btnThirdRemove;
    TextView txtFirstName;
    TextView txtSecondName;
    TextView txtThirdName;
    TextView txtFirstNumber;
    TextView txtSecondNumber;
    TextView txtThirdNumber;
    LinearLayout contact1;
    LinearLayout contact2;
    LinearLayout contact3;
    int check = 1;
    String contactData[];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        getSupportActionBar().hide();
        initializeData();
        //setup select contact functionality for all 3 contacts
        setupSelectContact();
    }

    private void initializeData() {
        btnFirstAdd = findViewById(R.id.btnAddFirst);
        btnSecondAdd= findViewById(R.id.btnAddSecond);
        btnThirdAdd = findViewById(R.id.btnAddThird);
        btnFirstRemove = findViewById(R.id.btnRemoveFirst);
        btnSecondRemove = findViewById(R.id.btnRemoveSecond);
        btnThirdRemove = findViewById(R.id.btnRemoveThird);
        txtFirstName = findViewById(R.id.txtFirstName);
        txtSecondName = findViewById(R.id.txtSecondName);
        txtThirdName = findViewById(R.id.txtThirdName);
        txtFirstNumber = findViewById(R.id.txtFirstNumber);
        txtSecondNumber = findViewById(R.id.txtSecondNumber);
        txtThirdNumber = findViewById(R.id.txtThirdNumber);
        contact1 = findViewById(R.id.firstContact);
        contact2 = findViewById(R.id.secondContact);
        contact3 = findViewById(R.id.thirdContact);
    }

    private void setupSelectContact() {
       //sertup add and remove button functionality
       setupFirstContact();
       setupSecondContact();
       setupThirdContact();
   }

    private void setupFirstContact() {
        btnFirstAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickContact();
                //set the first contact add button was clicked
                check = 1;
            }
        });
        btnFirstRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change attributes on removing a contact
                changeAttributesOnRemove(txtFirstName,txtFirstNumber,contact1,btnFirstAdd,btnFirstRemove);
            }
        });
    }

    private void setupSecondContact() {
        btnSecondAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickContact();
                //set the first contact add button was clicked
                check = 2;
            }
        });
        btnSecondRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change attributes on removing a contact
                changeAttributesOnRemove(txtSecondName,txtSecondNumber,contact2,btnSecondAdd,btnSecondRemove);
            }
        });
    }

    private void setupThirdContact() {
        btnThirdAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickContact();
                //set the third contact add button was clicked
                check = 3;
            }
        });
        btnThirdRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change attributes on removing a contact
                changeAttributesOnRemove(txtThirdName,txtThirdNumber,contact3,btnThirdAdd,btnThirdRemove);
            }
        });
    }

    @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);

       //check the result code
       if(requestCode == 1 && resultCode == RESULT_OK){
           ContactsHandler handler = new ContactsHandler(this);
           contactData = handler.getContactDetails(data.getData());
           if(check == 1){
               //change attributes of UI elements
               changeAtributesOnAdd(txtFirstName,txtFirstNumber,contact1,btnFirstAdd,btnFirstRemove);
           }
           else if(check == 2){
               //change attributes of UI elements
               changeAtributesOnAdd(txtSecondName,txtSecondNumber,contact2,btnSecondAdd,btnSecondRemove);
           }
           else if(check == 3){
               //change attributes of UI elements
               changeAtributesOnAdd(txtThirdName,txtThirdNumber,contact3,btnThirdAdd,btnThirdRemove);
           }
       }
   }

   public void pickContact(){
       Intent contactPicker = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
       startActivityForResult(contactPicker,1);
   }

   public void changeAtributesOnAdd(TextView name,TextView number, LinearLayout contact, ImageButton add, ImageButton remove){
        //set data
       name.setText(contactData[0]);
       number.setText(contactData[1]);
       //change visibility
       contact.setVisibility(View.VISIBLE);
       add.setVisibility(View.GONE);
       remove.setVisibility(View.VISIBLE);
   }
   public void changeAttributesOnRemove(TextView name,TextView number, LinearLayout contact, ImageButton add, ImageButton remove){
       //set data
       name.setText("");
       number.setText("");
       //change visibility
       contact.setVisibility(View.GONE);
       add.setVisibility(View.VISIBLE);
       remove.setVisibility(View.GONE);
   }
}
