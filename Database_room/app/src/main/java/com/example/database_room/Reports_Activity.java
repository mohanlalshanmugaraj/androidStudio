package com.example.database_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.database_room.Adapter.NoteAdapter;
import com.example.database_room.Database.DBController;
import com.example.database_room.Database.Note;
import com.example.database_room.Database.PunchInOut;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class Reports_Activity extends AppCompatActivity {
    private NoteAdapter mNoteAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        initRecyclerView();
        getData();
    }

    private void initRecyclerView() {
        RecyclerView recyclerview = findViewById(R.id.recyclerView1);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerview.addItemDecoration(dividerItemDecoration);
        mNoteAdapter=new NoteAdapter(this);
        recyclerview.setAdapter(mNoteAdapter);
    }


    private void getData() {
        DBController db=DBController.getInstance(this.getApplicationContext());
        List<PunchInOut> noteList =db.getDao().getAllPunchInOut();
        mNoteAdapter.setNoteAdapter(noteList);
        }
    }