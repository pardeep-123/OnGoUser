package com.ongouser.Home.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.ongouser.Adapter.Homegroceriesdapter
import com.ongouser.Adapter.Homeshopadapter
import com.ongouser.Home.NotificationActivity
import com.ongouser.Home.activity.CartActivity
import com.ongouser.Home.activity.ViewAllactivity
import com.ongouser.R
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.utils.helperclasses.CheckLocationFragment

class HomeFragment : CheckLocationFragment(), View.OnClickListener, Observer<RestObservable>{
    private lateinit var rvHomePost: RecyclerView
    private lateinit var rlNoHomePost: RelativeLayout
    private lateinit var rlTitleUsername: RelativeLayout
    lateinit var v: View
    lateinit var iVNoti: ImageView
    lateinit var cart: ImageView
    lateinit var rec_nearby: RecyclerView
    lateinit var rec_groceries: RecyclerView
    lateinit var btnCurrent: Button
    lateinit var btnPast: Button
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    lateinit var tv_viewallgroceri: TextView
    lateinit var tv_viewallshop: TextView
    lateinit var mContext: Context
    private var currentlat = ""
    private var currentlongitude: String = ""


    companion object {

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_home, container, false)
        iVNoti = v.findViewById(R.id.iVNoti)
        rec_nearby = v.findViewById(R.id.rec_nearby)
        rec_groceries = v.findViewById(R.id.rec_groceries)
        tv_viewallgroceri = v.findViewById(R.id.tv_viewallgroceri)
        tv_viewallshop = v.findViewById(R.id.tv_viewallshop)
        cart = v.findViewById(R.id.cart)


        iVNoti.setOnClickListener(this)
        tv_viewallgroceri.setOnClickListener(this)
        tv_viewallshop.setOnClickListener(this)
        cart.setOnClickListener(this)
        checkPermissionLocation(activity)

        setHomeshopadapter()
        setHomegroceriesdapter()
        return v
    }

    override fun onLocationGet(latitude: String?, longitude: String?) {
        currentlat = latitude!!
        currentlongitude = longitude!!
        Log.e("currentlat", currentlat)
        Log.e("currentlongitude", currentlongitude)
    }


    override fun onPermissionGranted() {

    }

    override fun onClick(v: View?) {

        when (v!!.id) {
            R.id.iVNoti -> {
                val i = Intent(activity, NotificationActivity::class.java)
                startActivity(i)
            }
            R.id.tv_viewallgroceri -> {
                val i = Intent(activity, ViewAllactivity::class.java)
                startActivity(i)
            }
            R.id.cart -> {
                val i = Intent(activity, CartActivity::class.java)
                startActivity(i)
            }
            R.id.tv_viewallshop -> {
              //  val i = Intent(activity, NotificationActivity::class.java)
               // startActivity(i)
            }

        }
    }

    fun setHomeshopadapter (){
        val shop = Homeshopadapter(activity)
        rec_nearby.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        rec_nearby.adapter = shop

    }

    fun setHomegroceriesdapter (){
        val homegroceriesdapter = Homegroceriesdapter(activity)
        rec_groceries.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        rec_groceries.adapter = homegroceriesdapter

    }

    override fun onChanged(t: RestObservable?) {
        TODO("Not yet implemented")
    }

}