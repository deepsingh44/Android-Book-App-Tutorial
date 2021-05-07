package com.example.deep.androidtutorial.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.deep.androidtutorial.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private LocalDatabase localDatabase;

    public BookDao(Context context) {
        localDatabase = new LocalDatabase(context);
    }

    public long addBook(Book book) {
        ContentValues cv = new ContentValues();
        cv.put(LocalDatabase.BOOK_NAME, book.getName());
        cv.put(LocalDatabase.BOOK_PRICE, book.getPrice());
        cv.put(LocalDatabase.BOOK_AUTHOR, book.getAuthor());
        cv.put(LocalDatabase.BOOK_DATE, book.getDate());
        cv.put(LocalDatabase.BOOK_CATEGORY, book.getCategory());
        return localDatabase.getWritableDatabase().insert(LocalDatabase.BOOK_TABLE_NAME, null, cv);
    }

    public List<Book> fetchAllBooks() {
        List<Book> books = new ArrayList<>();
        Cursor cursor = localDatabase.getReadableDatabase().query(LocalDatabase.BOOK_TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Book book = new Book();
            book.setId(cursor.getInt(0));
            book.setName(cursor.getString(1));
            book.setPrice(cursor.getFloat(2));
            book.setAuthor(cursor.getString(3));
            book.setDate(cursor.getString(4));
            book.setCategory(cursor.getString(5));
            books.add(book);
        }
        return books;
    }

    public void deleteBookById(int id) {
    }

}
