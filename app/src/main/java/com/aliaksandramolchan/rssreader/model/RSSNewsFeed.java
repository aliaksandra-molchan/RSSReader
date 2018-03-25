package com.aliaksandramolchan.rssreader.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "rss", strict = false)
public class RSSNewsFeed {
    @Element(name = "title")
    @Path("channel")
    private String channelTitle;

    @ElementList(name = "item", inline = true)
    @Path("channel")
    private List<RSSNewsItem> itemList;

    public String getChannelTitle() {
        return channelTitle;
    }

    public List<RSSNewsItem> getItemList() {
        return itemList;
    }

}
