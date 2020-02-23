package com.example.theshoppy;

import android.util.Log;
import android.widget.TextView;

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
    public static void clear(){
        customization.clear();
        totalCost = 0;
    }

    //print Invoice to the activity/ show to the user
    public static void printInvoice(String customerName, String province,
                                    String purchaseDate, String computerType,TextView txtInvoice){
        StringBuilder invoiceData = new StringBuilder();
        //append header data to the invoiceData
        invoiceData.append("Customer : " + customerName + "\n\n");
        invoiceData.append("Province : " + province+ "\n\n");
        invoiceData.append("Date of Purchase  " + purchaseDate + "\n\n");
        invoiceData.append("Computer : " + computerType + "\n\n");
        //create iterator to loop over the customizations
        Iterator iterator = customization.entrySet().iterator();

        //loop over customizations and add to the invoiceData
        while(iterator.hasNext()){
            Map.Entry customization = (Map.Entry)iterator.next();
            invoiceData.append(customization.getKey() + ": " + customization.getValue() + "\n\n");
        }

        //at last append the totalPrice including taxes
        //calculate taxes
        double tax = calculateTax();
        double finalCost = totalCost + tax;
        //final cost with tax
        finalCost = Math.round(finalCost * 100.0)/100.0;
        invoiceData.append("Cost : $" + finalCost);
        txtInvoice.setText(invoiceData);
    }
    //calculate the taxes
    private static double calculateTax(){
        return ((double)totalCost * 0.13);
    }
}
