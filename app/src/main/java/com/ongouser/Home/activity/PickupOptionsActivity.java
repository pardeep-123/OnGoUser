package com.ongouser.Home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ongouser.Home.activity.address.DeliveryOptions2Activity;
import com.ongouser.R;

public class PickupOptionsActivity extends AppCompatActivity {
    Button btnConfirm;
    Context mContext;
    CardView cardhome,cardpackage,cardpick;
    RecyclerView recyclerview;
    ImageView ivBack;
    TextView tvpackage,tv_homedelivery,tvpick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_options1);
        mContext =this;
        btnConfirm=findViewById(R.id.btnConfirm);
        cardpackage=findViewById(R.id.cardpackage);
        cardhome=findViewById(R.id.cardhome);
        ivBack=findViewById(R.id.ivBack);
        tvpackage=findViewById(R.id.tvpackage);
        tv_homedelivery=findViewById(R.id.tv_homedelivery);
        tvpick=findViewById(R.id.tvpick);
        cardpick=findViewById(R.id.cardpick);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Go();
            }
        });
        cardhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardpick.setCardBackgroundColor(getResources().getColor(R.color.white));
                cardpackage.setCardBackgroundColor(getResources().getColor(R.color.white));
                cardhome.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                tv_homedelivery.setTextColor(getResources().getColor(R.color.white));
                tvpick.setTextColor(getResources().getColor(R.color.black));
                tvpackage.setTextColor(getResources().getColor(R.color.black));
            }
        });
        cardpackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardpick.setCardBackgroundColor(getResources().getColor(R.color.white));
                cardpackage.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                tvpackage.setTextColor(getResources().getColor(R.color.white));
                tvpick.setTextColor(getResources().getColor(R.color.black));
                tv_homedelivery.setTextColor(getResources().getColor(R.color.black));
                cardhome.setCardBackgroundColor(getResources().getColor(R.color.white));
            }
        });

        cardpick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardpackage.setCardBackgroundColor(getResources().getColor(R.color.white));
                cardpick.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                cardhome.setCardBackgroundColor(getResources().getColor(R.color.white));

                tvpick.setTextColor(getResources().getColor(R.color.white));
                tvpackage.setTextColor(getResources().getColor(R.color.black));
                tv_homedelivery.setTextColor(getResources().getColor(R.color.black));
            }
        });
    }
    public void Go(){
        Intent intent= new Intent( this, DeliveryOptions2Activity.class);
        startActivity(intent);
    }
}