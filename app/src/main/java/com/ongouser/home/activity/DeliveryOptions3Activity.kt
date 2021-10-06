package com.ongouser.home.activity

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.Login.LoginActivity
import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.Adapter.DeliveryOptions3Adapter

class DeliveryOptions3Activity : BaseActivity() {
    lateinit var mContext: Context
    lateinit var btnConfirm: Button
    lateinit var dialog: Dialog
    lateinit var recyclerview: RecyclerView
    lateinit var deliveryOptions3Adapter: DeliveryOptions3Adapter
    override fun getContentId(): Int {
        return R.layout.activity_delivery_options3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        btnConfirm = findViewById(R.id.btnConfirm)
        btnConfirm.setOnClickListener(View.OnClickListener { showDailog() })
        val ivBack = findViewById<ImageView>(R.id.ivBack)
        ivBack.setOnClickListener { finish() }
        recyclerview = findViewById(R.id.recyclerview)
        deliveryOptions3Adapter = DeliveryOptions3Adapter(mContext)
        recyclerview.setLayoutManager(LinearLayoutManager(mContext))
        recyclerview.setAdapter(deliveryOptions3Adapter)
    }

    fun showDailog() {
        dialog = Dialog(mContext!!)
        dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog!!.setContentView(R.layout.alert_delivery3)
        dialog!!.setCancelable(true)
        val btnOk = dialog!!.findViewById<Button>(R.id.btnOk)
        btnOk.setOnClickListener {
            mContext!!.startActivity(Intent(mContext, LoginActivity::class.java))
            dialog!!.dismiss()
        }
        dialog!!.show()
    }
}