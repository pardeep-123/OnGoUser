package com.ongouser.Home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ongouser.Adapter.cartAdapter;
import com.ongouser.Home.HomeActivity;
import com.ongouser.R;

public class CartActivity extends AppCompatActivity {
    ImageView ivBack;
    Context mContext;
    Button btn_filter,btn_sort,btnpayment,btncheckout;
    RecyclerView recyclerview;
    cartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mContext=this;
        ivBack=findViewById(R.id.back);
        recyclerview=findViewById(R.id.rec_product);
        btn_sort=findViewById(R.id.btn_sort);
        btn_filter=findViewById(R.id.btn_filter);
        btnpayment=findViewById(R.id.btnpayment);
        btncheckout=findViewById(R.id.btncheckout);
        cartAdapter = new cartAdapter(mContext);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setAdapter(cartAdapter) ;

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        try {
            if(getIntent().getStringExtra("data").equals("call"))
            {
                btncheckout.setVisibility(View.GONE);
            }
        }catch (Exception e)
        {
            btncheckout.setVisibility(View.VISIBLE);
        }


        btnpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(CartActivity.this, PaymentActivity.class);
                startActivity(i);
            }
        });

        btncheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(CartActivity.this, PickupOptionsActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        try {
            if(getIntent().getStringExtra("data").equals("call"))
            {
                Intent i=new Intent(CartActivity.this, HomeActivity.class);
                startActivity(i);
            }
        }catch (Exception e)
        {
            super.onBackPressed();
        }

    }
}