package com.inconceivable.xutils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.inconceivable.xutils.database.DBManager;
import com.inconceivable.xutils.model.Book;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;

import java.util.List;

/**
 * Created
 * Author: yuqi
 * Email：inconceivable_man@163.com
 * Date: 2015/10/9
 */
public class BooksActivity extends Activity {

    private DbUtils dbUtils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_activity);

        //使用DbUtils进行数据的存储
        //指定数据对象的内容，然后直接存储
        dbUtils = DbUtils.create(getApplicationContext(), "app.db");

        //以下代码使用SQLite操作
//        DBManager.newInstance(this);
//        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();

        addBook();
        loadData();
        //deleteBook();
        try {
            Book book = dbUtils.findById(Book.class, 4L);
            Log.d("DB", "找到4的 " + book.getTitle());
            //自定义查询条件
            Selector selector = Selector.from(Book.class).where("price", "=", 1000.0f);
            //自定义查询
            List<Book> list = dbUtils.findAll(selector);
            for (Book bk : list) {
                Log.d("DB", "条件查询 " + bk.getId());
            }

        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    //使用whereBuilder进行复杂条件的删除
    private void deleteWhere() {
        //准备whereBuilder
        WhereBuilder builder = WhereBuilder
                .b("price", "=", 1000.0f)
                .expr("price between 800 and 1000");
        //DbUtils.delete(Class, WhereBuilder);
        try {
            dbUtils.delete(Book.class, builder);
        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    //使用DbUtils删除单一的一个数据记录
    private void deleteBook() {

        try {
            //找到所有记录
            List<Book> books = dbUtils.findAll(Book.class);
            if (books != null && !books.isEmpty()) {
                Book book = books.get(0);
                Log.d("DB", "book id " + book.getId());
                dbUtils.delete(book);
            }

        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    private void addBook() {
        Book book = new Book();
        book.setTitle("10天学会Android开发");
        book.setPrice(1000.0f);
        book.setAuthor("张哥");

        try {
            //添加一个记录，对应的就是insert语句
            //内容并没有设置id，代表对象直接添加
            dbUtils.save(book);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    //使用DbUtils来加载表当中的数据
    public void loadData() {
        try {
            List<Book> books = dbUtils.findAll(Book.class);

            for (Book book : books) {
                Log.d("DB", "Book" + book.getId());
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    //使用DBManager封装SQLite数据库操作，避免每次都要获取SQLiteDatabase
    private void initData() {
        DBManager dbManager = DBManager.getInstance();

        Book book = new Book();
        book.setTitle("10天学会Android开发");
        book.setPrice(1000.0f);
        book.setAuthor("张哥");

        long bid = dbManager.insertBook(book);

        Log.d("DB", "book id" + bid);
    }
}