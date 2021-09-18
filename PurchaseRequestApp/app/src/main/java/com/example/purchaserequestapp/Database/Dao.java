package com.example.purchaserequestapp.Database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Query("SELECT * FROM note")
    List<Note> getAll();

    @Insert
    void insertData(Note note);

    @Update
    void updateData(Note note);

    @Delete
    void delectData(Note note);

    @Query("DELETE FROM note")
    void deleteAll();
}
