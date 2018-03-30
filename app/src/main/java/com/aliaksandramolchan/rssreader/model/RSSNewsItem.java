package com.aliaksandramolchan.rssreader.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class RSSNewsItem {
    @Element(name = "title")
    private String title;

    @Element(name = "pubDate")
    private String pubDate;

    @Element(name = "description")
    private String description;

    public String getPubDate() {
        return pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
