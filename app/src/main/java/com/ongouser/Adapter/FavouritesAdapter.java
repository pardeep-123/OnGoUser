package com.ongouser.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ongouser.Home.activity.Product_detailActivity;
import com.ongouser.R;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.RecyclerViewHolder> {
    Context context;
    LayoutInflater inflater;
   RelativeLayout rl_list;


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public RecyclerViewHolder(View view) {
            super(view);

        }
    }

    public FavouritesAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public FavouritesAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.res_favourites, parent, false);
        FavouritesAdapter.RecyclerViewHolder viewHolder = new FavouritesAdapter.RecyclerViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

    }
    @Override
    public int getItemCount() {
        return 4;
    }


}


