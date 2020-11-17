package com.ongouser.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ongouser.Home.activity.Product_detailActivity;
import com.ongouser.R;

import java.util.ArrayList;

public class DeliverytimeslotAdapter extends RecyclerView.Adapter<DeliverytimeslotAdapter.RecyclerViewHolder> {
   Context context;
   LayoutInflater inflater;
   RelativeLayout rl_list;
   int positi;

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tvday,tvtime,tvavailable;
        CardView cardback;
        public RecyclerViewHolder(View view) {
            super(view);
            tvday=view.findViewById(R.id.tvday);
            tvtime=view.findViewById(R.id.tvtime);
            tvavailable=view.findViewById(R.id.tvavailable);
            cardback=view.findViewById(R.id.cardback);
        }
    }
    ArrayList <String> list;
    public DeliverytimeslotAdapter(Context context,ArrayList<String>list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public DeliverytimeslotAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.res_timeslot, parent, false);
        DeliverytimeslotAdapter.RecyclerViewHolder viewHolder = new DeliverytimeslotAdapter.RecyclerViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, final int position) {
        holder.tvday.setText(list.get(position));
            if(positi==position)
            {
                holder.tvavailable.setTextColor(Color.parseColor("#ffffff"));
                holder.tvday.setTextColor(Color.parseColor("#ffffff"));
                holder.tvtime.setTextColor(Color.parseColor("#ffffff"));
                holder.cardback.setCardBackgroundColor(Color.parseColor("#49c191"));
            }
            else
            {
                holder.tvavailable.setTextColor(Color.parseColor("#000000"));
                holder.tvday.setTextColor(Color.parseColor("#000000"));
                holder.tvtime.setTextColor(Color.parseColor("#000000"));
                holder.cardback.setCardBackgroundColor(Color.parseColor("#ffffff"));

            }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                positi=position;
               notifyDataSetChanged();
            }
        });
    }
    @Override
    public int getItemCount() {
        return 4;
    }


}


