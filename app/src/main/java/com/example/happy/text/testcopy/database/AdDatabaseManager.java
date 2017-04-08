package com.example.happy.text.testcopy.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.happy.text.testcopy.Book;
import com.example.happy.text.testcopy.MyApplication;
import com.example.happy.text.testcopy.StringUtil;

/**
 * 通过单例模式编写  数据库管理类
 */
public class AdDatabaseManager {

    /**
     * 可读写的数据库
     */
    private SQLiteDatabase db;

    private AdDatabaseManager() {

    }

    private static class AD_DATABASE_CLASS_INSTANCE {
        private final static AdDatabaseManager DATA_MANAGER_AD = new AdDatabaseManager();
    }

    public static AdDatabaseManager getInstance() {
        return AdDatabaseManager.AD_DATABASE_CLASS_INSTANCE.DATA_MANAGER_AD;
    }

    public void initDatabase() {
        if (db == null) {
            AdDatabaseHelper helper = new AdDatabaseHelper(MyApplication.context, AdDatabaseHelper.DATA_BASE_NAME, null, AdDatabaseHelper.VERSION);
            db = helper.getWritableDatabase();
        }
    }


    /**
     * 插入数据, 把Book值存入数据库
     *
     * @param book
     */
    public void insertData(Book book) {
        ContentValues cValue = new ContentValues();
        cValue.put(AdDatabaseHelper.KEY_BOOK_NAME, book.getBookName());
        cValue.put(AdDatabaseHelper.KEY_BOOK_PRICE, book.getBookPrice());
        cValue.put(AdDatabaseHelper.KEY_BOOK_AUTHOR, book.getBookAuthor());
        db.insert(AdDatabaseHelper.DATA_BASE_NAME, null, cValue);
    }

    /**
     * 根据书名 查找到要删除的条目
     *
     * @param bookName
     */
    public void deleteData(String bookName) {
        String whereClause = AdDatabaseHelper.KEY_BOOK_NAME + "=?";
        String[] whereArgs = {String.valueOf(bookName)};
        db.delete(AdDatabaseHelper.DATA_BASE_NAME, whereClause, whereArgs);
    }

    /**
     * 查询数据库
     *
     * @param bookName
     * @return 返回查到的值
     */
    public Book getData(String bookName) {

        String sql = StringUtil.join("SELECT * FROM ", AdDatabaseHelper.DATA_BASE_NAME
                , " WHERE ", AdDatabaseHelper.KEY_BOOK_NAME, "=\"", bookName + "\"");
        Cursor cursor = db.rawQuery(sql, null);

        Book book = null;
        if (cursor != null) {
            while (cursor.moveToNext()) {
                book = new Book();
                book.setBookName(cursor.getString(0));
                book.setBookPrice(cursor.getString(1));
                book.setBookAuthor(cursor.getString(2));
            }
            cursor.close();
        }
        return book;
    }

}
