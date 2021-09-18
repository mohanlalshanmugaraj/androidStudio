package com.example.roomdatatest.DB;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version=1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    private static AppDatabase Instance;

    public static AppDatabase getdbInstance(Context context){
        if(Instance==null){
            Instance= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"DB_NAME")
                    .allowMainThreadQueries()
                    .build();

        }
        return Instance;
    }

}
