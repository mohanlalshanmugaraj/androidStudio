package com.example.employee_punchinout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DbHelper {
    DbHelper.myDbHelper myhelper;
    public DbHelper(Context context)
    {
        myhelper = new DbHelper.myDbHelper(context);
    }
    public void addEmp(String e1,String e2,String e3)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put(myDbHelper.NAME, e1);
        Values.put(myDbHelper.ID, e2);
        Values.put(myDbHelper.DEPT, e3);
        long newRowId = db.insert(myDbHelper.EMPLOYEE,null, Values);
        db.close();
    }
    public List<String> getSpinData(){
        List<String>labels= new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + myDbHelper.EMPLOYEE;
        SQLiteDatabase db=myhelper.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do{
                labels.add(cursor.getString(1));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }
    static class myDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "myDatabase1";
        private static final String EMPLOYEE = "myTable1";   // Table Name
        private static final int DATABASE_Version = 1;   // Database Version
        private static final String UID = "_id";
        private static final String NAME = "empName";    //Column II
        private static final String ID = "empId";    // Column III
        private static final String DEPT = "dePartment";
        private static final String CREATE_TABLE = "CREATE TABLE " + EMPLOYEE +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " VARCHAR(255) , "
                + ID + " VARCHAR(255), "
                + DEPT + ")";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + EMPLOYEE;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }
    }
}
