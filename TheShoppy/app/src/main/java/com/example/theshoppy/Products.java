package com.example.theshoppy;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;

import static android.content.res.Resources.getSystem;

//maintain the products and their price as of now
public class Products {

    //declare a static HashMap of products
    static final HashMap<String,Integer> products = new HashMap<String,Integer>();

    //initialize the products HashMap
    public static void initializeProducts(Context context){
        try {
            //add laptops brands
            products.put(context.getString(R.string.dell_laptop), 1249);
            products.put(context.getString(R.string.lenova_laptop), 1549);
            products.put(context.getString(R.string.hp_laptop), 1150);
            //add desktops brands
            products.put(context.getString(R.string.dell_desktop), 475);
            products.put(context.getString(R.string.lenova_desktop), 450);
            products.put(context.getString(R.string.hp_desktop), 400);
            //add ons
            products.put(context.getString(R.string.ssd), 60);
            products.put(context.getString(R.string.printer), 100);
            //laptop peripherals
            products.put(context.getString(R.string.cooling_mat), 33);
            products.put(context.getString(R.string.usb), 60);
            products.put(context.getString(R.string.laptop_stand), 139);
            //desktop peripherals
            products.put(context.getString(R.string.webcam), 95);
            products.put(context.getString(R.string.hard_drive), 64);
        }
        catch (Exception e){
            Log.i("Info",e.getMessage());
        }
    }

    //define the getter for the HashMap elements on the basis of the key
    public static int getPrice(String productName){
        return products.get(productName);
    }

}
