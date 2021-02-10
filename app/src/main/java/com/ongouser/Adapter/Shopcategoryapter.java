package com.ongouser.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ongouser.home.activity.product.ProductActivity;
import com.ongouser.R;

import java.util.ArrayList;

public class Shopcategoryapter extends RecyclerView.Adapter<Shopcategoryapter.RecyclerViewHolder> {
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
    public Shopcategoryapter(Context context, ArrayList<String>list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Shopcategoryapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.res_shopcategory, parent, false);
        Shopcategoryapter.RecyclerViewHolder viewHolder = new Shopcategoryapter.RecyclerViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull final Shopcategoryapter.RecyclerViewHolder holder, int position) {
        holder.tvName.setText(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, ProductActivity.class);
                context.startActivity(i);
            }
        });

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

}



