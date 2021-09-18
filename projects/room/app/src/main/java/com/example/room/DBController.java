package com.example.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class}, version = 1)
public abstract class DBController extends RoomDatabase {

    private static DBController mDBController;

    public static DBController getInstance(Context context){
        if(mDBController == null){
            mDBController= Room.databaseBuilder(context.getApplicationContext(),
                    DBController.class,"Task").build();
        }
        return mDBController;
    }

    public abstract Dao getDao();
}
