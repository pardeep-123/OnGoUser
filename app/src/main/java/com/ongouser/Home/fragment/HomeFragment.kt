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
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.ongouser.Adapter.HomeShopAdapter
import com.ongouser.Adapter.Homegroceriesdapter
import com.ongouser.Home.NotificationActivity
import com.ongouser.Home.activity.CartActivity
import com.ongouser.Home.activity.ViewAllactivity
import com.ongouser.R
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status
import com.ongouser.pojo.HomeListingResponse
import com.ongouser.utils.helperclasses.CheckLocationFragment
import com.ongouser.utils.others.Constants
import com.ongouser.utils.others.MyApplication
import com.ongouser.viewmodel.HomeViewModel
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : CheckLocationFragment(), View.OnClickListener, Observer<RestObservable>{

    lateinit var v: View
    lateinit var iVNoti: ImageView
    lateinit var cart: ImageView
    lateinit var recNearbyStores: RecyclerView
    lateinit var rec_groceries: RecyclerView
    lateinit var btnCurrent: Button
    lateinit var btnPast: Button
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    lateinit var tv_viewallgroceri: TextView
    lateinit var tv_viewallshop: TextView
    lateinit var tvNoStore: TextView
    lateinit var mContext: Context
    private var currentlat = ""
    private var currentlongitude: String = ""

    private var homeVendorsList: ArrayList<HomeListingResponse.Vendor> = ArrayList()
    private var productList: ArrayList<HomeListingResponse.Product> = ArrayList()
    private var bannerList: ArrayList<HomeListingResponse.Banner> = ArrayList()

    private val viewModel: HomeViewModel
            by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }

    private lateinit var homeShopAdapter: HomeShopAdapter


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
        recNearbyStores = v.findViewById(R.id.rec_nearby)
        tvNoStore = v.findViewById(R.id.tv_no_store)
        rec_groceries = v.findViewById(R.id.rec_groceries)
        tv_viewallgroceri = v.findViewById(R.id.tv_viewallgroceri)
        tv_viewallshop = v.findViewById(R.id.tv_viewallshop)
        cart = v.findViewById(R.id.cart)


        iVNoti.setOnClickListener(this)
        tv_viewallgroceri.setOnClickListener(this)
        tv_viewallshop.setOnClickListener(this)
        cart.setOnClickListener(this)
        checkPermissionLocation(activity)

        setHomegroceriesdapter()
        return v
    }


    override fun onLocationGet(latitude: String?, longitude: String?) {
        currentlat = latitude!!
        currentlongitude = longitude!!
        Log.e("currentlat", currentlat)
        Log.e("currentlongitude", currentlongitude)

        getHomeListingApi()
    }

    private fun getHomeListingApi() {
        if (!MyApplication.hasNetwork())
            showAlerterRed(resources.getString(R.string.no_internet))
        else {
            val map = HashMap<String, String>()
            map.put("latitude", currentlat)
            map.put("longitude", currentlongitude)
            map.put("range", "10000")

            viewModel.getHomeListing(requireActivity(), true, map)
            viewModel.mResponse.observe(requireActivity(), this)
        }
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

    fun setHomeshopadapter(vendors: ArrayList<HomeListingResponse.Vendor>?) {
        homeShopAdapter = HomeShopAdapter(activity, vendors!!)
        recNearbyStores.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        recNearbyStores.adapter = homeShopAdapter

    }

    fun setHomegroceriesdapter (){
        val homegroceriesdapter = Homegroceriesdapter(activity)
        rec_groceries.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        rec_groceries.adapter = homegroceriesdapter

    }

    override fun onResume() {
        Log.e("DEBUG", "onResume of LoginFragment")
        super.onResume()
     //   getHomeListingApi()
    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is HomeListingResponse) {
                    val homeListingResponse: HomeListingResponse = it.data
                    if (homeListingResponse.code == Constants.success_code) {
                        showSuccessToast(homeListingResponse.message)

                        homeVendorsList.clear()
                        homeVendorsList.addAll(homeListingResponse.body!!.vendors!!)

                        if (homeVendorsList.size ==0){
                            tvNoStore.visibility = View.VISIBLE
                            recNearbyStores.visibility = View.GONE
                        }else {
                            tvNoStore.visibility = View.GONE
                            recNearbyStores.visibility = View.VISIBLE
                            setHomeshopadapter(homeListingResponse.body!!.vendors)
                        }

                    }

                    else{
                        showAlerterRed(homeListingResponse.code as String)

                    }
                }
            }
            it.status == Status.ERROR -> {
                if (it.data != null) {
                    showAlerterRed(it.data as String)
                } else {
                    showAlerterRed(it.error!!.toString())
                }
            }
            it.status == Status.LOADING -> {

            }
        }

    }

}