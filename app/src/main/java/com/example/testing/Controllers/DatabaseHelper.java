package com.example.testing.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.testing.Models.Story;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Tên bảng và các cột cho wishlist
    private static final String TABLE_WISHLIST = "wishlist";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";

    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_CHAPTER = "chapter";


    private static final String COLUMN_STORY_DATA = "story_data";

    // Tên bảng và các cột cho comment
    private static final String TABLE_COMMENTS = "comments";
    private static final String COLUMN_CHAPTER_ID = "chapter_id";
    private static final String COLUMN_CONTENT = "content";
    private static final String COLUMN_USERNAME = "username";

    public DatabaseHelper(Context context) {
        super(context, "YourDatabaseName.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng wishlist
        String createWishlistTableQuery = "CREATE TABLE " + TABLE_WISHLIST + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_IMAGE + " TEXT, "
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_CHAPTER + " INTEGER)";

        db.execSQL(createWishlistTableQuery);

        // Tạo bảng comments
        String createCommentsTableQuery = "CREATE TABLE " + TABLE_COMMENTS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CHAPTER_ID + " INTEGER, "
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_CONTENT + " TEXT)";
        db.execSQL(createCommentsTableQuery);
    }

    // Thêm một truyện vào wishlist
    public void addToWishlist(Story story) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, story.getId());
        values.put(COLUMN_IMAGE, story.getImage());
        values.put(COLUMN_TITLE, story.getName());
        values.put(COLUMN_CHAPTER, story.getNumberChapter());

        long result = db.insert(TABLE_WISHLIST, null, values);
        if (result == -1) {
            Log.e("DatabaseHelper", "Failed to add story to wishlist");
        } else {
            Log.d("DatabaseHelper", "Story added to wishlist successfully");
        }

        db.close();
    }

    private Story deserializeStory(String serializedStory) {
        // Chuyển đổi chuỗi JSON hoặc định dạng dữ liệu khác thành đối tượng Story
        Gson gson = new Gson();
        return gson.fromJson(serializedStory, Story.class);
    }

    // Xóa một truyện khỏi wishlist
    public void removeFromWishlist(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WISHLIST, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Story> getWishlist() {
        List<Story> wishlist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_WISHLIST, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(COLUMN_ID);
                int titleIndex = cursor.getColumnIndex(COLUMN_TITLE);
                int imageIndex = cursor.getColumnIndex(COLUMN_IMAGE);
                int chapterIndex = cursor.getColumnIndex(COLUMN_CHAPTER);

                if (idIndex != -1 && titleIndex != -1 && imageIndex != -1 && chapterIndex != -1) {
                    int id = cursor.getInt(idIndex);
                    String title = cursor.getString(titleIndex);
                    String image = cursor.getString(imageIndex);
                    int chapter = cursor.getInt(chapterIndex);

                    Story story = new Story(id, title, image, chapter);
                    wishlist.add(story);
                } else {
                    Log.e("DatabaseHelper", "One or more columns not found in cursor.");
                }
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return wishlist;
    }


    // Thêm comment cho một chapter
    public void addComment(int chapterId, String username, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CHAPTER_ID, chapterId);
        values.put(COLUMN_USERNAME, username);
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

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int usernameIndex = cursor.getColumnIndex(COLUMN_USERNAME);
                int contentIndex = cursor.getColumnIndex(COLUMN_CONTENT);
                if (usernameIndex != -1 && contentIndex != -1) {
                    String username = cursor.getString(usernameIndex);
                    String comment = cursor.getString(contentIndex);
                    commentList.add(username + ": " + comment);
                } else {
                    Log.e("DatabaseHelper", "Column does not exist in Cursor");
                }
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return commentList;
    }

    // Xóa comment
    public void deleteComment(int commentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_COMMENTS, COLUMN_ID + "=?", new String[]{String.valueOf(commentId)});
        db.close();
        Log.d("DatabaseHelper", "Delete comment result: " + result);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 3) {
            // Thêm cột mới hoặc thực hiện các thay đổi khác
//            db.execSQL("ALTER TABLE " + TABLE_WISHLIST + " ADD COLUMN " + COLUMN_IMAGE + " TEXT");
//            db.execSQL("ALTER TABLE " + TABLE_WISHLIST + " ADD COLUMN " + COLUMN_CHAPTER + " INTEGER");

            db.execSQL("ALTER TABLE " + TABLE_COMMENTS + " ADD COLUMN " + COLUMN_CONTENT + " TEXT");

        }
    }
}
