package com.ongouser.Home.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.ongouser.Adapter.created_productlist;
import com.ongouser.R;

import java.util.ArrayList;


public class Create_listfrag extends Fragment {
    View v;
    RecyclerView rec_category;
    Context mContext;
    Dialog dialog;
    ImageView iv_add;
    ArrayList<String> list=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_create_listfrag, container, false);
        mContext=getContext();
        rec_category=v.findViewById(R.id.rec_category);
        iv_add=v.findViewById(R.id.iv_add);
        list.add("Vegetables & Fruits");
        list.add("Shoes");
        list.add("Electronics");
       
        list();
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list();
            }
        });

        created_productlist shop = new created_productlist(mContext,list);
        rec_category.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL,false));
        rec_category.setAdapter(shop) ;
        return v;
    }
    public void list(){
        dialog = new Dialog(mContext);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.alert_edit_productdetail);
        dialog.setCancelable(true);
        Button btnSubmit;
        ImageView ivCross;
        btnSubmit= dialog.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ivCross= dialog.findViewById(R.id.ivCross);
        ivCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}