package com.example.apple.oldfriend.cofing;

import com.example.apple.oldfriend.model.bean.Article;
import com.example.apple.oldfriend.model.bean.User;

import java.util.List;

/**
 * Created by gan on 2016/4/21.
 */
public interface IGetArticleAndAuthor {
    void onGetArticleAndAuthor(List<Article> articleList);

    void onGetArticleAndAuthorError(String s);

}
