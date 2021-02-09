package com.ongouser.home.activity.address;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ongouser.Adapter.DeliverytimeslotAdapter;
import com.ongouser.home.activity.CartActivity;
import com.ongouser.R;

import java.util.ArrayList;

public class DeliverytimeSlotActivity extends AppCompatActivity {

    RecyclerView rec_timeslot;
    ImageView ivBack;
    Context mContext;
    Button btnext;
    ArrayList<String>list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliverytime_slot);
        mContext=this;
        rec_timeslot=findViewById(R.id.rec_timeslot);
        ivBack=findViewById(R.id.ivBack);
        btnext=findViewById(R.id.btnext);
        list.add("Today");
        list.add("Tomorrow");
        list.add("Sunday");
        list.add("Monday");

        DeliverytimeslotAdapter  deliverytimeslotAdapter = new DeliverytimeslotAdapter(mContext,list);
        rec_timeslot.setLayoutManager(new LinearLayoutManager(mContext));
        rec_timeslot.setAdapter(deliverytimeslotAdapter) ;

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(DeliverytimeSlotActivity.this, CartActivity.class);
                i.putExtra("data","call");
                startActivity(i);
                finish();
            }
        });
    }
}