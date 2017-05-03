package com.alarmate.application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by seongjinlee on 2017. 5. 3..
 */

public class DB implements DBAlarm {
    static final String DB_FILE_NAME = "ALARM_MATE_FILE";
    static final String TABLE_NAME = "ALARM_MATE";
    static final int DB_VERSION = 2;
    ///////////////////////////// TABLE INFO ////////////////////////////////
    public static final String A_ID    = "ALARM_ID";
    public static final String A_TIME = "ALARM_TIME";
    public static final String A_STATUS = "ALARM_STATUS";
    /////////////////////////////////////////////////////////////////////////
    MySQLiteOpenHelper helper;
    SQLiteDatabase db;
    Context ctx;

    public DB(Context c) {
        ctx = c;
    }


    public int DBConnect() {
        helper = new MySQLiteOpenHelper(ctx, DB_FILE_NAME, null, DB_VERSION);
        db = helper.getWritableDatabase();
        return 0;
    }

    public int DBClose() {
        helper.close();
        return 0;
    }

    @Override
    public int addAlarm(AlarmItem item) {
        Log.d("tinyhhj",item.showAInfo());
        ContentValues values = putValueInContentValue(item);
        long id = db.insert(TABLE_NAME, null, values);
        if (id < 0) {
            Log.v("tinyhhj", "### error " + TABLE_NAME + "INSERT ### ");
            return -1;
        }
        item.setId(id);
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
    public AlarmItem[] getAlarms()
    {
        Cursor c = db.query(TABLE_NAME , null, null, null , null, null ,
                A_TIME + " asc ; ");
        AlarmItem[] res = new AlarmItem[c.getCount() ];
        int cnt = 0;
        while (c.moveToNext())
        {
            res[cnt] = new AlarmItem();
            res[cnt].setId(c.getLong(c.getColumnIndex(A_ID)));
            res[cnt].setStatus(c.getInt(c.getColumnIndex(A_STATUS)));
            res[cnt].setTime(c.getLong(c.getColumnIndex(A_TIME)));
            cnt++;
        }
        return res;
    }

    private ContentValues putValueInContentValue(AlarmItem item)
    {
        ContentValues values = new ContentValues();
        values.put(A_TIME, item.getTimeinMillis());
        values.put(A_STATUS, item.getStatus());
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

            String sql  = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME
                    + " ( "
                    + A_ID + " integer primary key autoincrement, "
                    + A_TIME + " integer , "
                    + A_STATUS + " integer ); ";

            db.execSQL(sql);

        }
        @Override
        public void onUpgrade ( SQLiteDatabase db , int oldVersion , int newVersion )
        {
            String sql = "drop table if exists "+ TABLE_NAME;
            db.execSQL(sql);

            onCreate(db);
        }
    }
}
