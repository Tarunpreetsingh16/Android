package com.example.gradingapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchGrades extends Fragment {

    View view;
    ListView courseList;
    RadioGroup rgSearchBy;
    RadioButton rbId;
    RadioButton rbCode;
    Button btnSearch;
    EditText editId;
    String course;
    TextView txtTitle;
    LinearLayout linearSelectedCourse;
    TextView courseSelected;
    public SearchGrades() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search_grades, container, false);
        initializeFragment();
        return view;
    }
    private void initializeFragment(){
        //initialize variables
        initializeVariables(view);
        //setup adapter for the course list
        SharedFunctionality.setAdapter(getContext(),courseList);
        //setup course selection funtionality form list view
        setupCourseSelection();
        //setup radio button functionality
        setupRadioGroup();
        //setup search button functionality
        setupSearchButton();
    }
    private void initializeVariables(View view){
        courseList = view.findViewById(R.id.listCourses);
        rgSearchBy = view.findViewById(R.id.rgSearchBy);
        btnSearch = view.findViewById(R.id.btnSearch);
        editId = view.findViewById(R.id.editId);
        txtTitle = view.findViewById(R.id.txtTitle);
        linearSelectedCourse = view.findViewById(R.id.linearSelectedCourse);
        courseSelected  =view.findViewById(R.id.txtCode);
        rbId = view.findViewById(R.id.rbId);
        rbCode = view.findViewById(R.id.rbCode);
    }
    private void setupCourseSelection() {
        courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //get the course selected by the user and display it on the screen
                course = SharedFunctionality.getCourse(i);
                courseSelected.setText(course);
            }
        });
    }
    private void setupSearchButton(){
        //setup submit button functionality
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if data is provided by the user or not
                //if Id button is checked then check if id is provided by the user
                if(rbId.isChecked() && editId.getText().toString().trim().length() == 0){
                    //show toast to tell that data is not provided
                    Toast.makeText(getContext(),"Enter data!",Toast.LENGTH_SHORT).show();
                    return;
                }
                //if program code button is checked then check if course is provided by the user
                else if(rbCode.isChecked() && courseSelected.getText().toString().trim().length() == 0){
                    //show toast to tell that data is not provided
                    Toast.makeText(getContext(),"Select Course first!",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
    private void setupRadioGroup(){
        //create listener to listen when radio button is clicked
        rgSearchBy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                //check which radio button is checked
                switch(i){
                    case R.id.rbId:
                        //clear previously entered value in course
                        course = "";
                        courseSelected.setText("");
                        //change the visibility of editText ID and listView courseList
                        editId.setVisibility(View.VISIBLE);
                        txtTitle.setText("Enter Id");
                        courseList.setVisibility(View.GONE);
                        linearSelectedCourse.setVisibility(View.GONE);
                        break;
                    case R.id.rbCode:
                        //clear previously entered value in id
                        editId.setText("");
                        //change the visibility of edittext ID and listView courseList
                        txtTitle.setText("Select Course");
                        courseList.setVisibility(View.VISIBLE);
                        linearSelectedCourse.setVisibility(View.VISIBLE);
                        editId.setVisibility(View.GONE);
                }
            }
        });
    }
}
