package com.ongouser.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.ongouser.R;
import com.ongouser.pojo.BannersItem;

import java.util.ArrayList;

public class ImageAdapter extends PagerAdapter {
    Context context;
     ArrayList<BannersItem> bannersItems;

    LayoutInflater mLayoutInflater;

    public ImageAdapter(Context context,ArrayList<BannersItem> bannersItems){
        this.context=context;
        this.bannersItems = bannersItems;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return bannersItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.banner_layout, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.banner_imgview);

        Glide.with(context).load(bannersItems.get(position).getImage()).error(R.mipmap.no_image_placeholder).into(imageView);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}