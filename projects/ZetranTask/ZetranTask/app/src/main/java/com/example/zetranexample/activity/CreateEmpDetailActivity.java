package com.example.zetranexample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.zetranexample.database.Employee;
import com.example.zetranexample.databinding.ActivityCreateEmpDetailsBinding;
import com.example.zetranexample.viewmodel.EmployeeViewModel;


public class CreateEmpDetailActivity extends AppCompatActivity {
    private ActivityCreateEmpDetailsBinding empDetailsBinding;
    private EmployeeViewModel employeeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        empDetailsBinding = ActivityCreateEmpDetailsBinding.inflate(LayoutInflater.from(this));
        setContentView(empDetailsBinding.getRoot());

        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
        employeeViewModel.getStatusMsgLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(CreateEmpDetailActivity.this, s,Toast.LENGTH_SHORT).show();
                clearData();
            }
        });

        empDetailsBinding.btnSave.setOnClickListener(v -> {
            String name = empDetailsBinding.edtName.getText().toString();
            String empID = empDetailsBinding.edtId.getText().toString();
            String dept = empDetailsBinding.edtDept.getText().toString();
            if (TextUtils.isEmpty(name)) {
                empDetailsBinding.edtName.setError("Please enter name");
            } else if (TextUtils.isEmpty(empID)) {
                empDetailsBinding.edtId.setError("Please enter ID");
            } else if (TextUtils.isEmpty(dept)) {
                empDetailsBinding.edtDept.setError("Please enter Department");
            } else {
                insertEmployee(name, empID, dept);
            }
        });
    }

    public void insertEmployee(String name, String empID, String dept) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setId(Integer.valueOf(empID));
        employee.setDept(dept);

        employeeViewModel.insertEmployee(employee);

    }

    public void clearData() {
        empDetailsBinding.edtName.setText("");
        empDetailsBinding.edtId.setText("");
        empDetailsBinding.edtDept.setText("");
    }
}