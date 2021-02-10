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
import com.ongouser.pojo.GetAddedCardListResponse
import com.ongouser.utils.others.CommonMethods
import com.ongouser.utils.others.Constants
import com.ongouser.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_payment.*
import java.util.*
import kotlin.collections.ArrayList

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
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

    fun cardIdMethod(position: Int, cardid: String?) {
        cardId = cardid!!
        if (mValidationClass!!.isNetworkConnected()) {
            if (cardId.equals("") || cardId == null) {
                CommonMethods.AlertErrorMessage(mContext, "Please select/add card")
            } else {

            }
        } else {
            CommonMethods.failureMethod(mContext, getString(R.string.no_internet))
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
        var itemid = p0!!.id
        when (itemid) {
            R.id.btnPay -> {
                //   paymentAPIMethod()
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
        if (!mValidationClass!!.isNetworkConnected()) {
            showAlerterRed(resources.getString(R.string.no_internet))
        } else {
            val map = HashMap<String, String>()
            map.put("id", id!!)
            viewModel.deleteCardAPI(mContext, true, map)
        }

    }


    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is GetAddedCardListResponse) {
                    val getAddedCardListResponse: GetAddedCardListResponse = it.data
                    if (getAddedCardListResponse.getCode() == Constants.success_code) {
                        savedCardList.clear()
                        savedCardList.addAll(getAddedCardListResponse!!.getBody()!!)

                        if (getAddedCardListResponse.getBody()!!.size == 0) {
                            no_payment_card.visibility = View.VISIBLE
                            rv_payment_card.visibility = View.GONE
                        } else {
                            no_payment_card.visibility = View.GONE
                            rv_payment_card.visibility = View.VISIBLE
                            setSavedCardAdapter(getAddedCardListResponse.getBody()!!)
                        }
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