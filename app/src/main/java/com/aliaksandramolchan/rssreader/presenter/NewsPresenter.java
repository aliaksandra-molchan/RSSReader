package com.aliaksandramolchan.rssreader.presenter;


import com.aliaksandramolchan.rssreader.NewsContract;
import com.aliaksandramolchan.rssreader.handler.NewsHandler;

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
        switch (fragmentIndex) {
            case 0:
                newsView.update(handler.fetchLiveNews(), handler.getQueryTime());
                break;
            case 1:
                newsView.update(handler.fetchAnalytics(), handler.getQueryTime());
                break;
        }
    }

    @Override
    public void onDestroy() {
        handler.reset();
    }
}
