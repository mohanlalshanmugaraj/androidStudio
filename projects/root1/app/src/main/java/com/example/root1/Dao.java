package com.example.root1;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Query("select * from Task")
    List<Task> getAll();

    @Insert
    void insertData(Task task);

    @Update
    void updateData(Task task);

    @Delete
    void deleteData(Task task);


}
