package com.example.sos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
    List<Contact> contacts;
    SharedPreferences.Editor editor;
    Gson gson;

    SharedPreferences sharedPreferences;
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
        sharedPreferences = getSharedPreferences(getString(R.string.app_pref),MODE_PRIVATE);
        contacts = new ArrayList<Contact>();
        editor = sharedPreferences.edit();
        gson = new Gson();
        setContactList();
    }

    private void setContactList() {
        //get already added contacts from the share pref
        String jsonData = sharedPreferences.getString(getString(R.string.contacts),"");
        //check if there are any contacts
        if(!jsonData.isEmpty())
        {
            Type type = new TypeToken<List<Contact>>(){}.getType();
            contacts = gson.fromJson(jsonData,type);
            Log.i("Contacts",String.valueOf(contacts.size()));
            contactData = new String[2];
            //set the data to the
            if(contacts.size() == 1){
                contactData[0] = contacts.get(0).getName();
                contactData[1] = contacts.get(0).getNumber();
                changeAttributesOnAdd(txtFirstName,txtFirstNumber,contact1,btnFirstAdd,btnFirstRemove);
            }
            else if(contacts.size() == 2){
                contactData[0] = contacts.get(0).getName();
                contactData[1] = contacts.get(0).getNumber();
                changeAttributesOnAdd(txtFirstName,txtFirstNumber,contact1,btnFirstAdd,btnFirstRemove);


                contactData[0] = contacts.get(1).getName();
                contactData[1] = contacts.get(1).getNumber();
                changeAttributesOnAdd(txtSecondName,txtSecondNumber,contact2,btnSecondAdd,btnSecondRemove);
            }
            else if(contacts.size() == 3){
                contactData[0] = contacts.get(0).getName();
                contactData[1] = contacts.get(0).getNumber();
                changeAttributesOnAdd(txtFirstName,txtFirstNumber,contact1,btnFirstAdd,btnFirstRemove);


                contactData[0] = contacts.get(1).getName();
                contactData[1] = contacts.get(1).getNumber();
                changeAttributesOnAdd(txtSecondName,txtSecondNumber,contact2,btnSecondAdd,btnSecondRemove);

                contactData[0] = contacts.get(2).getName();
                contactData[1] = contacts.get(2).getNumber();
                changeAttributesOnAdd(txtThirdName,txtThirdNumber,contact3,btnThirdAdd,btnThirdRemove);
            }
        }
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
                //set, the first contact add button was clicked
                check = 1;
            }
        });
        btnFirstRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //remove from shared preferences
                removeFromSharedPreferences(txtFirstNumber);
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
                //remove from shared preferences
                removeFromSharedPreferences(txtSecondNumber);
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
                //remove from shared preferences
                removeFromSharedPreferences(txtThirdNumber);
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
           //check if  contact was already added to the list
           for(int i=0;i<contacts.size();i++){
               if(contacts.get(i).getNumber().equals( contactData[1])){
                   Log.i("Contacts","There");
                   Toast.makeText(this,"Contact already added!",Toast.LENGTH_LONG).show();
                   return;
               }
           }
           if(check == 1){
               //change attributes of UI elements
               changeAttributesOnAdd(txtFirstName,txtFirstNumber,contact1,btnFirstAdd,btnFirstRemove);
              //store in shared preference
               storeSharedPreferences();
           }
           else if(check == 2){
               //change attributes of UI elements
               changeAttributesOnAdd(txtSecondName,txtSecondNumber,contact2,btnSecondAdd,btnSecondRemove);
               //store in shared preference
               storeSharedPreferences();
           }
           else if(check == 3){
               //change attributes of UI elements
               changeAttributesOnAdd(txtThirdName,txtThirdNumber,contact3,btnThirdAdd,btnThirdRemove);
               //store in shared preference
               storeSharedPreferences();
           }
       }
   }

   public void storeSharedPreferences(){
       Contact contact =  new Contact();
       contact.setName(contactData[0]);
       contact.setNumber(contactData[1]);
       //add it to the list
       contacts.add(contact);
       String jsonData = gson.toJson(contacts);
       editor.putString(getString(R.string.contacts),jsonData);
       editor.commit();
   }

   public void pickContact(){
       Intent contactPicker = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
       startActivityForResult(contactPicker,1);
   }

   public void changeAttributesOnAdd(TextView name, TextView number, LinearLayout contact, ImageButton add, ImageButton remove){
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
    private void removeFromSharedPreferences(TextView txtNumber) {

        for(int i= 0;i<contacts.size();i++){
            if(txtNumber.getText().toString().equals(contacts.get(i).getNumber())){
                contacts.remove(i);
            }
        }

        //update shared pref
        String jsonData = gson.toJson(contacts);
        editor.putString(getString(R.string.contacts),jsonData);
        editor.commit();
    }


}
