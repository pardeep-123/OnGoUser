package com.ongouser.home.activity.product

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.home.activity.cart.CartActivity

class ProductDetailActivity : BaseActivity(), View.OnClickListener {

    lateinit var back: ImageView
    lateinit var cart: ImageView
    lateinit var btnaddcart: Button
    lateinit var mContext: ProductDetailActivity

    override fun getContentId(): Int {
        return R.layout.activity_product1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext= this
        back = findViewById(R.id.back)
        cart = findViewById(R.id.cart)
        btnaddcart = findViewById(R.id.btnaddcart)

        back.setOnClickListener(mContext)
        cart.setOnClickListener(mContext)
        btnaddcart.setOnClickListener(mContext)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.back -> {
                onLeftIconClick()
            }
            R.id.cart -> {
                val i = Intent(mContext, CartActivity::class.java)
                startActivity(i)
            }
          R.id.btnaddcart -> {
                finish()
            }
        }
    }
}