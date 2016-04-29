package com.example.apple.oldfriend.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextClock;
import android.widget.Toast;

import com.example.apple.oldfriend.R;
import com.example.apple.oldfriend.cofing.IGetBothMessage;
import com.example.apple.oldfriend.cofing.IRegist;
import com.example.apple.oldfriend.model.bean.User;
import com.example.apple.oldfriend.presenter.BothMessagePresenter;
import com.example.apple.oldfriend.presenter.OldManagePresenter;
import com.example.apple.oldfriend.presenter.UserManagePresenter;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements IRegist, IGetBothMessage {
    private Button mBtBack;
    private Button mBtSendIdentify;
    private Button mBtConfirm;
    private RadioButton mRbOld;
    private EditText mEtPhoneNum;
    private EditText mEtPassword;
    private EditText mEtIdentify;
    private UserManagePresenter userManagePresenter;
    private BothMessagePresenter bothMessagePresenter;
    private OldManagePresenter oldManagePresenter;
    private RadioGroup radioGroup;
    private Boolean isOld = null;
    private User nurse = null;
    boolean[] checkedItems = new boolean[]{false, false, false, false, false, false};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userManagePresenter = new UserManagePresenter(RegisterActivity.this);
        bothMessagePresenter = new BothMessagePresenter(RegisterActivity.this);
        oldManagePresenter = new OldManagePresenter(RegisterActivity.this);
        initView();
        initListener();
    }

    private void initListener() {
        mBtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBtSendIdentify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum = mEtPhoneNum.getText().toString();
                if (TextUtils.isEmpty(phoneNum)) {
                    Toast.makeText(RegisterActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                } else {
                    userManagePresenter.getSMS(phoneNum);
                }
            }
        });
        mBtConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOld == null) {
                    Toast.makeText(RegisterActivity.this, "请选择老人或护理人员", Toast.LENGTH_SHORT).show();
                } else if (isOld && nurse == null) {
                    Toast.makeText(RegisterActivity.this, "请选择你的护理人员", Toast.LENGTH_SHORT).show();
                } else {
                    String phoneNum = mEtPhoneNum.getText().toString();
                    String password = mEtPassword.getText().toString();
                    String identify = mEtIdentify.getText().toString();
                    if (TextUtils.isEmpty(phoneNum) || TextUtils.isEmpty(password) || TextUtils.isEmpty(identify)) {
                        Toast.makeText(RegisterActivity.this, "不能有空项", Toast.LENGTH_SHORT).show();
                    } else {
                        userManagePresenter.register("18902679166", "159753",
                                identify, isOld, nurse, RegisterActivity.this);
                        finish();
                    }
                }
            }


        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == mRbOld.getId()) {
                    bothMessagePresenter.getAllNurseMessage(RegisterActivity.this);
                    isOld = true;
                } else {
                    isOld = false;
                    nurse = null;
                }
            }
        });

    }

    private void initView() {
        setContentView(R.layout.activity_register);
        mBtBack = (Button) findViewById(R.id.bt_back);
        mBtSendIdentify = (Button) findViewById(R.id.bt_getidentify);
        mEtPhoneNum = (EditText) findViewById(R.id.et_phonenum);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mEtIdentify = (EditText) findViewById(R.id.et_identify);
        mBtConfirm = (Button) findViewById(R.id.bt_confirmrig);
        mRbOld = (RadioButton) findViewById(R.id.older_user);
        radioGroup = (RadioGroup) findViewById(R.id.rg_radiogroup);
    }

    @Override
    public void registSuccess() {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean("isOld", isOld);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public void getNurseMessage(User nurse) {

    }

    @Override
    public void getAllNurseMessage(final List<User> allNurseList) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (User nurse : allNurseList) {
            arrayList.add(nurse.getMyNurseState().getName());
        }
        final String[] itemStrings = arrayList.toArray(new String[arrayList.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        builder.setTitle("请选择照顾你的护理人员");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setSingleChoiceItems(itemStrings, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nurse = allNurseList.get(which);
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (nurse == null) {
                    Toast.makeText(RegisterActivity.this, "请选择你的护理人员", Toast.LENGTH_SHORT).show();
                } else {
                    dialog.dismiss();
                }
            }
        });
        builder.setNegativeButton("取消", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void getOldMessage(List<User> oldList) {

    }

    @Override
    public void getAllOldMessage(List<User> allOldList) {

    }
}


