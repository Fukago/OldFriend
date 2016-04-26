package com.example.apple.oldfriend.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.oldfriend.R;


public class NurseFragment extends Fragment {

    private ImageView im_nurseFace_nurseFragment;
    private TextView tv_nurseName_nurseFraghment;
    private TextView tv_nurseSex_nurseFraghment;
    private TextView tv_nurseTel_nurseFraghment;
    private Button bn_fastCall_NurseFragment;
    private TextView tv_experience_NurseFragment;
    private TextView tv_zone_NurseFragment;


    public NurseFragment() {

    }


    public static NurseFragment newInstance(String param1, String param2) {
        NurseFragment fragment = new NurseFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_nurse, container, false);
        im_nurseFace_nurseFragment = (ImageView) view.findViewById(R.id.im_nurseFace_nurseFragment);
        tv_nurseName_nurseFraghment = (TextView) view.findViewById(R.id.tv_nurseName_nurseFraghment);
        tv_nurseSex_nurseFraghment = (TextView) view.findViewById(R.id.tv_nurseSex_nurseFraghment);
        tv_nurseTel_nurseFraghment = (TextView) view.findViewById(R.id.tv_nursetel_nurseFraghment);
        bn_fastCall_NurseFragment = (Button) view.findViewById(R.id.bn_fastCall_NurseFragment);
        tv_experience_NurseFragment = (TextView) view.findViewById(R.id.tv_experience_NurseFragment);
        tv_zone_NurseFragment = (TextView) view.findViewById(R.id.tv_zone_NurseFragment);
        bn_fastCall_NurseFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = "" +tv_nurseTel_nurseFraghment.getText().toString();
                if (!TextUtils.isEmpty(mobile)) {
                    Log.d("tel1","mobile"+mobile);
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "18902679166"));
                    getActivity().startActivity(intent);
                }
            }
        });
        return view;
    }


}
