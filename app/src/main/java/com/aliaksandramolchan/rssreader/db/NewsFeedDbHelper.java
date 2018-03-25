package com.aliaksandramolchan.rssreader.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NewsFeedDbHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "NewsFeed.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA = ", ";

    private static final String CREATE_NEWS_TABLE = " (" +
            NewsFeedDBContract.NewsFeedTable._ID + " INTEGER PRIMARY KEY" + COMMA +
            NewsFeedDBContract.NewsFeedTable.COLUMN_TITLE + TEXT_TYPE + COMMA +
            NewsFeedDBContract.NewsFeedTable.COLUMN_DATE + TEXT_TYPE + COMMA +
            NewsFeedDBContract.NewsFeedTable.COLUMN_DESCRIPTION + TEXT_TYPE +
            " )";

    private static final String CREATE_LIVE_NEWS_TABLE =
            "CREATE TABLE " +
                    NewsFeedDBContract.NewsFeedTable.TABLE_NAME_LIVE_NEWS + CREATE_NEWS_TABLE;
    private static final String CREATE_ANALYTICS_TABLE =
            "CREATE TABLE "
                    + NewsFeedDBContract.NewsFeedTable.TABLE_NAME_ANALYTICS + CREATE_NEWS_TABLE;

    public NewsFeedDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LIVE_NEWS_TABLE);
        db.execSQL(CREATE_ANALYTICS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
