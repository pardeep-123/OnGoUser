package com.ongouser.home.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.Adapter.AddressShopListingAdapter
import com.ongouser.Adapter.OrderDetailAdapter
import com.ongouser.Adapter.OrderDetailcartAdapter
import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status
import com.ongouser.pojo.CommonModel
import com.ongouser.pojo.GetAddressListResponse
import com.ongouser.pojo.OrderDetailModel
import com.ongouser.pojo.ShopAdressModel
import com.ongouser.utils.others.Constants
import com.ongouser.utils.others.MyApplication
import com.ongouser.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_address_listing.*
import kotlinx.android.synthetic.main.activity_ordersummry.*
import java.util.*
import kotlin.collections.ArrayList

class OrdersummryActivity : BaseActivity(), View.OnClickListener, Observer<RestObservable> {
    lateinit var ivBack: ImageView
    lateinit var mContext: OrdersummryActivity
    private val viewModel: HomeViewModel
            by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }
    override fun getContentId(): Int {
        return R.layout.activity_ordersummry
    }
   var imagelist = ArrayList<ImageView>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext= this
        ivBack = findViewById(R.id.ivBack)
        ivBack.setOnClickListener(mContext)
        imagelist.add(findViewById<ImageView>(R.id.pendingimage))
        imagelist.add(findViewById<ImageView>(R.id.acceptedimage))
        imagelist.add(findViewById<ImageView>(R.id.packedimage))
        imagelist.add(findViewById<ImageView>(R.id.shippedimage))
        imagelist.add(findViewById<ImageView>(R.id.deliveredimage))
        getorders()
    }

    override fun onBackPressed() {

        if (intent.hasExtra("from"))
        {
            val intent = Intent(this,OrderDetailActivity::class.java)
            intent.putExtra("from","notification")
            startActivity(intent)
            finish()
        }
        else
        {
            super.onBackPressed()
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.ivBack -> {
                onBackPressed()
            }
        }
    }

    private fun getorders() {
        if (!MyApplication.instance!!.checkIfHasNetwork())
            showAlerterRed(resources.getString(R.string.no_internet))
        else {
            val map = HashMap<String, String>()
            map.put("id", intent.getStringExtra("id")!!)

            viewModel.getorderdetail(this
                    , true, map)
            viewModel.mResponse.observe(this, this)
        }
    }


    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is OrderDetailModel) {
                    val orderDetailModel: OrderDetailModel = it.data
                    if (orderDetailModel.code == Constants.success_code) {

                        orderDetailModel.body?.let {
                            setupimages(it.orderStatus!!)
                        }


                        orderdetailrec.adapter = OrderDetailcartAdapter(this,orderDetailModel.body!!.orderItems!!)
                    }

                }
                if (it.data is CommonModel) {

                    val data: CommonModel =it.data
                    showSuccessToast(this,data.message!!)
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

    fun setupimages(status:Int)
    {

            when(status)
            {
               0 ->{
                   acceptedimage.setColorFilter(ContextCompat.getColor(this,R.color.statuslightcolor))
                   packedimage.setColorFilter(ContextCompat.getColor(this,R.color.statuslightcolor))
                   shippedimage.setColorFilter(ContextCompat.getColor(this,R.color.statuslightcolor))
                   deliveredimage.setColorFilter(ContextCompat.getColor(this,R.color.statuslightcolor))


               }
                1 ->{
                  //  tv_accept.visibility=View.VISIBLE
                    packedimage.setColorFilter(ContextCompat.getColor(this,R.color.statuslightcolor))
                    shippedimage.setColorFilter(ContextCompat.getColor(this,R.color.statuslightcolor))
                    deliveredimage.setColorFilter(ContextCompat.getColor(this,R.color.statuslightcolor))
                }
                2 ->{
                  //  tv_accept.visibility=View.VISIBLE
                  //  tv_packed.visibility=View.VISIBLE


                    shippedimage.setColorFilter(ContextCompat.getColor(this,R.color.statuslightcolor))
                    deliveredimage.setColorFilter(ContextCompat.getColor(this,R.color.statuslightcolor))
                }
                3 ->{
                   // tv_accept.visibility=View.VISIBLE
                  //  tv_packed.visibility=View.VISIBLE
                  //  tv_shipped.visibility=View.VISIBLE


                    deliveredimage.setColorFilter(ContextCompat.getColor(this,R.color.statuslightcolor))
                }

                4 ->{
                 //   tv_accept.visibility=View.VISIBLE
                 //   tv_packed.visibility=View.VISIBLE
                  //  tv_shipped.visibility=View.VISIBLE
                  //  tv_deliver.visibility=View.VISIBLE

                    // all done
                }
            }

    }
}