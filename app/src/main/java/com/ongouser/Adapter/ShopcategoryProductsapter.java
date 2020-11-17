package com.ongouser.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ongouser.Home.activity.Shop_category_products;
import com.ongouser.Home.activity.Shop_productCategory;
import com.ongouser.R;

import java.util.ArrayList;

public class ShopcategoryProductsapter extends RecyclerView.Adapter<ShopcategoryProductsapter.RecyclerViewHolder> {
    Context context;
    LayoutInflater inflater;

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        public RecyclerViewHolder(View view) {
            super(view);
            tvName=view.findViewById(R.id.tvName);
        }
    }
ArrayList<String>list;
    public ShopcategoryProductsapter(Context context, ArrayList<String>list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ShopcategoryProductsapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.res_shopcategory, parent, false);
        ShopcategoryProductsapter.RecyclerViewHolder viewHolder = new ShopcategoryProductsapter.RecyclerViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull final ShopcategoryProductsapter.RecyclerViewHolder holder, int position) {
        holder.tvName.setText(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, Shop_category_products.class);
                context.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

}



