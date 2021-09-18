package com.example.timesheet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timesheet.database.DBController;
import com.example.timesheet.database.Timesheet;
import com.example.timesheet.databinding.ActivityCreateTimesheetBinding;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CreateTimesheetActivity extends AppCompatActivity {
    private ActivityCreateTimesheetBinding timesheetBinding;

    private DatePickerDialog dpd;
    private static final Integer MAX_YEAR = 5 * 365;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timesheetBinding = ActivityCreateTimesheetBinding.inflate(LayoutInflater.from(this));
        setContentView(timesheetBinding.getRoot());


        timesheetBinding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getWeekEndDetails();
            }
        });

        timesheetBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        timesheetBinding.tvdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getDatePicker();
            }
        });
    }

    private void getDatePicker() {


        dpd = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                timesheetBinding.tvdate.setText(dayOfMonth + "-" + monthOfYear);
            }
        });

        ArrayList<Calendar> weekends = new ArrayList<>();
        Calendar day = Calendar.getInstance();
        for (int i = 0; i < MAX_YEAR; i++) {
            if (day.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                Calendar d = (Calendar) day.clone();
                weekends.add(d);
            }
            day.add(Calendar.DATE, 1);
        }
        Calendar[] weekendDays = weekends.toArray(new Calendar[weekends.size()]);

        dpd.setSelectableDays(weekendDays);

        dpd.show(getSupportFragmentManager(), "Date Picker");
    }

    public void getWeekEndDetails() {

        String task1 = timesheetBinding.edtTask1.getText().toString();
        String task2 = timesheetBinding.edtTask2.getText().toString();
        String task3 = timesheetBinding.edtTask3.getText().toString();
        String task4 = timesheetBinding.edtTask4.getText().toString();
        String task5 = timesheetBinding.edtTask5.getText().toString();
        String task6 = timesheetBinding.edtTask6.getText().toString();

        String hours1 = timesheetBinding.edtHours1.getText().toString();
        String hours2 = timesheetBinding.edtHours2.getText().toString();
        String hours3 = timesheetBinding.edtHours3.getText().toString();
        String hours4 = timesheetBinding.edtHours4.getText().toString();
        String hours5 = timesheetBinding.edtHours5.getText().toString();
        String hours6 = timesheetBinding.edtHours6.getText().toString();

        String totalHours = timesheetBinding.edtTotalHours.getText().toString();

        if (TextUtils.isEmpty(task1)) {
            timesheetBinding.edtTask1.setError("Pleas enter task1");
        } else if (TextUtils.isEmpty(task2)) {
            timesheetBinding.edtTask2.setError("Pleas enter task2");
        } else if (TextUtils.isEmpty(task3)) {
            timesheetBinding.edtTask3.setError("Pleas enter task3");
        } else if (TextUtils.isEmpty(task4)) {
            timesheetBinding.edtTask4.setError("Pleas enter task4");
        } else if (TextUtils.isEmpty(task5)) {
            timesheetBinding.edtTask5.setError("Pleas enter task5");
        } else if (TextUtils.isEmpty(task6)) {
            timesheetBinding.edtTask6.setError("Pleas enter task6");
        } else if (TextUtils.isEmpty(hours1)) {
            timesheetBinding.edtHours1.setError("Pleas enter hours1");
        } else if (TextUtils.isEmpty(hours2)) {
            timesheetBinding.edtHours2.setError("Pleas enter hours2");
        } else if (TextUtils.isEmpty(hours3)) {
            timesheetBinding.edtHours3.setError("Pleas enter hours3");
        } else if (TextUtils.isEmpty(hours4)) {
            timesheetBinding.edtHours4.setError("Pleas enter hours4");
        } else if (TextUtils.isEmpty(hours5)) {
            timesheetBinding.edtHours5.setError("Pleas enter hours5");
        } else if (TextUtils.isEmpty(hours6)) {
            timesheetBinding.edtHours6.setError("Pleas enter hours6");
        } else if (TextUtils.isEmpty(totalHours)) {
            timesheetBinding.edtTotalHours.setError("Please enter the total Number of Working hours");
        } else {
            Timesheet timesheet = new Timesheet();
            timesheet.setDays(timesheetBinding.tvdate.getText().toString());
            timesheet.setTask1(task1);
            timesheet.setTask2(task2);
            timesheet.setTask3(task3);
            timesheet.setTask4(task4);
            timesheet.setTask5(task5);
            timesheet.setTask6(task6);

            timesheet.setHours1(hours1);
            timesheet.setHours2(hours2);
            timesheet.setHours3(hours3);
            timesheet.setHours4(hours4);
            timesheet.setHours5(hours5);
            timesheet.setHours6(hours6);
            timesheet.setTotalHours(totalHours);

            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    DBController.getInstance(CreateTimesheetActivity.this).getDao().insertData(timesheet);
                    clearData();
                }
            });
        }
    }

    public void clearData() {

        timesheetBinding.edtTask1.setText("");
        timesheetBinding.edtTask2.setText("");
        timesheetBinding.edtTask3.setText("");
        timesheetBinding.edtTask4.setText("");
        timesheetBinding.edtTask5.setText("");
        timesheetBinding.edtTask6.setText("");
        timesheetBinding.edtHours1.setText("");
        timesheetBinding.edtHours2.setText("");
        timesheetBinding.edtHours3.setText("");
        timesheetBinding.edtHours4.setText("");
        timesheetBinding.edtHours5.setText("");
        timesheetBinding.edtHours6.setText("");
        timesheetBinding.edtTotalHours.setText("");

    }
}