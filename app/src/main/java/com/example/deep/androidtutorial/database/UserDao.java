package com.example.deep.androidtutorial.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.deep.androidtutorial.model.User;

public class UserDao {
    private LocalDatabase localDatabase;

    public UserDao(Context context) {
        localDatabase = new LocalDatabase(context);
    }

    public long register(User user) {
        ContentValues cv = new ContentValues();
        cv.put(LocalDatabase.USER_NAME, user.getName());
        cv.put(LocalDatabase.USER_EMAIL, user.getEmail());
        cv.put(LocalDatabase.USER_PASSWORD, user.getPassword());
        return localDatabase.getWritableDatabase().insert(LocalDatabase.USER_TABLE_NAME, null, cv);
    }

    public User login(String email, String password) {
        User user = null;
        Cursor cursor = localDatabase.getReadableDatabase().query(LocalDatabase.USER_TABLE_NAME, null, "" + LocalDatabase.USER_EMAIL + "=? and " + LocalDatabase.USER_PASSWORD + "=?", new String[]{email, password}, null, null, null);
        if (cursor.moveToNext()) {
            user = new User();
            user.setName(cursor.getString(0));
            user.setEmail(cursor.getString(1));
            user.setPassword(cursor.getString(2));
        }
        return user;
    }
}
