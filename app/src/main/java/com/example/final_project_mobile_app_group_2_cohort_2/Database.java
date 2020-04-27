package com.example.final_project_mobile_app_group_2_cohort_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private static final String DBNAME = "Uzuri.db";
    private static final String PRODUCT_TABLE = "Products";
    private static final String USER_TABLE = "Users";

    private static final int VERSION = 1;

    private static final String Product_ID = "Product_ID";
    private static final String Product = "Product";
    private static final String Price = "Price";
    private static final String Product_Img = "Product_Img";
    private static final String Rating = "Rating";
    private static final String Category = "Category";
    private static final String Description = "Description";

    private static final String User_ID = "User_ID";
    private static final String Name = "Name";
    private static final String Email = "Email";
    private static final String Password = "Password";

    public Database(@Nullable Context context) {
        super(context, DBNAME, null, VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE if not exists "+ PRODUCT_TABLE + "(" + Product_ID + " INTEGER PRIMARY KEY," + Product + " TEXT," + Price + " INTEGER," + Product_Img + " BLOB," + Rating + " INTEGER," + Category + " TEXT," + Description + " TEXT)");
        db.execSQL("CREATE TABLE if not exists "+ USER_TABLE + "(" + User_ID + " INTEGER PRIMARY KEY," + Name + " TEXT," + Email + " TEXT," + Password + " TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists TABLENAME");
        onCreate(db);
    }

    public void addProduct(int product_ID, String product, int price, byte[] product_Img, String category, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues record = new ContentValues();

        record.put(Product_ID, product_ID);
        record.put(Product, product);
        record.put(Price, price);
        record.put(Product_Img, product_Img);
        record.put(Category, category);
        record.put(Description, description);

        db.insert(PRODUCT_TABLE, null, record);
    }

    public Cursor getProduct(){
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + PRODUCT_TABLE , null);

        return cursor;
    }

    public void addUser(String name, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues user = new ContentValues();

        user.put(Name, name);
        user.put(Email, email);
        user.put(Password, password);

        db.insert(USER_TABLE, null, user);
    }

    public boolean getUser(String email, String password){
        SQLiteDatabase database = this.getReadableDatabase();

//        Cursor cursor = database.query(USER_TABLE, new String[]{Password}, Email, new String[]{email}, null, null, null);
//        Cursor cursor1 = database.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE Email=" + email, null);


        // array of columns to fetch
        String[] columns = {
                User_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = Email + " = ?" + " AND " + Password + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(USER_TABLE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;


//        return cursor;
    }

}
