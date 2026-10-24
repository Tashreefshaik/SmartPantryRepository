package com.example.smartpantrymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SmartPantry.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_PANTRY = "pantry";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_UNIT = "unit";
    private static final String COLUMN_EXPIRY = "expiry_date";

    private static final String CREATE_TABLE_PANTRY = "CREATE TABLE " + TABLE_PANTRY + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT NOT NULL,"
            + COLUMN_QUANTITY + " INTEGER NOT NULL,"
            + COLUMN_UNIT + " TEXT,"
            + COLUMN_EXPIRY + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PANTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PANTRY);
        onCreate(db);
    }

    public long addPantryItem(PantryItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, item.getName());
        values.put(COLUMN_QUANTITY, item.getQuantity());
        values.put(COLUMN_UNIT, item.getUnit());
        values.put(COLUMN_EXPIRY, item.getExpiryDate());
        long id = db.insert(TABLE_PANTRY, null, values);
        db.close();
        return id;
    }

    public List<PantryItem> getAllPantryItems() {
        List<PantryItem> items = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PANTRY, null, null, null, null, null, COLUMN_NAME + " ASC");

        if (cursor.moveToFirst()) {
            do {
                PantryItem item = new PantryItem();
                item.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                item.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                item.setQuantity(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)));
                item.setUnit(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UNIT)));
                item.setExpiryDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXPIRY)));
                items.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return items;
    }

    public int updatePantryItem(PantryItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, item.getName());
        values.put(COLUMN_QUANTITY, item.getQuantity());
        values.put(COLUMN_UNIT, item.getUnit());
        values.put(COLUMN_EXPIRY, item.getExpiryDate());
        int rowsAffected = db.update(TABLE_PANTRY, values, COLUMN_ID + " = ?", new String[]{String.valueOf(item.getId())});
        db.close();
        return rowsAffected;
    }

    public int deletePantryItem(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE_PANTRY, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return rowsDeleted;
    }

    public void clearAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PANTRY, null, null);
        db.close();
    }
}
