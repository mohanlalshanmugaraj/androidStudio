package com.example.zetranexample.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.zetranexample.adapter.EmployeeAdapter;
import com.example.zetranexample.databinding.ActivityReportsBinding;
import com.example.zetranexample.viewmodel.EmployeeViewModel;

public class ReportsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityReportsBinding mBinding = ActivityReportsBinding.inflate(LayoutInflater.from(this));
        setContentView(mBinding.getRoot());

        EmployeeViewModel viewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
        viewModel.getEmployeeListLiveData().observe(this, employeeList -> {
            mBinding.rvReportsList.setLayoutManager(new LinearLayoutManager(ReportsActivity.this));
            EmployeeAdapter adapter = new EmployeeAdapter(employeeList);
            mBinding.rvReportsList.setAdapter(adapter);
        });

        viewModel.getStatusMsgLiveData().observe(this, s ->
                Toast.makeText(ReportsActivity.this, s, Toast.LENGTH_SHORT).show()
        );

        viewModel.getPunchInList();
    }
}