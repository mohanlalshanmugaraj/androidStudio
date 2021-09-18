package com.example.timesheet.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;

import com.example.timesheet.R;
import com.example.timesheet.adapter.ViewAdapter;
import com.example.timesheet.database.DBController;
import com.example.timesheet.database.Timesheet;
import com.example.timesheet.databinding.ActivityViewTimesheetsBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class ViewTimesheetsActivity extends AppCompatActivity {

    private ActivityViewTimesheetsBinding viewTimesheetsBinding;
    private ViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewTimesheetsBinding = ActivityViewTimesheetsBinding.inflate(LayoutInflater.from(this));
        setContentView(viewTimesheetsBinding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                List<Timesheet> timesheet = DBController.getInstance(ViewTimesheetsActivity.this).getDao().getAll();

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        viewTimesheetsBinding.rvTimeSheet.setLayoutManager(new LinearLayoutManager(ViewTimesheetsActivity.this));
                        adapter = new ViewAdapter(timesheet);
                        viewTimesheetsBinding.rvTimeSheet.setAdapter(adapter);

                    }
                });
            }
        });

    }
}