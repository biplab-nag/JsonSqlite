package com.orionitbd.hello.jsonsqlite.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //databsae info
    public static final String DATABASE_NAME ="post_db";
    public static final int DATABASE_VERSION =1;

    //table info
    public static final String TABLE_POST ="tbl_post";
    public static final String COL_USERID ="col_userid";
    public static final String COL_ID ="col_id";
    public static final String COL_TITLE ="col_title";
    public static final String COL_BODY ="col_body";

    //query
    public static final String CREATE_TABLE = "create table "+TABLE_POST+ " ( "+
            COL_USERID+" integer ," +
            COL_ID+"  text ,"+
            COL_TITLE+" text ,"+
            COL_BODY+" text )";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override


    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // drop table
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_POST);
        onCreate(sqLiteDatabase);
    }
}
