package com.example.room;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.Executors;

public class UpdateTask extends AppCompatActivity {
    private EditText editTextTask, editTextDesc, editTextFinishBy;
    private CheckBox checkBoxFinished;
    private Task task;
    private List<Task> mTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        editTextTask = findViewById(R.id.editTextTask);
        editTextDesc = findViewById(R.id.editTextDesc);
        editTextFinishBy = findViewById(R.id.editTextFinishBy);

        checkBoxFinished = findViewById(R.id.checkBoxFinished);


        task = (Task) getIntent().getSerializableExtra("task");

        loadTask(task);

        findViewById(R.id.button_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
                updateTask(task);
            }
        });

        findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateTask.this);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteTask(task);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog ad = builder.create();
                ad.show();
            }
        });
    }

    private void updateTask(Task task) {
        final String sTask = editTextTask.getText().toString().trim();
        final String sDesc = editTextDesc.getText().toString().trim();
        final String sFinishBy = editTextFinishBy.getText().toString().trim();

        if (TextUtils.isEmpty(sTask)) {
            editTextTask.setError("Task required");
        } else if (TextUtils.isEmpty(sDesc)) {
            editTextDesc.setError("Desc required");
        } else if (TextUtils.isEmpty(sFinishBy)) {
            editTextFinishBy.setError("Finish by required");
        } else {

            task.setTask(sTask);
            task.setTask(sDesc);
            task.setFinishBy(sFinishBy);
            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    DBController.getInstance(UpdateTask.this).getDao().updateData(task);

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(UpdateTask.this, MainActivity.class));
                        }
                    });
                }
            });
        }
    }
    private void loadTask(Task task) {
        editTextTask.setText(task.getTask());
        editTextDesc.setText(task.getDesc());
        editTextFinishBy.setText(task.getFinishBy());
        checkBoxFinished.setChecked(task.isFinished());
    }

    private void deleteTask(Task task) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                DBController.getInstance(UpdateTask.this).getDao().deleteData(task);

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(new Intent(UpdateTask.this, MainActivity.class));
                    }
                });
            }
        });

    }
}