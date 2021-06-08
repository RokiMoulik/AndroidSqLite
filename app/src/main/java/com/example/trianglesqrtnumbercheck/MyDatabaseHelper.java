package com.example.trianglesqrtnumbercheck;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Number.db";
    private static final String TABLE_NAME = "number_details";
    private static final String _ID = "Id";
    private static final String NUMBER = "Number";
    private static final String TYPE = "Type";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+ " ( " +_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+NUMBER+" INTEGER, "+TYPE+" VARCHAR(255)); ";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String SELECT_ALL = "SELECT * FROM "+TABLE_NAME;

    private final Context context;
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
            Toast.makeText(context, "onCreate is called in MyDatabaseHelper", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(context, "Exception: "+e, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_TABLE);
            onCreate(db);
            Toast.makeText(context, "onUpgrade successfully", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(context, "Exception: " +e, Toast.LENGTH_SHORT).show();
        }
    }

    public long insertData(int number, String string){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NUMBER, number);
        contentValues.put(TYPE, string);
        long rowId = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return rowId;
    }
    public Cursor displayData(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL, null);
        return cursor;
    }
}
