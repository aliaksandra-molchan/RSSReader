package com.aliaksandramolchan.rssreader.repository;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aliaksandramolchan.rssreader.Extras;
import com.aliaksandramolchan.rssreader.db.NewsFeedDBContract;
import com.aliaksandramolchan.rssreader.db.NewsFeedDbHelper;
import com.aliaksandramolchan.rssreader.model.RSSNewsItem;
import com.aliaksandramolchan.rssreader.network.App;

import java.util.ArrayList;
import java.util.List;

public class NewsRepository {
    private int readableNewsCount = 30;
    private NewsFeedDbHelper dbHelper = App.getNewsFeedDbHelper();

    public void insert(RSSNewsItem item, String channel) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NewsFeedDBContract.NewsFeedTable.COLUMN_TITLE, item.getTitle());
        values.put(NewsFeedDBContract.NewsFeedTable.COLUMN_DATE, item.getPubDate());
        values.put(NewsFeedDBContract.NewsFeedTable.COLUMN_DESCRIPTION, item.getDescription());
        db.insert(chooseTableName(channel), null, values);
        db.close();
    }

    public List<RSSNewsItem> select(String channel) {
        List<RSSNewsItem> newsItems = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                chooseTableName(channel),
                null,
                null,
                null,
                null,
                null,
                NewsFeedDBContract.NewsFeedTable.COLUMN_DATE + " DESC"
        );
        for (int i = 0; i < readableNewsCount && cursor.moveToNext(); i++) {
            int titleIndex = cursor.getColumnIndexOrThrow(
                    NewsFeedDBContract.NewsFeedTable.COLUMN_TITLE);
            int dateIndex = cursor.getColumnIndexOrThrow(
                    NewsFeedDBContract.NewsFeedTable.COLUMN_DATE);
            int descriptionIndex = cursor.getColumnIndexOrThrow(
                    NewsFeedDBContract.NewsFeedTable.COLUMN_DESCRIPTION);
            RSSNewsItem item = new RSSNewsItem();
            item.setTitle(cursor.getString(titleIndex));
            item.setPubDate(cursor.getString(dateIndex));
            item.setDescription(cursor.getString(descriptionIndex));
            newsItems.add(item);
        }
        cursor.close();
        db.close();
        return newsItems;
    }

    public void update(RSSNewsItem item, String channel) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NewsFeedDBContract.NewsFeedTable.COLUMN_TITLE, item.getTitle());
        values.put(NewsFeedDBContract.NewsFeedTable.COLUMN_DATE, item.getPubDate());
        values.put(NewsFeedDBContract.NewsFeedTable.COLUMN_DESCRIPTION, item.getDescription());
        db.update(
                chooseTableName(channel),
                values,
                NewsFeedDBContract.NewsFeedTable._ID + " = ?",
                new String[]{String.valueOf(values.get(NewsFeedDBContract.NewsFeedTable._ID))});
        db.close();
    }

    private String chooseTableName(String channel) {
        String tableTitle = null;
        if (channel != null) {
            switch (channel) {
                case Extras.CHANNEL_LIVE_NEWS:
                    tableTitle = NewsFeedDBContract.NewsFeedTable.TABLE_NAME_LIVE_NEWS;
                    break;
                case Extras.CHANNEL_ANALYTICS:
                    tableTitle = NewsFeedDBContract.NewsFeedTable.TABLE_NAME_ANALYTICS;
                    break;
            }
        }
        return tableTitle;
    }
}
