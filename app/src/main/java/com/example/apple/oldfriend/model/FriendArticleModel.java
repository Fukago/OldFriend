package com.example.apple.oldfriend.model;

import android.content.Context;
import android.text.Html;
import android.util.Log;

import com.example.apple.oldfriend.model.bean.Article;
import com.example.apple.oldfriend.model.bean.Comment;
import com.example.apple.oldfriend.model.bean.User;
import com.example.apple.oldfriend.util.TimeUtil;

import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by gan on 2016/4/21.
 */
public class FriendArticleModel {
    private Context context;

    public FriendArticleModel(Context context) {
        this.context = context;
    }

    //得到文章和坐着
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

    //发表文章
    public void setArticleAndAuthor(String content, String picSrc) {
        User me = BmobUser.getCurrentUser(context, User.class);
        final Article article = new Article();
        final BmobFile bmobFile = new BmobFile(new File(picSrc));
        bmobFile.uploadblock(context, new UploadFileListener() {
            @Override
            public void onSuccess() {
                article.setArticlePic(bmobFile);
                Log.d("TAG", "上传图片成功");
            }

            @Override
            public void onFailure(int i, String s) {
                Log.d("TAG", "上传图片失败");
            }
        });
        article.setReadTimes(1);
        article.setTime(TimeUtil.getTime("yyyy-MM-dd"));
        article.setContent(content);
        article.setAuthor(me);
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


    //转发文章
    public void transmitArticle(Article article) {
        User me = BmobUser.getCurrentUser(context, User.class);
        article.setTransmitAuthor(me);
        article.save(context, new SaveListener() {
            @Override
            public void onSuccess() {
                Log.d("TAG", "transmitArticle ------- Success");

            }

            @Override
            public void onFailure(int i, String s) {
                Log.d("TAG", "transmitArticle -------  Failure:" + s);

            }
        });

    }

    //点赞
    public void setLike(Article article) {
        User me = BmobUser.getCurrentUser(context, User.class);
        if (me != null) {
            BmobRelation relation = new BmobRelation();
            //将当前用户添加到多对多关联中
            relation.add(article);
            me.setLikeArticle(relation);
            me.update(context, new UpdateListener() {
                @Override
                public void onSuccess() {
                    Log.d("TAG", "点赞成功");
                }

                @Override
                public void onFailure(int i, String s) {
                    Log.d("TAG", "点赞失败" + s);

                }
            });
        }
    }

    //取消赞
    public void cancelLike(Article article) {
        User me = BmobUser.getCurrentUser(context, User.class);
        BmobRelation relation = new BmobRelation();
        relation.remove(article);
        me.setLikeArticle(relation);
        me.update(context, new UpdateListener() {
            @Override
            public void onSuccess() {
                Log.d("TAG", "取消赞成功");
            }

            @Override
            public void onFailure(int i, String s) {
                Log.d("TAG", "取消赞失败");
            }
        });
    }

    //设置评论
    public void setComment(Article article, String content) {
        User me = BmobUser.getCurrentUser(context, User.class);
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setArticle(article);
        comment.setAuthor(me);
        comment.save(context, new SaveListener() {
            @Override
            public void onSuccess() {
                Log.d("TAG", "评论成功");
            }

            @Override
            public void onFailure(int i, String s) {
                Log.d("TAG", "评论失败");
            }
        });
    }


}
