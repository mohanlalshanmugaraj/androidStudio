package com.example.purchaserequestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.purchaserequestapp.Database.DbController;
import com.example.purchaserequestapp.Database.Note;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.Executors;

public class CreateRequest_Activity extends AppCompatActivity {
    private String SelectedDate="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);
        EditText request1=findViewById(R.id.ev_Request);
        EditText date1=findViewById(R.id.ev_Date);
        EditText laptop1=findViewById(R.id.ev_laptop);
        EditText mouse1=findViewById(R.id.ev_mouse);
        EditText keyboard1=findViewById(R.id.ev_keyboard);
        EditText mobile1=findViewById(R.id.ev_mobile);
        Button save1=findViewById(R.id.save);

        SelectedDate=getTodaydate(new Date());
        date1.setText(SelectedDate);

        save1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String request=request1.getText().toString();
                String date=SelectedDate;
                //String  date=date1.getText().toString();
                String laptop=laptop1.getText().toString();
                String mouse=mouse1.getText().toString();
                String keyboard=keyboard1.getText().toString();
                String mobile=mobile1.getText().toString();

                if(TextUtils.isEmpty(request)){
                    request1.setError("please  Enter a requestNO!");
                }else if(TextUtils.isEmpty(laptop)){
                    laptop1.setError("please Enter a laptop count!");
                }else if (TextUtils.isEmpty(mouse)){
                    mouse1.setError("please Enter a mouse count!");
                }else if(TextUtils.isEmpty(keyboard)){
                    keyboard1.setError("please Enter a kayboard count!");
                }else if (TextUtils.isEmpty(mobile)){
                    mobile1.setError("please Enter a mobile count!");
                }else {

                    Note note = new Note();
                    note.setRequestno(request);
                    note.setDate(date);
                    note.setLaptop(laptop);
                    note.setMouse(mouse);
                    note.setKeyboard(keyboard);
                    note.setMobile(mobile);
                    try {
                        Executors.newSingleThreadExecutor().execute(() -> {
                            DbController.getInstance(getApplicationContext()).getDao().insertData(note);
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(CreateRequest_Activity.this, "saved", Toast.LENGTH_SHORT).show();
                                }
                            });
                        });
                    }catch (Exception e){
                        Toast.makeText(CreateRequest_Activity.this, "already used number!!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    private String getTodaydate(Date time) {
        return DateFormat.getDateInstance(DateFormat.MEDIUM).format(time);
    }
    private String getCurrentTime() {
        return DateFormat.getTimeInstance(DateFormat.DATE_FIELD).format(new Date());
    }
}