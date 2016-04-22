package com.example.apple.oldfriend.presenter;

import android.content.Context;

import com.example.apple.oldfriend.model.FriendArticleModel;
import com.example.apple.oldfriend.model.IGetArticleAndAuthor;
import com.example.apple.oldfriend.model.bean.Article;
import com.example.apple.oldfriend.model.bean.User;

import cn.bmob.v3.BmobUser;

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
     * 传入当前文章来发布文章
     * content:文章内容
     * picSrc:图片的本地地址
     * article.getArticlePic().loadImage();加载图片
     */
    public void setArticleAndAuthor(String content, String picSrc) {
        model.setArticleAndAuthor(content, picSrc);
    }

    /**
     * 点赞
     */
    public void setGood(Article article) {
        model.setLike(article);
    }

    /**
     * 取消赞
     */
    public void cancelLike(Article article) {
        model.cancelLike(article);
    }

    /**
     * 设置评论
     */
    public void setComment(Article article, String content) {
        model.setComment(article, content);
    }

    /**
     * 转发文章
     */
    public void transmitArticle(Article article) {
        model.transmitArticle(article);
    }
}
