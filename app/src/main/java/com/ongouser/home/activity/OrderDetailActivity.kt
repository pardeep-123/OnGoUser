package com.ongouser.home.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.Adapter.OrderDetailAdapter

class OrderDetailActivity : BaseActivity() , View.OnClickListener{

    lateinit var mContext: OrderDetailActivity
    lateinit var v: View
    lateinit var back: ImageView
    lateinit var rec_order: RecyclerView
    lateinit var btnCurrent: Button
    lateinit var btnPast: Button
    lateinit var orderDetailAdapter: OrderDetailAdapter
    override fun getContentId(): Int {
        return R.layout.activity_order_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this
        btnPast = findViewById(R.id.btnPast)
        btnCurrent = findViewById(R.id.btnCurrent)
        rec_order = findViewById(R.id.rec_order)
        back = findViewById(R.id.back)

        orderDetailAdapter = OrderDetailAdapter(mContext, "1")
        rec_order.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        rec_order.adapter = orderDetailAdapter

        back.setOnClickListener(mContext)
        btnCurrent.setOnClickListener(mContext)
        btnPast.setOnClickListener(mContext)

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.back -> {
              onLeftIconClick()
            }
            R.id.btnPast -> {
                btnPast.setTextColor(resources.getColor(R.color.white))
                btnCurrent.setTextColor(resources.getColor(R.color.ligthgrey))
                val shop = OrderDetailAdapter(mContext, "2")
                rec_order.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
                rec_order.adapter = shop
            }
            R.id.btnCurrent -> {
                btnCurrent.setTextColor(resources.getColor(R.color.white))
                btnPast.setTextColor(resources.getColor(R.color.ligthgrey))
                val shop = OrderDetailAdapter(mContext, "1")
                rec_order.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
                rec_order.adapter = shop
            }
        }
    }
}