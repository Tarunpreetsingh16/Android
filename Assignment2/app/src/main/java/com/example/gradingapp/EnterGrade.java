package com.example.gradingapp;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
    Button submit;
    List<String> courses;
    TextView courseSelected;
    String course;
    int credit;
    RadioGroup rgCredit;
    //create database instance
    SQLiteDatabase db;
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
        //setup course selection
        setupCourseSelection();
        //setup radio button group to get the credit
        setupCreditSelection();
        //setup submit button functionality
        setupSubmit();
    }

    private void setupCreditSelection() {
        rgCredit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb1:
                        credit = 1;
                        break;
                    case R.id.rb2:
                        credit = 2;
                        break;
                    case R.id.rb3:
                        credit = 3;
                        break;
                    case R.id.rb4:
                        credit = 4;
                        break;
                    default:
                        credit = 0;
                        break;
                }
            }
        });
    }

    private void setupCourseSelection() {
        courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                course = courses.get(i);
                courseSelected.setText(course);
            }
        });
    }

    private void setupSubmit() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = true;
                //check if any of the details is not present
                if( editFirstName.getText().toString().trim().length() == 0 ||
                        editLastName.getText().toString().trim().length() == 0 ||
                        course.trim().length() == 0 ||
                        credit == 0 ||
                        marks.getText().toString().trim().length() == 0) {
                    //set flag to false if any of the details is not present
                    flag = false;
                }
                if(flag){
                    Toast.makeText(getContext(),"True",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getContext(),"Enter Data!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addCoursesToList(View view) {
        //create list of courses
        courses = new ArrayList<String>();
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
        submit = view.findViewById(R.id.btnSubmit);
        rgCredit = view.findViewById(R.id.rgCredit);
        courseSelected = view.findViewById(R.id.txtCourseCode);
    }

}
