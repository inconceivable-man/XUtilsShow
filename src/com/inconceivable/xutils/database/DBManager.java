package com.inconceivable.xutils.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.inconceivable.xutils.model.Book;

/**
 * Created
 * Author: yuqi
 * Email：inconceivable_man@163.com
 * Date: 2015/10/9
 */
public class DBManager {
    private static DBManager ourInstance;

    public static DBManager newInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new DBManager(context);
        } else {
            throw new IllegalArgumentException("Context must be set.");
        }
        return ourInstance;
    }

    private Context context;

    public static DBManager getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException("please invoke newInstance() before this method");
        }
        return ourInstance;
    }

    private DBHelper helper;

    private DBManager(Context context) {
        this.context = context;
        helper = new DBHelper(context);
    }

    public long insertBook(Book book) {
        long ret = 0;
        if (book != null) {
            SQLiteDatabase db = helper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put("title", book.getTitle());
            values.put("price", book.getPrice());
            values.put("author", book.getAuthor());

            ret = db.insert("books", null, values);

            db.close();
        }

        return ret;
    }

    public Book findById(long id) {
        Book ret = null;

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query("books",
                null,
                "_id = ?",
                new String[]{Long.toString(id)},
                null,
                null,
                null
        );
        if (cursor != null) {
            if (cursor.moveToNext()) {
                ret = new Book();
                int index = cursor.getColumnIndex("_id");
                if (index != -1) {
                    ret.setId(cursor.getLong(index));
                }
                index = cursor.getColumnIndex("title");
                if (index != -1) {
                    ret.setTitle(cursor.getString(index));
                }
            }
        }
        return ret;
    }
}
