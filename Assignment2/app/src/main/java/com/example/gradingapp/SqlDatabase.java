package com.example.gradingapp;

import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;

public class SqlDatabase implements Serializable {
    private SQLiteDatabase db;
    //getter and setter
    public void setDb(SQLiteDatabase db){
        this.db = db;
    }
    public SQLiteDatabase getDb(){
        return this.db;
    }
}
