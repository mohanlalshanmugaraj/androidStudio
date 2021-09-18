package com.example.zetranexample.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmpDAO {

    @Insert
    void insertEmpData(Employee user);

    @Query("select * from PunchInOut where punchIn is NOT NULL")
    List<PunchInOut> getAllPunchInOut();

    @Query("select * from Employee where id=:id")
    Employee getEmp(Integer id);

    @Query("select id from Employee")
    List<Integer> getEmpIds();

    @Query("select name from Employee where id=:id")
    String getEmpName(Integer id);

    @Insert
    void insertPunchData(PunchInOut user);

    @Query("update PunchInOut set punchIn=:punchIn where date=:date and empId=:id")
    void updatePunchInTime(Integer id, String date, String punchIn);

    @Query("update PunchInOut set punchOut=:punchOut where date=:date and empId=:id")
    void updatePunchOutTime(Integer id, String date, String punchOut);

    @Query("update PunchInOut set date=:date where empId=:id")
    void updateDateForTheDay(Integer id, String date);

    @Query("select * from PunchInOut where date=:todayDate and empId=:id")
    PunchInOut getEmpBasedDate(Integer id, String todayDate);

    @Query("select count() from PunchInOut where date=:todayDate and empId=:id")
    int checkDateAlreadyInserted(String todayDate, Integer id);
}

