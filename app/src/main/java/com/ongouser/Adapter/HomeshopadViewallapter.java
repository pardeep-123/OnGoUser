package com.ongouser.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ongouser.home.activity.Shop_productCategory;
import com.ongouser.R;

public class HomeshopadViewallapter extends RecyclerView.Adapter<HomeshopadViewallapter.RecyclerViewHolder> {
    Context context;
    LayoutInflater inflater;

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public RecyclerViewHolder(View view) {
            super(view);

        }
    }

    public HomeshopadViewallapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public HomeshopadViewallapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.res_nearbyshopvewall, parent, false);
        HomeshopadViewallapter.RecyclerViewHolder viewHolder = new HomeshopadViewallapter.RecyclerViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull final HomeshopadViewallapter.RecyclerViewHolder holder, int position) {
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
        return 10;
    }

}



