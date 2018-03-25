package com.aliaksandramolchan.rssreader.network;


import com.aliaksandramolchan.rssreader.model.RSSNewsFeed;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpotfxbrokerApi {

    @GET("GetLiveNewsRss")
    Observable<RSSNewsFeed> getLiveNews(@Query("domain") String domain);

    @GET("GetAnalyticsRss")
    Observable<RSSNewsFeed> getAnalytics(@Query("domain") String domain);
}
