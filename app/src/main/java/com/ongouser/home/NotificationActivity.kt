package com.ongouser.home

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.Adapter.NotificationAdapter
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ongouser.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.ongouser.base.BaseActivity
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status
import com.ongouser.pojo.GetNotificationModel
import com.ongouser.utils.others.CommonMethods
import com.ongouser.utils.others.Constants
import com.ongouser.utils.others.MyApplication
import com.ongouser.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class NotificationActivity : BaseActivity(), Observer<RestObservable> {
    var ivBack: ImageView? = null
    var mContext: Context? = null
    var recyclerview: RecyclerView? = null
    var notificationAdapter: NotificationAdapter? = null
    private val viewModel: HomeViewModel
            by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }

    override fun getContentId(): Int {
        return R.layout.activity_notification
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        ivBack = findViewById(R.id.ivBack)
        ivBack?.setOnClickListener(View.OnClickListener { finish() })
        recyclerview = findViewById(R.id.recyclerview)

        getnotificationapi()
    }
    private fun getnotificationapi() {
        if (!MyApplication.hasNetwork())
            showAlerterRed(resources.getString(R.string.no_internet))
        else {

            viewModel.getnotificationapi(this, true)
            viewModel.mResponse.observe(this, this)
        }
    }
    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is GetNotificationModel) {
                    val getNotificationModel: GetNotificationModel = it.data
                    if (getNotificationModel.code!!.equals(Constants.success_code)) {
                        notificationAdapter = NotificationAdapter(mContext!!,getNotificationModel)
                        recyclerview?.setLayoutManager(LinearLayoutManager(mContext))
                        recyclerview?.setAdapter(notificationAdapter!!)

                    } else {
                        CommonMethods.AlertErrorMessage(this, getNotificationModel.message)
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