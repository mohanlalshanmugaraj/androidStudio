package com.example.employee_punchinout;

import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class CreateEmployee extends AppCompatActivity {
    EditText name,id,dept;
    Button createEmp;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_employee);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        name=(EditText)findViewById(R.id.empName);
        id=(EditText)findViewById(R.id.empId);
        dept=(EditText)findViewById(R.id.empDept);
        createEmp=(Button)findViewById(R.id.saveEmp);

        createEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e1=name.getText().toString();
                String e2=id.getText().toString();
                String e3=dept.getText().toString();
                if (e1.isEmpty() || e2.isEmpty() || e3.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Enter All Fields...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    DbHelper dbb = new DbHelper(CreateEmployee.this);
                    dbb.addEmp(e1,e2,e3);
                    Toast.makeText(getApplicationContext(), "Inserted Successfully...", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    id.setText("");
                    dept.setText("");
                }
            }
        });
    }
}
