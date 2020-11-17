package com.ongouser.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ongouser.Home.activity.ProductActivity;
import com.ongouser.R;

import java.util.ArrayList;

public class oderdetail_adapter extends RecyclerView.Adapter<oderdetail_adapter.RecyclerViewHolder> {
    Context context;
    LayoutInflater inflater;

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView tvName,tvstatus,tvdate,tv_reorder;
        public RecyclerViewHolder(View view) {
            super(view);
            tvName=view.findViewById(R.id.tvName);
            tvstatus=view.findViewById(R.id.tvstatus);
            tvdate=view.findViewById(R.id.tvdate);
            tv_reorder=view.findViewById(R.id.tv_reorder);
        }
    }
    String type;
    public oderdetail_adapter(Context context,String type) {
        this.context = context;
        this.type= type;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public oderdetail_adapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.list_past, parent, false);
        oderdetail_adapter.RecyclerViewHolder viewHolder = new oderdetail_adapter.RecyclerViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull final oderdetail_adapter.RecyclerViewHolder holder, int position) {
        if(type.equals("2"))
        {
            holder.tvstatus.setVisibility(View.VISIBLE);
            holder.tvdate.setVisibility(View.VISIBLE);
            holder.tv_reorder.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.tvstatus.setVisibility(View.GONE);
            holder.tvdate.setVisibility(View.GONE);
            holder.tv_reorder.setVisibility(View.GONE);
        }
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
        return 2;
    }

}



