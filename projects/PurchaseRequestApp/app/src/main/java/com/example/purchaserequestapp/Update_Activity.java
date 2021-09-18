package com.example.purchaserequestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.purchaserequestapp.Adapter.NoteAdapter;
import com.example.purchaserequestapp.Database.DbController;
import com.example.purchaserequestapp.Database.Note;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.Executors;

public class Update_Activity extends AppCompatActivity {
    private Note mNote;
    EditText request1, date1, laptop1, mouse1, keyboard1, mobile1;
    private String SelectedDate = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        request1 = findViewById(R.id.uev_Request);
        date1 = findViewById(R.id.uev_Date);
        laptop1 = findViewById(R.id.uev_laptop);
        mouse1 = findViewById(R.id.uev_mouse);
        keyboard1 = findViewById(R.id.uev_keyboard);
        mobile1 = findViewById(R.id.uev_mobile);
        Button save1 = findViewById(R.id.usave);

        SelectedDate=getTodaydate(new Date());
        date1.setText(SelectedDate);


        mNote = (Note) getIntent().getSerializableExtra("note");
        loadNote(mNote);
        save1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
                UpdateNote(mNote);

            }
        });
    }
    private void UpdateNote(Note note1) {
        String request = request1.getText().toString();
        String date = SelectedDate;
        //String  date=date1.getText().toString();
        String laptop = laptop1.getText().toString();
        String mouse = mouse1.getText().toString();
        String keyboard = keyboard1.getText().toString();
        String mobile = mobile1.getText().toString();
        if(TextUtils.isEmpty(request)){
            request1.setError("please  Enter a requestNO!");
        }else if(TextUtils.isEmpty(laptop)){
            laptop1.setError("please Enter a laptop count!");
        }else if (TextUtils.isEmpty(mouse)){
            mouse1.setError("please Enter a mouse count!");
        }else if(TextUtils.isEmpty(keyboard)){
            keyboard1.setError("please Enter a kayboard count!");
        }else if (TextUtils.isEmpty(mobile)) {
            mobile1.setError("please Enter a mobile count!");
        }else {

            note1.setRequestno(request);
            note1.setDate(date);
            note1.setLaptop(laptop);
            note1.setMouse(mouse);
            note1.setKeyboard(keyboard);
            note1.setMobile(mobile);
            Executors.newSingleThreadExecutor().execute(() -> {
                DbController.getInstance(getApplicationContext()).getDao().updateData(note1);

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                        finish();

                    }
                });
            });
        }

    }
    private void loadNote(Note note) {
        request1.setText(note.getRequestno());
        date1.setText(note.getDate());
        laptop1.setText(note.getLaptop());
        mouse1.setText(note.getMouse());
        keyboard1.setText(note.getKeyboard());
       mobile1.setText(note.getMobile());

    }
    private String getTodaydate(Date time) {
        return DateFormat.getDateInstance(DateFormat.MEDIUM).format(time);
    }
}