package com.mera.android.memoirs.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.mera.android.memoirs.Data.MaMContact.MaM_Table;

/**
 * Created by AmiraRabea on 7/26/2018.
 */

public class MaMOpenHelper extends SQLiteOpenHelper {

    public final static String DATABASE_NAME="MaM.db";
    public final static int DATABASE_VERSION=1;

            // SQLite database
    public final static String SQL_CREATE_TABLE="CREATE TABLE "+MaM_Table.TABLE_NAME+"(\n" +
            MaM_Table.TABLE_COL_ID+"  INTEGER PRIMARY KEY AUTOINCREMENT\n" +
            "                        NOT NULL,\n" +
            MaM_Table.TABLE_COL_ADDRESS+" TEXT,\n" +
           MaM_Table.TABLE_COL_TOPIC+ "    TEXT    NOT NULL,\n" +
            MaM_Table.TABLE_COL_DATE+"     TEXT    NOT NULL\n" +
            ");\n";

        // Create MaMOpenHelper constructor with arguments
    public MaMOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

        // Override onCreate method to create table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    // Override onUpgrade method to delete table if exists
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists "+MaM_Table.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
