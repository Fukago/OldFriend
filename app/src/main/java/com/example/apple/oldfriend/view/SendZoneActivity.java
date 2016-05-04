package com.example.apple.oldfriend.view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.apple.oldfriend.R;
import com.example.apple.oldfriend.cofing.ISetArticle;
import com.example.apple.oldfriend.presenter.FriendArticlePresenter;
import com.jude.library.imageprovider.ImageProvider;
import com.jude.library.imageprovider.OnImageSelectListener;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;

public class SendZoneActivity extends AppCompatActivity implements View.OnClickListener, OnImageSelectListener {
    private EditText et_send_zone;
    private ImageView im_addpicture_send_zone;
    private AlertDialog.Builder builder;
    private Button bn_tittle_toolbar_send;
    private static final String DIALOG_TITTLE = "是否退出";
    private static final String DIALOG_MESSGAE = "未发送编辑内容,确认退出?";
    private static final String DIALOG_OK = "确认";
    private static final String DIALOG_CANCEL = "取消";
    private FriendArticlePresenter presenter;
    private MaterialDialog dialog;
    private ImageProvider provider;
    private Bitmap bitmap;
    private String im_uri = null;
    private boolean isSend = false;
    public ProgressDialog p_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_zone);
        provider = new ImageProvider(this);
        initPresenter();
        initToolbar();
        initDialog();
        initView();
    }

    private void initPresenter() {
        presenter = new FriendArticlePresenter(this);
    }

    private void initDialog() {
        builder = new AlertDialog.Builder(SendZoneActivity.this);
        builder.setTitle(DIALOG_TITTLE);
        builder.setMessage(DIALOG_MESSGAE);
        builder.setPositiveButton(DIALOG_OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                finish();
            }
        }).setNegativeButton(DIALOG_CANCEL, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        }).create();

    }

    private void initView() {
        et_send_zone = (EditText) findViewById(R.id.et_send_zone);
        if (et_send_zone != null) {
            et_send_zone.addTextChangedListener(et_watcher);
        }
        im_addpicture_send_zone = (ImageView) findViewById(R.id.im_addpicture_send_zone);
        if (im_addpicture_send_zone != null) {
            im_addpicture_send_zone.setOnClickListener(this);
        }
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            TextView tv_tittle_toolbar_cancel = (TextView) findViewById(R.id.tv_tittle_toolbar_cancel);
            if (tv_tittle_toolbar_cancel != null) {
                tv_tittle_toolbar_cancel.setOnClickListener(this);
            }
            bn_tittle_toolbar_send = (Button) toolbar.findViewById(R.id.bn_tittle_toolbar_send);
            bn_tittle_toolbar_send.setOnClickListener(this);
        }
    }

    TextWatcher et_watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
           /* if (!TextUtils.isEmpty(et_send_zone.getText())) {
                Log.d("clickable", "" + true);
                bn_tittle_toolbar_send.setClickable(true);
                bn_tittle_toolbar_send.setTextColor(R.color.colorWhite_ffffff);
            } else {
                Log.d("clickable", "" + false);
                bn_tittle_toolbar_send.setClickable(false);
                bn_tittle_toolbar_send.setTextColor(R.color.colorGray_a9b7b7);
            }*/
            if (!TextUtils.isEmpty(s)) {
                isSend = false;
            }

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_addpicture_send_zone: {
                Toast.makeText(SendZoneActivity.this, "send", Toast.LENGTH_SHORT).show();
                new MaterialDialog.Builder(SendZoneActivity.this)
                        .title("选择图片来源")
                        .items(new String[]{"相机", "相册", "网络"})
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                switch (which) {
                                    case 0: {
                                        provider.getImageFromCamera(SendZoneActivity.this);
                                        break;
                                    }
                                    case 1: {
                                        provider.getImageFromAlbum(SendZoneActivity.this);
                                        break;
                                    }
                                    case 2: {
                                        provider.getImageFromNet(SendZoneActivity.this);
                                        break;
                                    }
                                }

                            }
                        }).show();
                break;
            }
            case R.id.tv_tittle_toolbar_cancel: {
                if (!isSend) {
                    builder.show();
                } else {
                    finish();
                }
                break;
            }
            case R.id.bn_tittle_toolbar_send: {
                p_dialog = ProgressDialog
                        .show(SendZoneActivity.this,
                                null,
                                "正在为您登录...",
                                true);
                if (TextUtils.isEmpty(im_uri)) {
                    presenter.setArticleAndAuthor(et_send_zone.getText().toString(), new ISetArticle() {
                        @Override
                        public void setArticleSuccess() {
                            p_dialog.cancel();
                        }
                    });
                } else {
                    presenter.setArticleAndAuthor(et_send_zone.getText().toString(), im_uri, new ISetArticle() {
                        @Override
                        public void setArticleSuccess() {
                            p_dialog.cancel();
                        }
                    });
                }
                isSend = true;
                finish();
                break;
            }
        }
    }

    @Override
    public void onImageSelect() {
        Log.d("changeImage", "" + "onImageSelect---");
        dialog = new MaterialDialog.Builder(SendZoneActivity.this)
                .progress(true, 100)
                .title("加载中")
                .content("请稍候")
                .cancelable(false)
                .show();
    }

    @Override
    public void onImageLoaded(Uri uri) {
        dialog.dismiss();
        addImage(uri);
        Log.d("changeImage", "" + "onImageLoaded---");
        provider.corpImage(uri, 272, 192, new OnImageSelectListener() {
            @Override
            public void onImageSelect() {

            }

            @Override
            public void onImageLoaded(Uri uric) {
                Log.d("changeImage", "" + uric);
                addImage(uric);
            }

            @Override
            public void onError() {
                Toast.makeText(SendZoneActivity.this, "添加图片失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onError() {
        dialog.dismiss();
        Toast.makeText(SendZoneActivity.this, "添加图片失败", Toast.LENGTH_SHORT).show();
    }


    public void addImage(Uri uri) {
        im_uri = uri.toString();
        im_uri = im_uri.substring(7);
        Log.d("changeImage", "" + uri);
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (bitmap != null) {
            Picasso.with(SendZoneActivity.this)
                    .load(uri)
                    .resize(98, 64)
                    .centerCrop()
                    .error(R.drawable.user_ic_face)
                    .into(im_addpicture_send_zone);
            im_addpicture_send_zone.setClickable(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        provider.onActivityResult(requestCode, resultCode, data);
    }
}
