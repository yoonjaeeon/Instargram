package com.example.instargram;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public DBHelper(Context context){
        super(context, "contact.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSQL= "CREATE TABLE IF NOT EXISTS CONTACT_T (" +
                "NAME "         + "TEXT not null," +
                "ID "        + "TEXT not null unique," +
                "PW "       + "TEXT not null)";
        db.execSQL(createSQL);

        db.execSQL("insert into  CONTACT_T values('홍성우','painter3400','1234')");
        db.execSQL("insert into  CONTACT_T values('하준원','cupi12','1234')");
        db.execSQL("insert into  CONTACT_T values('윤재언','yoonjaeeon','1234')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion!=DATABASE_VERSION){ //
            db.execSQL("drop table CONTACT_T");
            onCreate(db);
        }
    }
}