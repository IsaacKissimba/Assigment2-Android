package com.example.bookstore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    public DataBase(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Userdetails(name TEXT primary key, isbn TEXT, dor TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists Userdetails");

    }
    public Boolean insertuserdata(String name, String isbn, String dor)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("isbn", isbn);
        contentValues.put("dor", dor);
        long result=db.insert("Userdetails",null, contentValues);
        if (result==-1){
            return false;
        }else {
            return true;
        }
    }



    public Boolean updateuserdata(String name, String isbn, String dor)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("isbn", isbn);
        contentValues.put("dor", dor);
        Cursor cursor = db.rawQuery("select * from Userdetails where name= ?", new String[]{name});
        if (cursor.getCount()>0)
        {

            long result = db.update("Userdetails", contentValues, "name=?", new String[]{name});
            if (result ==-1) {
                return false;
            } else {
                return true;
            }
        } else
        {
            return false;
        } }
    public Boolean deletedata(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Userdetails where name= ?", new String[]{name});
        if (cursor.getCount()>0)
        {

            long result = db.delete("Userdetails", "name=?", new String[]{name});
            if (result ==-1) {
                return false;
            } else {
                return true;
            }
        } else
        {
            return false;
        }
    }

    public Cursor getdata ()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Userdetails ", null);
        return cursor;
    }


}
