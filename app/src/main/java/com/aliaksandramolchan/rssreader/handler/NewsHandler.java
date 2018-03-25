package com.aliaksandramolchan.rssreader.handler;


import com.aliaksandramolchan.rssreader.Extras;
import com.aliaksandramolchan.rssreader.model.RSSNewsFeed;
import com.aliaksandramolchan.rssreader.repository.NewsRepository;
import com.aliaksandramolchan.rssreader.model.RSSNewsItem;
import com.aliaksandramolchan.rssreader.network.App;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsHandler {
    private static final String DOMAIN = "fxopen.com";
    private boolean isNetworkConnected;
    private int newsCount = 100;
    private String queryTime;
    private Disposable disposable;
    private NewsRepository repository;

    public NewsHandler(boolean isNetworkConnected) {
        this.isNetworkConnected = isNetworkConnected;
        repository = new NewsRepository();
    }

    public List<RSSNewsItem> fetchLiveNews() {
        fetchNews(App.getApi().getLiveNews(DOMAIN));
        return repository.select(Extras.CHANNEL_LIVE_NEWS);
    }

    public List<RSSNewsItem> fetchAnalytics() {
        fetchNews(App.getApi().getAnalytics(DOMAIN));
        return repository.select(Extras.CHANNEL_ANALYTICS);
    }

    private void fetchNews(Observable<RSSNewsFeed> call) {
        if (isNetworkConnected) {
            disposable = call
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(rssNewsFeed -> {
                                List<RSSNewsItem> items = rssNewsFeed.getItemList();
                                if (!items.isEmpty()) {
                                    if (newsCount > items.size()) {
                                        newsCount = items.size();
                                    }
                                    String channel = rssNewsFeed.getChannelTitle();
                                    if (repository.select(channel).isEmpty()) {
                                        for (int i = 0; i < newsCount; i++) {
                                            repository.insert(items.get(i), channel);
                                        }
                                    } else {
                                        for (int i = 0; i < newsCount; i++) {
                                            repository.update(items.get(i), channel);
                                        }
                                    }
                                }
                            },
                            Throwable::printStackTrace);
        }
        queryTime = App.getTimeRepository().getCurrentTime();
    }

    public void reset() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    public String getQueryTime() {
        return queryTime;
    }
}
