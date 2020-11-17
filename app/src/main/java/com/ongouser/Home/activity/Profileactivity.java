package com.ongouser.Home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ongouser.R;

public class Profileactivity extends AppCompatActivity {

    Context mContext;
    Button btnEditProfile;
    RelativeLayout rlShop;
    ImageView ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);
        mContext=this;

        btnEditProfile=findViewById(R.id.btnEditProfile);
        ivBack=findViewById(R.id.ivBack);
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Profileactivity.this, EditProfileActivity.class);
                startActivity(i);
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }
}