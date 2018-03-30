package com.aliaksandramolchan.rssreader.view;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.aliaksandramolchan.rssreader.Extras;
import com.aliaksandramolchan.rssreader.NewsContract;
import com.aliaksandramolchan.rssreader.R;
import com.aliaksandramolchan.rssreader.model.RSSNewsItem;
import com.aliaksandramolchan.rssreader.network.App;
import com.aliaksandramolchan.rssreader.presenter.NewsPresenter;

import java.util.List;

public class NewsFragment extends Fragment implements NewsContract.NewsView {
    private NewsContract.NewsPresenter presenter;
    private NewsGridAdater adapter;
    private View view;
    private GridView gridView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        int fragmentIndex = 0;
        if (bundle != null) {
            fragmentIndex = bundle.getInt(Extras.KEY_FRAGMENT);
        }
        adapter = new NewsGridAdater(this.getContext());
        presenter = new NewsPresenter(this, checkNetworkConnection(), fragmentIndex);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gridView = (GridView) view.findViewById(R.id.grid_view);
        presenter.onCreate();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    private boolean checkNetworkConnection() {
        ConnectivityManager cm = (ConnectivityManager) this
                .getActivity()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }


    @Override
    public void update(List<RSSNewsItem> items, String time) {
        App.getTimeRepository().storeTime(this.getContext(), time);
        adapter = new NewsGridAdater(this.getContext());
        adapter.setNewsFeed(items);
        gridView.setAdapter(adapter);
    }
}
