package com.example.arraysedittext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Savepoint;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView tv;
    EditText e1,e2,e3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btn);
        tv=findViewById(R.id.testView);
        e1=findViewById(R.id.name);
        e2=findViewById(R.id.age);
        e3=findViewById(R.id.college);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                String[] myStrings = new String[] {"e1.getText().toString()", "e2.getText().toString()",
                        "e3.getText().toString()"};
                intent.putExtra("strings", myStrings);
                startActivity(intent);


                SharedPreferences preferences = getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Name","test");
                editor.commit();


            }
        });


    }
}