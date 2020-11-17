package com.ongouser.Home.activity.address;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ongouser.R;

public class Add_address extends AppCompatActivity {

    ImageView ivBack;
    Button btnadd;
    TextView tvhedder;
    EditText edaddresss,edcity,edstate,edpostalcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        ivBack=findViewById(R.id.ivBack);
        btnadd=findViewById(R.id.btnadd);
        edaddresss=findViewById(R.id.edaddresss);
        edcity=findViewById(R.id.edcity);
        tvhedder=findViewById(R.id.tvhedder);
        edstate=findViewById(R.id.edstate);
        edpostalcode=findViewById(R.id.edpostalcode);
try {
    if(getIntent().getStringExtra("data").equals("data"))
    {
        edaddresss.setText("701. Nlock B-sidh markit pafharc");
        edcity.setText("Mohali");
        edstate.setText("Punjab");
        edpostalcode.setText("140012");
        btnadd.setText("Update");
        tvhedder.setText("Edit Address");
    }
}catch (Exception e)
{

}
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}