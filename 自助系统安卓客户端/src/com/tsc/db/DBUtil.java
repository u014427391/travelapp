
package com.tsc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBUtil {
    private static DBUtil mInstance;
    private SQLiteHelper mSQLHelp;
    private SQLiteDatabase mSQLiteDatabase;

    private DBUtil(Context context) {
        mSQLHelp = new SQLiteHelper(context);
        mSQLiteDatabase = mSQLHelp.getWritableDatabase();
    }

    public static DBUtil getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DBUtil(context);
        }
        return mInstance;
    }

    /**
     */
    public void close() {
        mSQLHelp.close();
        mSQLHelp = null;
        mSQLiteDatabase.close();
        mSQLiteDatabase = null;
        mInstance = null;
    }

    /**
     */
    public void insertData(ContentValues values) {
        mSQLiteDatabase.insert(SQLiteHelper.TABLE_CHANNEL, null, values);
    }

    /**
     * 
     * @param values
     * @param whereClause
     * @param whereArgs
     */
    public void updateData(ContentValues values, String whereClause,
            String[] whereArgs) {
        mSQLiteDatabase.update(SQLiteHelper.TABLE_CHANNEL, values, whereClause,
                whereArgs);
    }

    /**
     * 
     * @param whereClause
     * @param whereArgs
     */
    public void deleteData(String whereClause, String[] whereArgs) {
        mSQLiteDatabase
                .delete(SQLiteHelper.TABLE_CHANNEL, whereClause, whereArgs);
    }

    /**
     * 
     * @param columns
     * @param selection
     * @param selectionArgs
     * @param groupBy
     * @param having
     * @param orderBy
     * @return
     */
    public Cursor selectData(String[] columns, String selection,
            String[] selectionArgs, String groupBy, String having,
            String orderBy) {
        Cursor cursor = mSQLiteDatabase.query(SQLiteHelper.TABLE_CHANNEL, columns, selection,
                selectionArgs, groupBy, having, orderBy);
        return cursor;
    }
}
