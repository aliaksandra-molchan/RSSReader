package com.aliaksandramolchan.rssreader.db;


import android.provider.BaseColumns;

public final class NewsFeedDBContract {
    public static abstract class NewsFeedTable implements BaseColumns {
        public static final String TABLE_NAME_LIVE_NEWS = "LiveNewsFeed";
        public static final String TABLE_NAME_ANALYTICS = "AnalyticsFeed";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_DESCRIPTION = "description";
    }
}
