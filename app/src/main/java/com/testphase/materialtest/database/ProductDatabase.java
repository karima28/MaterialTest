package com.testphase.materialtest.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.testphase.materialtest.logging.L;
import com.testphase.materialtest.pojo.Product;

import java.util.ArrayList;

/**
 * The ProductDatabase Class contains all the database functions of reading, adding, updating and deleting entries from the database
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


    /**
     * To insert a new product into the one of the two tables
     * @param product object to be added to the database
     */
    public void insertProduct(Product product, String tableName){

        if (tableName.equals("primary"))
            tableName = DbHelper.ITEM_TABLE_NAME;
        else
            tableName = DbHelper.FAVORITES_TABLE_NAME;

        String sql = "INSERT INTO " + (tableName) + " (" +
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

        L.m("inserting an entry with id " + product.getId() + "into " + tableName);
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
    }


    /**
     * Updates the Goodness Value of a product in the database
     * @param id the id of the product from Products table
     * @param newGValue the new Goodness Value
     */
    public void updateProductGValue(long id, Integer newGValue){

        String sql = "UPDATE " + DbHelper.ITEM_TABLE_NAME +
                " SET " + DbHelper.ITEM_COLUMN_GOODNESS_VALUE + " = " + newGValue +
                " WHERE " + DbHelper.ITEM_COLUMN_ID + " = " + id;

        mDatabase.compileStatement(sql).execute();

    }

    /**
     * Retrieves and returns all the entries of the corresponsing table
     * @return the arraylist of all products in the corresponding table
     */
    public ArrayList<Product> getAllProducts(String tableName) {

        if (tableName.equals("primary"))
            tableName = DbHelper.ITEM_TABLE_NAME;
        else
            tableName = DbHelper.FAVORITES_TABLE_NAME;

        ArrayList<Product> listProducts = new ArrayList<>();

        Cursor cursor = mDatabase.query(tableName, columns, null, null, null, null, null);
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

    /**
     * Retrieves and returns the product from the Product table with the id specified
     * @param id the id of the product from Products table
     * @return the product with the id specified
     */
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


    /**
     * Deletes the product from the database with the id specified
     * @param id the id of the product from Products table
     */
    public void deleteProduct(long id) {

        mDatabase.delete(DbHelper.ITEM_TABLE_NAME, DbHelper.ITEM_COLUMN_ID +"=?", new String[] {Long.toString(id)});


    }
}
