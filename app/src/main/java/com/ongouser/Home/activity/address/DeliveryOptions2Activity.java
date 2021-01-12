package com.ongouser.Home.activity.address;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ongouser.Home.DeliveryOptions3Activity;
import com.ongouser.R;

public class DeliveryOptions2Activity extends AppCompatActivity {
    Button btnConfirm;
    Context mContext;
    LinearLayout ll_address;
    Button btnnext;
    TextView tvedit,tvedit2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_options2);
        mContext =this;
        btnConfirm=findViewById(R.id.btnnext);
        ImageView ivBack=findViewById(R.id.ivBack);
        ll_address=findViewById(R.id.ll_address);
        btnnext=findViewById(R.id.btnnext);
        tvedit=findViewById(R.id.tvedit);
        tvedit2=findViewById(R.id.tvedit2);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent( DeliveryOptions2Activity.this, Add_address.class);
                intent.putExtra("data","data");
                startActivity(intent);
            }
        });
        tvedit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent( DeliveryOptions2Activity.this, Add_address.class);
                intent.putExtra("data","data");
                startActivity(intent);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Go();
            }
        });

        ll_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent( DeliveryOptions2Activity.this, Add_address.class);
                startActivity(intent);
            }
        });
        
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent( DeliveryOptions2Activity.this, DeliverytimeSlotActivity.class);
                startActivity(intent);
            }
        });
    }
    public void Go(){
        Intent intent= new Intent( this, DeliveryOptions3Activity.class);
        startActivity(intent);
    }
}