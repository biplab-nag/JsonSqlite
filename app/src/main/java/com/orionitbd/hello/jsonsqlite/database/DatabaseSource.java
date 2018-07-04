package com.orionitbd.hello.jsonsqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.orionitbd.hello.jsonsqlite.response.PostResponse;

import java.util.ArrayList;

public class DatabaseSource {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    public DatabaseSource(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void open(){
        database = databaseHelper.getWritableDatabase();
    }
    public void close(){
        database.close();
    }

    public boolean insertPost(PostResponse response){
        this.open();
        ContentValues values = new ContentValues();
        values.put(databaseHelper.COL_USERID,response.getUserId());
        values.put(databaseHelper.COL_ID,response.getId());
        values.put(databaseHelper.COL_TITLE,response.getTitle());
        values.put(databaseHelper.COL_BODY,response.getBody());

        long inserted = database.insert(databaseHelper.TABLE_POST,null,values);
        this.close();

        if(inserted >0){
            return  true;
        }
        else {
            return false;
        }
    }

    public ArrayList<PostResponse> getAllPost(){
        this.open();
        ArrayList<PostResponse> list  = new ArrayList<>();
        Cursor cursor = database.query(databaseHelper.TABLE_POST,null,null,null,null,null,null);

        if(cursor != null || cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                long userID = cursor.getLong(cursor.getColumnIndex(databaseHelper.COL_USERID));
                long id =cursor.getLong(cursor.getColumnIndex(databaseHelper.COL_ID));
                String title = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_TITLE));
                String body = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_BODY));

                PostResponse response = new PostResponse(userID,id,title,body);
                list.add(response);

            }while (cursor.moveToNext());
        }
        cursor.close();
        this.close();

        return list;


    }
}
