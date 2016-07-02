package com.tumpi.sia.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tumpi.sia.R;
import com.tumpi.sia.activity.LandingActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResetPaswwordFragment extends Fragment {


    public ResetPaswwordFragment() {
        // Required empty public constructor
    }
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reset_paswword, container, false);
        return view;
    }

}
