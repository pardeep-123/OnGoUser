package com.ongouser.home.activity.product

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.Adapter.CreatedProductAdapter
import com.ongouser.R
import com.ongouser.base.BaseActivity

class Productlisting : BaseActivity() {
  
    lateinit var ivBack: ImageView
    lateinit var cart: ImageView
    lateinit var btn_filter: Button
    lateinit var mContext: Productlisting
    lateinit var btn_sort: Button
    lateinit var recyclerview: RecyclerView
    lateinit var productAdapter: CreatedProductAdapter
    override fun getContentId(): Int {
        return R.layout.activity_productlisting
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ivBack = findViewById(R.id.back)
        mContext = this
        recyclerview = findViewById(R.id.rec_product)
        btn_sort = findViewById(R.id.btn_sort)
        btn_filter = findViewById(R.id.btn_filter)
        cart = findViewById(R.id.cart)

        ivBack.setOnClickListener(View.OnClickListener {
            onLeftIconClick() })

        productAdapter = CreatedProductAdapter(mContext)
        recyclerview.setLayoutManager(LinearLayoutManager(mContext))
        recyclerview.setAdapter(productAdapter)
    }
}