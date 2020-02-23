package com.example.theshoppy;

import java.util.HashMap;

//maintain the products and their price as of now
public class Products {

    //declare a static HashMap of products
    static final HashMap<String,Integer> products = new HashMap<String,Integer>();

    //initialize the products HashMap
    public static void initializeProducts(){
        //add laptops brands
        products.put("Dell_Laptop",1249);
        products.put("Lenova_Laptop",1549);
        products.put("HP_Laptop",1150);
        //add desktops brands
        products.put("Dell_Desktop",475);
        products.put("Lenova_Desktop",450);
        products.put("HP_Desktop",400);
        //add ons
        products.put("SSD",60);
        products.put("Printer",100);
        //laptop peripherals
        products.put("Cooling Mat",33);
        products.put("USB C-HUB",60);
        products.put("Laptop Stand",139);
        //desktop peripherals
        products.put("Webcam",95);
        products.put("External Hard Drive",64);
    }

    //define the getter for the HashMap elements on the basis of the key
    public static int getPrice(String productName){
        return products.get(productName);
    }

}
