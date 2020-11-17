package com.ongouser;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ongouser.Home.activity.OrdersummryActivity;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.RecyclerViewHolder> {
    Context context;
    LayoutInflater inflater;

    RelativeLayout ll_1;


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
    public OrderDetailAdapter(Context context,String type) {
        this.context = context;
        this.type= type;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public OrderDetailAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.list_past, parent, false);
        OrderDetailAdapter.RecyclerViewHolder viewHolder = new OrderDetailAdapter.RecyclerViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull OrderDetailAdapter.RecyclerViewHolder holder, int position) {
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
                Intent i=new Intent(context, OrdersummryActivity.class);
                context.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
        return 3;
    }


}


