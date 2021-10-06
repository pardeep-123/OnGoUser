package com.ongouser.home.fragment

import android.app.Activity
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
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.ongouser.Adapter.HomeShopAdapter
import com.ongouser.Adapter.Homegroceriesdapter
import com.ongouser.Adapter.ImageAdapter
import com.ongouser.home.NotificationActivity
import com.ongouser.home.activity.cart.CartActivity
import com.ongouser.home.activity.ViewAllActivity
import com.ongouser.R
import com.ongouser.home.activity.product.ProductlistingSearch
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status
import com.ongouser.pojo.BannersItem
import com.ongouser.pojo.HomeListingResponse
import com.ongouser.pojo.ProductsItem
import com.ongouser.pojo.VendorsItem
import com.ongouser.utils.helperclasses.CheckLocationFragment
import com.ongouser.utils.others.CommonMethods
import com.ongouser.utils.others.Constants
import com.ongouser.utils.others.MyApplication
import com.ongouser.utils.others.SharedPrefUtil
import com.ongouser.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_view_all.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.tv_cartbadges
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : CheckLocationFragment(), View.OnClickListener, Observer<RestObservable>{

    private var latLng: LatLng?=null
    lateinit var v: View
    lateinit var iVNoti: ImageView
    lateinit var searchrl: RelativeLayout
    lateinit var cart: ImageView
    lateinit var recNearbyStores: RecyclerView
    lateinit var rec_groceries: RecyclerView
    lateinit var btnCurrent: Button
    lateinit var btnPast: Button
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    lateinit var tv_viewallgroceri: TextView
    lateinit var tv_viewallshop: TextView
    private var tv_location: TextView?=null
    lateinit var tvNoStore: TextView
    lateinit var mContext: Context
    private var currentlat = ""
    private lateinit var bannerviewpager:ViewPager

    private var currentlongitude: String = ""
    private val autoCompleteRequest = 1


    private val viewModel: HomeViewModel
            by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }

    private lateinit var homeShopAdapter: HomeShopAdapter


    companion object {

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.fragment_home, container, false)
        iVNoti = v.findViewById(R.id.iVNoti)
        recNearbyStores = v.findViewById(R.id.rec_nearby)
        tvNoStore = v.findViewById(R.id.tv_no_store)
        searchrl = v.findViewById(R.id.searchrl)
        rec_groceries = v.findViewById(R.id.rec_groceries)
        tv_viewallgroceri = v.findViewById(R.id.tv_viewallgroceri)
        tv_viewallshop = v.findViewById(R.id.tv_viewallshop)
        cart = v.findViewById(R.id.cart)
        bannerviewpager = v.findViewById(R.id.bannervp)
        tv_location = v.findViewById(R.id.tv_location)


        iVNoti.setOnClickListener(this)
        tv_viewallgroceri.setOnClickListener(this)
        tv_viewallshop.setOnClickListener(this)
        cart.setOnClickListener(this)
      //  getHomeListingApi()
        checkPermissionLocation(activity)
         searchrl.setOnClickListener {

             val intent=Intent(requireActivity(),ProductlistingSearch::class.java)
             startActivity(intent)
             //startActivity(Intent(requireContext(),ProductlistingSearch::class.java))
         }
        /**
         *@author Pardeep Sharma
         *  Created on 19 August 2021
         *  function for select the location from map
         */
        val apiKey = getString(R.string.api_key_map)
        Places.initialize(mContext, apiKey)
        //set On click listener on location EditText
        tv_location?.setOnClickListener {
            // Set the fields to specify which types of place data to
// return after the user has made a selection.
            val fields = listOf(
                Place.Field.ID,
                Place.Field.NAME,
                Place.Field.LAT_LNG,
                Place.Field.ADDRESS_COMPONENTS,
                Place.Field.ADDRESS)

// Start the autocomplete intent.
            val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                .build(mContext)
            startActivityForResult(intent, autoCompleteRequest)
        }

        /**
         *  Ends here
         */

        return v
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == autoCompleteRequest){
            when (resultCode) {
                Activity.RESULT_OK -> {

                    data?.let {
                        val place = Autocomplete.getPlaceFromIntent(data)
                        Log.d(
                            "locationNew",
                            "Place: ${place.name}, ${place.id}, ${place.addressComponents}, ${place.address}")

                        tv_location?.text = place.address.toString()

                        latLng = place.latLng
                        currentlat = latLng!!.latitude.toString()
                        currentlongitude = latLng!!.longitude.toString()

                        getHomeListingApi()
                    }
                }

                AutocompleteActivity.RESULT_ERROR -> {
                    data?.let {
                        val status = Autocomplete.getStatusFromIntent(data)
                        Log.i("TAG", status.statusMessage!!)
                    }
                }

                Activity.RESULT_CANCELED -> {
// The user canceled the operation.
                }
            }
        }
    }
    override fun onLocationGet(latitude: String?, longitude: String?) {
        currentlat = latitude!!
        currentlongitude = longitude!!
        Log.e("currentlat", currentlat)
        Log.e("currentlongitude", currentlongitude)

        try {
            tv_location?.text=  CommonMethods.getAddressFromLatLong(requireActivity(),currentlat,currentlongitude)
        } catch (e: Exception) {
        }
        getHomeListingApi()
    }

    private fun getHomeListingApi() {
        if (!MyApplication.hasNetwork())
            showAlerterRed(resources.getString(R.string.no_internet))
        else {
            val map = HashMap<String, String>()
            map["latitude"] = currentlat
            map["longitude"] = currentlongitude
            map["range"] = "10000"
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
                val i = Intent(activity, ViewAllActivity::class.java)
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

    fun setHomeshopadapter(vendors: ArrayList<VendorsItem>?) {
        homeShopAdapter = HomeShopAdapter(activity, vendors!!)
        recNearbyStores.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        recNearbyStores.adapter = homeShopAdapter

    }

    override fun onResume() {
        Log.e("DEBUG", "onResume of LoginFragment")
        super.onResume()

        tv_cartbadges?.text = SharedPrefUtil.getInstance().badge.toString()
     //   getHomeListingApi()
        if (SharedPrefUtil.getInstance().badge.toString()=="0"){
            homebadgeLayout.visibility = View.GONE
        }else
            homebadgeLayout.visibility = View.VISIBLE
    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is HomeListingResponse) {
                    val homeListingResponse: HomeListingResponse = it.data
                    if (homeListingResponse.code == Constants.success_code) {
                       // showSuccessToast(homeListingResponse.message)

                       bannerviewpager.adapter = ImageAdapter(requireContext(),homeListingResponse.body!!.banners!!)

                        val homegroceriesdapter = Homegroceriesdapter(requireActivity(),homeListingResponse.body.categories!!)
                        rec_groceries.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
                        rec_groceries.adapter = homegroceriesdapter
                        if (homeListingResponse.body.vendors!!.size ==0){
                            tvNoStore.visibility = View.VISIBLE
                            recNearbyStores.visibility = View.GONE
                        }else {
                            tvNoStore.visibility = View.GONE
                            recNearbyStores.visibility = View.VISIBLE
                            setHomeshopadapter(homeListingResponse.body.vendors)
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