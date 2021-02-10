package com.ongouser.home.activity.product

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.Adapter.ShopcategoryProductsapter
import com.ongouser.R
import com.ongouser.home.activity.cart.CartActivity
import java.util.*

class ShopProductCategoryActivity : AppCompatActivity() {

    lateinit var rec_category: RecyclerView
    lateinit var mContext: ShopProductCategoryActivity
    lateinit var back: ImageView
    lateinit var cart: ImageView

    var list = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_product_category)
        mContext = this
        back = findViewById(R.id.back)
        rec_category = findViewById(R.id.rec_category)
        cart = findViewById(R.id.cart)
        list.add("Grocery")
        list.add("Baking Needs")
        list.add("Bathroom")
        list.add("Drinks & Beverages")
        list.add("Fruits & Vegetables")
        list.add("Health & Beauty")
        list.add("Household Cleaning")
        list.add("Kitchen")
        list.add("Laundry")
        cart.setOnClickListener(View.OnClickListener {
            val i = Intent(mContext, CartActivity::class.java)
            startActivity(i)
        })
        back.setOnClickListener(View.OnClickListener { finish() })
        val shop = ShopcategoryProductsapter(mContext, list)
        rec_category.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        rec_category.adapter = shop
    }
}