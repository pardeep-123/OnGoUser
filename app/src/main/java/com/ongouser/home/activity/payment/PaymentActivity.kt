package com.ongouser.home.activity.payment

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.Adapter.SavedCardsAdapter
import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.home.HomeActivity
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status
import com.ongouser.pojo.CommonModel
import com.ongouser.pojo.DeleteCardResponse
import com.ongouser.pojo.GetAddedCardListResponse
import com.ongouser.utils.others.Constants
import com.ongouser.utils.others.SharedPrefUtil
import com.ongouser.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : BaseActivity(), View.OnClickListener, Observer<RestObservable> {
    lateinit var dialog: Dialog
    lateinit var mContext: PaymentActivity
    lateinit var savedCardsAdapter: SavedCardsAdapter
    var savedCardList: ArrayList<GetAddedCardListResponse.Body> = ArrayList()
    var pos = 0
    private var bookingId = ""
    private var cardId = ""
    private var from = ""
    private val viewModel: HomeViewModel
            by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }

    override fun getContentId(): Int {
        return R.layout.activity_payment
    }
    var totalamount = ""
    var ispickedup = ""
    var addressid = ""
    var timeslot=  ""
    var timeslotdates = ""
    var vendorid = ""
    var totalFee = ""
    var totalTax = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        totalamount = intent.getStringExtra(Constants.TotalAmount)!!
        ispickedup = intent.getStringExtra(Constants.isPickedup)!!
        addressid = intent.getStringExtra(Constants.AddressId)!!
        timeslot = intent.getStringExtra(Constants.TimeslotDAy)!!
        timeslotdates = intent.getStringExtra(Constants.TimeslotsTime)!!
        totalFee = intent.getStringExtra(Constants.TotalFee)!!
        totalTax = intent.getStringExtra(Constants.TotalTax)!!
        btnPay.setOnClickListener(mContext)
        ivBack.setOnClickListener(mContext)
        tv_add_card.setOnClickListener(mContext)

        try {
            if (intent != null) {
                bookingId = intent.getStringExtra("bookingId").toString()
                from = intent.getStringExtra("from").toString()
            }
        } catch (e: Exception) {

        }
    }


    override fun onResume() {
        super.onResume()
        if (!mValidationClass!!.isNetworkConnected()) {
            showAlerterRed(resources.getString(R.string.no_internet))
        } else {
            viewModel.allCardsAPI(this, true)
            viewModel.mResponse.observe(this, this)
        }

    }


    fun setSavedCardAdapter(savedCardList: ArrayList<GetAddedCardListResponse.Body>?) {
        savedCardsAdapter = SavedCardsAdapter(mContext, savedCardList!!, mContext)
        rv_payment_card.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        rv_payment_card.adapter = savedCardsAdapter
    }

    fun showDailog() {
        dialog = Dialog(mContext)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.alert_payment)
        dialog.setCancelable(true)
        val btnDone = dialog.findViewById<Button>(R.id.btnDone)
        btnDone.setOnClickListener {
            mContext.startActivity(Intent(mContext, HomeActivity::class.java))
            dialog.dismiss()
        }
        dialog.show()
    }


    override fun onClick(p0: View?) {
        val itemid = p0!!.id
        when (itemid) {
            R.id.btnPay -> {
                if (savedCardsAdapter.getselectedcardid().isEmpty())
                {
                    showErrorToast(this,"Please select card ")
                }
                else if (savedCardsAdapter.getselectedcvv().isEmpty())
                {
                    showErrorToast(this,"Please enter cvv number")
                }
                else
                {

                    val map = HashMap<String,String>()
                    map["amount"] =totalamount
                    map["isSelfpickup"] =ispickedup
                    map["deliveryDate"] =timeslotdates
                    map["deliverySlot"] =timeslot
                    map["userAddressId"] =addressid
                    map["gst_amount"] =totalTax
                    map["admin_commission"] =totalFee
                    map["cardId"] =savedCardsAdapter.getselectedcardid()
                    map["cvv"] =savedCardsAdapter.getselectedcvv()
                    viewModel.placeorderAPI(this, true,map)
                    viewModel.mResponse.observe(this, this)
                }
            }
            R.id.ivBack -> {
                onLeftIconClick()
            }
            R.id.tv_add_card -> {
                startActivity(Intent(mContext, AddCardDetailActivity::class.java))
            }
        }
    }


    fun deleteAPIMethod(position: Int, id: String?) {
        pos = position
        if (!mValidationClass.isNetworkConnected) {
            showAlerterRed(resources.getString(R.string.no_internet))
        } else {

            viewModel.deleteCardAPI(mContext, true, id!!)
        }

    }


    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is GetAddedCardListResponse) {
                    val getAddedCardListResponse: GetAddedCardListResponse = it.data
                    if (getAddedCardListResponse.getCode() == Constants.success_code) {
                        savedCardList.clear()
                        savedCardList.addAll(getAddedCardListResponse.getBody()!!)

                        for (i in 0 until savedCardList.size){
                            savedCardList[i].isSelected="false"
                        }
                        setSavedCardAdapter(savedCardList)
                        if (savedCardList.size == 0) {
                            no_payment_card.visibility = View.VISIBLE
                            rv_payment_card.visibility = View.GONE
                        } else {
                            no_payment_card.visibility = View.GONE
                            rv_payment_card.visibility = View.VISIBLE

                        }
                    }

                }

                if (it.data is DeleteCardResponse){
                    showSuccessToast(this,it.data.message)
                    savedCardList.removeAt(pos)
                    savedCardsAdapter.notifyDataSetChanged()

                    if (savedCardList.size == 0) {
                        no_payment_card.visibility = View.VISIBLE
                        rv_payment_card.visibility = View.GONE
                    } else {
                        no_payment_card.visibility = View.GONE
                        rv_payment_card.visibility = View.VISIBLE

                    }
                }
                if (it.data is CommonModel)
                {

                    val data:CommonModel = it.data
                    showSuccessToast(this,data.message!!)
                    SharedPrefUtil.getInstance().saveBadge(0)
                    val intent = Intent(this,HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
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