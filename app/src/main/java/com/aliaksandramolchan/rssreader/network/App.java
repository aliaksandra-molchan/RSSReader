package com.aliaksandramolchan.rssreader.network;


import android.app.Application;

import com.aliaksandramolchan.rssreader.repository.TimeRepository;
import com.aliaksandramolchan.rssreader.db.NewsFeedDbHelper;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class App extends Application {
    private static final String ENDPOINT = "https://widgets.spotfxbroker.com:8088/";

    private static SpotfxbrokerApi api;
    private static NewsFeedDbHelper dbHelper;
    private static TimeRepository timeRepository;

    public static SpotfxbrokerApi getApi() {
        return api;
    }

    public static NewsFeedDbHelper getNewsFeedDbHelper() {
        return dbHelper;
    }

    public static TimeRepository getTimeRepository() {
        return timeRepository;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create());
        Retrofit retrofit = retrofitBuilder.client(httpClient.build()).build();
        api = retrofit.create(SpotfxbrokerApi.class);
        dbHelper = new NewsFeedDbHelper(this);
        timeRepository = new TimeRepository();
    }

}
