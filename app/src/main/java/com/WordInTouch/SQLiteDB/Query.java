package com.WordInTouch.SQLiteDB;

import android.database.Cursor;

import java.text.MessageFormat;

import static com.WordInTouch.Application.db;

/**
 * Created by cloner on 2/16/18.
 */

public class Query {



    public static int getIntFromString(String table , String stringColumnName , String stringColumnValue , String intColumnName ){

        String query = MessageFormat.format("SELECT * FROM ${0} WHERE {1}={2}",table,stringColumnName,"'"+stringColumnValue+"'");

        Cursor cursor_type = db.rawQuery(query, null);

        int intValue  = -1 ;
        if (cursor_type != null && cursor_type.getCount() > 0) {

            if (cursor_type.moveToFirst()) {

                do {

                    intValue=cursor_type.getInt(cursor_type.getColumnIndex(intColumnName));

                } while (cursor_type.moveToNext());
            }
            cursor_type.close();
        }

        return intValue;
    }


    public  static String getStringFromInt(String table , String intColumnName , int intColumnValue , String stringColumnName){

        String query = MessageFormat.format("SELECT * FROM {0} WHERE {1}={2}",table,intColumnName,"'"+intColumnValue+"'");
        Cursor cursor_type = db.rawQuery(query, null);

        String stringValue = null ;

        if (cursor_type != null && cursor_type.getCount() > 0) {

            if (cursor_type.moveToFirst()) {

                do {

                    stringValue=cursor_type.getString(cursor_type.getColumnIndex(stringColumnName));

                } while (cursor_type.moveToNext());
            }
            cursor_type.close();
        }

        return  stringValue ;
    }

    public static int getCountExist(String table , String columnName , String columnValue){

        String query = MessageFormat.format("SELECT * FROM {0} WHERE {1}={2}",table,columnName,"'"+columnValue+"'") ;
        Cursor cursor_type = db.rawQuery(query, null);

        int count = 0;

        if (cursor_type != null && cursor_type.getCount() > 0) {

            if (cursor_type.moveToFirst()) {

                do {

                    count+=1;

                } while (cursor_type.moveToNext());
            }
            cursor_type.close();
        }

        return count;
    }

}
