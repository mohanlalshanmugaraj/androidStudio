package com.example.tiimeentryapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tiimeentryapplication.Database.DBController;
import com.example.tiimeentryapplication.Database.Note;
import com.example.timeentry.MainActivity;
import com.example.timeentry.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CreateTimesheet extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText mondayHr, tusedayHr, wednesdayHr, thursdayHr, fridayHr;
    EditText task1,task2,task3,task4,task5;
    TextView totalHr;
    Spinner date;
    Button submit, cancel;
    int totHr;
    String[] spin = {"2-Feb", "9-Feb", "16-Feb", "23-Feb", "2-Mar", "9-Mar", "16-Mar", "23-Mar", "30-Mar"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_timesheet);
        task1 = (EditText) findViewById(R.id.task1);
        task2 = (EditText) findViewById(R.id.task2);
        task3 = (EditText) findViewById(R.id.task3);
        task4 = (EditText) findViewById(R.id.task4);
        task5 = (EditText) findViewById(R.id.task5);

        GridLayout gl = (GridLayout) findViewById(R.id.grid_layout);

        mondayHr = (EditText) findViewById(R.id.monday_hr);
        tusedayHr = (EditText) findViewById(R.id.tuesday_hr);
        wednesdayHr = (EditText) findViewById(R.id.wednesday_hr);
        thursdayHr = (EditText) findViewById(R.id.thursday_hr);
        fridayHr = (EditText) findViewById(R.id.friday_hr);
        totalHr = (TextView) findViewById(R.id.total_hr);

        submit = (Button) findViewById(R.id.submit_button);
        cancel = (Button) findViewById(R.id.cancel_button);

        date = (Spinner) findViewById(R.id.weekOffDate);

        date.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spin);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        date.setAdapter(aa);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String d1 = mondayHr.getText().toString();
                String d2 = tusedayHr.getText().toString();
                String d3 = wednesdayHr.getText().toString();
                String d4 = thursdayHr.getText().toString();
                String d5 = fridayHr.getText().toString();

                String t1 = task1.getText().toString();
                String t2 = task2.getText().toString();
                String t3 = task3.getText().toString();
                String t4 = task4.getText().toString();
                String t5 = task5.getText().toString();

                if (d1.isEmpty() || d2.isEmpty() || d3.isEmpty() || d4.isEmpty() || d5.isEmpty() || t1.isEmpty() || t2.isEmpty() || t3.isEmpty() || t4.isEmpty() || t5.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Fill the Field", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (Integer.parseInt(d1) <= 12 && Integer.parseInt(d2) <= 12 && Integer.parseInt(d3) <= 12 && Integer.parseInt(d4) <= 12 && Integer.parseInt(d5) <= 12) {
                        totHr = Integer.parseInt(d1) + Integer.parseInt(d2) + Integer.parseInt(d3) + Integer.parseInt(d4) + Integer.parseInt(d5);
                        totalHr.setText(Integer.toString(totHr));
                    }
                    else {
                        gl.setBackgroundResource(R.drawable.error_background);
                        Toast.makeText(getApplicationContext(), "Enter Correct Hours", Toast.LENGTH_SHORT).show();
                    }
                }
                String lastDate = date.getSelectedItem().toString();
                String totalHours = totalHr.getText().toString();
                if (lastDate.isEmpty() || totalHours.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Check Your Entry Details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    gl.setBackgroundResource(R.drawable.button_background);
                    Toast.makeText(CreateTimesheet.this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
                    Note note=new Note();
                    note.setDate(lastDate);
                    note.setDay1(d1);
                    note.setDay2(d2);
                    note.setDay3(d3);
                    note.setDay4(d4);
                    note.setDay5(d5);
//                    note.setTask1(t1);
//                    note.setTask2(t2);
//                    note.setTask3(t3);
//                    note.setTask4(t4);
//                    note.setTask5(t5);
                    note.setTotalHours(totalHours);
                    try {
                        Executors.newSingleThreadExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                DBController.getInstance(getApplicationContext()).getDao().insertData(note);
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(CreateTimesheet.this, "saved", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(CreateTimesheet.this, "already used number!!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateTimesheet.this, MainActivity.class);
                startActivity(intent);
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