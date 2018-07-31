package com.esperer.shopshop.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseFavorite extends SQLiteOpenHelper {

/// set database
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "favorite.db";
    public static final String TABLE_NAME = "favorite_table";
    public static final String col_1 = "product_id";
    public static final String col_2 = "product_name";
    public static final String col_3 = "product_brand";
    public static final String col_4 = "product_img";
    public static final String col_5 = "product_price";
    public static final String col_6 = "product_discount";


    /// Provide a suitable constructor
    public databaseFavorite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }

    ///  create table
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE "+TABLE_NAME+" " +
                "( product_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "product_name TEXT, " +
                "product_brand TEXT, " +
                "product_img TEXT, " +
                "product_price INTEGER, " +
                "product_discount INTEGER ) ";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        String sql = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(sql);

        onCreate(db);

    }

    /// insert values to database
    public boolean insert (String product_id, String product_name,String product_brand, String product_img,
                           String product_price,String product_discount )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();
        contentValues.put(col_1,product_id);
        contentValues.put(col_2,product_name);
        contentValues.put(col_3,product_brand);
        contentValues.put(col_4,product_img);
        contentValues.put(col_5,product_price);
        contentValues.put(col_6,product_discount);

        long results =  db.insert(TABLE_NAME,null,contentValues);

        if (results == -1)
        {
            return false;
        }
        else return true;
    }

    ///  method to get all the data from database
    public Cursor getAllData( )
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);

        return  res;
    }

    /// method to update data into database
    public boolean update(String product_id, String product_name,String product_brand, String product_img,
                         String product_price,String product_discount )
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1,product_id);
        contentValues.put(col_2,product_name);
        contentValues.put(col_3,product_brand);
        contentValues.put(col_4,product_img);
        contentValues.put(col_5,product_price);
        contentValues.put(col_5,product_discount);
        db.update(TABLE_NAME,contentValues,"product_id = ?",new String[] { product_id });


        return true;


    }

   /*  method to delete data
       @param product_id
   */

    public Integer delete( String product_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();


        return  db.delete(TABLE_NAME,"product_id = ?",new String [] { product_id });


    }


    /// method to get no of item
    public int noOfItem( ){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select product_id from "+TABLE_NAME,null);
        int i = 0;
        while (res.moveToNext())
        {

            i = i+1;


        }

        return  i;
    }

    /// method to get total price of items
    public Cursor getTotalPrice(){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select product_price * product_quantity from "+TABLE_NAME,null);


        return res;


    }



}
