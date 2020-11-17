package com.ongouser.Home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ongouser.Adapter.CreatedProductAdapter;
import com.ongouser.Adapter.ProductAdapter;
import com.ongouser.R;

public class Productlisting extends AppCompatActivity {
    ImageView ivBack,cart,ivnot2,ivnot21;
    Context mContext;
    Button btn_filter,btn_sort;
    RecyclerView recyclerview;
    CreatedProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlisting);
        ivBack=findViewById(R.id.back);
        mContext=this;
        recyclerview=findViewById(R.id.rec_product);
        btn_sort=findViewById(R.id.btn_sort);
        btn_filter=findViewById(R.id.btn_filter);
        cart=findViewById(R.id.cart);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        productAdapter = new CreatedProductAdapter(mContext);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setAdapter(productAdapter) ;
    }
}