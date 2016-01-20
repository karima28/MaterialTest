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

        L.m("inserting an entry with id " + thing.getId());
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

    public Thing readItem(int position){

        String[] columns = {DbHelper.ITEM_COLUMN_ID,
                DbHelper.ITEM_COLUMN_NAME,
                DbHelper.ITEM_COLUMN_SHORT_DESC,
                DbHelper.ITEM_COLUMN_LONG_DESC,
                DbHelper.ITEM_COLUMN_GOODNESS_VALUE
        };

        Cursor cursor = mDatabase.query(DbHelper.ITEM_TABLE_NAME, columns, null, null, null, null, null);

        Thing thing = new Thing();

        if(cursor != null && cursor.moveToPosition(position))
        {

            thing.setName((cursor.getString(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_NAME))));
            thing.setShortDescription(cursor.getString(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_SHORT_DESC)));
            thing.setLongdescription(cursor.getString(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_LONG_DESC)));
            thing.setGoodnessvalue(cursor.getDouble(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_GOODNESS_VALUE)));
        }

        return thing;
    }

    public String getItem(int position){

        String[] columns = {DbHelper.ITEM_COLUMN_ID,
                DbHelper.ITEM_COLUMN_NAME,
                DbHelper.ITEM_COLUMN_SHORT_DESC,
                DbHelper.ITEM_COLUMN_LONG_DESC,
                DbHelper.ITEM_COLUMN_GOODNESS_VALUE
        };

        Cursor cursor = mDatabase.query(DbHelper.ITEM_TABLE_NAME, columns, null, null, null, null, null);

        Thing thing = new Thing();

        if(cursor != null && cursor.moveToPosition(position))
        {

            thing.setName((cursor.getString(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_NAME))));
            thing.setShortDescription(cursor.getString(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_SHORT_DESC)));
            thing.setLongdescription(cursor.getString(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_LONG_DESC)));
            thing.setGoodnessvalue(cursor.getDouble(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_GOODNESS_VALUE)));
        }

        String information = thing.getName() + thing.getShortDescription() + thing.getLongdescription() + Double.toString(thing.getGoodnessValue());

        return information;
    }

    public void deleteItem(int position) {
        /*String sql = "DELETE FROM " + (DbHelper.ITEM_TABLE_NAME) + "WHERE " + DbHelper.ITEM_COLUMN_ID + " = ? ";
        SQLiteStatement statement = mDatabase.compileStatement(sql);
        mDatabase.delete(DbHelper.ITEM_TABLE_NAME, null, null);*/

        long id = readItem(position).getId();
        String[] columns = {DbHelper.ITEM_COLUMN_ID,
                DbHelper.ITEM_COLUMN_NAME,
                DbHelper.ITEM_COLUMN_SHORT_DESC,
                DbHelper.ITEM_COLUMN_LONG_DESC,
                DbHelper.ITEM_COLUMN_GOODNESS_VALUE
        };

        Cursor cursor = mDatabase.query(DbHelper.ITEM_TABLE_NAME, columns, null, null, null, null, null);

        if(cursor != null && cursor.moveToPosition(position))
        {
            mDatabase.delete(DbHelper.ITEM_TABLE_NAME, DbHelper.ITEM_COLUMN_ID +"=?", columns);
        }

    }




}
