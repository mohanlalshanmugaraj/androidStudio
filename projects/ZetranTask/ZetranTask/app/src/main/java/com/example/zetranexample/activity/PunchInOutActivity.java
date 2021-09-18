package com.example.zetranexample.activity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.zetranexample.database.PunchInOut;
import com.example.zetranexample.R;
import com.example.zetranexample.databinding.ActivityPunchInOutBinding;
import com.example.zetranexample.viewmodel.EmployeeViewModel;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PunchInOutActivity extends AppCompatActivity {
    private ActivityPunchInOutBinding punchInOutBinding;
    private Integer empId;
    private EmployeeViewModel viewModel;
    private String selectedDate = "";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        punchInOutBinding = ActivityPunchInOutBinding.inflate(LayoutInflater.from(this));
        setContentView(punchInOutBinding.getRoot());

        viewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
        viewModel.getEmployeeIdsLiveData().observe(this, integers -> setUpSpinnerAdapter(integers));

        viewModel.getEmpIds();

        selectedDate = getTodayDate(new Date());

        punchInOutBinding.tvDate.setText(selectedDate);

        punchInOutBinding.tvDate.setOnClickListener(v -> showDatePickerDialog());

        viewModel.insertPunchData(getPunchData());
    }

    private void manageInOutBtn() {
        Integer id = (Integer) punchInOutBinding.sprId.getSelectedItem();
        viewModel.getEmployeePIOLiveData().observe(this, s -> {
            if (s != null) {
                if (!TextUtils.isEmpty(s.getPunchIn()) && !TextUtils.isEmpty(s.getPunchOut())) {
                    punchInOutBinding.btnPunch.setText(getString(R.string.punch_in_out_done));
                } else if (!TextUtils.isEmpty(s.getPunchIn())) {
                    punchInOutBinding.btnPunch.setText(getString(R.string.punch_out));
                } else {
                    punchInOutBinding.btnPunch.setText(getString(R.string.punch_in));
                }
            } else {
                punchInOutBinding.btnPunch.setText(getString(R.string.punch_in));
            }
        });

        viewModel.getEmpDataOnDailyBasis(id, selectedDate);

        punchInOutBinding.btnPunch.setOnClickListener(v -> {
            Integer id1 = (Integer) punchInOutBinding.sprId.getSelectedItem();
            String time = getCurrentTime();
            if (isPunchIn()) {
                viewModel.updatePunchInTime(id1, selectedDate, time);
            } else if (isPunchOut()) {
                viewModel.updatePunchOutTime(id1, selectedDate, time);
            }
        });

        viewModel.getEmployeePunchInLiveData().observe(this, s -> {
            Integer id12 = (Integer) punchInOutBinding.sprId.getSelectedItem();
            viewModel.getEmpDataOnDailyBasis(id12, selectedDate);
        });
    }

    private String getCurrentTime() {
        return DateFormat.getTimeInstance(DateFormat.DATE_FIELD).format(new Date());
    }

    private boolean isPunchIn() {
        return punchInOutBinding.btnPunch.getText().toString()
                .equalsIgnoreCase(getString(R.string.punch_in));
    }

    private boolean isPunchOut() {
        return punchInOutBinding.btnPunch.getText().toString()
                .equalsIgnoreCase(getString(R.string.punch_out));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(PunchInOutActivity.this);
        datePickerDialog.show();
        datePickerDialog.setOnDateSetListener((view, year, month, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DATE, dayOfMonth);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.YEAR, year);
            selectedDate = getTodayDate(calendar.getTime());
            punchInOutBinding.tvDate.setText(selectedDate);
            viewModel.insertPunchData(getPunchData());
            viewModel.getEmpDataOnDailyBasis(empId, selectedDate);
        });
    }

    private String getTodayDate(Date time) {
        return DateFormat.getDateInstance(DateFormat.MEDIUM).format(time);
    }

    private void setUpSpinnerAdapter(List<Integer> integers) {
        ArrayAdapter<Integer> spinnerList = new ArrayAdapter<>(PunchInOutActivity.this,
                android.R.layout.simple_list_item_1, integers);
        punchInOutBinding.sprId.setAdapter(spinnerList);
        punchInOutBinding.sprId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                empId = (Integer) punchInOutBinding.sprId.getItemAtPosition(position);
                Log.d("ID", String.valueOf(empId));
                manageInOutBtn();
                viewModel.insertPunchData(getPunchData());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private PunchInOut getPunchData() {
        PunchInOut punchInOut = new PunchInOut();
        punchInOut.setDate(selectedDate);
        punchInOut.setEmpId(empId);
        return punchInOut;
    }
}