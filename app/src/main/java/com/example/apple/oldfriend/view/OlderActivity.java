package com.example.apple.oldfriend.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apple.oldfriend.R;
import com.example.apple.oldfriend.model.bean.User;
import com.example.apple.oldfriend.presenter.OldManagePresenter;
import com.example.apple.oldfriend.util.CircleTransform;
import com.example.apple.oldfriend.weidge.UnScrollLisiView;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OlderActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView im_back_toolbar;
    private ImageView im_older_face;
    private LinearLayout ll_basic_message;
    private List<String> list = new ArrayList();
    private TextView tv_item;
    private LinearLayout ll_body_message;
    private TextView tv_body_message;
    private LinearLayout ll_social_message;
    private TextView tv_social_message;
    private OldManagePresenter presenter;
    private User old;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_older);
        initToolbar();
        initView();
        iniData();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.older_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            im_back_toolbar = (ImageView) findViewById(R.id.im_back_toolbar);
            if (im_back_toolbar != null) {
                im_back_toolbar.setOnClickListener(this);
            }
        }
    }

    private void iniData() {
        presenter = new OldManagePresenter(OlderActivity.this);
        Intent it = getIntent();
        old = (User) it.getSerializableExtra("old");
        list.clear();
        list.add("姓名:          " + old.getMyOldState().getName());
        list.add("年龄:          " + old.getMyOldState().getAge());
        list.add("性别:          " + old.getSex());
        list.add("血型:          " + old.getBlood());
        tv_body_message.setText("最近情况:   " + old.getMyOldState().getBriefState());
        im_older_face.setImageResource(R.drawable.picasso_ic_loading);
        if (old.getHeadPic()!=null) {
            Picasso.with(this)
                    .load(old.getHeadPic().getFileUrl(this))
                    .resize(136, 136)
                    .transform(new CircleTransform())
                    .placeholder(R.drawable.picasso_ic_loading)
                    .error(R.drawable.picasso_ic_loadingerror)
                    .into(im_older_face);
        }
        /*presenter.getOldPhysioState(old, new IGetOldPhysioAndPsychoState() {
            @Override
            public void getOldPhysioStateSuccess(List<OldPhysioState> allOldPhysioState) {

            }

            @Override
            public void getOldPsychoStateSuccess(List<OldPsychoState> allOldPsychoState) {

            }
        });*/
    }

    private void initView() {
        im_older_face = (ImageView) findViewById(R.id.im_older_face_old_activity);
        ll_basic_message = (LinearLayout) findViewById(R.id.ll_basic_message_old_activity);
        UnScrollLisiView lv_basic_message_old_activity = (UnScrollLisiView) findViewById(R.id
                .lv_basic_message_old_activity);
        if (lv_basic_message_old_activity != null) {
            lv_basic_message_old_activity.setAdapter(new BaseAdapter() {
                @Override
                public int getCount() {
                    return list.size();
                }

                @Override
                public Object getItem(int position) {
                    return list.get(position);
                }

                @Override
                public long getItemId(int position) {
                    return position;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view;
                    view = LayoutInflater.from(OlderActivity.this).inflate(R.layout
                            .item_basic_message_older_activity, null);
                    tv_item = (TextView) view.findViewById(R.id.tv_item_older_activity);
                    tv_item.setText(list.get(position));
                    return view;
                }
            });
        }
        if (lv_basic_message_old_activity != null) {
            lv_basic_message_old_activity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView textView = (TextView) parent.getChildAt(position).findViewById(R.id
                            .tv_item_older_activity);
                    switch (position) {
                        case 0: {
                            createDialog(textView, "姓名", "姓名", "输入姓名", 5, 2);
                            break;
                        }
                        case 1: {
                            createDialog(textView, "年龄", "年龄", "输入年龄", 3, 1);
                            break;
                        }
                        case 2: {
                            createDialog(textView, "性别", "性别", "输入性别", 1, 1);
                            break;
                        }
                        case 3: {
                            createDialog(textView, "血型", "血型", "输入血型", 3, 1);
                            break;
                        }
                    }
                }
            });
        }
        ll_body_message = (LinearLayout) findViewById(R.id.ll_body_message_old_activity);
        tv_body_message = (TextView) findViewById(R.id.tv_body_message_old_activity);
        ll_social_message = (LinearLayout) findViewById(R.id.ll_social_message_old_activity);
        tv_social_message = (TextView) findViewById(R.id.tv_social_message_old_activity);
        ll_basic_message.setOnClickListener(this);
        ll_body_message.setOnClickListener(this);
        ll_social_message.setOnClickListener(this);
    }

    private void createDialog(final TextView textView, final String hint, String flt, String tittle, int max, int min) {
        AlertDialog.Builder alert = new AlertDialog.Builder(OlderActivity.this);
        LayoutInflater factory = LayoutInflater.from(OlderActivity.this);
        final View DialogView = factory.inflate(R.layout.view_dialog, null);
        final MaterialEditText mEditText = (MaterialEditText) DialogView.findViewById(R.id.materialEdit);
        mEditText.setHint(hint);
        mEditText.setFloatingLabelText(flt);
        mEditText.setMaxCharacters(max);
        mEditText.setMinCharacters(min);
        mEditText.setShowClearButton(true);
        alert.setTitle(tittle);
        alert.setView(DialogView);
        alert.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                textView.setText(hint + ":          " + mEditText.getText().toString());

            }
        });

        alert.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        alert.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_back_toolbar: {
                finish();
                break;
            }
            case R.id.ll_basic_message_old_activity: {

                break;
            }
            case R.id.ll_body_message_old_activity: {
                Intent it = new Intent(this, OlderSituationActivity.class);
                it.putExtra("old", old);
                startActivity(it);
                break;
            }
            case R.id.ll_social_message_old_activity: {
                createDialog(tv_social_message, "社会赡养", "社会赡养", "输入赡养情况", 500, 5);
                break;
            }
        }
    }
}
