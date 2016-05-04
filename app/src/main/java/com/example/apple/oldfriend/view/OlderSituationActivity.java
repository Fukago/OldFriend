package com.example.apple.oldfriend.view;

import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.oldfriend.R;
import com.example.apple.oldfriend.weidge.UnScrollLisiView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

public class OlderSituationActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView im_back_toolbar;
    private BarChart mChart;
    private List<String> list = new ArrayList();
    private TextView tv_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_older_situation);
        initToolbar();
        initView();
        initData();
    }

    private void initData() {



        list.clear();
        list.add("体温:           ");
        list.add("血糖:           ");
        list.add("血压:           ");
        list.add("血脂:           ");
    }

    private void initView() {
        mChart = (BarChart) findViewById(R.id.chart);
        initChart();
        UnScrollLisiView lv_body_message = (UnScrollLisiView) findViewById(R.id.lv_body_message_older_situation);
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
                    tv_item = (TextView) view.findViewById(R.id.tv_item_older_situation_activity);
                    tv_item.setText(list.get(position));
                    return view;
                }
            });
        }
        if (lv_body_message != null) {
            lv_body_message.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView textView = (TextView) parent.getChildAt(position).findViewById(R.id.tv_item_older_situation_activity);
                    switch (position) {
                        case 0: {
                            createDialog(textView, "体温", "体温", "输入体温", 10, 2);
                            break;
                        }
                        case 1: {
                            createDialog(textView, "血糖", "血糖", "输入血糖", 10, 1);
                            break;
                        }
                        case 2: {
                            createDialog(textView, "血压", "血压", "输入血压", 10, 1);
                            break;
                        }
                        case 3: {
                            createDialog(textView, "血脂", "血脂", "输入血脂", 10, 1);
                            break;
                        }
                    }
                }
            });
        }
    }


    private void createDialog(final TextView textView, final String hint, String flt, String tittle, int max, int min) {
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
                Log.d("mEditText.getText()",""+mEditText.getText().toString());
                textView.setText(hint + ":         " + mEditText.getText().toString());
            }
        });

        alert.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        alert.show();
    }

    private void initChart() {
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");


        ArrayList<BarEntry> group1 = new ArrayList<>();
        group1.add(new BarEntry(4f, 0));
        group1.add(new BarEntry(8f, 1));
        group1.add(new BarEntry(6f, 2));
        group1.add(new BarEntry(12f, 3));
        group1.add(new BarEntry(18f, 4));
        group1.add(new BarEntry(9f, 5));

        ArrayList<BarEntry> group2 = new ArrayList<>();
        group2.add(new BarEntry(6f, 0));
        group2.add(new BarEntry(7f, 1));
        group2.add(new BarEntry(8f, 2));
        group2.add(new BarEntry(12f, 3));
        group2.add(new BarEntry(15f, 4));
        group2.add(new BarEntry(10f, 5));

        BarDataSet barDataSet1 = new BarDataSet(group1, "钙");
        barDataSet1.setColors(new int[]{Color.rgb(0xe8, 0x3f, 0x9c)});

        BarDataSet barDataSet2 = new BarDataSet(group2, "铁");
        barDataSet2.setColors(new int[]{Color.rgb(0x32, 0xCD, 0x32)});

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);

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
        }
    }
}
