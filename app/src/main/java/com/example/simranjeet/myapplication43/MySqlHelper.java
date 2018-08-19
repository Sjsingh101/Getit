package com.example.simranjeet.myapplication43;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MySqlHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "myDatabase";    // Database Name
           static final String TABLE_NAME = "myTable";   // Table Name
      private static final int DATABASE_Version = 4;    // Database Version
    static final String NAME="Name";
    static final String BRAND="Brand";
    static final String COLOR="Color";
    static final String PRICE="Price";
    static final String IMAGE="Image";
    static final String IMAGE64="Image64";
    static final String IMAGEURI="Imageuri";
    static final String ID="Id";

    private Context context;


    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+NAME+" VARCHAR(255) ,"+ BRAND+" VARCHAR(225) ,"+COLOR+" VARCHAR(255) ,"+PRICE+" INTEGER , "+IMAGEURI+" VARCHAR(955));";
    private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;


    public MySqlHelper(Context context) {
        super(context,DATABASE_NAME, null,DATABASE_Version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);

    }

    Allitems getItemDetail(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { ID,NAME,BRAND,COLOR,PRICE,IMAGEURI,IMAGE64}, NAME + "=?",
                new String[] {Integer.toString(id) }, null, null, null, null);


        if (cursor != null)
            cursor.moveToFirst();

        Allitems contact = new Allitems(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2),cursor.getString(3),Integer.parseInt(cursor.getString(4)),cursor.getString(5),false);
        // return contact
        return contact;
    }


    public ArrayList<Allitems> getAllItems() {
        ArrayList<Allitems> allitemsList = new ArrayList<Allitems>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase dbb = this.getReadableDatabase();
        Cursor cursor = dbb.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Allitems contact = new Allitems(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2),cursor.getString(3),Integer.parseInt(cursor.getString(4)),cursor.getString(5),false);
                allitemsList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return allitemsList;
    }


    public int updateItems(Allitems allitems) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MySqlHelper.NAME, allitems.getName());
        values.put(MySqlHelper.BRAND, allitems.getBrand());
        values.put(MySqlHelper.COLOR, allitems.getColor());
        values.put(MySqlHelper.PRICE, allitems.getPrice().toString());
        values.put(MySqlHelper.IMAGEURI, allitems.getImageUri());
       // values.put(MySqlHelper.IMAGE64, allitems.getImage64());

        // updating row
        return db.update(MySqlHelper.TABLE_NAME, values,MySqlHelper.ID+" = ?",new String[] { String.valueOf(allitems.getId())});
    }
    public void deleteItem(Allitems allitems) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID + " = ?", new String[] { String.valueOf(allitems.getId()) });
        db.close();
    }

    public int getItemCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


    public long insertData(Allitems allitems){
        SQLiteDatabase dbb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqlHelper.NAME, allitems.getName());
        contentValues.put(MySqlHelper.BRAND, allitems.getBrand());
        contentValues.put(MySqlHelper.COLOR, allitems.getColor());
        contentValues.put(MySqlHelper.PRICE, allitems.getPrice());
        contentValues.put(MySqlHelper.IMAGEURI, allitems.getImageUri());
        //contentValues.put(MySqlHelper.IMAGE64, allitems.getImage64());

        long id = dbb.insert(MySqlHelper.TABLE_NAME, null , contentValues);
        return id;
    }



    public String makeDetails(int id) {
        StringBuilder builder = new StringBuilder();
        Allitems allitems = getItemDetail(id);
        builder.append("Name : ").append(allitems.getName()+"/n");
        builder.append("Brand : ").append(allitems.getBrand()+"/n");
        builder.append("Color : ").append(allitems.getColor()+"/n");
        builder.append("Price : ").append(allitems.getPrice()+"/n");


        return builder.toString();
    }


}
