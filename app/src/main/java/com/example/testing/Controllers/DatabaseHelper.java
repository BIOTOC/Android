package com.example.testing.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "comments.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_COMMENTS = "comments";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CHAPTER_ID = "chapter_id";
    private static final String COLUMN_CONTENT = "content";
    private static final String COLUMN_USERNAME = "username";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_COMMENTS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CHAPTER_ID + " INTEGER, "
                + COLUMN_USERNAME + " TEXT, "  // Thêm cột username vào bảng
                + COLUMN_CONTENT + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_COMMENTS + " ADD COLUMN " + COLUMN_USERNAME + " TEXT");
        }

    }


    public void addComment(int chapterId, String username, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CHAPTER_ID, chapterId);
        values.put(COLUMN_USERNAME, username); // Thêm username vào ContentValues
        values.put(COLUMN_CONTENT, content);
        long result = db.insert(TABLE_COMMENTS, null, values);
        db.close();
        Log.d("DatabaseHelper", "Add comment result: " + result);
    }


    public List<String> getCommentsForChapter(int chapterId) {
        List<String> commentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_COMMENTS, new String[]{COLUMN_USERNAME, COLUMN_CONTENT},
                COLUMN_CHAPTER_ID + "=?", new String[]{String.valueOf(chapterId)},
                null, null, null, null);

        // Kiểm tra xem Cursor có dữ liệu không
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Kiểm tra xem có cột COLUMN_CONTENT trong Cursor không
                int usernameIndex = cursor.getColumnIndex(COLUMN_USERNAME);
                int contentIndex = cursor.getColumnIndex(COLUMN_CONTENT);
                if (usernameIndex != -1 && contentIndex != -1) {
                    String username = cursor.getString(usernameIndex);
                    String comment = cursor.getString(contentIndex);
                    commentList.add(username + ": " + comment);
                } else {
                    // Xử lý trường hợp không có cột COLUMN_CONTENT trong Cursor
                    Log.e("DatabaseHelper", "Column does not exist in Cursor");
                }
            } while (cursor.moveToNext());
        }

        // Đóng Cursor và SQLiteDatabase
        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return commentList;
    }

    public void deleteComment(int commentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_COMMENTS, COLUMN_ID + "=?", new String[]{String.valueOf(commentId)});
        db.close();
        Log.d("DatabaseHelper", "Delete comment result: " + result);
    }


}
