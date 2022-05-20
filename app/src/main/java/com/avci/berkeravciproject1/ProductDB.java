package com.avci.berkeravciproject1;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.Enumeration;

public class ProductDB {
    public static String TABLE_NAME="product_table";
    public static String FIELD_NAME = "name";
    public static String FIELD_NUMBER = "number";
    public static String FIELD_PRICE = "price";

    public static final String CREATE_TABLE_SQL = "CREATE TABLE "+TABLE_NAME+" ("+FIELD_NAME+" text, "+FIELD_NUMBER+" text, "+FIELD_PRICE+" text);";
    public static String DROP_TABLE_SQL = "DROP TABLE if exists "+TABLE_NAME;

    public static ArrayList<Product> getAllBooks(DatabaseHelper dbHelper){
        Product anItem;
        ArrayList<Product> data = new ArrayList<>();
        Cursor cursor = dbHelper.getAllRecords(TABLE_NAME, null);
        Log.d("DATABASE OPERATIONS", cursor.getCount()+",  "+cursor.getColumnCount());
        while(cursor.moveToNext()){
            String name=cursor.getString(0);
            String number = cursor.getString(1);
            String price= cursor.getString(2);

            anItem = new Product(name, number, price);
            data.add(anItem);

        }
        Log.d("DATABASE OPERATIONS",data.toString());
        return data;
    }

    public static ArrayList<Product> findPro(DatabaseHelper dbHelper, String key) {
        Product anItem;
        ArrayList<Product> data = new ArrayList<>();
        String where = FIELD_NAME +" like '%"+key+"%'";

        Cursor cursor = dbHelper.getSomeRecords(TABLE_NAME, null, where);
        Log.d("DATABASE OPERATIONS",  where+", "+cursor.getCount()+",  "+cursor.getColumnCount());
        while(cursor.moveToNext()){
            String name=cursor.getString(0);
            String number = cursor.getString(1);
            String price= cursor.getString(2);

            anItem = new Product(name, number, price);
            data.add(anItem);
        }
        Log.d("DATABASE OPERATIONS",data.toString());
        return data;
    }

    public static boolean insert(DatabaseHelper dbHelper, Product b) {
        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_NAME, b.getName());
        contentValues.put(FIELD_NUMBER, b.getNumber());
        contentValues.put(FIELD_PRICE, b.getPrice());
        boolean res = dbHelper.insert(TABLE_NAME,contentValues);
        return res;
    }

    public static boolean insert(DatabaseHelper dbHelper, String name, String number, String price ) {
        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_NUMBER, number);
        contentValues.put(FIELD_PRICE, price);
        boolean res = dbHelper.insert(TABLE_NAME,contentValues);
        return res;
    }

    public static boolean update(DatabaseHelper dbHelper, String name, String number, String price,String img) {

        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_NUMBER, number);
        contentValues.put(FIELD_PRICE, price);

        String where = FIELD_NAME +" = "+name;
        boolean res = dbHelper.update(TABLE_NAME,contentValues,where );
        return res;
    }

    public static boolean delete(DatabaseHelper dbHelper, String name){
        Log.d("DATABASE OPERATIONS", "DELETE DONE");
        String where = FIELD_NAME +" = "+name;
        boolean res =  dbHelper.delete(TABLE_NAME, where);
        return  res;
    }
}
