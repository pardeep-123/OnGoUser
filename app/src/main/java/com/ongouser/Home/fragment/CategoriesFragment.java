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

import com.ongouser.Adapter.categoryapter;
import com.ongouser.Home.HomeActivity;
import com.ongouser.R;

import java.util.ArrayList;


public class CategoriesFragment extends Fragment {
    View v;
    RecyclerView rec_category;
    Context mContext;
    ImageView back;
    ArrayList<String> list=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_categories, container, false);
       mContext=getContext();
        back            =v.findViewById(R.id.back);
        rec_category    =v.findViewById(R.id.rec_category);
        list.add("Grocery");
        list.add("Baking Needs");
        list.add("Bathroom");
        list.add("Drinks & Bevrages");
        list.add("Fruits & Vegitables");
        list.add("Health & Beauty");
        list.add("Household Cleaning");
        list.add("Kitchen");
        list.add("Laundry");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i=new Intent(getActivity(), HomeActivity.class);
               startActivity(i);
            }
        });
        categoryapter shop = new categoryapter(mContext,list);
        rec_category.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL,false));
        rec_category.setAdapter(shop) ;
        return v;
    }
}