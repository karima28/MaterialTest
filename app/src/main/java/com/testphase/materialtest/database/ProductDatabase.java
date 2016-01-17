package com.testphase.materialtest.database;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.testphase.materialtest.logging.L;
import com.testphase.materialtest.pojo.Thing;

import java.util.ArrayList;

/**
 * Created by deea on 17/01/16.
 */
public class ProductDatabase {

    private DbHelper dbHelper;
    private SQLiteDatabase mDatabase;

    public ProductDatabase(Context context) {
        dbHelper = new DbHelper(context);
        mDatabase = dbHelper.getWritableDatabase();
    }


    public void updateItem(Thing thing) {




    }

    public void insertItem(Thing thing){

        String sql = "INSERT INTO " + (DbHelper.ITEM_TABLE_NAME) + " (" +
                DbHelper.ITEM_COLUMN_NAME + ", " +
                DbHelper.ITEM_COLUMN_SHORT_DESC + ", " +
                DbHelper.ITEM_COLUMN_LONG_DESC + ", " +
                DbHelper.ITEM_COLUMN_GOODNESS_VALUE + "" +
                ") VALUES (?,?,?,?);";

        SQLiteStatement statement = mDatabase.compileStatement(sql);
        mDatabase.beginTransaction();

        statement.bindString(1, thing.getName());
        statement.bindString(2, thing.getShortDescription());
        statement.bindString(3, thing.getLongdescription());
        statement.bindDouble(4, thing.getGoodnessValue());


        statement.execute();

        L.m("inserting an entry ");
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
    }



    public ArrayList<Thing> readItems() {
        ArrayList<Thing> listThings = new ArrayList<>();

        //get a list of columns to be retrieved, we need all of them
        String[] columns = {DbHelper.ITEM_COLUMN_ID,
                DbHelper.ITEM_COLUMN_NAME,
                DbHelper.ITEM_COLUMN_SHORT_DESC,
                DbHelper.ITEM_COLUMN_LONG_DESC,
                DbHelper.ITEM_COLUMN_GOODNESS_VALUE
        };
        Cursor cursor = mDatabase.query(DbHelper.ITEM_TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            L.m("loading entries " + cursor.getCount());
            do {

                //create a new object and retrieve the data from the cursor to be stored in this object
                Thing thing = new Thing();
                //each step is a 2 part process, find the index of the column first, find the data of that column using
                //that index and finally set our blank object to contain our data
                thing.setName((cursor.getString(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_NAME))));
                thing.setShortDescription(cursor.getString(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_SHORT_DESC)));
                thing.setLongdescription(cursor.getString(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_LONG_DESC)));
                thing.setGoodnessvalue(cursor.getDouble(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_GOODNESS_VALUE)));

                listThings.add(thing);

            } while (cursor.moveToNext());
        }

        return listThings;
    }

    public void deleteItem() {
        String sql = "DELETE FROM " + (DbHelper.ITEM_TABLE_NAME) + "WHERE " + DbHelper.ITEM_COLUMN_ID + " = ? ";
        SQLiteStatement statement = mDatabase.compileStatement(sql);
        mDatabase.delete(DbHelper.ITEM_TABLE_NAME, null, null);
    }



    private static class DbHelper extends SQLiteOpenHelper {

        public static final String DATABASE_NAME = "itemtable";
        public static final int DATABASE_VERSION = 2;

        public static final String ITEM_TABLE_NAME = "itemlist";
        public static final String ITEM_COLUMN_ID = "_id";
        public static final String ITEM_COLUMN_NAME = "name";
        public static final String ITEM_COLUMN_SHORT_DESC = "shortdescription";
        public static final String ITEM_COLUMN_LONG_DESC = "longdescription";
        public static final String ITEM_COLUMN_GOODNESS_VALUE = "goodnessvalue";

        private static final String CREATE_TABLE_ITEMS = "CREATE TABLE " + ITEM_TABLE_NAME + " (" +
                ITEM_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ITEM_COLUMN_NAME + " TEXT," +
                ITEM_COLUMN_SHORT_DESC + " TEXT," +
                ITEM_COLUMN_LONG_DESC + " TEXT," +
                ITEM_COLUMN_GOODNESS_VALUE + " DOUBLE" +
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
                L.m("Create table items executed");
                db.execSQL(CREATE_TABLE_ITEMS);
            } catch (SQLiteException exception) {
                L.t(mContext, exception + "");
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                L.m("Upgrade table items executed");
                db.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE_NAME);
                onCreate(db);
            } catch (SQLiteException exception) {
                L.t(mContext, exception + "");
            }
        }


    }
}
