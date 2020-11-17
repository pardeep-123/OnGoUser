package com.ongouser.Home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.ongouser.Adapter.ProductAdapter;
import com.ongouser.R;

import java.util.Objects;

public class ProductActivity extends AppCompatActivity {
    ImageView ivBack,cart,ivnot2,ivnot21;
    Context mContext;
    Button btn_filter,btn_sort;
    RecyclerView recyclerview;
    ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        mContext=this;
        ivBack=findViewById(R.id.back);
        recyclerview=findViewById(R.id.rec_product);
        btn_sort=findViewById(R.id.btn_sort);
        btn_filter=findViewById(R.id.btn_filter);
        cart=findViewById(R.id.cart);

        productAdapter = new ProductAdapter(mContext);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setAdapter(productAdapter) ;

        btn_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sort();
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ProductActivity.this,CartActivity.class);
                startActivity(i);
            }
        });

        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter();
            }
        });
    }
    public void sort()
    {
        final Dialog dialogSuccessful = new Dialog(Objects.requireNonNull(mContext), R.style.Theme_Dialog);
        dialogSuccessful.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogSuccessful.setContentView(R.layout.dilog_sort);
        dialogSuccessful.setCancelable(false);
        Objects.requireNonNull(dialogSuccessful.getWindow()).setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialogSuccessful.setCanceledOnTouchOutside(false);
        dialogSuccessful.getWindow().setGravity(Gravity.BOTTOM);
        Button ok = dialogSuccessful.findViewById(R.id.btnapply);
        ImageView cross = dialogSuccessful.findViewById(R.id.cross);
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSuccessful.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialogSuccessful.dismiss();
        }
    });
        ivnot2=dialogSuccessful.findViewById(R.id.ivnot2);
        ivnot21=dialogSuccessful.findViewById(R.id.ivnot21);
        ivnot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivnot2.setImageResource(R.drawable.radio_button);
                ivnot21.setImageResource(R.drawable.rado_button);
            }
        });

        ivnot21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivnot21.setImageResource(R.drawable.radio_button);
                ivnot2.setImageResource(R.drawable.rado_button);
            }
        });
        dialogSuccessful.show();
    }

    public void filter()
    {
        final Dialog dialogSuccessful = new Dialog(Objects.requireNonNull(mContext), R.style.Theme_Dialog);
        dialogSuccessful.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogSuccessful.setContentView(R.layout.dilog_filter);
        dialogSuccessful.setCancelable(false);
        Objects.requireNonNull(dialogSuccessful.getWindow()).setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialogSuccessful.setCanceledOnTouchOutside(false);
        dialogSuccessful.getWindow().setGravity(Gravity.BOTTOM);
        ivnot2=dialogSuccessful.findViewById(R.id.ivnot2);
        ivnot21=dialogSuccessful.findViewById(R.id.ivnot21);

        Button ok = dialogSuccessful.findViewById(R.id.btnapply);
        ImageView cross = dialogSuccessful.findViewById(R.id.cross);
        ivnot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivnot2.setImageResource(R.drawable.radio_button);
                ivnot21.setImageResource(R.drawable.rado_button);
            }
        });

        ivnot21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivnot21.setImageResource(R.drawable.radio_button);
                ivnot2.setImageResource(R.drawable.rado_button);
            }
        });
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSuccessful.dismiss();

            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialogSuccessful.dismiss();
        }
    });

        dialogSuccessful.show();
    }

}