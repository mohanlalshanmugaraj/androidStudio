package com.example.employee_punchinout;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PunchInPunchOut extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //String[] names={"Selvapandian","Selvam","PsP","Mohan","XYZ","ABC"};
    Spinner spinEmpId;
    TextView punchIn;
    TextView punchOut;
    Button punchButton;
    private String selectedDate = "";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.punch_inout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        spinEmpId = (Spinner) findViewById(R.id.empSpinId);
        punchIn = (TextView) findViewById(R.id.punch_In);
        punchOut = (TextView) findViewById(R.id.punch_Out);
        punchButton = (Button) findViewById(R.id.punchButton);

        SpinnerData();
        spinEmpId.setOnItemSelectedListener(this);

//        spinEmpId.setOnItemSelectedListener(this);
//        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,names);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinEmpId.setAdapter(aa);

        punchOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(PunchInPunchOut.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        punchOut.setText(getTodayDate(new Date())+" / "+selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });


        SpinnerData();
        spinEmpId.setOnItemSelectedListener(this);
        selectedDate = getTodayDate(new Date());
        punchIn.setText(selectedDate+ " / " +getCurrentTime());

        punchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p1 = punchIn.getText().toString();
                String p2 = punchOut.getText().toString();
                String p3 = spinEmpId.getSelectedItem().toString();
                if(p1.isEmpty() || p2.isEmpty() || p3.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Enter All Fields...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    punchOut.setVisibility(View.GONE);
                    DbHandler dbHandler = new DbHandler(PunchInPunchOut.this);
                    dbHandler.addEmployee(p1,p2,p3);
                    Toast.makeText(getApplicationContext(), "Inserted Successfully...", Toast.LENGTH_SHORT).show();
                    punchButton.setText("Punched Out");
                    punchButton.setEnabled(false);
                }
            }
        });
    }
    private void SpinnerData() {
        DbHelper db= new DbHelper(getApplicationContext());
        List<String> labels= db.getSpinData();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labels);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinEmpId.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private String getTodayDate(Date time)
    {
        return DateFormat.getDateInstance(DateFormat.MEDIUM).format(time);
    }
    private String getCurrentTime()
    {
        return DateFormat.getTimeInstance(DateFormat.DATE_FIELD).format(new Date());
    }
}
