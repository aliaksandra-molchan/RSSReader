package com.aliaksandramolchan.rssreader.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aliaksandramolchan.rssreader.R;
import com.aliaksandramolchan.rssreader.model.RSSNewsItem;

import java.util.ArrayList;
import java.util.List;


public class NewsGridAdater extends BaseAdapter {
    private List<RSSNewsItem> newsFeed = new ArrayList<>();

    private Context context;

    public NewsGridAdater(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return newsFeed.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.table_item, parent, false);
        }

        RSSNewsItem news = newsFeed.get(position);

        TextView title = (TextView) convertView.findViewById(R.id.newsTitle);
        TextView date = (TextView) convertView.findViewById(R.id.newsDate);
        title.setText(news.getTitle());
        date.setText(news.getPubDate());

        WebView webView = (WebView) convertView.findViewById(R.id.webView);
        webView.loadData(news.getDescription(), "text/html", "en_US");

        return convertView;
    }

    public void setNewsFeed(List<RSSNewsItem> newsFeed) {
        this.newsFeed = newsFeed;
    }
}
