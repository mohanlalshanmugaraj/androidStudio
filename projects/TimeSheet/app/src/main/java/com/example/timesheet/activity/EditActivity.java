package com.example.timesheet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.text.TextUtils;
import android.text.method.CharacterPickerDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.example.timesheet.database.DBController;
import com.example.timesheet.database.Timesheet;
import com.example.timesheet.databinding.ActivityCreateTimesheetBinding;
import com.example.timesheet.databinding.ActivityViewTimesheetsBinding;

import java.sql.Time;
import java.util.concurrent.Executors;

public class EditActivity extends AppCompatActivity {
    private ActivityCreateTimesheetBinding editBinding;
    private Timesheet time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editBinding = ActivityCreateTimesheetBinding.inflate(LayoutInflater.from(this));
        setContentView(editBinding.getRoot());

        time = getIntent().getParcelableExtra("timesheet");
        editBinding.setTimesheet(time);


        editBinding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTimesheet();
            }
        });
    }

    private void editTimesheet() {
        String task1 = editBinding.edtTask1.getText().toString();
        String task2 = editBinding.edtTask2.getText().toString();
        String task3 = editBinding.edtTask3.getText().toString();
        String task4 = editBinding.edtTask4.getText().toString();
        String task5 = editBinding.edtTask5.getText().toString();
        String task6 = editBinding.edtTask6.getText().toString();

        String hours1 = editBinding.edtHours1.getText().toString();
        String hours2 = editBinding.edtHours2.getText().toString();
        String hours3 = editBinding.edtHours3.getText().toString();
        String hours4 = editBinding.edtHours4.getText().toString();
        String hours5 = editBinding.edtHours5.getText().toString();
        String hours6 = editBinding.edtHours6.getText().toString();

        String totalHours = editBinding.edtTotalHours.getText().toString();

        if (TextUtils.isEmpty(task1)) {
            editBinding.edtTask1.setError("Pleas enter task1");
        } else if (TextUtils.isEmpty(task2)) {
            editBinding.edtTask2.setError("Pleas enter task2");
        } else if (TextUtils.isEmpty(task3)) {
            editBinding.edtTask3.setError("Pleas enter task3");
        } else if (TextUtils.isEmpty(task4)) {
            editBinding.edtTask4.setError("Pleas enter task4");
        } else if (TextUtils.isEmpty(task5)) {
            editBinding.edtTask5.setError("Pleas enter task5");
        } else if (TextUtils.isEmpty(task6)) {
            editBinding.edtTask6.setError("Pleas enter task6");
        } else if (TextUtils.isEmpty(hours1)) {
            editBinding.edtHours1.setError("Pleas enter hours1");
        } else if (TextUtils.isEmpty(hours2)) {
            editBinding.edtHours2.setError("Pleas enter hours2");
        } else if (TextUtils.isEmpty(hours3)) {
            editBinding.edtHours3.setError("Pleas enter hours3");
        } else if (TextUtils.isEmpty(hours4)) {
            editBinding.edtHours4.setError("Pleas enter hours4");
        } else if (TextUtils.isEmpty(hours5)) {
            editBinding.edtHours5.setError("Pleas enter hours5");
        } else if (TextUtils.isEmpty(hours6)) {
            editBinding.edtHours6.setError("Pleas enter hours6");
        } else if (TextUtils.isEmpty(totalHours)) {
            editBinding.edtTotalHours.setError("Please enter the total Number of Working hours");
        } else {

            time.setTask1(task1);
            time.setTask2(task2);
            time.setTask3(task3);
            time.setTask4(task4);
            time.setTask5(task5);
            time.setTask6(task6);

            time.setHours1(hours1);
            time.setHours2(hours2);
            time.setHours3(hours3);
            time.setHours4(hours4);
            time.setHours5(hours5);
            time.setHours6(hours6);
            time.setTotalHours(totalHours);

            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    DBController.getInstance(EditActivity.this).getDao().updateData(time);
                    finish();

                }
            });

        }
    }
}