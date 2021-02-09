package com.ongouser.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ongouser.home.activity.Product_detailActivity;
import com.ongouser.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.RecyclerViewHolder> {
    Context context;
    LayoutInflater inflater;
   RelativeLayout rl_list;


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public RecyclerViewHolder(View view) {
            super(view);

        }
    }

    public ProductAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ProductAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.list_products, parent, false);
        ProductAdapter.RecyclerViewHolder viewHolder = new ProductAdapter.RecyclerViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, Product_detailActivity.class);
                context.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
        return 4;
    }


}


