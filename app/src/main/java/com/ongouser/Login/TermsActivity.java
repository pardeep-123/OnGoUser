package com.ongouser.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ongouser.R;
import com.ongouser.base.BaseActivity;

public class TermsActivity extends BaseActivity implements View.OnClickListener {
Context mContext;
ImageView ivBack;

    @Override
    protected int getContentId() {
        return R.layout.activity_terms;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        ivBack=findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}