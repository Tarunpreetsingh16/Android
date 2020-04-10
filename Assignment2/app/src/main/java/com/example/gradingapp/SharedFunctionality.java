package com.example.gradingapp;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SharedFunctionality {

    static List<String> courses;
    public static void addCoursesToList(Context context) {
        //create list of courses
        courses = new ArrayList<String>();
        courses.add(context.getString(R.string.PROG8450));
        courses.add(context.getString(R.string.PROG8460));
        courses.add(context.getString(R.string.PROG8470));
        courses.add(context.getString(R.string.PROG8480));

    }
    public static void setAdapter(Context context,ListView courseList){
        //crete array adapter to attach it to the listview
        ArrayAdapter<String> courseAdapter = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_list_item_1,
                courses);
        //attach the adapter to the list view
        courseList.setAdapter(courseAdapter);
    }
    public static String getCourse(int  i){
        return courses.get(i);
    }
}
