package com.example.apple.oldfriend.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apple.oldfriend.R;
import com.example.apple.oldfriend.cofing.IGetBothMessage;
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
import com.example.apple.oldfriend.presenter.BothMessagePresenter;
import com.example.apple.oldfriend.presenter.NurseManagePresenter;
import com.example.apple.oldfriend.presenter.OldManagePresenter;
import com.example.apple.oldfriend.presenter.UserManagePresenter;

import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.UpdateListener;


public class LoginActivity extends AppCompatActivity implements IRegist, ILogin, IUser, IGetBothMessage,
        IGetOldPhysioAndPsychoState {
    private UserManagePresenter presenter;
    private OldManagePresenter oldManagePresenter;
    private UserManagePresenter userManagePresenter;
    private BothMessagePresenter bothMessagePresenter;
    private NurseManagePresenter nurseManagePresenter;
    private Button getMyNurse;
    private Button nurseState;
    private Button regist;
    private Button login;
    private Button getMyOld;
    private Button send;
    private Button setMyNurse;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        nurseManagePresenter = new NurseManagePresenter(this);
        bothMessagePresenter = new BothMessagePresenter(this);
        oldManagePresenter = new OldManagePresenter(this);
        presenter = new UserManagePresenter(this);
        presenter.getUser(this);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getSMS("18902679166");
            }
        });
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.register("18902679166", "159753", et.getText().toString(), false, null, LoginActivity.this);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login("18902679166", "159753", LoginActivity.this);
            }
        });
        setMyNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                bothMessagePresenter.getAllNurseMessage(LoginActivity.this);
                User user = BmobUser.getCurrentUser(LoginActivity.this, User.class);
                oldManagePresenter.setOldSociaState(user, "感觉身体又被掏空");
                oldManagePresenter.setOldPsychoState(user, "内心空虚");
                oldManagePresenter.setOldPhysioState(user, "tiwen1", "xuetang1", "xueya1", "xuezhi1");

            }
        });
        getMyNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bothMessagePresenter.getNurseMessage(LoginActivity.this);
            }
        });
        getMyOld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bothMessagePresenter.getOldMessage(LoginActivity.this);
            }
        });

    }

    public void initView() {
        setContentView(R.layout.activity_login);
        send = (Button) findViewById(R.id.button);
        regist = (Button) findViewById(R.id.button1);
        login = (Button) findViewById(R.id.button2);
        et = (EditText) findViewById(R.id.ed);
        nurseState = (Button) findViewById(R.id.button4);
        setMyNurse = (Button) findViewById(R.id.button5);
        getMyNurse = (Button) findViewById(R.id.button6);
        getMyOld = (Button) findViewById(R.id.button7);
    }


    @Override
    public void registSuccess() {
        Log.d("TAG", "RegistSuc" + BmobUser.getCurrentUser(LoginActivity.this, User.class).getObjectId());
    }

    @Override
    public void loginSuccess() {
        Log.d("TAG", "loginSuccess" + BmobUser.getCurrentUser(LoginActivity.this, User.class).getObjectId());

    }

    @Override
    public void getUserSuccess(final User user) {
        nurseState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nurseManagePresenter.setNurseAllInfomation(user, "王大雷", 32, "经验丰富", "身体良好");
            }
        });
    }

    /**
     * ----------------------------------------------------------------------------------------------------------------
     */
    @Override
    public void getNurseMessage(User nurse) {
        Log.d("Tag", "nurseInfo" + nurse.getMyNurseState().toString());
    }

    @Override
    public void getAllNurseMessage(List<User> allNurseList) {
        User user = BmobUser.getCurrentUser(LoginActivity.this, User.class);
        user.setMyNurse(allNurseList.get(0));
        user.update(LoginActivity.this, new UpdateListener() {
            @Override
            public void onSuccess() {
                Log.d("TAG", "succuss");
            }

            @Override
            public void onFailure(int i, String s) {
                Log.d("TAG", "fail------" + s);

            }
        });
    }

    @Override
    public void getOldMessage(List<User> oldList) {
        for (User old : oldList) {
            Log.d("TAG", "name:" + old.getMyOldState().getName() + "\n" + "brief" + old.getMyOldState().getBriefState
                    () + "\n");
            oldManagePresenter.getOldPhysioState(old, LoginActivity.this);
        }
    }

    @Override
    public void getAllOldMessage(List<User> allOldList) {

    }

    @Override
    public void getOldPhysioStateSuccess(List<OldPhysioState> allOldPhysioState) {
        Log.d("TAG", allOldPhysioState.get(0).toString());
    }

    @Override
    public void getOldPsychoStateSuccess(List<OldPsychoState> allOldPsychoState) {
        Log.d("TAG", allOldPsychoState.get(0).toString());

    }
}
