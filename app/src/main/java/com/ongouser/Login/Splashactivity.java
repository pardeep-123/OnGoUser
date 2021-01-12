package com.ongouser.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.ongouser.R;

import java.util.Timer;
import java.util.TimerTask;

public class Splashactivity extends AppCompatActivity {
    Context mContaxt;
    ImageView ivLogo;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContaxt=this;
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent i= new Intent(Splashactivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        },3000);
        ivLogo=findViewById(R.id.ivLogo);
    }
}
