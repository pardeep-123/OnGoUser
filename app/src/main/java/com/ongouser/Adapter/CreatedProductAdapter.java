package com.ongouser.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.ongouser.R;
import com.ongouser.home.activity.product.ProductDetailActivity;
import com.ongouser.pojo.GetProductModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CreatedProductAdapter extends RecyclerView.Adapter<CreatedProductAdapter.RecyclerViewHolder> {
    Context context;
    LayoutInflater inflater;
   RelativeLayout rl_list;
   GetProductModel getProductModel;

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView productimage;
        TextView productname,productprice;
         ProgressBar progressBar;
        public RecyclerViewHolder(View view) {
            super(view);
            productimage=view.findViewById(R.id.productimage);
            productname=view.findViewById(R.id.productname);
            productprice=view.findViewById(R.id.productprice);
            progressBar = view.findViewById(R.id.progress);
        }
    }

    public CreatedProductAdapter(Context context,GetProductModel getProductModel) {
        this.context = context;
        this.getProductModel = getProductModel;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public CreatedProductAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.res_createdproduct, parent, false);
        CreatedProductAdapter.RecyclerViewHolder viewHolder = new CreatedProductAdapter.RecyclerViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position) {
      //  Glide.with(context).load(getProductModel.getBody().get(position).getImage()).placeholder(R.mipmap.no_image_placeholder).into(holder.productimage);
        holder.productname.setText(getProductModel.getBody().get(position).getName());
        holder.productprice.setText("$"+getProductModel.getBody().get(position).getMrp());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, ProductDetailActivity.class);
                i.putExtra("data",getProductModel.getBody().get(position));
                context.startActivity(i);
            }
        });

        Glide.with(context)
                .asBitmap()
                .load(getProductModel.getBody().get(position).getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        holder.productimage.setImageBitmap(resource);
                        holder.productimage.buildDrawingCache();
                        holder.progressBar.setVisibility(View.GONE);
                    }
                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) { }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        holder.progressBar.setVisibility(View.GONE);
                    }
                });

/*
        Glide.with(context)
                .load(getProductModel.getBody().get(position).getImage())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into( holder.productimage);
*/

    }
    @Override
    public int getItemCount() {
        return getProductModel.getBody().size();
    }


}


