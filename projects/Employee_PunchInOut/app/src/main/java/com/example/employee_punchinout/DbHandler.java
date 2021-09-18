package com.example.employee_punchinout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DbHandler  {
    myDbHelper myhelper;
    public DbHandler(Context context)
    {
        myhelper = new myDbHelper(context);
    }
    public void addEmployee(String p1,String p2,String p3)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put(myDbHelper.EMP_NAME, p1);
        Values.put(myDbHelper.EMP_ID, p2);
        Values.put(myDbHelper.EMP_DEPT, p3);
        long newRowId = db.insert(myDbHelper.TABLE_EMP,null, Values);
        db.close();
    }
//    public void addName(String e1)
//    {
//        SQLiteDatabase db = myhelper.getWritableDatabase();
//        ContentValues Value = new ContentValues();
//        Value.put(myDbHelper.EMP_NAMEE, e1);
//        long newRowId = db.insert(myDbHelper.TABLE_EMP,null, Value);
//        db.close();
//    }
    public ArrayList<HashMap<String, String>> punch(){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT Name, Password, RetypePassword FROM "+ myDbHelper.TABLE_EMP;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("Name",cursor.getString(cursor.getColumnIndex(myDbHelper.EMP_NAME)));
            user.put("Password",cursor.getString(cursor.getColumnIndex(myDbHelper.EMP_ID)));
            user.put("RetypePassword",cursor.getString(cursor.getColumnIndex(myDbHelper.EMP_DEPT)));
            userList.add(user);
        }
        return  userList;
    }
//    public void add(String e1, String e2, String e3)
//    {
//        SQLiteDatabase db = myhelper.getWritableDatabase();
//        ContentValues Values = new ContentValues();
//        Values.put(myDbHelper.EMP_NAME, e1);
//        Values.put(myDbHelper.EMP_ID,e2);
//        Values.put(myDbHelper.EMP_DEPT,e3);
//        long newRowId = db.insert(myDbHelper.TABLE_EMP,null, Values);
//        db.close();
//    }
//    public List<String> getSpinData(){
//        List<String>labels= new ArrayList<>();
//        String selectQuery = "SELECT  * FROM " + myDbHelper.TABLE_EMP;
//        SQLiteDatabase db=myhelper.getReadableDatabase();
//        Cursor cursor=db.rawQuery(selectQuery,null);
//        if (cursor.moveToFirst()){
//            do{
//                labels.add(cursor.getString(1));
//            }while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return labels;
//    }
    static class myDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "myDatabase";
        private static final String TABLE_EMP = "myTable";   // Table Name
        private static final int DATABASE_Version = 1;   // Database Version
        private static final String UID = "_id";
       // private static final String EMP_NAMEE = "Namee";// Column I (Primary Key)
        private static final String EMP_NAME = "Name";    //Column II
        private static final String EMP_ID = "Password";    // Column III
        private static final String EMP_DEPT = "RetypePassword";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_EMP +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EMP_NAME + " VARCHAR(255) , "
                + EMP_ID + " VARCHAR(255), "
                + EMP_DEPT + ")";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_EMP;
        private Context context;

//        private static final String TABLE_PUNCH = "myTable1";   // Table Name
//        private static final String UID1 = "_id1";     // Column I (Primary Key)
//        private static final String EMP_ID1 = "empId";    //Column II
//        private static final String EMP_PUNCHIN = "punchInDate";    // Column III
//        private static final String CREATE_TABLE1 = "CREATE TABLE " + TABLE_PUNCH +
//                " (" + UID1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + EMP_ID1 + " VARCHAR(255) , "
//                +EMP_PUNCHIN+" VARCHAR(100));";
//        private static final String DROP_TABLE1 = "DROP TABLE IF EXISTS " + TABLE_PUNCH;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
           // db.execSQL(CREATE_TABLE1);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TABLE);
            onCreate(db);
           // db.execSQL(DROP_TABLE1);
            //onCreate(db);
        }
    }
}
