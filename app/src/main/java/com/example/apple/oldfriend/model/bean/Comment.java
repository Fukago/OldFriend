package com.example.apple.oldfriend.model.bean;

import cn.bmob.v3.BmobObject;

/**
 *
 * 评论
 * Created by gan on 2016/4/21.
 */
public class Comment extends BmobObject {
    private Article article;
    private String content;
    private User author;


    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
