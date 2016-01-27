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
    private final String[] columns = {
                                        DbHelper.ITEM_COLUMN_ID,
                                        DbHelper.ITEM_COLUMN_NAME,
                                        DbHelper.ITEM_COLUMN_LONG_DESC,
                                        DbHelper.ITEM_COLUMN_SHORT_DESC,
                                        DbHelper.ITEM_COLUMN_GOODNESS_VALUE
                                    };

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



    //Change name to getItems
    public ArrayList<Thing> readItems() {

        ArrayList<Thing> listThings = new ArrayList<>();

        Cursor cursor = mDatabase.query(DbHelper.ITEM_TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            L.m("loading entries " + cursor.getCount());
            do {

                //create a new object and retrieve the data from the cursor to be stored in this object
                Thing thing = new Thing(cursor.getLong(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_ID)),
                                        cursor.getString(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_NAME)),
                                        cursor.getString(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_SHORT_DESC)),
                                        cursor.getString(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_LONG_DESC)),
                                        cursor.getDouble(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_GOODNESS_VALUE)));
                listThings.add(thing);

            } while (cursor.moveToNext());
        }

        return listThings;
    }

    //change name to getItem
    public Thing readItem(int position){

        Cursor cursor = mDatabase.query(DbHelper.ITEM_TABLE_NAME, columns, null, null, null, null, null);

        Thing thing = null;
        if(cursor != null && cursor.moveToPosition(position))
        {
            thing = new Thing(cursor.getLong(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_SHORT_DESC)),
                    cursor.getString(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_LONG_DESC)),
                    cursor.getDouble(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_GOODNESS_VALUE)));
        }

        return thing;
    }


    public void deleteThing(long id) {

        mDatabase.delete(DbHelper.ITEM_TABLE_NAME, DbHelper.ITEM_COLUMN_ID +"=?", new String[] {Long.toString(id)});


    }




}
