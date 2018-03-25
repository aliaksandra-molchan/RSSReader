package com.aliaksandramolchan.rssreader;


import com.aliaksandramolchan.rssreader.model.RSSNewsItem;

import java.util.List;

public interface NewsContract {
    interface NewsView {
        void update(List<RSSNewsItem> items, String time);
    }

    interface NewsPresenter {
        void onCreate();

        void onDestroy();
    }
}
