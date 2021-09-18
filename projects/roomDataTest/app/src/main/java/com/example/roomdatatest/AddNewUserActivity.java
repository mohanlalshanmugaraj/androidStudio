package com.example.roomdatatest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomdatatest.DB.AppDatabase;
import com.example.roomdatatest.DB.User;

public class AddNewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);
        final EditText name=findViewById(R.id.e_Name);
        final EditText id=findViewById(R.id.e_Id);
        final EditText dept=findViewById(R.id.e_dept);
        Button save=findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNewUser(name.getText().toString(),id.getText().toString(),dept.getText().toString());

            }
        });
    }
    private void saveNewUser(String name, String id, String dept){
        AppDatabase db=AppDatabase.getdbInstance(this);
        User user=new User();
        user.name=name;
        user.id=id;
        user.dept=dept;
        db.userDao().insertUsers(user);
        finish();
    }
}