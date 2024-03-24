package com.sribse.utappsinfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Map;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "usage_stats.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usage_stats (_id INTEGER PRIMARY KEY AUTOINCREMENT, package_name TEXT UNIQUE, usage_count INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not needed for this example
    }

    public static void storeUsageStatsInDatabase(Context context, Map<String, Integer> usageCounts) {
        SQLiteHelper dbHelper = new SQLiteHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        for (Map.Entry<String, Integer> entry : usageCounts.entrySet()) {
            String packageName = entry.getKey();
            int usageCount = entry.getValue();

            ContentValues values = new ContentValues();
            values.put("package_name", packageName);
            values.put("usage_count", usageCount);

            db.insertWithOnConflict("usage_stats", null, values, SQLiteDatabase.CONFLICT_REPLACE);
        }
        db.close();
    }
}

