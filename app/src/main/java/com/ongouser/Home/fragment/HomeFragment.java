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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ongouser.Adapter.Homegroceriesdapter;
import com.ongouser.Adapter.Homeshopadapter;
import com.ongouser.Home.activity.CartActivity;
import com.ongouser.Home.activity.ViewAllactivity;
import com.ongouser.NotificationActivity;
import com.ongouser.R;

public class HomeFragment extends Fragment  {
Context mContext;
View v;
ImageView iVNoti,cart;
RecyclerView rec_nearby,rec_groceries;
Button btnCurrent,btnPast;
TextView tv_viewallgroceri,tv_viewallshop;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);
        mContext = getActivity();
        iVNoti   = v.findViewById(R.id.iVNoti);
        rec_nearby   = v.findViewById(R.id.rec_nearby);
        rec_groceries   = v.findViewById(R.id.rec_groceries);
        tv_viewallgroceri   = v.findViewById(R.id.tv_viewallgroceri);
        tv_viewallshop   = v.findViewById(R.id.tv_viewallshop);
        cart   = v.findViewById(R.id.cart);

        Homeshopadapter  shop = new Homeshopadapter(mContext);
        rec_nearby.setLayoutManager(new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false));
        rec_nearby.setAdapter(shop) ;

        Homegroceriesdapter groceri = new Homegroceriesdapter(mContext);
        rec_groceries.setLayoutManager(new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false));
        rec_groceries.setAdapter(groceri) ;

        iVNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getActivity(), NotificationActivity.class);
                startActivity(i);
            }
        }); cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getActivity(), CartActivity.class);
                startActivity(i);
            }
        });
        tv_viewallgroceri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getActivity(), ViewAllactivity.class);
                startActivity(i);
            }
        });

        tv_viewallshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   Intent i= new Intent(getActivity(), ViewAllactivity.class);
                startActivity(i);*/
            }
        });
        return v;
    }
}