package com.ongouser.home.activity.product

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.Adapter.Shopcategoryapter
import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.home.activity.cart.CartActivity
import java.util.*

class ShopCategoryProductsActivity : BaseActivity() {
    lateinit var rec_category: RecyclerView
    lateinit var mContext: ShopCategoryProductsActivity
    lateinit var back: ImageView
    lateinit var cart: ImageView
    var list = ArrayList<String>()
    override fun getContentId(): Int {
        return R.layout.activity_shop_category_products
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        back = findViewById(R.id.back)
        cart = findViewById(R.id.cart)
        rec_category = findViewById(R.id.rec_category)
        list.add("Flavoured milk")
        list.add("Tea")
        list.add("Coffee")
        list.add("Soft Drinks")
        list.add("Water")
        list.add("Sports")
        list.add("Long Life Milk")
        list.add("Kitchen")
        cart.setOnClickListener(View.OnClickListener {
            val i = Intent(mContext, CartActivity::class.java)
            startActivity(i)
        })
        back.setOnClickListener(View.OnClickListener { finish() })
        val shop = Shopcategoryapter(mContext, list)
        rec_category.setLayoutManager(LinearLayoutManager(mContext, RecyclerView.VERTICAL, false))
        rec_category.setAdapter(shop)
    }
}