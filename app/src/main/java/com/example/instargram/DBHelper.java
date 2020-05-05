package com.example.instargram;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public DBHelper(Context context){
        super(context, "contact.db", null, DATABASE_VERSION);
    }

//    private static final String DATABASE_NAME = "project.db";
////    private static final int DATABASE_VERSION = 1;
//
//    public static final String TABLE_NAME = "CONTENT";
//    public static final String Uri = "uri";
//    public static final String $Content = "content";
//
//    private static final String DATABASE_CREATE_TEAM = "create table"
//    + CONTENT + "("+ Uri + "TEXT" +

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


        String createSQL2= "CREATE TABLE IF NOT EXISTS CONTENT (" +
                "URI "         + "TEXT," +
                "CONTENT "        + "TEXT )";
        db.execSQL(createSQL2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion!=DATABASE_VERSION){ //
            db.execSQL("drop table CONTACT_T");
            db.execSQL("drop table CONTENT");
            onCreate(db);


        }
    }
}