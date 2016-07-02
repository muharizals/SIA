package com.tumpi.sia.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tumpi.sia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }

    private OnLoginInteractionListener mListener;

    public interface OnLoginInteractionListener {
        // TODO: Update argument type and name
        void finishLoginActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_login, container, false);
        AppCompatButton button = (AppCompatButton) view.findViewById(R.id.btn_login);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mListener.finishLoginActivity();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginInteractionListener) {
            mListener = (OnLoginInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLoginInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
