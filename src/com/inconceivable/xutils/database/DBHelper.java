package com.inconceivable.xutils.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created
 * Author: yuqi
 * Email：inconceivable_man@163.com
 * Date: 2015/10/9
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "app.db";
    public static final int DB_VER = 1;

    public static final String CREATE_TABLE_BOOKS =
            "create table books ( _id integer primary key autoincrement,"
                    + "title text not null,"
                    + "price real default 0,"
                    + "author text default 'None'"
                    + ")";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_BOOKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
