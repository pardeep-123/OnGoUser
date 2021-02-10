package com.ongouser.home.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.Adapter.HomeshopadViewallapter
import com.ongouser.R
import com.ongouser.home.activity.cart.CartActivity

class ViewAllActivity : AppCompatActivity() {
    lateinit var rec_nearshop: RecyclerView
    lateinit var mContext: Context
    lateinit var back: ImageView
    lateinit var cart: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all)
        back = findViewById(R.id.back)
        rec_nearshop = findViewById(R.id.rec_nearshop)
        cart = findViewById(R.id.cart)
        mContext = this
        cart.setOnClickListener(View.OnClickListener {
            val i = Intent(this@ViewAllActivity, CartActivity::class.java)
            startActivity(i)
        })
        back.setOnClickListener(View.OnClickListener { finish() })
        val shop = HomeshopadViewallapter(mContext)
        rec_nearshop.setLayoutManager(GridLayoutManager(this, 2))
        rec_nearshop.setAdapter(shop)
    }
}