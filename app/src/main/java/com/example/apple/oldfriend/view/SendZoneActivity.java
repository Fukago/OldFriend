package com.example.apple.oldfriend.view;

import android.content.DialogInterface;
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

import com.example.apple.oldfriend.R;

public class SendZoneActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_send_zone;
    private ImageView im_addpicture_send_zone;
    private AlertDialog.Builder builder;
    private Button bn_tittle_toolbar_send;
    private static final String DIALOG_TITTLE = "是否退出";
    private static final String DIALOG_MESSGAE = "未保存编辑内容,确认退出?";
    private static final String DIALOG_OK = "确认";
    private static final String DIALOG_CANCEL = "取消";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_zone);
        initToolbar();
        initDialog();
        initView();
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
        et_send_zone.addTextChangedListener(et_watcher);
        im_addpicture_send_zone = (ImageView) findViewById(R.id.im_addpicture_send_zone);

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            TextView tv_tittle_toolbar_cancel = (TextView) findViewById(R.id.tv_tittle_toolbar_cancel);
            tv_tittle_toolbar_cancel.setOnClickListener(this);
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
            if (!TextUtils.isEmpty(et_send_zone.getText())) {
                Log.d("clickable", "" + true);
                bn_tittle_toolbar_send.setClickable(true);
                bn_tittle_toolbar_send.setTextColor(R.color.colorWhite_ffffff);
            } else {
                Log.d("clickable", "" + false);
                bn_tittle_toolbar_send.setClickable(false);
                bn_tittle_toolbar_send.setTextColor(R.color.colorGray_a9b7b7);
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_addpicture_send_zone: {
                Toast.makeText(SendZoneActivity.this, "send", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.tv_tittle_toolbar_cancel: {
                builder.show();
                break;
            }
            case R.id.bn_tittle_toolbar_send: {

                break;
            }
        }
    }
}