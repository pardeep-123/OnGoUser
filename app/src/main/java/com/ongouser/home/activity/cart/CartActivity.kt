package com.ongouser.home.activity.cart

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.Adapter.cartAdapter
import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.home.HomeActivity
import com.ongouser.home.activity.payment.PaymentActivity
import com.ongouser.home.activity.PickupOptionsActivity

class CartActivity: BaseActivity() , View.OnClickListener{

    lateinit var ivBack: ImageView
    lateinit var mContext: CartActivity
    lateinit var btn_filter: Button
    lateinit var btn_sort:Button
    lateinit var btnpayment: Button
    lateinit var btncheckout:Button
    lateinit var recyclerview: RecyclerView
    lateinit var cartAdapter: cartAdapter

    override fun getContentId(): Int {
        return R.layout.activity_cart
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this
        ivBack = findViewById(R.id.back)
        recyclerview = findViewById(R.id.rec_product)
        btn_sort = findViewById<Button>(R.id.btn_sort)
        btn_filter = findViewById(R.id.btn_filter)
        btnpayment = findViewById<Button>(R.id.btnpayment)
        btncheckout = findViewById<Button>(R.id.btncheckout)
        cartAdapter = cartAdapter(mContext)
        recyclerview.setLayoutManager(LinearLayoutManager(mContext))
        recyclerview.setAdapter(cartAdapter)

        try {
            if (intent.getStringExtra("data") == "call") {
                btncheckout.setVisibility(View.GONE)
            }
        } catch (e: Exception) {
            btncheckout.setVisibility(View.VISIBLE)
        }
        ivBack.setOnClickListener(mContext)
        btnpayment.setOnClickListener(mContext)
        btncheckout.setOnClickListener(mContext)
    }



    override fun onBackPressed() {
        try {
            if (intent.getStringExtra("data") == "call") {
                val i = Intent(mContext, HomeActivity::class.java)
                startActivity(i)
            }
        } catch (e: java.lang.Exception) {
            super.onBackPressed()
        }
    }

    override fun onClick(v: View?) {
       when(v !!.id){
           R.id.btncheckout -> {
               val i = Intent(mContext, PickupOptionsActivity::class.java)
               startActivity(i)
           }
           R.id.btnpayment -> {
               val i = Intent(mContext, PaymentActivity::class.java)
               startActivity(i)
           }
           R.id.btnpayment -> {
               val i = Intent(mContext, PaymentActivity::class.java)
               startActivity(i)
           }
           R.id.ivBack -> {
               onBackPressed()
           }

       }
    }
}