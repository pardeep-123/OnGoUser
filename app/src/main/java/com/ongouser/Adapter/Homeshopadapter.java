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

import com.ongouser.Home.activity.Shop_productCategory;
import com.ongouser.R;

public class Homeshopadapter extends RecyclerView.Adapter<Homeshopadapter.RecyclerViewHolder> {
    Context context;
    LayoutInflater inflater;
    Button btnBasket;

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public RecyclerViewHolder(View view) {
            super(view);
        }
    }

    public Homeshopadapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Homeshopadapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.res_nearbyshop, parent, false);
        Homeshopadapter.RecyclerViewHolder viewHolder = new Homeshopadapter.RecyclerViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull final Homeshopadapter.RecyclerViewHolder holder, int position) {
/*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, Shop_productCategory.class);
                context.startActivity(i);
            }
        });
*/

    }
    @Override
    public int getItemCount() {
        return 5;
    }

}



