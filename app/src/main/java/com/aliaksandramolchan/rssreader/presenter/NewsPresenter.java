package com.aliaksandramolchan.rssreader.presenter;


import com.aliaksandramolchan.rssreader.NewsContract;
import com.aliaksandramolchan.rssreader.handler.NewsHandler;
import com.aliaksandramolchan.rssreader.model.RSSNewsItem;

import java.util.ArrayList;
import java.util.List;

public class NewsPresenter implements NewsContract.NewsPresenter {
    private int fragmentIndex;
    private NewsContract.NewsView newsView;

    private NewsHandler handler;

    public NewsPresenter(NewsContract.NewsView newsView, boolean isNetworkConnected,
                         int fragmentIndex) {
        this.newsView = newsView;
        this.fragmentIndex = fragmentIndex;
        handler = new NewsHandler(isNetworkConnected);
    }

    @Override
    public void onCreate() {
        List<RSSNewsItem> liveNewsFeed = handler.fetchLiveNews();
        List<RSSNewsItem> analyticsFeed = handler.fetchAnalytics();
        switch (fragmentIndex) {
            case 0:
                newsView.update(liveNewsFeed, handler.getQueryTime());
                break;
            case 1:
                newsView.update(analyticsFeed, handler.getQueryTime());
                break;
        }
    }

    @Override
    public void onDestroy() {
        handler.reset();
    }
}
