package com.ongouser.Home;
//com.ongouser
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ongouser.Home.activity.PaymentActivity;
import com.ongouser.R;

import java.util.Calendar;

public class AddCardActivity extends AppCompatActivity {
    Context mContext;
    ImageView ivBack,ivOn,ivOff,ivImg;
    LinearLayout ll_chackbox;
    Button btnSave;
    int temp=2;
    TextView tv_expiry_month_year;
    CharSequence[] items = {"01","02","03","04", "05", "06", "07", "08", "09", "10", "11", "12"};
    private String[] yearArray;
    int current_year, future_year = 40;
    String month="",year="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        mContext =this;
        ivBack=findViewById(R.id.ivBack);
        tv_expiry_month_year=findViewById(R.id.tv_expiry_month_year);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSave=findViewById(R.id.btnSave);
        yearArray = new String[future_year];
        current_year                    = Calendar.getInstance().get(Calendar.YEAR);

        for (int i = 0; i < future_year; i++) {
            yearArray[i] = String.valueOf(current_year + i);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent( AddCardActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
        tv_expiry_month_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMonth();
            }
        });
    }

    private void openMonth() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.expiry_month));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection

                month= String.valueOf(items[item]);
                openYear();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();


    }
    private void openYear() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.expiry_year));
        builder.setItems(yearArray, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                year=yearArray[item];
                tv_expiry_month_year.setText(month+"/"+year);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }
}