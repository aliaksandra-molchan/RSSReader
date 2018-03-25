package com.aliaksandramolchan.rssreader.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
        Date date = new Date();
        this.pubDate = dateFormat.format(date);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
