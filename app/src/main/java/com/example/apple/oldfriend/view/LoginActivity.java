package com.example.apple.oldfriend.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apple.oldfriend.R;
import com.example.apple.oldfriend.cofing.ILogin;
import com.example.apple.oldfriend.model.bean.User;
import com.example.apple.oldfriend.presenter.UserManagePresenter;

import cn.bmob.v3.BmobUser;


public class LoginActivity extends AppCompatActivity implements ILogin {
    private EditText mEtPhoneNum;
    private EditText mEtPassword;
    private Button mBtLogin;
    private Button mBtRegister;
    private UserManagePresenter userManagePresenter;
    public ProgressDialog p_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userManagePresenter = new UserManagePresenter(this);
        initView();
        initListener();
    }

    private void initListener() {
        mBtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p_dialog = ProgressDialog
                        .show(LoginActivity.this,
                                null,
                                "正在为您登录...",
                                true);
                userManagePresenter.login(mEtPhoneNum.getText().toString(), mEtPassword.getText().toString(),
                        LoginActivity.this);
            }
        });

        mBtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    public void initView() {
        setContentView(R.layout.activity_login);
        mEtPhoneNum = (EditText) findViewById(R.id.et_phonenum);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mBtLogin = (Button) findViewById(R.id.bt_login);
        mBtRegister = (Button) findViewById(R.id.bt_register);
    }


    @Override
    public void loginSuccess() {
        p_dialog.cancel();
        Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
        User user = BmobUser.getCurrentUser(LoginActivity.this, User.class);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("isOld", user.getOld());
        startActivity(intent);
        finish();
    }
}
