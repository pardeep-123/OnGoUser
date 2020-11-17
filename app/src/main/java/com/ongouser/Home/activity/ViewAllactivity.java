package com.ongouser.Home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ongouser.Adapter.HomeshopadViewallapter;
import com.ongouser.Adapter.Homeshopadapter;
import com.ongouser.R;

public class ViewAllactivity extends AppCompatActivity {
    RecyclerView rec_nearshop;
    Context mContext;
    ImageView back,cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        back=findViewById(R.id.back);
        rec_nearshop=findViewById(R.id.rec_nearshop);
        cart=findViewById(R.id.cart);
        mContext = this;
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ViewAllactivity.this,CartActivity.class);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        HomeshopadViewallapter shop = new HomeshopadViewallapter(mContext);
        rec_nearshop.setLayoutManager(new GridLayoutManager(this, 2));
        rec_nearshop.setAdapter(shop) ;
    }
}