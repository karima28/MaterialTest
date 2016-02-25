package com.testphase.materialtest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.testphase.materialtest.logging.L;

/**
 * The DBHelper Class is the database helper class and contains the main database create and upgrade statements
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "itemtable";
    public static final int DATABASE_VERSION = 5;

    public static final String ITEM_TABLE_NAME = "itemlist";
    public static final String ITEM_COLUMN_ID = "_id";
    public static final String ITEM_COLUMN_NAME = "name";
    public static final String ITEM_COLUMN_SHORT_DESC = "shortdescription";
    public static final String ITEM_COLUMN_LONG_DESC = "longdescription";
    public static final String ITEM_COLUMN_GOODNESS_VALUE = "goodnessvalue";

    public static final String FAVORITES_TABLE_NAME = "favoriteslist";


    //Product List Table (Main Table)
    private static final String CREATE_TABLE_ITEMS = "CREATE TABLE " + ITEM_TABLE_NAME + " (" +
            ITEM_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ITEM_COLUMN_NAME + " TEXT," +
            ITEM_COLUMN_SHORT_DESC + " TEXT," +
            ITEM_COLUMN_LONG_DESC + " TEXT," +
            ITEM_COLUMN_GOODNESS_VALUE + " INTEGER" +
            ");";

    //Favorites List Table (Secondary Table)
    private static final String CREATE_TABLE_FAVORITES = "CREATE TABLE " + FAVORITES_TABLE_NAME + " (" +
            ITEM_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ITEM_COLUMN_NAME + " TEXT," +
            ITEM_COLUMN_SHORT_DESC + " TEXT," +
            ITEM_COLUMN_LONG_DESC + " TEXT," +
            ITEM_COLUMN_GOODNESS_VALUE + " INTEGER" +
            ");";


    private Context mContext;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            L.m("SQL: " + CREATE_TABLE_ITEMS);
            db.execSQL(CREATE_TABLE_ITEMS);
            db.execSQL(CREATE_TABLE_FAVORITES);
        } catch (SQLiteException exception) {
            L.t(mContext, exception + "");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            L.m("Upgrade table items executed");
            db.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + FAVORITES_TABLE_NAME);
            onCreate(db);
        } catch (SQLiteException exception) {
            L.t(mContext, exception + "");
        }
    }


}