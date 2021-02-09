package com.ongouser.home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ongouser.Adapter.FeedbackAdapter;
import com.ongouser.R;

import java.util.ArrayList;

public class FeedbackActivity extends AppCompatActivity {
    RecyclerView rec_category;
    Context mContext;
    ImageView back;
    ArrayList<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        mContext = this;
        back            =findViewById(R.id.back);
        rec_category    =findViewById(R.id.rec_category);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        FeedbackAdapter shop = new FeedbackAdapter(mContext);
        rec_category.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL,false));
        rec_category.setAdapter(shop) ;
    }
}