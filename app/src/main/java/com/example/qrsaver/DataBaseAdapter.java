package com.example.qrsaver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseAdapter {
    // table에 들어갈 컬럼값
    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DATA = "data";
    public static final String KEY_DATE = "date";

    // DataBaseHelper와 SQLDatabase객체 정의(SQLiteDatabase : 추가, 삭제, 쿼리, 수정과 관련)
    private DataBaseHelper mDBHelper;
    private SQLiteDatabase mDB;

    // Create Table 정의
    private static final String DB_CREATE = "create table codes (id integer primary key autoincrement,"
            + "title text not null, data text not null, date text not null DEFAULT (datetime('now', 'localtime')));";

    // SQLiteOpenHelper Values
    private static final String DATABASE_NAME = "codes.db";
    private static final String DATABASE_TABLE = "codes";
    private static final int DATABASE_VERSION = 1;

    private final Context mContext;

    // SQLiteOpenHelper 정의(SQLiteOpenHelper : 생성, 열기, 업데이트와 관련)
    private class DataBaseHelper extends SQLiteOpenHelper {

        public DataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        // table 생성
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
        }

        // db업데이트
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public DataBaseAdapter(Context context) {
        mContext = context;
    }

    // DB열기
    public DataBaseAdapter open() {
        mDBHelper = new DataBaseHelper(mContext);
        try {
            mDB = mDBHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            mDB = mDBHelper.getReadableDatabase();
        }
        return this;
    }

    // DB 닫기
    public void close() {
        mDBHelper.close();
    }

    // 모든 필드값 삭제
    public void initialTable() {
        mDB.execSQL("DELETE FROM " + DATABASE_TABLE+";");
    }

    // 필드값 insert
    public long insertCode(String title, String data) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_DATA, data);

        return mDB.insert(DATABASE_TABLE, null, initialValues);
    }

    // 지우고싶은 로우값 지우기
    public boolean deleteCode(long rowID) {
        return mDB.delete(DATABASE_TABLE, KEY_ID + "=" + rowID, null) > 0;
    }

    // 모든 필드값 받아오기
    public Cursor fetchAllCode() {
        return mDB.rawQuery("SELECT * FROM "+ DATABASE_TABLE, null);
    }

    // 원하는 로우값 받아오기
    public Cursor fetchCode(long rowID) {
        Cursor cursor = mDB.query(DATABASE_TABLE, new String[] { KEY_ID,
                        KEY_TITLE, KEY_DATA, KEY_DATE },
                KEY_ID + "=" + rowID, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return cursor;
    }

    // 필드값 Update
    public boolean updateCode(long rowID, String title) {
        ContentValues args = new ContentValues();
        args.put(KEY_TITLE, title);

        return mDB.update(DATABASE_TABLE, args, KEY_ID + "=" + rowID, null) > 0;
    }
}