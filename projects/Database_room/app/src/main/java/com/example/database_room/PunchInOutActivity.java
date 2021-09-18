package com.example.database_room;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.database_room.Database.DBController;
import com.example.database_room.Database.Note;
import com.example.database_room.Database.PunchInOut;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

public class PunchInOutActivity extends AppCompatActivity {
    DBController mDBController;
    TextView timeAndDate;
    Button punchInout;
    String[] names={"Selvapandian","Selvam","PsP","Mohan","XYZ","ABC"};
    private String selectedDate="";

    private ArrayList<String> userType;
    private Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punch_in_out);
        spin =findViewById(R.id.spinner);
        /*ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,names);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);*/
        punchInout=findViewById(R.id.punchInOUt);
        timeAndDate=findViewById(R.id.select_date);
        selectedDate=getTodaydate(new Date());
        timeAndDate.setText(selectedDate +"/"+getCurrentTime());

        methodSpinner();


        timeAndDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PunchInOutActivity.this);
                datePickerDialog.show();

            }
        });
        punchInout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  String name=spin.getSelectedItem().toString();
                String date=selectedDate;
                String time=getCurrentTime();
                PunchInOut punchIn=new PunchInOut();
                punchIn.setName(name);
                punchIn.setDate(date);
                punchIn.setPunchIn(time);

                Executors.newSingleThreadExecutor().execute(() -> {
                    DBController.getInstance(getApplicationContext()).getDao().insertPunchData(punchIn);
                });*/
                ButtonChange();
            }
        });
    }

    private void ButtonChange() {
        String pInOut=punchInout.getText().toString();
        if(pInOut.equals("punchIn")){
            PunchInTimeAndDate();
            punchInout.setText("punchOut");
        }
        if(pInOut.equals("punchOut")){
            PunchOutTimeAndDate();
            punchInout.setText("PunchIn/PunchOut Completed");
        }
    }

    private void PunchInTimeAndDate() {
        String name=spin.getSelectedItem().toString();
        String date=selectedDate;
        String time=getCurrentTime();
        PunchInOut punchIn=new PunchInOut();
        punchIn.setName(name);
        punchIn.setDate(date);
        punchIn.setPunchIn("Punch-In ="+time);
        DBController.getInstance(getApplicationContext()).getDao().insertPunchData(punchIn);
        Executors.newSingleThreadExecutor().execute(() -> {
            DBController.getInstance(getApplicationContext()).getDao().updatePunchInTime(name,date,time);

        });

    }
    private void PunchOutTimeAndDate() {
        String name=spin.getSelectedItem().toString();
        String date=selectedDate;
        String time=getCurrentTime();
        PunchInOut punchOut=new PunchInOut();
        punchOut.setName(name);
        punchOut.setDate(date);
        punchOut.setPunchOut("punch-Out ="+time);
        DBController.getInstance(getApplicationContext()).getDao().updatepunchData(punchOut);

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                DBController.getInstance(getApplicationContext()).getDao().updatePunchOutTime(name,date,time);

            }
        });
        //  DBController.getInstance(getApplicationContext()).getDao().insertPunchData(punchOut);
    }

    private void methodSpinner() {
        List<String> users=DBController.getInstance(PunchInOutActivity.this).getDao().getEmpNames();
        ArrayAdapter<String> spinnerAdapter =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,new ArrayList<>());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(spinnerAdapter);
        spinnerAdapter.addAll(users);
        spinnerAdapter.notifyDataSetChanged();
    }

    private String getTodaydate(Date time) {
        return DateFormat.getDateInstance(DateFormat.MEDIUM).format(time);
    }
    private String getCurrentTime() {
        return DateFormat.getTimeInstance(DateFormat.DATE_FIELD).format(new Date());
    }
}

