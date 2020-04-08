package com.example.gradingapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewGrades extends Fragment {
    private RecyclerView recyclerView;
    private List<RecordsAdapter> records = new ArrayList<>();
    private RecordListAdapter listAdapter;
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
        fetchRecords();
        bindAdapter(view);
    }

    private void bindAdapter(View view) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new RecordListAdapter(records,view.getContext());
        recyclerView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }

    private void fetchRecords() {
        //fetch records from the database in a list
    }
}
