package com.example.employee_details;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PunchInout extends AppCompatActivity {
    Button punchIn;
    Intent intent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.punch_inout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        punchIn=(Button)findViewById(R.id.punchIN);
        TextView textView=findViewById(R.id.Datetime);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy / HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        textView.setText(currentDateandTime);
        punchIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateTime = textView.getText().toString();
                DbHandler dbHandler = new DbHandler(PunchInout.this);
                dbHandler.dateTime(dateTime);
                Toast.makeText(getApplicationContext(), "Date & Time Inserted Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
