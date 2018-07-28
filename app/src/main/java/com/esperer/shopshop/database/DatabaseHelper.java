package com.esperer.shopshop.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "cart.db";
    public static final String TABLE_NAME = "cart_table";
    public static final String col_1 = "product_id";
    public static final String col_2 = "product_name";
    public static final String col_3 = "product_brand";
    public static final String col_4 = "product_img";
    public static final String col_5 = "product_quantity";
    public static final String col_6 = "product_price";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE "+TABLE_NAME+" " +
                "( product_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "product_name TEXT, " +
                "product_brand TEXT, " +
                "product_img TEXT, " +
                "product_quantity INTEGER, " +
                "product_price INTEGER ) ";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        String sql = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(sql);

        onCreate(db);

    }

    public boolean insert (String product_id, String product_name,String product_brand, String product_img,
                           String product_quantity, String product_price )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();
        contentValues.put(col_1,product_id);
        contentValues.put(col_2,product_name);
        contentValues.put(col_3,product_brand);
        contentValues.put(col_4,product_img);
        contentValues.put(col_5,product_quantity);
        contentValues.put(col_6,product_price);
        long results =  db.insert(TABLE_NAME,null,contentValues);

        if (results == -1)
        {
            return false;
        }
        else return true;
    }

    public Cursor getAllData( )
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);

        return  res;
    }

    public boolean update(String product_id, String product_name,String product_brand, String product_img,
                          String product_quantity, String product_price )
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1,product_id);
        contentValues.put(col_2,product_name);
        contentValues.put(col_3,product_brand);
        contentValues.put(col_4,product_img);
        contentValues.put(col_5,product_quantity);
        contentValues.put(col_6,product_price);
        db.update(TABLE_NAME,contentValues,"product_id = ?",new String[] { product_id });


        return true;


    }

    public Integer delete( String product_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();


        return  db.delete(TABLE_NAME,"product_id = ?",new String [] { product_id });


    }

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

    public Cursor getQuantity (String id )
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT product_quantity FROM "+TABLE_NAME+" WHERE product_id=" +id ,null);


        return res;
    }

    public Cursor getTotalPrice(){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select product_price * product_quantity from "+TABLE_NAME,null);


        return res;


    }



}
