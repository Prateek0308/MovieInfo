package com.test.movieinformation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "HELPER";
    private static final String TableName = "MovieInfo";

    public DatabaseHelper(Context context) {
        super(context, TableName, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, YEAR TEXT, GRNRE TEXT, " +
                "DIRECTOR TEXT, COUNTRY TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }

    public boolean addData(String title, String year, String genre, String director, String country) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TITLE", title);
        contentValues.put("YEAR", year);
        contentValues.put("GRNRE", genre);
        contentValues.put("DIRECTOR", director);
        contentValues.put("COUNTRY", country);
        long result = sqLiteDatabase.insert(TableName, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM " + TableName;
        Cursor data = sqLiteDatabase.rawQuery(query, null);
        return data;
    }

    public void delData(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TableName,"ID=?",new String[]{id});
        sqLiteDatabase.close();
    }
}
