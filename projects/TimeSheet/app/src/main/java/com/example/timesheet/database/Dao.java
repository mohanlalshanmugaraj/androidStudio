package com.example.timesheet.database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Query("select* from Timesheet")
    List<Timesheet> getAll();

    @Insert
    void insertData(Timesheet time);

    @Update
    void updateData(Timesheet time);

    @Delete
    void deleteData(Timesheet time);
}
