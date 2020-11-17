package com.ongouser.Home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ongouser.Adapter.Shopcategoryapter;
import com.ongouser.R;

import java.util.ArrayList;

public class Shop_category_products extends AppCompatActivity {
    RecyclerView rec_category;
    Context mContext;
    ImageView back,cart;
    ArrayList<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_category_products);
        mContext = this;
        back            =findViewById(R.id.back);
        cart            =findViewById(R.id.cart);
        rec_category    =findViewById(R.id.rec_category);
        list.add("Flavoured milk");
        list.add("Tea");
        list.add("Coffee");
        list.add("Soft Drinks");
        list.add("Water");
        list.add("Sports");
        list.add("Long Life Milk");
        list.add("Kitchen");
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Shop_category_products.this,CartActivity.class);
                startActivity(i);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Shopcategoryapter shop = new Shopcategoryapter(mContext,list);
        rec_category.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL,false));
        rec_category.setAdapter(shop) ;
    }
}