package com.example.database_room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.database_room.Database.DBController;
import com.example.database_room.Database.Note;

import java.util.concurrent.Executors;

public class CreateEmployee_Activity extends AppCompatActivity {
    EditText eName,eId,eDept;
    Button save1,getSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_employee);
        eName=findViewById(R.id.e_Name);
        eId=findViewById(R.id.e_Id);
        eDept=findViewById(R.id.e_dept);
        save1=findViewById(R.id.save);
        getSave=findViewById(R.id.getdata);
        /*getSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),EmployeeDetails_Activity.class));
            }
        })*/
        save1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = eName.getText().toString().trim();
                final String id = eId.getText().toString().trim();
                final String dept = eDept.getText().toString().trim();
                Note note = new Note();
                note.setName(name);
                note.setId(id);
                note.setDept(dept);
                Executors.newSingleThreadExecutor().execute(() -> {
                    DBController.getInstance(getApplicationContext()).getDao().insertData(note);
                });

            }
        });
    }
}