package com.ongouser.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ongouser.home.activity.Shop_productCategory;
import com.ongouser.R;

public class Homegroceriesdapter extends RecyclerView.Adapter<Homegroceriesdapter.RecyclerViewHolder> {
    Context context;
    LayoutInflater inflater;
    Button btnBasket;


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView ivOn,ivOff;
        int temp=2;
        LinearLayout ll_chackbox;
        public RecyclerViewHolder(View view) {
            super(view);
            ll_chackbox=view.findViewById(R.id.ll_chackbox);
            ivOn=view.findViewById(R.id.ivOn);
            ivOff=view.findViewById(R.id.ivOff);
        }
    }

    public Homegroceriesdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Homegroceriesdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.res_groceries, parent, false);
        Homegroceriesdapter.RecyclerViewHolder viewHolder = new Homegroceriesdapter.RecyclerViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull final Homegroceriesdapter.RecyclerViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, Shop_productCategory.class);
                context.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
        return 5;
    }

}



