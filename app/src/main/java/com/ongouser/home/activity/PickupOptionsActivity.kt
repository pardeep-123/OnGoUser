package com.ongouser.home.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.home.activity.address.AddressListActivity
import com.ongouser.utils.others.Constants

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
    var ispickup = ""
    var totalamount = ""
    var vendorid = ""
    var totalFee = ""
    var totalTax = ""
    var homeDelivery = ""

    override fun getContentId(): Int {
        return R.layout.activity_delivery_options1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        totalamount = intent.getStringExtra(Constants.TotalAmount)!!
        vendorid = intent.getStringExtra(Constants.VendorId)!!

        totalFee = intent.getStringExtra(Constants.TotalFee)!!
        totalTax = intent.getStringExtra(Constants.TotalTax)!!
        homeDelivery = intent.getStringExtra("homeDelivery")!!

        Log.e("totalamount",totalamount)
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
              //  ispickup = "2"
                ispickup = "1"
                cardpackage.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white))
                cardpick.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark))
                cardhome.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white))
                tvpick.setTextColor(ContextCompat.getColor(mContext, R.color.white))
                tvpackage.setTextColor(ContextCompat.getColor(mContext, R.color.black))
                tv_homedelivery.setTextColor(ContextCompat.getColor(mContext, R.color.black))
            }
            R.id.cardpackage -> {
                ispickup = "1"
                cardpick.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white))
                cardpackage.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark))
                tvpackage.setTextColor(ContextCompat.getColor(mContext, R.color.white))
                tvpick.setTextColor(ContextCompat.getColor(mContext, R.color.black))
                tv_homedelivery.setTextColor(ContextCompat.getColor(mContext, R.color.black))
                cardhome.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white))
            }
            R.id.cardhome -> {
                 if (homeDelivery == "0"){
                     showAlerterRed("Shop does not provide Home Delivery")

                 }else {
                     ispickup = "0"
                     cardpick.setCardBackgroundColor(
                         ContextCompat.getColor(
                             mContext,
                             R.color.white
                         )
                     )
                     cardpackage.setCardBackgroundColor(
                         ContextCompat.getColor(
                             mContext,
                             R.color.white
                         )
                     )
                     cardhome.setCardBackgroundColor(
                         ContextCompat.getColor(
                             mContext,
                             R.color.colorPrimaryDark
                         )
                     )
                     tv_homedelivery.setTextColor(ContextCompat.getColor(mContext, R.color.white))
                     tvpick.setTextColor(ContextCompat.getColor(mContext, R.color.black))
                     tvpackage.setTextColor(ContextCompat.getColor(mContext, R.color.black))
                 }
            }
            R.id.btnConfirm -> {

                if (ispickup.isEmpty())
                    showAlerterRed("Please select a delivery option")

                else{
                    val intent = Intent(this, AddressListActivity::class.java)
                    intent.putExtra(Constants.TotalAmount,totalamount)
                    intent.putExtra(Constants.VendorId,vendorid)
                    intent.putExtra(Constants.isPickedup,ispickup)
                    intent.putExtra(Constants.TotalFee,totalFee)
                    intent.putExtra(Constants.TotalTax,totalTax)
                    startActivity(intent)
                }

            }
              R.id.ivBack -> {
               onLeftIconClick()
            }

        }
    }
}