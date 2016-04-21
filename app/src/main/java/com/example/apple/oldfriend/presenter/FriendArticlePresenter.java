package com.example.apple.oldfriend.presenter;

import android.content.Context;

import com.example.apple.oldfriend.model.FriendArticleModel;
import com.example.apple.oldfriend.model.IGetArticleAndAuthor;
import com.example.apple.oldfriend.model.bean.Article;
import com.example.apple.oldfriend.model.bean.User;

/**
 * Created by gan on 2016/4/21.
 */
public class FriendArticlePresenter {
    private FriendArticleModel model;

    public FriendArticlePresenter(Context context) {
        model = new FriendArticleModel(context);
    }

    /**
     * 拿到老友圈的全部文章和作者
     * 通过article.getAuthor拿到作者
     */
    public void getArticleAndAuthor(IGetArticleAndAuthor callback) {
        model.getArticleAndAuthor(callback);
    }

    /**
     * 传入当前文章和文章的作者来发布文章
     */
    public void setArticleAndAuthor(Article article, User author) {
        model.setArticleAndAuthor(article, author);
    }

    /**
     * 点赞
     */
    public void setGood(Article article){

    }

}
