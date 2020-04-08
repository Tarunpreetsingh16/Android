package com.example.gradingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecordListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<RecordsAdapter> records;

    public RecordListAdapter(List<RecordsAdapter> list, Context context){
        super();
        records = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtId;
        public TextView txtFirstName;
        public TextView txtLastName;
        public TextView txtMarks;
        public TextView txtCredit;
        public TextView txtCourse;
        public ViewHolder(View itemView){
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtFirstName = itemView.findViewById(R.id.txtFirstName );
            txtLastName = itemView.findViewById(R.id.txtLastName );
            txtMarks = itemView.findViewById(R.id.txtMarks);
            txtCredit = itemView.findViewById(R.id.txtCredit);
            txtCourse = itemView.findViewById(R.id.txtCourse);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecordsAdapter recordsAdapter = records.get(position);
        ((ViewHolder) holder).txtId.setText(recordsAdapter.getId());
        ((ViewHolder) holder).txtFirstName.setText(recordsAdapter.getFirstName());
        ((ViewHolder) holder).txtLastName.setText(recordsAdapter.getLastName());
        ((ViewHolder) holder).txtMarks.setText(recordsAdapter.getMarks());
        ((ViewHolder) holder).txtCredit.setText(recordsAdapter.getCredit());
        ((ViewHolder) holder).txtCourse.setText(recordsAdapter.getCourse());
    }

    @Override
    public int getItemCount() {
        return records.size();
    }
}
