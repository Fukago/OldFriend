package com.example.apple.oldfriend.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.oldfriend.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NurseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NurseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NurseFragment extends Fragment {

    private ImageView im_nurseFace_nurseFragment;
    private TextView tv_nurseName_nurseFraghment;

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
        view =inflater.inflate(R.layout.fragment_nurse, container, false);

        return view;
    }


}
