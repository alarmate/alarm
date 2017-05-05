package com.alarmate.application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by seongjinlee on 2017. 5. 3..
 */

public class DB implements DBAlarm {
    static final String DB_FILE_NAME = "ALARM_MATE_FILE";
    static final String TABLE_NAME = "ALARM_MATE";
    static final int DB_VERSION = 7;
    ///////////////////////////// TABLE INFO ////////////////////////////////
    public static final String A_ID    = "ALARM_ID";
    public static final String A_TITLE = "ALARM_TITLE";
    public static final String A_TIME = "ALARM_TIME";
    public static final String A_STATUS = "ALARM_STATUS";
    public static final String A_CONTENT = "ALARM_CONTENT";
    public static final String A_ENABLE  =  "ALARM_ENABLE";
    public static final String A_REPEAT   = "ALARM_REPEAT";
    public static final String A_RINGTONE  ="ALARM_RINGTONE";
    /////////////////////////////////////////////////////////////////////////
    MySQLiteOpenHelper helper;
    public SQLiteDatabase db;
    Context ctx;

    public DB(Context c) {
        ctx = c;
        helper = new MySQLiteOpenHelper(ctx, DB_FILE_NAME, null, DB_VERSION);
    }


    public int DBConnect() {
        db = helper.getWritableDatabase();
        return 0;
    }

    public int DBClose() {
        db.close();

        return 0;
    }

    @Override
    public int addAlarm(AlarmItem item) {

        ContentValues values = putValueInContentValue(item);
        long id = db.insert(TABLE_NAME, null, values);
        if (id < 0) {
            Log.v("tinyhhj", "### error " + TABLE_NAME + "INSERT ### ");
            return -1;
        }

        item.setId(id);
        Log.d("tinyhhj",item.showAInfo());
        return 0;
    }

    @Override
    public int updateAlarm(AlarmItem item) {
        Log.d("tinyhhj",item.showAInfo());
        ContentValues values = putValueInContentValue(item);
        db.update(TABLE_NAME, values, A_ID + " = " +item.getId() , null);

        return 0;
    }

    @Override
    public int removeAlarm(AlarmItem item)
    {
        Log.d("tinyhhj",item.showAInfo());
        db.delete(TABLE_NAME , A_ID + " = "+ item.getId() , null);
        return 0;
    }

    @Override
    public ArrayList<AlarmItem> getAlarms()
    {
        Cursor c = db.query(TABLE_NAME , null, null, null , null, null ,
                A_TIME + " asc ; ");
        //AlarmItem[] res = new AlarmItem[c.getCount() ];
        ArrayList<AlarmItem> lists = new ArrayList<AlarmItem>();
        AlarmItem res;
        int cnt = 0;
        while (c.moveToNext())
        {
            res = new AlarmItem();
            res.setId(c.getLong(c.getColumnIndex(A_ID)));
            res.setStatus(c.getInt(c.getColumnIndex(A_STATUS)));
            res.setTime(c.getLong(c.getColumnIndex(A_TIME)));
            res.setTitle(c.getString(c.getColumnIndex(A_TITLE)));
            res.setContent(c.getString(c.getColumnIndex(A_CONTENT)));
            res.setEnable(c.getString(c.getColumnIndex(A_ENABLE)));
            res.setRepeat(c.getString(c.getColumnIndex(A_REPEAT)));
            res.setRingtone(c.getString(c.getColumnIndex(A_RINGTONE)));
            lists.add(res);
            Log.d("tinyhhj",res.showAInfo());
            cnt++;
        }
        c.close();
        return lists;
    }

    private ContentValues putValueInContentValue(AlarmItem item)
    {
        ContentValues values = new ContentValues();
        values.put(A_TIME, item.getTimeinMillis());
        values.put(A_STATUS, item.getStatus());
        values.put(A_TITLE , item.getTitle());
        values.put(A_CONTENT , item.getContent());
        values.put(A_ENABLE , item.getEnable());
        values.put(A_REPEAT , item.getRepeat());
        values.put(A_RINGTONE , item.getRingtone());
        return values;
    }

    static public class MySQLiteOpenHelper extends SQLiteOpenHelper
    {
        public MySQLiteOpenHelper (Context context, String name, SQLiteDatabase.CursorFactory factory , int version )
        {
            super(context,name,factory , version);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            Log.d("tinyhhj","### SQLITEOPENHELPER ONCREATE ENTRANCE ###");
            String sql  = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME
                    + " ( "
                    + A_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                    + A_TITLE + " TEXT ,"
                    + A_TIME + " INTEGER , "
                    + A_STATUS + " INTEGER , "
                    + A_CONTENT + " TEXT ,  "
                    + A_ENABLE + " TEXT , "
                    + A_REPEAT + " TEXT , "
                    + A_RINGTONE + " TEXT "
                    +  " ); ";

            db.execSQL(sql);

        }
        @Override
        public void onUpgrade ( SQLiteDatabase db , int oldVersion , int newVersion )
        {
            Log.d("tinyhhj","### SQLITEOPENHELPER ONUPGRADE ENTRANCE ###");
            String sql = "DROP TABLE IF EXISTS "+ TABLE_NAME;
            db.execSQL(sql);

            onCreate(db);
        }
    }
}
