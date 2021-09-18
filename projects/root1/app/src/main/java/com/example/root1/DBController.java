package com.example.root1;


import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class},version = 1)
public abstract class DBController extends RoomDatabase {
    private static DBController mDBcontroller;
    public static DBController getInstance(Context context){
        if (mDBcontroller==null)
        {
            mDBcontroller= Room.databaseBuilder(context.getApplicationContext(),
                    DBController.class,"Task").build();
        }
        return mDBcontroller;
    }
    public abstract Dao getDao();

}
