package com.ongouser.Home.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.ongouser.Home.activity.ChangePasswordActivity;
import com.ongouser.Home.activity.FeedbackActivity;
import com.ongouser.Home.activity.OrderDetailActivity;
import com.ongouser.Login.LoginActivity;
import com.ongouser.Home.activity.Profileactivity;
import com.ongouser.R;
import com.ongouser.TermsActivity;

public class SettingsFragment extends Fragment {
    Context mContext;
    View v;
    RelativeLayout rlChange,rlTerms,rlLogout,rlmyorder,rlmyprofile,rlfeedback;
Dialog dialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_settings, container, false);
        mContext=getActivity();
        rlChange=v.findViewById(R.id.rlChange);
        rlTerms=v.findViewById(R.id.rlTerms);
        rlLogout=v.findViewById(R.id.rlLogout);
        rlmyprofile=v.findViewById(R.id.rlmyprofile);
        rlfeedback=v.findViewById(R.id.rlfeedback);
        rlmyorder=v.findViewById(R.id.rlmyorder);

        rlfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FeedbackActivity.class);
                startActivity(intent);
            }
        });

        rlmyprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Profileactivity.class);
                startActivity(intent);
            }
        });
        rlmyorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                startActivity(intent);
            }
        });
        rlLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showDailog();
            }
        });
        rlTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TermsActivity.class);
                startActivity(intent);
            }
        });
        rlChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        return  v;
    }
    public void showDailog(){

        dialog = new Dialog(mContext);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.alert_logout);
        dialog.setCancelable(true);
        Button btnYes,btnNo;
        btnYes= dialog.findViewById(R.id.btnYes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, LoginActivity.class));
                dialog.dismiss();
            }
        });

        btnNo= dialog.findViewById(R.id.btnNo);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}