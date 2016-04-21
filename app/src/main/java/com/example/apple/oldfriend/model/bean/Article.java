package com.example.apple.oldfriend.model.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by gan on 2016/4/21.
 */
public class Article extends BmobObject {
    private String time;
    private String content;
    private Integer readTimes;
    private BmobFile articlePic;
    private User author;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReadTimes() {
        return readTimes;
    }

    public void setReadTimes(Integer readTimes) {
        this.readTimes = readTimes;
    }

    public BmobFile getArticlePic() {
        return articlePic;
    }

    public void setArticlePic(BmobFile articlePic) {
        this.articlePic = articlePic;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
