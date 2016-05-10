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
import com.example.apple.oldfriend.cofing.IGetMyNurse;
import com.example.apple.oldfriend.cofing.IGetNurseState;
import com.example.apple.oldfriend.cofing.IUser;
import com.example.apple.oldfriend.model.bean.User;
import com.example.apple.oldfriend.presenter.NurseManagePresenter;
import com.example.apple.oldfriend.presenter.OldManagePresenter;
import com.example.apple.oldfriend.presenter.UserManagePresenter;
import com.example.apple.oldfriend.util.CircleTransform;
import com.squareup.picasso.Picasso;


public class NurseFragment extends Fragment implements IGetNurseState {

    private ImageView im_nurseFace_nurseFragment;
    private TextView tv_nurseName_nurseFraghment;
    private TextView tv_nurseSex_nurseFraghment;
    private TextView tv_nurseTel_nurseFraghment;
    private Button bn_fastCall_NurseFragment;
    private TextView tv_experience_NurseFragment;
    private TextView tv_zone_NurseFragment;

    private NurseManagePresenter nursePresenter;
    private UserManagePresenter userPresenter;


    public NurseFragment() {

    }


    public static NurseFragment newInstance(String param1, String param2) {
        NurseFragment fragment = new NurseFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nursePresenter = new NurseManagePresenter(getContext());
        userPresenter = new UserManagePresenter(getContext());
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
        initData();
        return view;
    }

    private void initData() {
        bn_fastCall_NurseFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = "" + tv_nurseTel_nurseFraghment.getText().toString();
                if (!TextUtils.isEmpty(mobile)) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "18902679166"));
                    getActivity().startActivity(intent);
                }
            }
        });

        userPresenter.getUser(new IUser() {
            @Override
            public void getUserSuccess(final User user) {
                Log.d("moblie", "" + true);
                tv_nurseTel_nurseFraghment.setText("" + user.getMyNurse().getUsername());
                Log.d("moblie", "" + user.getMyNurse().getUsername());
                new OldManagePresenter(getContext()).getMyNurseInfo(new IGetMyNurse() {
                    @Override
                    public void getMyNurseSuccess(User nurse) {
                        nursePresenter.getNurseNameAndAge(nurse, NurseFragment.this);
                        nursePresenter.getNurseExp(nurse, NurseFragment.this);
                        nursePresenter.getNurseSituation(nurse, NurseFragment.this);

                        if (nurse.getHeadPic() != null) {
                            String url = "" + nurse.getHeadPic().getFileUrl(getContext());
                            Picasso.with(getContext())
                                    .load(url)
                                    .transform(new CircleTransform())
                                    .placeholder(R.drawable.picasso_ic_loading)
                                    .error(R.drawable.picasso_ic_loadingerror)
                                    .into(im_nurseFace_nurseFragment);

                        }

                    }
                });
            }
        });
    }


    @Override
    public void getNurseNameAndAgeSuccess(String name, Integer age) {
        tv_nurseName_nurseFraghment.setText("" + name);

    }

    @Override
    public void getNurseExpSuccess(String exp) {
        tv_experience_NurseFragment.setText("" + exp);

    }

    @Override
    public void getNurseSituationSuccess(String situation) {
        tv_zone_NurseFragment.setText("" + situation);

    }
}
