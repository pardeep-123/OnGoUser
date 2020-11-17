package com.ongouser.Home.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ongouser.Adapter.FavouritesAdapter;
import com.ongouser.Adapter.categoryapter;
import com.ongouser.Home.activity.CartActivity;
import com.ongouser.Home.activity.Product_detailActivity;
import com.ongouser.R;

import java.util.ArrayList;

public class Favorites_frag extends Fragment {

    View v;
    RecyclerView rec_category;
    Context mContext;
    ImageView back,cart;
    ArrayList<String> list=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_favorites_frag, container, false);
        mContext=getActivity();
        rec_category=v.findViewById(R.id.rec_category);
        cart=v.findViewById(R.id.cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), CartActivity.class);
                startActivity(i);
            }
        });
        FavouritesAdapter shop = new FavouritesAdapter(mContext);
        rec_category.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL,false));
        rec_category.setAdapter(shop) ;
        return v;
           }
}