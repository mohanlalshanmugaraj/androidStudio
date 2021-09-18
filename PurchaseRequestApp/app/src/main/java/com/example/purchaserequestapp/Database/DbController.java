package com.example.purchaserequestapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class},version = 1,exportSchema = false)
public abstract class DbController extends RoomDatabase {
    private static DbController mDbController;
    public static DbController getInstance(Context context)
    {
        if(mDbController==null) {
            mDbController = Room.databaseBuilder(context, DbController.class, "note")
                    .allowMainThreadQueries()
                    .build();

        }
        return mDbController;
    }
    public abstract Dao getDao();
}
