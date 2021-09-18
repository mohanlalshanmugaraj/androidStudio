package com.example.database_room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button creatEmpoyee,punchInOut,reports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        creatEmpoyee=findViewById(R.id.createEmployee1);
        reports=findViewById(R.id.reports1);
        punchInOut=findViewById(R.id.punch1);


        creatEmpoyee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,CreateEmployee_Activity.class);
                startActivity(intent);
            }
        });
        punchInOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,PunchInOutActivity.class);
                startActivity(intent);

            }
        });
        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,Reports_Activity.class);
                startActivity(intent);

            }
        });

    }
}