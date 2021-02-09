package com.ongouser.home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ongouser.Adapter.ShopcategoryProductsapter;
import com.ongouser.R;

import java.util.ArrayList;

public class Shop_productCategory extends AppCompatActivity {
    RecyclerView rec_category;
    Context mContext;
    ImageView back,cart;
    ArrayList<String>list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_product_category);
        mContext = this;
        back            =findViewById(R.id.back);
        rec_category    =findViewById(R.id.rec_category);
        cart    =findViewById(R.id.cart);
        list.add("Grocery");
        list.add("Baking Needs");
        list.add("Bathroom");
        list.add("Drinks & Beverages");
        list.add("Fruits & Vegetables");
        list.add("Health & Beauty");
        list.add("Household Cleaning");
        list.add("Kitchen");
        list.add("Laundry");
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Shop_productCategory.this,CartActivity.class);
                startActivity(i);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ShopcategoryProductsapter shop = new ShopcategoryProductsapter(mContext,list);
        rec_category.setLayoutManager(new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false));
        rec_category.setAdapter(shop) ;
    }
}