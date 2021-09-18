package com.example.purchaserequestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Insert;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

import com.example.purchaserequestapp.Adapter.NoteAdapter;
import com.example.purchaserequestapp.Database.DbController;
import com.example.purchaserequestapp.Database.Note;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ViewRequest_Activity extends AppCompatActivity {
    private NoteAdapter mNoteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request);
        initRecyclerView();
        getData();

    }
    private void getData() {
        DbController db=DbController.getInstance(this.getApplicationContext());
        List<Note> noteList =db.getDao().getAll();
        mNoteAdapter.setNoteAdapter(noteList);
    }

    private void initRecyclerView() {
        RecyclerView recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerview.addItemDecoration(dividerItemDecoration);
        mNoteAdapter=new NoteAdapter(this);
        recyclerview.setAdapter(mNoteAdapter);
    }
//   @Override
//    protected void onResume() {
//        super.onResume();
//        mNoteAdapter.notifyDataSetChanged();
//        Executors.newSingleThreadExecutor().execute(new Runnable() {
//            @Override
//            public void run() {
//               new Handler(Looper.getMainLooper()).post(new Runnable() {
//                   @Override
//                   public void run() {
//                       initRecyclerView();
//                       getData();
//                   }
//               });
//
//            }
//        });
//
//    }

}