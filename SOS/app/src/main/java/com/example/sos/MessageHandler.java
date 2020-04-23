package com.example.sos;

import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class MessageHandler {
    List<Contact> contacts = new ArrayList<Contact>();

    public void sendMessages(List<LocationDetails> locations,List<Contact> contacts){

        SmsManager smsManager = SmsManager.getDefault();
        //loop through locations
        ArrayList<String> message = new ArrayList<>();
        for(int i = 0;i<locations.size();i++){
            message.add(locations.get(i).getAddress());
        }
        try {
            //if there are any messages to be sent
            if (message.size() > 0) {
                //loop through contacts
                for (int i = 0; i < contacts.size(); i++) {
                    smsManager.sendMultipartTextMessage(contacts.get(i).getNumber(), null, message, null, null);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
