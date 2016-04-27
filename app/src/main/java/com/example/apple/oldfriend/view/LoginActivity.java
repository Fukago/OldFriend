package com.example.apple.oldfriend.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apple.oldfriend.R;
import com.example.apple.oldfriend.cofing.IGetOldBriefState;
import com.example.apple.oldfriend.cofing.IGetOldPhysioAndPsychoState;
import com.example.apple.oldfriend.cofing.ILogin;
import com.example.apple.oldfriend.cofing.IRegist;
import com.example.apple.oldfriend.cofing.IUser;
import com.example.apple.oldfriend.model.bean.OldPhysioState;
import com.example.apple.oldfriend.model.bean.OldPsychoState;
import com.example.apple.oldfriend.model.bean.OldSociaState;
import com.example.apple.oldfriend.model.bean.OldState;
import com.example.apple.oldfriend.model.bean.User;
import com.example.apple.oldfriend.presenter.OldManagePresenter;
import com.example.apple.oldfriend.presenter.UserManagePresenter;

import java.util.List;


public class LoginActivity extends AppCompatActivity implements IRegist, ILogin, IGetOldBriefState,
        IGetOldPhysioAndPsychoState, IUser {
    private UserManagePresenter presenter;
    private OldManagePresenter oldManagePresenter;
    private UserManagePresenter userManagePresenter;
    private Button button;
    private Button button1;
    private Button exitLogin;
    private Button login;
    private EditText et;
    private User me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        presenter = new UserManagePresenter(LoginActivity.this);
        oldManagePresenter = new OldManagePresenter(LoginActivity.this);
        userManagePresenter = new UserManagePresenter(LoginActivity.this);
        userManagePresenter.getUser(this);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                presenter.getSMS("18902679166");
//
//            }
//        });
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("TAG", "Sms------------" + et.getText().toString());
//                presenter.register("18902679166", "159753", et.getText().toString(), true, null, LoginActivity.this);
//            }
//        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login("18902679166", "159753", LoginActivity.this);
            }
        });
        exitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.exitLogin();
            }
        });
    }

    public void initView() {
        setContentView(R.layout.activity_login);
        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1);
        login = (Button) findViewById(R.id.button2);
        exitLogin = (Button) findViewById(R.id.button3);
        et = (EditText) findViewById(R.id.ed);
    }

    @Override
    public void registSuccess() {
        Toast.makeText(LoginActivity.this, "RegistSuccess", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess() {
        Log.d("TAG", "LoginSuccess");
    }

    @Override
    public void getOldSociaStateSuccess(List<OldSociaState> allOldSociaState) {
        Log.d("TAG", "getOldSociaStateSuccess----" + allOldSociaState.get(0).getSituation());
    }

    @Override
    public void getOldbriefStateSuccess(String s) {
        Log.d("TAG", "getOldbriefStateSuccess----" + s);

    }

    @Override
    public void getOldNameAndAge(String name, Integer age) {
        Log.d("TAG", "getOldNameAndAge----" + name + "------>" + age);

    }

    @Override
    public void getOldPhysioStateSuccess(List<OldPhysioState> allOldPhysioState) {
        Log.d("TAG", "getOldPhysioStateSuccess" + allOldPhysioState.get(0).toString());
    }


    @Override
    public void getOldPsychoStateSuccess(List<OldPsychoState> allOldPsychoState) {
        Log.d("TAG", "getOldPsychoStateSuccess" + allOldPsychoState.get(0).toString());

    }

    @Override
    public void getUserSuccess(final User user) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                oldManagePresenter.setOldNameAndAge(user, "王瓜娃", 72);
                oldManagePresenter.setOldbriefState(user, "健康活泼");
                oldManagePresenter.setOldPhysioState(user, "体温", "血糖", "血压", "血脂1");
                oldManagePresenter.setOldPsychoState(user, "心理正常");
                oldManagePresenter.setOldSociaState(user, "状态不错");

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("TAG", "Sms------------" + et.getText().toString());
//                presenter.register("18902679166", "159753", et.getText().toString(), true, null, LoginActivity.this);
                oldManagePresenter.getOldSociaState(user, LoginActivity.this);
                oldManagePresenter.getOldPsychoState(user, LoginActivity.this);
                oldManagePresenter.getOldNameAndAge(user, LoginActivity.this);
                oldManagePresenter.getOldBriefState(user, LoginActivity.this);
                oldManagePresenter.getOldPhysioState(user, LoginActivity.this);

            }
        });
    }
}
