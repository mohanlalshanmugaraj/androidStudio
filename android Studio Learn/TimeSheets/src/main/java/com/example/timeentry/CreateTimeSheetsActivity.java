package com.example.timeentry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.timeentry.Database.DBController;
import com.example.timeentry.Database.TimeSheets;
import com.example.timeentry.databinding.ActivityCreateTimeSheetsBinding;
import com.example.timeentry.databinding.ActivityMainBinding;

import java.util.concurrent.Executors;

public class CreateTimeSheetsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Context context;
  ActivityCreateTimeSheetsBinding mActivityCreateTimeSheetsBinding;
  private String[] date1={"sep-4","sep-11","sep-18","sep-25","Oct-2","Oct-9","Oct-16","Oct-23","Oct-30"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityCreateTimeSheetsBinding=DataBindingUtil.setContentView(this,R.layout.activity_create_time_sheets);

        mActivityCreateTimeSheetsBinding.spinnerDate.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,date1);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mActivityCreateTimeSheetsBinding.spinnerDate.setAdapter(aa);

        mActivityCreateTimeSheetsBinding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task1 =mActivityCreateTimeSheetsBinding.task1.getText().toString();
                String task2= mActivityCreateTimeSheetsBinding.task2.getText().toString();
                String task3=mActivityCreateTimeSheetsBinding.task3.getText().toString();
                String task4=mActivityCreateTimeSheetsBinding.task4.getText().toString();
                String task5=mActivityCreateTimeSheetsBinding.task5.getText().toString();
                int hour1= Integer.parseInt(mActivityCreateTimeSheetsBinding.hour1.getText().toString());
                int hour2=Integer.parseInt(mActivityCreateTimeSheetsBinding.hour2.getText().toString());
                int hour3=Integer.parseInt(mActivityCreateTimeSheetsBinding.hour3.getText().toString());
                int hour4=Integer.parseInt(mActivityCreateTimeSheetsBinding.hour4.getText().toString());
                int hour5=Integer.parseInt(mActivityCreateTimeSheetsBinding.hour5.getText().toString());
                int totalhours=hour1+hour2+hour3+hour4+hour5;
                int date= (int) mActivityCreateTimeSheetsBinding.spinnerDate.getSelectedItem();

                TimeSheets timeSheets=new TimeSheets();
                timeSheets.setTask1(task1);
                timeSheets.setTask2(task2);
                timeSheets.setTask3(task3);
                timeSheets.setTask4(task4);
                timeSheets.setTask5(task5);
                timeSheets.setHour1(hour1);
                timeSheets.setHour2(hour2);
                timeSheets.setHour3(hour3);
                timeSheets.setHour4(hour4);
                timeSheets.setHour5(hour5);
                timeSheets.setTotalhours(totalhours);
                timeSheets.setDate(date);
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        DBController.getInstance(getApplicationContext()).getDao(
                    }
                });

            }
        });





    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}