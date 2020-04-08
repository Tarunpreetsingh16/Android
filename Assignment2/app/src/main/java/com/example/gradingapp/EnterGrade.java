package com.example.gradingapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnterGrade extends Fragment {

    View view;
    EditText editFirstName;
    EditText editLastName;
    EditText marks;
    ListView courseList;

    public EnterGrade() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_enter_grade, container, false);
        //initialize variables
        intializeFragment(view);
        return view;
    }

    private void intializeFragment(View view) {
        //initialize variables
        initializeVariables(view);
        //setup courses list
        addCoursesToList(view);
    }

    private void addCoursesToList(View view) {
        //create list of courses
        List<String> courses = new ArrayList<String>();
        courses.add(getString(R.string.PROG8450));
        courses.add(getString(R.string.PROG8460));
        courses.add(getString(R.string.PROG8470));
        courses.add(getString(R.string.PROG8480));
        //crete array adapter to attach it to the listview
        ArrayAdapter<String> courseAdapter = new ArrayAdapter<String>(
                view.getContext(),
                android.R.layout.simple_list_item_1,
                courses);
        //attach the adapter to the list view
        courseList.setAdapter(courseAdapter);
    }

    private void initializeVariables(View view) {
        editFirstName = view.findViewById(R.id.editFirstName);
        editLastName = view.findViewById(R.id.editLastName);
        marks = view.findViewById(R.id.editMarks);
        courseList = view.findViewById(R.id.listCourses);
    }

}
