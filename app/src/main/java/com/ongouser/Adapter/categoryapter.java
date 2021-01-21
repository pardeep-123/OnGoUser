package com.ongouser.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ongouser.Home.activity.Shop_category_products;
import com.ongouser.Home.activity.ViewAllactivity;
import com.ongouser.R;
import com.ongouser.pojo.CategoryListingResponse;

import java.util.ArrayList;

public class categoryapter extends RecyclerView.Adapter<categoryapter.RecyclerViewHolder> {
    Context context;
    LayoutInflater inflater;

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;

        public RecyclerViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
        }
    }

    ArrayList<CategoryListingResponse.Body> list;

    public categoryapter(Context context, ArrayList<CategoryListingResponse.Body> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public categoryapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.res_shopcategory, parent, false);
        categoryapter.RecyclerViewHolder viewHolder = new categoryapter.RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final categoryapter.RecyclerViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ViewAllactivity.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}



