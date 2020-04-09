package com.example.gradingapp;


import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewGrades extends Fragment {
    private RecyclerView recyclerView;
    private List<RecordsAdapter> records = new ArrayList<>();
    private RecordListAdapter listAdapter;
    private SqlDatabase database;

    View view;
    public ViewGrades() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_view_grades, container, false);
        initializeData(view);
        return view ;
    }

    private void initializeData(View view) {
        recyclerView = view.findViewById(R.id.recyclerRecords);
        database = (SqlDatabase)getArguments().getSerializable("database");
        fetchRecords();
        bindAdapter(view);
    }

    private void bindAdapter(View view) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());

        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new RecordListAdapter(records,view.getContext());
         //##tested till here##//
        recyclerView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }

    private void fetchRecords() {
        //fetch records from the database in a list
        Cursor cursor = database.getDb().rawQuery("SELECT * FROM grades",null);
        //if no records were found in database
        if(cursor.getCount() == 0){
            Toast.makeText(getContext(),"No records were found!",Toast.LENGTH_SHORT).show();
            return;
        }
        RecordsAdapter recordsAdapter;
        //get the records in the list
        while(cursor.moveToNext()){
            recordsAdapter = new RecordsAdapter();
            recordsAdapter.setId(cursor.getInt(0));
            recordsAdapter.setFirstName(cursor.getString(1));
            recordsAdapter.setLastName(cursor.getString(2));
            recordsAdapter.setMarks(cursor.getInt(3));
            recordsAdapter.setCourse(cursor.getString(4));
            recordsAdapter.setCredit(cursor.getInt(5));
            records.add(recordsAdapter);
        }
        for(int i = 0;i<records.size();i++){
            Log.i("check",records.get(i).getFirstName());
        }
    }
}
