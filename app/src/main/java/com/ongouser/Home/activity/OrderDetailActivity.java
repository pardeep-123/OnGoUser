package com.ongouser.Home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ongouser.OrderDetailAdapter;
import com.ongouser.R;

public class OrderDetailActivity extends AppCompatActivity {
    Context mContext;
    View v;
    ImageView back;
    RecyclerView rec_order;
    Button btnCurrent,btnPast;
    OrderDetailAdapter orderDetailAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        mContext = this;

        btnPast=findViewById(R.id.btnPast);
        btnCurrent=findViewById(R.id.btnCurrent);
        rec_order=findViewById(R.id.rec_order);
        back=findViewById(R.id.back);
        OrderDetailAdapter shop = new OrderDetailAdapter(mContext,"1");
        rec_order.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL,false));
        rec_order.setAdapter(shop) ;

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCurrent.setTextColor(getResources().getColor(R.color.white));
                btnPast.setTextColor(getResources().getColor(R.color.ligthgrey));
                OrderDetailAdapter shop = new OrderDetailAdapter(mContext,"1");
                rec_order.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL,false));
                rec_order.setAdapter(shop) ;
            }
        });
        btnPast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPast.setTextColor(getResources().getColor(R.color.white));
                btnCurrent.setTextColor(getResources().getColor(R.color.ligthgrey));
                OrderDetailAdapter shop = new OrderDetailAdapter(mContext,"2");
                rec_order.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL,false));
                rec_order.setAdapter(shop) ;
            }
        });
    }
}