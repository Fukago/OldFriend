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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apple.oldfriend.R;
import com.example.apple.oldfriend.cofing.IGetOldBriefState;
import com.example.apple.oldfriend.model.bean.OldSociaState;
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
    private List<String> mList = new ArrayList<>();
    private TextView tv_item;
    private TextView tv_item_older_number_activity;
    private LinearLayout ll_body_message;
    private TextView tv_body_message;
    private LinearLayout ll_social_message;
    private TextView tv_social_message;
    private OldManagePresenter presenter;
    private UnScrollLisiView lv_basic_message_old_activity;
    private User old;
    private Button bn_tittle_toolbar_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_older);
        initToolbar();
        initPresenter();
        initView();
        iniData();
    }

    private void initPresenter() {
        presenter = new OldManagePresenter(OlderActivity.this);
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
        mList.clear();
        mList.add("姓名:           ");
        mList.add("年龄:           ");
        mList.add("性别:           ");
        mList.add("血型:           ");
        Intent it = getIntent();
        old = (User) it.getSerializableExtra("old");
        list.clear();
        list.add(old.getMyOldState().getName());
        list.add("" + old.getMyOldState().getAge());
        list.add(old.getSex());
        list.add(old.getBlood());
        tv_body_message.setText("最近情况:   " + old.getMyOldState().getBriefState());
        presenter.getOldSociaState(old, new IGetOldBriefState() {
            @Override
            public void getOldSociaStateSuccess(List<OldSociaState> allOldSociaState) {
                tv_social_message.setText(allOldSociaState.get(allOldSociaState.size() - 1).getSituation());
            }

            @Override
            public void getOldbriefStateSuccess(String s) {

            }

            @Override
            public void getOldNameAndAge(String name, Integer age) {

            }
        });

        im_older_face.setImageResource(R.drawable.picasso_ic_loading);
        if (old.getHeadPic() != null) {
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
        bn_tittle_toolbar_update = (Button) findViewById(R.id.bn_tittle_toolbar_update);
        bn_tittle_toolbar_update.setOnClickListener(this);
        im_older_face = (ImageView) findViewById(R.id.im_older_face_old_activity);
        ll_basic_message = (LinearLayout) findViewById(R.id.ll_basic_message_old_activity);
        lv_basic_message_old_activity = (UnScrollLisiView) findViewById(R.id
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
                    tv_item_older_number_activity = (TextView) view.findViewById(R.id.tv_item_older_activity);
                    tv_item_older_number_activity.setText(mList.get(position));

                    tv_item = (TextView) view.findViewById(R.id.tv_item_older_number_activity);
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
                            .tv_item_older_number_activity);
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
                textView.setText(mEditText.getText().toString());
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
            case R.id.bn_tittle_toolbar_update: {
                TextView xingming = (TextView) lv_basic_message_old_activity.getChildAt(0).findViewById(R.id.tv_item_older_number_activity);
                TextView nianling = (TextView) lv_basic_message_old_activity.getChildAt(1).findViewById(R.id.tv_item_older_number_activity);
                TextView xingbie = (TextView) lv_basic_message_old_activity.getChildAt(2).findViewById(R.id.tv_item_older_number_activity);
                TextView xuexing = (TextView) lv_basic_message_old_activity.getChildAt(3).findViewById(R.id.tv_item_older_number_activity);
                presenter.setOldNameAndAge(old, xingming.getText().toString(), Integer.parseInt(nianling.getText().toString()));
                presenter.setOldSociaState(old, tv_social_message.getText().toString());
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
