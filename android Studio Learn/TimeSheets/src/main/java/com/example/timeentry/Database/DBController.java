package com.example.timeentry.Database;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TimeSheets.class},version = 1,exportSchema = false)
public abstract class DBController extends RoomDatabase {
    private static DBController mDbController;
    public static DBController getInstance(Context context)
    {
        if(mDbController==null) {
            mDbController = Room.databaseBuilder(context, DBController.class, "note")
                    .allowMainThreadQueries()
                    .build();

        }
        return mDbController;
    }
    public abstract Dao getDao();
}
