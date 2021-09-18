package com.example.database_room.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.database_room.Dao.Dao;

@Database(entities = {Note.class,PunchInOut.class},version = 1, exportSchema = false)
public abstract class DBController extends RoomDatabase {

    public abstract Dao getDao ();

    private static DBController mDBController;
    public static DBController getInstance(Context context) {
        if(mDBController==null) {
                mDBController = Room.databaseBuilder(context, DBController.class, "PunchInOut")
                        .allowMainThreadQueries()
                        .build();

        }
            return mDBController;
        }
}