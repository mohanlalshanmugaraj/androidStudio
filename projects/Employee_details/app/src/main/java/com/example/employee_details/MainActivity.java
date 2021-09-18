package com.example.employee_details;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button createEmp,reportBtn,punchIn;
    Intent intent;
    @Override
    protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        createEmp = (Button) findViewById(R.id.createEmployee);
        reportBtn=(Button)findViewById(R.id.report);
        punchIn=(Button)findViewById(R.id.punchInout);
            reportBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(MainActivity.this, DetailsActivity.class);
                    startActivity(intent);
                }
            });
            createEmp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(MainActivity.this, AddEmployee.class);
                    startActivity(intent);

                }
            });
        punchIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, PunchInout.class);
                startActivity(intent);

            }
        });
        }
    }