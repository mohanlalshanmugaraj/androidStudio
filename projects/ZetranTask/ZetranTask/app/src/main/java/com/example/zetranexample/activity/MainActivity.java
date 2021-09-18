package com.example.zetranexample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zetranexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(activityMainBinding.getRoot());
    }

    public void createEmployeeDetails(View view) {
        Intent create = new Intent(this, CreateEmpDetailActivity.class);
        startActivity(create);
    }

    public void getPunchInOut(View view) {
        Intent punch = new Intent(this, PunchInOutActivity.class);
        startActivity(punch);
    }

    public void getReport(View view) {
        Intent report = new Intent(this, ReportsActivity.class);
        startActivity(report);
    }
}