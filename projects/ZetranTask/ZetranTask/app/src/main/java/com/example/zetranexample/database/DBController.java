package com.example.zetranexample.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Employee.class, PunchInOut.class}, version = 1, exportSchema = false)
public abstract class DBController extends RoomDatabase {

    private static DBController mDBController;

    public static DBController getInstance(Context context) {
        if (mDBController == null) {
            mDBController = Room.databaseBuilder(context.getApplicationContext(), DBController.class,
                    "EmployeeDB")
                    .build();
        }
        return mDBController;
    }

    public abstract EmpDAO getEmpDAO();
}
