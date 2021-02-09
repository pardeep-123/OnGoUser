package com.ongouser.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ongouser.R;

public class CreatedProductAdapter extends RecyclerView.Adapter<CreatedProductAdapter.RecyclerViewHolder> {
    Context context;
    LayoutInflater inflater;
   RelativeLayout rl_list;


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public RecyclerViewHolder(View view) {
            super(view);

        }
    }

    public CreatedProductAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public CreatedProductAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.res_createdproduct, parent, false);
        CreatedProductAdapter.RecyclerViewHolder viewHolder = new CreatedProductAdapter.RecyclerViewHolder(v);
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


