package com.example.sos;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

public class ContactsHandler {

    private Context context;

    public ContactsHandler(Context context){
        this.context =context;
    }
    public String[] getContactDetails(Uri data) {
        String contactDetails[] = new String[2];
        //fetch contact name from the data that was selected
        contactDetails[0] = getContactName(data);
        //fetch contact number from the data that was selected
        contactDetails[1] = getContactNumber(data);
        //return the contact data
        return contactDetails;
    }

    private String getContactName(Uri data) {
        //create a cursor to get data from source
        Cursor crs = context.getContentResolver().query(data,null,null,null,null);

        //move the cursor to the first record
        if(crs.moveToFirst()){
            //now fetch the contact name
            return crs.getString(crs.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        }
        crs.close();
        return null;
    }
    private String getContactNumber(Uri data){
        //get contact id using cursor
        Cursor crs = context.getContentResolver().query(data,new String[]{ContactsContract.Contacts._ID},null,null,null);
        String id = null;
        //now move to the first record
        if(crs.moveToFirst()){
            //fetch contact id from the data
            id = crs.getString((crs.getColumnIndex(ContactsContract.Contacts._ID)));
        }
        crs.close();
        if(id != null)
        {
            try {
                //fetch number of from the contract using the id fetched
                Cursor crsPhone = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ",
                        id,
                        null);
                Log.i("GetCount",String.valueOf(crsPhone.getCount()));
                //move the cursor to the first record
                if (crsPhone.moveToFirst()) {

                    Log.i("CheckNumber",crs.getString(crs.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                    //Log.i("number",crs.getString(crs.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                    //return the number that was fetched
                    return crsPhone.getString(crsPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                }
                crsPhone.close();
            }
            catch (Exception e){
                Log.i("Exception",e.getMessage());
            }
        }
        return null;
    }
}
