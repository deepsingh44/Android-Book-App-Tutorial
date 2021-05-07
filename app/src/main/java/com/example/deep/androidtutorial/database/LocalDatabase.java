package com.example.deep.androidtutorial.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LocalDatabase extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "bookapp";
    //Book Table
    public final static String BOOK_TABLE_NAME = "books";
    public final static String BOOK_ID = "id";
    public final static String BOOK_NAME = "name";
    public final static String BOOK_PRICE = "price";
    public final static String BOOK_AUTHOR = "author";
    public final static String BOOK_DATE = "date";
    public final static String BOOK_CATEGORY = "category";
    public final static String BOOK_CREATE_QUERY = "create table " + BOOK_TABLE_NAME + " (" + BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + BOOK_NAME + " varchar(30)," + BOOK_PRICE + " float," + BOOK_AUTHOR + " varchar(30)," + BOOK_DATE + " varchar(15)," + BOOK_CATEGORY + " varchar(30));";
    //User table
    public final static String USER_TABLE_NAME = "users";
    public final static String USER_NAME = "name";
    public final static String USER_EMAIL = "email";
    public final static String USER_PASSWORD = "password";
    public final static String USER_CREATE_QUERY = "create table users (" + USER_NAME + " varchar(30)," + USER_EMAIL + " varchar(35) primary key," + USER_PASSWORD + " varchar(20));";


    public LocalDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(BOOK_CREATE_QUERY);
        sqLiteDatabase.execSQL(USER_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + BOOK_TABLE_NAME + "");
        sqLiteDatabase.execSQL("drop table if exists " + USER_TABLE_NAME + "");
        onCreate(sqLiteDatabase);
    }



}
