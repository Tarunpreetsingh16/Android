package com.example.sos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String dbName = "Security";
    public static final int version = 1;
    public static final String TABLE_NAME = "Locations";
    public static final String COL1 = "id";
    public static final String COL2 = "longitude";
    public static final String COL3 = "latitude";
    public static final String COL4 = "country";
    public static final String COL5 = "locality";
    public static final String COL6 = "address";
    public static final String COL7 = "date";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                                                COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                COL2  + " TEXT NOT NULL, " +
                                                COL3  + " TEXT NOT NULL, " +
                                                COL4  + " TEXT NULL, " +
                                                COL5  + " TEXT NULL, " +
                                                COL6  + " TEXT NULL, " +
                                                COL7  + " TEXT NULL); ";

    //constructor
    public DatabaseHandler(@Nullable Context context){
        super(context,dbName,null,version);
    }
    //create table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    //insert data
    public boolean insertLocation(LocationDetails details){
        //instance of database
        SQLiteDatabase db = this.getWritableDatabase();
        //set content values
        ContentValues contentValues = new ContentValues();
        //set content values
        contentValues.put(COL2,details.getLongitude());
        contentValues.put(COL3,details.getLatitude());
        contentValues.put(COL4,details.getCountry());
        contentValues.put(COL5,details.getLocality());
        contentValues.put(COL6,details.getAddress());
        contentValues.put(COL7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        //insert content values in the table
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1) //no records were inserted
            return false;
        return true;
    }
    //fetch data public
    public Cursor getData(){
        //instance of database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor crs;
        crs = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL1 + " DESC LIMIT 2",null);
        return crs;
    }
}
