package com.example.database_room.Dao;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.database_room.Database.Note;
import com.example.database_room.Database.PunchInOut;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert
    void insertData(Note note);

    @Query("select * from note")
    List<Note> getAll();

    @Query("select * from Note where name")
    List<Note> name();

    @Query("select * from Note where name=:name")
    Note getEmpName(String name);

    @Query("select name from Note")
    List<String> getEmpNames();

    @Insert
    void insertPunchData(PunchInOut note);

    @Query("select * from PunchInOut")
    List<PunchInOut> getAllPunchInOut();


    @Update
    int updatepunchData(PunchInOut note);


    @Query("update PunchInOut set punchIn=:punchIn where date=:date and name=:name")
    void updatePunchInTime(String name, String date, String punchIn);

    @Query("update PunchInOut set punchOut=:punchOut where date=:date and name=:name")
    void updatePunchOutTime(String name, String date, String punchOut);

    @Query("update PunchInOut set date=:date where name=:name")
    void updateDateForTheDay(String name, String date);

    @Query("select * from PunchInOut where date=:todayDate and name=:name")
    PunchInOut getEmpBasedDate(String name, String todayDate);

    @Query("select count() from PunchInOut where date=:todayDate and name=:name")
    int checkDateAlreadyInserted(String todayDate, String name);





}
