package com.testphase.materialtest.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.testphase.materialtest.logging.L;
import com.testphase.materialtest.pojo.Product;

import java.nio.DoubleBuffer;
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


    public void insertProduct(Product product){

        String sql = "INSERT INTO " + (DbHelper.ITEM_TABLE_NAME) + " (" +
                DbHelper.ITEM_COLUMN_NAME + ", " +
                DbHelper.ITEM_COLUMN_SHORT_DESC + ", " +
                DbHelper.ITEM_COLUMN_LONG_DESC + ", " +
                DbHelper.ITEM_COLUMN_GOODNESS_VALUE + "" +
                ") VALUES (?,?,?,?);";

        SQLiteStatement statement = mDatabase.compileStatement(sql);
        mDatabase.beginTransaction();

        statement.bindString(1, product.getName());
        statement.bindString(2, product.getShortDescription());
        statement.bindString(3, product.getLongdescription());
        statement.bindDouble(4, product.getGoodnessValue());

        statement.execute();

        L.m("inserting an entry with id " + product.getId());
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
    }


    public void updateProductGValue(long id, Integer newGValue){

        String sql = "UPDATE " + DbHelper.ITEM_TABLE_NAME +
                " SET " + DbHelper.ITEM_COLUMN_GOODNESS_VALUE + " = " + newGValue +
                " WHERE " + DbHelper.ITEM_COLUMN_ID + " = " + id;

        mDatabase.compileStatement(sql).execute();

    }


    public ArrayList<Product> getAllProducts() {

        ArrayList<Product> listProducts = new ArrayList<>();

        Cursor cursor = mDatabase.query(DbHelper.ITEM_TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            L.m("loading entries " + cursor.getCount());
            do {

                //create a new object and retrieve the data from the cursor to be stored in this object
                Product product = new Product(cursor.getLong(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_ID)),
                                        cursor.getString(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_NAME)),
                                        cursor.getString(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_SHORT_DESC)),
                                        cursor.getString(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_LONG_DESC)),
                                        cursor.getInt(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_GOODNESS_VALUE)));
                listProducts.add(product);

            } while (cursor.moveToNext());
        }

        return listProducts;
    }

    public Product getProduct(long id) {

        Cursor cursor = mDatabase.query(DbHelper.ITEM_TABLE_NAME, columns, DbHelper.ITEM_COLUMN_ID + " = " + id, null, null, null, null);

        Product product = null;

        if(cursor != null && cursor.moveToFirst())
        {
            product = new Product(cursor.getLong(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_SHORT_DESC)),
                    cursor.getString(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_LONG_DESC)),
                    cursor.getInt(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_GOODNESS_VALUE)));
        }


        return product;
    }


    public void deleteProduct(long id) {

        mDatabase.delete(DbHelper.ITEM_TABLE_NAME, DbHelper.ITEM_COLUMN_ID +"=?", new String[] {Long.toString(id)});


    }

    public void addToFavorites(Product product) {

        L.m("Created new favorite");

        String sql = "INSERT INTO " + (DbHelper.FAVORITES_TABLE_NAME) + " (" +
                DbHelper.ITEM_COLUMN_NAME + ", " +
                DbHelper.ITEM_COLUMN_SHORT_DESC + ", " +
                DbHelper.ITEM_COLUMN_LONG_DESC + ", " +
                DbHelper.ITEM_COLUMN_GOODNESS_VALUE + "" +
                ") VALUES (?,?,?,?);";

        SQLiteStatement statement = mDatabase.compileStatement(sql);
        mDatabase.beginTransaction();

        statement.bindString(1, product.getName());
        statement.bindString(2, product.getShortDescription());
        statement.bindString(3, product.getLongdescription());
        statement.bindDouble(4, product.getGoodnessValue());

        statement.execute();

        L.m("Adding favorite with id " + product.getId());
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
    }

    public ArrayList<Product> getAllFavorites() {

        ArrayList<Product> listFavorites = new ArrayList<>();

        Cursor cursor = mDatabase.query(DbHelper.FAVORITES_TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            L.m("loading favorite entries " + cursor.getCount());
            do {

                //create a new object and retrieve the data from the cursor to be stored in this object
                Product product = new Product(cursor.getLong(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_SHORT_DESC)),
                        cursor.getString(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_LONG_DESC)),
                        cursor.getInt(cursor.getColumnIndex(DbHelper.ITEM_COLUMN_GOODNESS_VALUE)));
                listFavorites.add(product);

            } while (cursor.moveToNext());
        }
        return listFavorites;
    }


}
