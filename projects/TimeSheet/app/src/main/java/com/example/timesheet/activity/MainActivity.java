package com.example.timesheet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.timesheet.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
private ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding=ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(mainBinding.getRoot());
    }

    public void createTimesheets(View view) {
        Intent createTimesheet = new Intent(MainActivity.this,CreateTimesheetActivity.class);
        startActivity(createTimesheet);
    }

    public void viewTimesheets(View view) {
        Intent viewTimesheet = new Intent(MainActivity.this,ViewTimesheetsActivity.class);
        startActivity(viewTimesheet);
    }
}