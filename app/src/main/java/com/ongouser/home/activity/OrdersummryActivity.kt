package com.ongouser.home.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.ongouser.R
import com.ongouser.base.BaseActivity

class OrdersummryActivity : BaseActivity(), View.OnClickListener {
    lateinit var ivBack: ImageView
    lateinit var mContext: OrdersummryActivity

    override fun getContentId(): Int {
        return R.layout.activity_ordersummry
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext= this
        ivBack = findViewById(R.id.ivBack)
        ivBack.setOnClickListener(mContext)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.ivBack -> {
                onLeftIconClick()
            }
        }
    }
}