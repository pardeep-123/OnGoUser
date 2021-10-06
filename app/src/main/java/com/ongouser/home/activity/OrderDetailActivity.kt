package com.ongouser.home.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.Adapter.OrderDetailAdapter
import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.home.HomeActivity
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status
import com.ongouser.pojo.MyOrderModel
import com.ongouser.pojo.PastdatesItem
import com.ongouser.utils.others.CommonMethods
import com.ongouser.utils.others.Constants
import com.ongouser.utils.others.MyApplication
import com.ongouser.viewmodel.HomeViewModel
import java.util.*
import kotlin.collections.ArrayList


class OrderDetailActivity : BaseActivity() , View.OnClickListener, Observer<RestObservable> {

    private val viewModel: HomeViewModel
            by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }
    lateinit var mContext: OrderDetailActivity
    lateinit var v: View
    lateinit var back: ImageView
    lateinit var rec_order: RecyclerView
    lateinit var btnCurrent: Button
    lateinit var btnPast: Button
    var orderlist = ArrayList<PastdatesItem>()
    var futureorderlist = ArrayList<PastdatesItem>()
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


        back.setOnClickListener(mContext)
        btnCurrent.setOnClickListener(mContext)
        btnPast.setOnClickListener(mContext)
        val lbm = LocalBroadcastManager.getInstance(this)
        lbm.registerReceiver(receiver, IntentFilter("filter_string"))
        getorders()
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
    }
    override fun onBackPressed() {
        if (intent.hasExtra("from"))
        {
            val intent = Intent(this,HomeActivity::class.java)
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
        when(v!!.id){
            R.id.back -> {
              onBackPressed()
            }
            R.id.btnPast -> {
                if (orderlist.isEmpty())
                {showAlerterRed("No  past orders found")}
                btnPast.setTextColor(resources.getColor(R.color.white))
                btnCurrent.setTextColor(resources.getColor(R.color.ligthgrey))
                val shop = OrderDetailAdapter(mContext, "2",orderlist)
                rec_order.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
                rec_order.adapter = shop
            }
            R.id.btnCurrent -> {
                if (futureorderlist.isEmpty())
                {showAlerterRed("No current orders found")}
                btnCurrent.setTextColor(resources.getColor(R.color.white))
                btnPast.setTextColor(resources.getColor(R.color.ligthgrey))
                val shop = OrderDetailAdapter(mContext, "1",futureorderlist)
                rec_order.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
                rec_order.adapter = shop
            }
        }

    }


    private fun getorders() {
        if (!MyApplication.instance!!.checkIfHasNetwork())
            showAlerterRed(resources.getString(R.string.no_internet))
        else {
            val map = HashMap<String, String>()
            map.put("saa", "0")

            viewModel.getmyorder(this
                    , true, map)
            viewModel.mResponse.observe(this, this)
        }
    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is MyOrderModel) {


                    val myOrderModel: MyOrderModel = it.data


                    if (myOrderModel.code == Constants.success_code) {

                        if (myOrderModel.body!!.pastdates!!.isEmpty()){
                            showAlerterRed("No orders found")
                        }else {
                            orderlist.clear()
                            orderlist.addAll(myOrderModel.body!!.pastdates!!)
                            Collections.reverse(orderlist)
                            futureorderlist.clear()
                            futureorderlist.addAll(myOrderModel.body!!.futureDates!!)
                            Collections.reverse(futureorderlist)
                            if (futureorderlist.isEmpty())
                            {showAlerterRed("No current orders found")}
                            orderDetailAdapter = OrderDetailAdapter(mContext, "1",futureorderlist)
                            rec_order.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
                            rec_order.adapter = orderDetailAdapter
                        }



                    } else {
                        CommonMethods.AlertErrorMessage(
                                this,
                                myOrderModel.message
                        )
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

    var receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent != null) {
                val id = intent.getStringExtra("id")
                showAlerterGreen("Refreshing...")
                getorders()
                // get all your data from intent and do what you want
            }
        }
    }
}