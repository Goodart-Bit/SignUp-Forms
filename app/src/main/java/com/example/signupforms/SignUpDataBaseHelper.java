package com.example.signupforms;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class SignUpDataBaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Users.db";
    public static final int DATABASE_VER = 1;
    public static final String TABLE_NAME = "Registered_users_table";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="FULLNAME";
    public static final String COL_3 ="USERNAME";
    public static final String COL_4 ="EMAIL";
    public static final String COL_5 ="PASSWORD";
    public static final String COL_6 ="GENDER";

    public SignUpDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "FULLNAME TEXT NOT NULL," +
                "USERNAME TEXT NOT NULL," +
                "EMAIL TEXT NOT NULL UNIQUE," +
                "PASSWORD TEXT NOT NULL," +
                "GENDER TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void initDb(){
        SQLiteDatabase db=this.getWritableDatabase();
        onUpgrade(db,1,1);
    }

    public boolean insertUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues userDataSet = new ContentValues();
        userDataSet.put(COL_2,user.getFullName());
        userDataSet.put(COL_3,user.getUsername());
        userDataSet.put(COL_4,user.getEmail());
        userDataSet.put(COL_5,user.getPassword());
        userDataSet.put(COL_6,user.getGender());

        long ins_result = db.insert(TABLE_NAME,null,userDataSet);
        return ins_result != -1;
    }

    public Cursor getUserData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor userData = db.rawQuery("SELECT * FROM "+TABLE_NAME+"WHERE id="+id+"",null);
        return userData;
    }

    public Cursor getAllUserData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor allUsers = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return allUsers;
    }
}
