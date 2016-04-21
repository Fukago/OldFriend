package com.example.apple.oldfriend.model;

import android.content.Context;
import android.util.Log;

import com.example.apple.oldfriend.model.bean.Article;
import com.example.apple.oldfriend.model.bean.User;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by gan on 2016/4/21.
 */
public class FriendArticleModel {
    private Context context;

    public FriendArticleModel(Context context) {
        this.context = context;
    }

    public void getArticleAndAuthor(final IGetArticleAndAuthor callback) {
        BmobQuery<Article> query = new BmobQuery<>();
        query.include("author");
        query.findObjects(context, new FindListener<Article>() {
            @Override
            public void onSuccess(List<Article> list) {
                callback.onGetArticleAndAuthor(list);
            }

            @Override
            public void onError(int i, String s) {
                callback.onGetArticleAndAuthorError(s);
            }
        });

    }

    public void setArticleAndAuthor(Article article, User author) {
        article.setAuthor(author);
        article.save(context, new SaveListener() {
            @Override
            public void onSuccess() {
                Log.d("TAG", "setArticleAndAuthor -------  Success");
            }

            @Override
            public void onFailure(int i, String s) {
                Log.d("TAG", "setArticleAndAuthor -------  Failure:" + s);

            }
        });
    }

}
