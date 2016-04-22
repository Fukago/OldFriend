package com.example.apple.oldfriend.model.bean;

/**
 * 新闻数据
 * Created by gan on 2016/4/21.
 */
public class NewsInfo {
    private String description;

    private String img;

    private String keywords;

    private String message;

    private String time;

    private String title;

    public String getDescription() {
        return description;
    }

    public String getImg() {
        return "http://tnfs.tngou.net/image" + img + "_180x120";
    }

    public String getKeywords() {
        return keywords;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "NewsInfo{" +
                "description='" + description + '\'' +
                ", img='" + img + '\'' +
                ", keywords='" + keywords + '\'' +
                ", message='" + message + '\'' +
                ", time=" + time +
                ", title='" + title + '\'' +
                '}';
    }
}
