package com.example.instargram;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DAO {
    DBHelper dbHelper = null;

    public void insert(Context context, VO vo){
        dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sqlInsert = "INSERT INTO CONTACT_T " +
                "(NAME, ID, Pw) VALUES ('" +
                vo.getName() + "'," +
                "'" + vo.getId() + "'," +
                "'" + vo.getPw() + "')" ;

        db.execSQL(sqlInsert);
        dbHelper.close();
    }

    public Boolean loginCheck(Context context, VO vo){

        DBHelper dbhelper = new DBHelper(context);
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql = "select id from CONTACT_T  WHERE id = '" + vo.getId() + "' AND pw = '"+vo.getPw()+"'" ;


        Cursor cursor=db.rawQuery(sql, null);
        if (cursor.moveToNext()){ //cursor == resultset
            return true;
        }else{
            return false;
        }
    }
}
