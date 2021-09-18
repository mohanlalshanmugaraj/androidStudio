package com.example.timeentry;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.timeentry.Database.TimeSheets;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Query("SELECT * FROM timesheets")
    List<TimeSheets> getAlldatas();

    @Insert
    void insertAllData(TimeSheets timeSheets);

    @Update
    void updateAllData(TimeSheets timeSheets);

    @Delete
    void delectAllData(TimeSheets timeSheets);
}
