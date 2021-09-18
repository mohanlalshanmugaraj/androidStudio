package com.example.employee_punchinout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class Report extends AppCompatActivity {
    Button backButton;
    Intent intent;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        backButton=(Button)findViewById(R.id.btnBack);
        DbHandler db = new DbHandler(this);
        ArrayList<HashMap<String, String>> userList = db.punch();
        //Toast.makeText(getApplicationContext(), userList.toString(), Toast.LENGTH_SHORT).show();
        ListView lv = (ListView) findViewById(R.id.user_list);
        ListAdapter adapter = new SimpleAdapter(Report.this, userList, R.layout.listrow,new String[]{"Name","Password","RetypePassword"}, new int[]{R.id.dateIn,R.id.dateOut,R.id.spin_ID});
        lv.setAdapter(adapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Report.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

