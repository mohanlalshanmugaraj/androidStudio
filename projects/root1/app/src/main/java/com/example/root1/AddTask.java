package com.example.root1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.Executors;

public class AddTask extends AppCompatActivity {
    private EditText editTextTask, editTextDesc, editTextFinishBy;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        editTextTask = findViewById(R.id.editTextTask);
        editTextDesc = findViewById(R.id.editTextDesc);
        editTextFinishBy = findViewById(R.id.editTextFinishBy);
        save =findViewById(R.id.button_save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               saveTask();
            }
        });

    }

    private void saveTask() {
        final String sTask=editTextTask.getText().toString().trim();
        final String sDesc=editTextDesc.getText().toString().trim();
        final String sFinishBy =editTextFinishBy.getText().toString().trim();

        if(TextUtils.isEmpty(sTask) || TextUtils.isEmpty(sDesc) || TextUtils.isEmpty(sFinishBy)){
            Toast.makeText(this, "Place Enter A Details ", Toast.LENGTH_SHORT).show();
        }
        else{
             Task task =new Task();
             task.setTask(sTask);
             task.setTask(sDesc);
             task.setTask(sFinishBy);

            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    DBController.getInstance(AddTask.this).getDao().
                }
            });


        }
    }
}