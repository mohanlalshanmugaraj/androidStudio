package com.example.roomdatatest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.roomdatatest.DB.AppDatabase;
import com.example.roomdatatest.DB.User;
import com.example.roomdatatest.DB.UserListAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserListAdapter mUserListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addbtn=findViewById(R.id.add);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this,AddNewUserActivity.class),100);

            }
        });
        initRecyclerView();
        loadUserList();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        mUserListAdapter=new UserListAdapter(this);
        recyclerView.setAdapter(mUserListAdapter);
    }

    private void loadUserList(){
        AppDatabase db=AppDatabase.getdbInstance(this.getApplicationContext());
        List<User> userList=db.userDao().getAllUsers();
        mUserListAdapter.setUserList(userList);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        if(requestCode== 100){
            loadUserList();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}