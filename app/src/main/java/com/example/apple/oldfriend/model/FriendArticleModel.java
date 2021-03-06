package com.example.apple.oldfriend.model;

import android.app.IntentService;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.apple.oldfriend.cofing.IGetArticleAndAuthor;
import com.example.apple.oldfriend.cofing.ISetArticle;
import com.example.apple.oldfriend.cofing.IjudgeLike;
import com.example.apple.oldfriend.model.bean.Article;
import com.example.apple.oldfriend.model.bean.Comment;
import com.example.apple.oldfriend.model.bean.User;
import com.example.apple.oldfriend.util.TimeUtil;

import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobPointer;
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

    //得到文章和作者
    public void getArticleAndAuthor(final IGetArticleAndAuthor callback) {
        BmobQuery<Article> query = new BmobQuery<>();
        query.include("author.myOldState");
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

    //发表文章（有图）
    public void setArticleAndAuthor(String content, String picSrc, final ISetArticle callback) {
        User me = BmobUser.getCurrentUser(context, User.class);
        final Article article = new Article();
        final BmobFile bmobFile = new BmobFile(new File(picSrc));
        bmobFile.uploadblock(context, new UploadFileListener() {
            @Override
            public void onSuccess() {
                article.setArticlePic(bmobFile);
                article.update(context);

            }

            @Override
            public void onFailure(int i, String s) {
                Log.d("TAG", "上传图片失败");
            }
        });
        article.setReadTimes(1);
        article.setLikeTimes(0);
        article.setTransmitTimes(0);
        article.setTime(TimeUtil.getTime("yyyy-MM-dd"));
        article.setContent(content);
        if (me != null) {
            BmobQuery<User> query = new BmobQuery<>();
            query.addWhereEqualTo("username", me.getUsername());
            query.include("myOldState.oldPsychoState,myOldState.oldSociaState,myOldState.oldPhysioState,myNurse" +
                    ".myNurseState," +
                    "headPic");
            query.findObjects(context, new FindListener<User>() {
                @Override
                public void onSuccess(List<User> list) {
                    final User user = list.get(0);
                    article.setAuthor(user);
                    if (user.getOld() && user.getMyOldState().getName() != null) {
                        article.save(context, new SaveListener() {
                            @Override
                            public void onSuccess() {
                                callback.setArticleSuccess();
                            }

                            @Override
                            public void onFailure(int i, String s) {

                            }
                        });
                    } else {
                        Toast.makeText(context, "请确认你是老人并且填写了真实姓名", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(int i, String s) {
                    Log.d("TAG", "getUser----fail" + s);
                }
            });
        }
//        article.setAuthor(me);
//        article.save(context, new SaveListener() {
//            @Override
//            public void onSuccess() {
//                callback.setArticleSuccess();
//                Log.d("TAG", "setArticleAndAuthor -------  Success");
//            }
//
//            @Override
//            public void onFailure(int i, String s) {
//                Log.d("TAG", "setArticleAndAuthor -------  Failure:" + s);
//
//            }
//        });
    }

    //发表文章（无图）
    public void setArticleAndAuthor(String content, final ISetArticle callback) {
        User me = BmobUser.getCurrentUser(context, User.class);
        final Article article = new Article();
        article.setReadTimes(1);
        article.setLikeTimes(0);
        article.setTransmitTimes(0);
        article.setTime(TimeUtil.getTime("yyyy-MM-dd"));
        article.setContent(content);
        if (me != null) {
            BmobQuery<User> query = new BmobQuery<>();
            query.addWhereEqualTo("username", me.getUsername());
            query.include("myOldState.oldPsychoState,myOldState.oldSociaState,myOldState.oldPhysioState,myNurse" +
                    ".myNurseState," +
                    "headPic");
            query.findObjects(context, new FindListener<User>() {
                @Override
                public void onSuccess(List<User> list) {
                    User user = list.get(0);
                    article.setAuthor(user);
                    if (user.getOld() && user.getMyOldState().getName() != null) {
                        article.save(context, new SaveListener() {
                            @Override
                            public void onSuccess() {
                                callback.setArticleSuccess();
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                Log.d("TAG", "setArticleAndAuthor -------  Failure:" + s);

                            }
                        });
                    } else {
                        Toast.makeText(context, "请确认你是老人并且填写了真实姓名", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(int i, String s) {
                    Log.d("TAG", "getUser----fail" + s);
                }
            });
        }

//        article.setAuthor(me);
//        article.save(context, new SaveListener() {
//            @Override
//            public void onSuccess() {
//                callback.setArticleSuccess();
//            }
//
//            @Override
//            public void onFailure(int i, String s) {
//                Log.d("TAG", "setArticleAndAuthor -------  Failure:" + s);
//
//            }
//        });
    }


    //转发文章
    public void transmitArticle(Article article) {
        article.increment("transmitTimes");
        article.update(context);
        User me = BmobUser.getCurrentUser(context, User.class);
        Article article1 = new Article();
        article1.setAuthor(article.getAuthor());
        article1.setTransmitAuthor(me);
        article1.setLikeTimes(article.getLikeTimes());
        article1.setReadTimes(1);
        article1.setTransmitTimes(article.getTransmitTimes());
        article1.setTime(TimeUtil.getTime("yyyy-MM-dd"));
        article1.setArticlePic(article.getArticlePic());
        article1.setContent(article.getContent());
        article1.save(context, new SaveListener() {
            @Override
            public void onSuccess() {
                Log.d("TAG", "transmitArticle -------  Success");
            }

            @Override
            public void onFailure(int i, String s) {
                Log.d("TAG", "transmitArticle -------  Failure:" + s);

            }
        });

    }

    //点赞
    public void setLike(final Article article) {
        User me = BmobUser.getCurrentUser(context, User.class);
        if (me != null) {
            BmobRelation relation = new BmobRelation();
            //将当前用户添加到多对多关联中
            relation.add(article);
            me.setLikeArticle(relation);
            me.update(context, new UpdateListener() {
                @Override
                public void onSuccess() {
                    article.increment("likeTimes");
                    article.update(context);
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
    public void cancelLike(final Article article) {
        User me = BmobUser.getCurrentUser(context, User.class);
        BmobRelation relation = new BmobRelation();
        relation.remove(article);
        me.setLikeArticle(relation);
        me.update(context, new UpdateListener() {
            @Override
            public void onSuccess() {
                article.increment("likeTimes", -1);
                article.update(context);
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

    //判断赞过没有
    public void isLike(final Article article, final IjudgeLike callback) {
        User me = BmobUser.getCurrentUser(context, User.class);
        BmobQuery<Article> query = new BmobQuery<Article>();
        query.addWhereRelatedTo("likeArticle", new BmobPointer(me));
        query.findObjects(context, new FindListener<Article>() {
            @Override
            public void onSuccess(List<Article> list) {
                if (list.contains(article)) {
                    callback.isLike();
                } else {
                    callback.isNotLike();
                }
            }

            @Override
            public void onError(int i, String s) {
                Log.d("TAG", "judge isLike error -------->" + s);
            }
        });
    }


}
