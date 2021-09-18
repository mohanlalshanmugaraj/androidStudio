package com.example.timeentry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.timeentry.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {



   // ActivityMainBinding mActivityMainBinding=ActivityMainBinding.inflate(getLayoutInflater());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mActivityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
       // setContentView(mActivityMainBinding.getRoot());

        mActivityMainBinding.createTimesheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,CreateTimeSheetsActivity.class);
                startActivity(intent);
            }
        });
        mActivityMainBinding.viewTimesheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,ViewTimeSheetsActivity.class);
                startActivity(intent);
            }
        });
    }
}