package com.ongouser.home.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.home.activity.address.AddressListActivity

class PickupOptionsActivity : BaseActivity(), View.OnClickListener {

    lateinit var btnConfirm: Button
    lateinit var mContext: PickupOptionsActivity
    lateinit var cardhome: CardView
    lateinit var cardpackage: CardView
    lateinit var cardpick: CardView
    lateinit var recyclerview: RecyclerView
    lateinit var ivBack: ImageView
    lateinit var tvpackage: TextView
    lateinit var tv_homedelivery: TextView
    lateinit var tvpick: TextView

    override fun getContentId(): Int {
        return R.layout.activity_delivery_options1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this
        btnConfirm = findViewById(R.id.btnConfirm)
        cardpackage = findViewById(R.id.cardpackage)
        cardhome = findViewById(R.id.cardhome)
        ivBack = findViewById(R.id.ivBack)
        tvpackage = findViewById(R.id.tvpackage)
        tv_homedelivery = findViewById(R.id.tv_homedelivery)
        tvpick = findViewById(R.id.tvpick)
        cardpick = findViewById(R.id.cardpick)

        ivBack.setOnClickListener(mContext)
        btnConfirm.setOnClickListener(mContext)
        cardhome.setOnClickListener(mContext)
        cardpackage.setOnClickListener(mContext)
        cardpick.setOnClickListener(mContext)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.cardpick -> {
                cardpackage.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white))
                cardpick.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark))
                cardhome.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white))
                tvpick.setTextColor(ContextCompat.getColor(mContext, R.color.white))
                tvpackage.setTextColor(ContextCompat.getColor(mContext, R.color.black))
                tv_homedelivery.setTextColor(ContextCompat.getColor(mContext, R.color.black))
            }
            R.id.cardpackage -> {
                cardpick.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white))
                cardpackage.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark))
                tvpackage.setTextColor(ContextCompat.getColor(mContext, R.color.white))
                tvpick.setTextColor(ContextCompat.getColor(mContext, R.color.black))
                tv_homedelivery.setTextColor(ContextCompat.getColor(mContext, R.color.black))
                cardhome.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white))
            }
            R.id.cardhome -> {
                cardpick.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white))
                cardpackage.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white))
                cardhome.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark))
                tv_homedelivery.setTextColor(ContextCompat.getColor(mContext, R.color.white))
                tvpick.setTextColor(ContextCompat.getColor(mContext, R.color.black))
                tvpackage.setTextColor(ContextCompat.getColor(mContext, R.color.black))
            }
            R.id.btnConfirm -> {
                val intent = Intent(this, AddressListActivity::class.java)
                startActivity(intent)
            }
              R.id.ivBack -> {
               onLeftIconClick()
            }

        }
    }
}