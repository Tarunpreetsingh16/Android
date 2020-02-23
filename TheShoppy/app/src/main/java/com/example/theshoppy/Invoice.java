package com.example.theshoppy;

import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//class for handling invoice related things
public class Invoice {

    //declare variables
    static int totalCost;
    //create map for adding customization details
    static HashMap<String,String> customization = new HashMap<String,String>();

    //method to add item(as of now only price)
    public static void addItem(String key,String value, int price) {
        String finalValue = value;
        /*there can be multiple additional peripherals
         * so we need to check if there is already a key
         * and concatenate the string
         */
        if (customization.containsKey(key)) {
            //get old peripherals and concat new to them
            String oldPeripherals = customization.get(key);
            finalValue = oldPeripherals + ", " + value;
        }
        //add the type and value of the item
        customization.put(key, finalValue);
        //add to the total cost of the products checked
        totalCost += price;
    }

    //print Invoice to the activity/ show to the user
    public static void printInvoice(String customerName, String province, String purchaseDate,EditText txtInvoice){
        StringBuilder invoiceData = new StringBuilder();
        //append header data to the invoiceData
        invoiceData.append("Customer : " + customerName + "\n\n");
        invoiceData.append("Province: " + province+ "\n\n");
        invoiceData.append("Date of Purchase: " + purchaseDate + "\n\n");
        //create iterator to loop over the customizations
        Iterator iterator = customization.entrySet().iterator();

        //loop over customizations and add to the invoiceData
        while(iterator.hasNext()){
            Map.Entry customization = (Map.Entry)iterator.next();
            invoiceData.append(customization.getKey() + ": " + customization.getValue() + "\n\n");
        }

        //at last append the totalPrice including taxes
        //calculate taxes
        float tax = calculateTax();
        float finalCost = totalCost + tax;

        invoiceData.append("Cost : $" + finalCost);
    }
    //calculate the taxes
    private static float calculateTax(){
        return (totalCost * 13)/100;
    }
}
