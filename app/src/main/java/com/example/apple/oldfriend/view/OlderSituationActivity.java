package com.example.apple.oldfriend.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.oldfriend.R;
import com.example.apple.oldfriend.cofing.IGetOldPhyStateAndTimeList;
import com.example.apple.oldfriend.cofing.IGetOldPhysioAndPsychoState;
import com.example.apple.oldfriend.model.bean.OldPhysioState;
import com.example.apple.oldfriend.model.bean.OldPsychoState;
import com.example.apple.oldfriend.model.bean.User;
import com.example.apple.oldfriend.presenter.OldManagePresenter;
import com.example.apple.oldfriend.weidge.UnScrollLisiView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OlderSituationActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView im_back_toolbar;
    private BarChart mChart;
    private List<String> list = new ArrayList();
    private List<String> mList = new ArrayList();
    private TextView tv_item_older_situation_activity;
    private TextView tv_item_older_number_situation_activity;
    private UnScrollLisiView lv_body_message;
    private OldManagePresenter presenter;
    private EditText et_send_situation;
    private Button bn_toolbar_update;
    private ArrayList<String> labels = new ArrayList<String>();
    private ArrayList<BarEntry> tiWen = new ArrayList<>();
    private ArrayList<BarEntry> xueTang = new ArrayList<>();
    private ArrayList<BarEntry> xueYa = new ArrayList<>();
    private ArrayList<BarEntry> xueZhi = new ArrayList<>();
    private User old;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_older_situation);
        iniPresenter();
        initToolbar();
        initView();
        initData();
    }

    private void iniPresenter() {
        presenter = new OldManagePresenter(this);
    }

    private void initData() {
        Intent it = getIntent();
        old = (User) it.getSerializableExtra("old");
        presenter.getOldPhysioState(old, new IGetOldPhysioAndPsychoState() {
            @Override
            public void getOldPhysioStateSuccess(List<OldPhysioState> allOldPhysioState) {
                list.clear();
                list.add(allOldPhysioState.get(allOldPhysioState.size() - 1).getTiwen());
                list.add(allOldPhysioState.get(allOldPhysioState.size() - 1).getXuetang());
                list.add(allOldPhysioState.get(allOldPhysioState.size() - 1).getXueya());
                list.add(allOldPhysioState.get(allOldPhysioState.size() - 1).getXuezhi());
            }

            @Override
            public void getOldPsychoStateSuccess(List<OldPsychoState> allOldPsychoState) {
                et_send_situation.setText("近况:          " + allOldPsychoState.get(allOldPsychoState.size() - 1).getSituation());
            }
        });

        mList.clear();
        mList.add("体温:(单位:    摄氏度)           ");
        mList.add("血糖:(单位:    mmol/l)           ");
        mList.add("血压:(单位:    mmHg)           ");
        mList.add("血脂:(单位:    mmol/l)           ");


        presenter.getOldPhysioStateTimeList(old, new IGetOldPhyStateAndTimeList() {
            @Override
            public void getOldPhyStateAndTimeListSuccess(List<String> timeList, HashMap<String, ArrayList<BarEntry>> stateMap) {
                labels.clear();
                labels.addAll(timeList);
                tiWen.clear();
                tiWen.addAll(stateMap.get("tiwen"));
                xueTang.clear();
                xueTang.addAll(stateMap.get("xuetang"));
                xueYa.clear();
                xueYa.addAll(stateMap.get("xueya"));
                xueZhi.clear();
                xueZhi.addAll(stateMap.get("xuezhi"));
                initChart();
            }
        });
    }

    private void initView() {
        et_send_situation = (EditText) findViewById(R.id.et_send_situation);
        mChart = (BarChart) findViewById(R.id.chart);
        initChart();
        lv_body_message = (UnScrollLisiView) findViewById(R.id.lv_body_message_older_situation);
        if (lv_body_message != null) {
            lv_body_message.setAdapter(new BaseAdapter() {
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
                    view = LayoutInflater.from(OlderSituationActivity.this).inflate(R.layout.item_body_message_situation_activity, null);
                    tv_item_older_situation_activity = (TextView) view.findViewById(R.id.tv_item_older_situation_activity);
                    tv_item_older_situation_activity.setText(mList.get(position));
                    tv_item_older_number_situation_activity= (TextView) view.findViewById(R.id.tv_item_older_number_situation_activity);
                    tv_item_older_number_situation_activity.setText(list.get(position));
                    return view;
                }
            });
        }
        bn_toolbar_update = (Button) findViewById(R.id.bn_tittle_toolbar_update);
        bn_toolbar_update.setOnClickListener(this);
        if (lv_body_message != null) {
            lv_body_message.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView textView = (TextView) parent.getChildAt(position).findViewById(R.id.tv_item_older_number_situation_activity);
                    switch (position) {
                        case 0: {
                            createDialog(old, textView, "体温", "体温", "输入体温", 10, 2,"    摄氏度");
                            break;
                        }
                        case 1: {
                            createDialog(old, textView, "血糖", "血糖", "输入血糖", 10, 1,"  mmol/l");
                            break;
                        }
                        case 2: {
                            createDialog(old, textView, "血压", "血压", "输入血压", 10, 1,"    mmHg");
                            break;
                        }
                        case 3: {
                            createDialog(old, textView, "血脂", "血脂", "输入血脂", 10, 1,"   mmol/l");
                            break;
                        }
                    }
                }
            });
        }
    }


    private void createDialog(final User old, final TextView textView, final String hint, String flt, String tittle, int max, int min, final String danWei) {
        AlertDialog.Builder alert = new AlertDialog.Builder(OlderSituationActivity.this);
        LayoutInflater factory = LayoutInflater.from(OlderSituationActivity.this);
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
                Log.d("mEditText.getText()", "" + mEditText.getText().toString());
                textView.setText(mEditText.getText().toString());
            }
        });

        alert.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        alert.show();
    }

    private void initChart() {
        BarDataSet barDataSet1 = new BarDataSet(tiWen, "体温");
        barDataSet1.setColors(new int[]{Color.rgb(0xe8, 0x3f, 0x9c)});

        BarDataSet barDataSet2 = new BarDataSet(xueTang, "血糖");
        barDataSet2.setColors(new int[]{Color.rgb(0x32, 0xCD, 0x32)});

        BarDataSet barDataSet3 = new BarDataSet(xueYa, "血压");
        barDataSet2.setColors(new int[]{Color.rgb(0x32, 0xCD, 0x32)});

        BarDataSet barDataSet4 = new BarDataSet(xueZhi, "血脂");
        barDataSet2.setColors(new int[]{Color.rgb(0x32, 0xCD, 0x32)});

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        dataSets.add(barDataSet3);
        dataSets.add(barDataSet4);

        BarData data = new BarData(labels, dataSets);
        mChart.setData(data);
    }


    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.older_situation_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            im_back_toolbar = (ImageView) findViewById(R.id.im_back_toolbar);
            if (im_back_toolbar != null) {
                im_back_toolbar.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_back_toolbar: {
                finish();
                break;
            }
            case R.id.bn_tittle_toolbar_update: {
                TextView tiwen = (TextView) lv_body_message.getChildAt(0).findViewById(R.id.tv_item_older_number_situation_activity);
                TextView xuetang = (TextView) lv_body_message.getChildAt(1).findViewById(R.id.tv_item_older_number_situation_activity);
                TextView xueya = (TextView) lv_body_message.getChildAt(2).findViewById(R.id.tv_item_older_number_situation_activity);
                TextView xuezhi = (TextView) lv_body_message.getChildAt(3).findViewById(R.id.tv_item_older_number_situation_activity);
                presenter.setOldPhysioState(old, tiwen.getText().toString(), xuetang.getText().toString(), xueya.getText().toString(), xuezhi.getText().toString());
                presenter.setOldbriefState(old,et_send_situation.getText().toString());
                break;
            }
        }
    }
}
