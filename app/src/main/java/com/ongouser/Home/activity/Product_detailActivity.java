package com.ongouser.Home.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ongouser.R;

public class Product_detailActivity extends AppCompatActivity {
    ImageView back,cart;
Button btnaddcart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product1);
        back            =findViewById(R.id.back);
        cart            =findViewById(R.id.cart);
        btnaddcart            =findViewById(R.id.btnaddcart);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Product_detailActivity.this,CartActivity.class);
                startActivity(i);
            }
        }); btnaddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              finish();
            }
        });
    }
}