package com.mera.android.memoirs.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mera.android.memoirs.Data.MaMContact.MaM_Table;

import java.util.ArrayList;

/**
 * Created by AmiraRabea on 7/26/2018.
 */

public class MaMController {
    MaMOpenHelper mamOpenHelper;
    SQLiteDatabase database;

        // Constructor with no-arguments
        public MaMController(){
        }

        // Constructor with arguments
    public MaMController(Context context) {
        mamOpenHelper = new MaMOpenHelper(context);

    }
        // Open MaMOpenHelper class
    public void open() {
        database = mamOpenHelper.getWritableDatabase();
    }

    // Open MaMOpenHelper class
    public void close() {
        mamOpenHelper.close();
    }

        // Insert data in database
    public long insertMamoirs(String address, String topic, String date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MaM_Table.TABLE_COL_ADDRESS, address);
        contentValues.put(MaM_Table.TABLE_COL_TOPIC, topic);
        contentValues.put(MaM_Table.TABLE_COL_DATE, date);

        long index = database.insert(MaM_Table.TABLE_NAME, null, contentValues);
        return index;
    }

    // Update data in database
    public int updateMamoirs(long id,String address, String topic, String date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MaM_Table.TABLE_COL_ADDRESS, address);
        contentValues.put(MaM_Table.TABLE_COL_TOPIC, topic);
        contentValues.put(MaM_Table.TABLE_COL_DATE, date);
        int index = database.update(MaM_Table.TABLE_NAME, contentValues,
                MaM_Table.TABLE_COL_ID + "=" + id, null);
        return index;
    }

    // Delete data from database
    public int deleteMamoirs(long id) {
        int index = database.delete(MaM_Table.TABLE_NAME, MaM_Table.TABLE_COL_ID+ "=" + id,
                null);
        return index;
    }
    // Select all data row in database
    public String selectMamoirs(int id) {
        String[] projection = {
                MaM_Table.TABLE_COL_ID,
                MaM_Table.TABLE_COL_ADDRESS,
                MaM_Table.TABLE_COL_TOPIC,
                MaM_Table.TABLE_COL_DATE};

        Cursor cursor = database.query(MaM_Table.TABLE_NAME,
                projection,
                MaM_Table.TABLE_COL_ID + "=" + id,
                null, null, null,
                null);

        Mamoir mamoir = new Mamoir(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));

        return mamoir.toString();
    }

        // Select one data row in database
    public Cursor selectOneMamoirs(long id) {
        String[] projection = {
                MaM_Table.TABLE_COL_ID,
                MaM_Table.TABLE_COL_ADDRESS,
                MaM_Table.TABLE_COL_TOPIC,
                MaM_Table.TABLE_COL_DATE};

        Cursor cursor = database.query(MaM_Table.TABLE_NAME,
                projection,
                MaM_Table.TABLE_COL_ID + "=" + id,
                null, null, null,
                null);


       if(cursor !=null)
           cursor.moveToFirst();

        return cursor;
    }

        //  Select all data in database
    public ArrayList<String> selectAllMamoris() {
        ArrayList<String> mamoirs = new ArrayList<>();
        String[] projection = {
                MaM_Table.TABLE_COL_ID,
                MaM_Table.TABLE_COL_ADDRESS,
                MaM_Table.TABLE_COL_TOPIC,
                MaM_Table.TABLE_COL_DATE};

        Cursor cursor = database.query(MaM_Table.TABLE_NAME,
                projection,
                null,
                null, null, null,
                null);


        while (cursor.moveToNext()) {
            Mamoir mamoir = new Mamoir(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));
            mamoirs.add(mamoir.toString());
        }
        return mamoirs;
    }

}
