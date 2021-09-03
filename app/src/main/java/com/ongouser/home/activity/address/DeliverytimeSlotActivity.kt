package com.ongouser.home.activity.address

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.Adapter.DeliverytimeslotAdapter
import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.home.activity.payment.PaymentActivity
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status
import com.ongouser.pojo.DeliverySlotModel
import com.ongouser.pojo.Deliveryslotmodelbody
import com.ongouser.utils.others.Constants
import com.ongouser.viewmodel.HomeViewModel

import java.util.*
import kotlin.collections.ArrayList

class DeliverytimeSlotActivity : BaseActivity(), Observer<RestObservable?> {
    private val viewModel: HomeViewModel
            by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }

    var rec_timeslot: RecyclerView? = null
    var ivBack: ImageView? = null
    var mContext: Context? = null
    var btnext: Button? = null
    var list = ArrayList<String>()
    var totalamount = ""
    var ispickedup = ""
    var addressid = ""
    var vendorid = ""
    var date = ""
    var timeslot = ""
    var totalFee = ""
    var totalTax = ""
    override fun getContentId(): Int {
        return R.layout.activity_deliverytime_slot
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        totalamount = intent.getStringExtra(Constants.TotalAmount)!!
        ispickedup = intent.getStringExtra(Constants.isPickedup)!!
        addressid = intent.getStringExtra(Constants.AddressId)!!
        vendorid = intent.getStringExtra(Constants.VendorId)!!
        totalFee = intent.getStringExtra(Constants.TotalFee)!!
        totalTax = intent.getStringExtra(Constants.TotalTax)!!
        rec_timeslot = findViewById(R.id.rec_timeslot)
        ivBack = findViewById(R.id.ivBack)
        btnext = findViewById(R.id.btnext)
        list.add("Today")
        list.add("Tomorrow")
        list.add("Sunday")
        list.add("Monday")

        viewModel.getdeliverytimeslots(this
                , true,vendorid)
        viewModel.mResponse.observe(this, this)

        ivBack!!.setOnClickListener { finish() }
        btnext!!.setOnClickListener {

            if (date.isEmpty()) {
                showAlerterRed("Please select time slot")
            } else {
                val intent = Intent(this@DeliverytimeSlotActivity, PaymentActivity::class.java)
                intent.putExtra(Constants.isPickedup, ispickedup)
                intent.putExtra(Constants.AddressId, addressid)
                intent.putExtra(Constants.TimeslotDAy, date)
                intent.putExtra(Constants.TimeslotsTime, timeslot)
                intent.putExtra(Constants.TotalAmount, totalamount)

                intent.putExtra(Constants.TotalFee,totalFee)
                intent.putExtra(Constants.TotalTax,totalTax)

                intent.putExtra("data", "call")
                startActivity(intent)
            }

        }
    }

    override fun onChanged(it: RestObservable?) {

        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is DeliverySlotModel) {
                    val deliverySlotModel: DeliverySlotModel = it.data
                    if (deliverySlotModel.code == Constants.success_code) {
                        val slotmodelbody: MutableList<Deliveryslotmodelbody> = ArrayList()
                        deliverySlotModel.body!!.forEach {
                            if (it.noDelivery==0)
                            {
                                slotmodelbody.add(it)
                            }

                        }

                        val deliverytimeslotAdapter = DeliverytimeslotAdapter(mContext!!, slotmodelbody,object :DeliverytimeslotAdapter.Timeslotclick{
                            override fun onClick(day: String, slot: String) {
                                date = day
                                timeslot =slot
                            }

                        })
                        rec_timeslot!!.layoutManager = LinearLayoutManager(mContext)
                        rec_timeslot!!.adapter = deliverytimeslotAdapter
                    }
                    else
                    {
                        showAlerterGreen(deliverySlotModel.message!!)
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