package com.ongouser.Login

import android.os.Bundle

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status
import com.ongouser.pojo.TermsConditionsResponse
import com.ongouser.utils.others.CommonMethods
import com.ongouser.utils.others.Constants
import com.ongouser.viewmodel.SettingsViewModel
import kotlinx.android.synthetic.main.activity_terms.*


class TermsConditionsActivity : BaseActivity(), View.OnClickListener, Observer<RestObservable> {

    private lateinit var mContext: TermsConditionsActivity
    private val viewModel: SettingsViewModel
            by lazy { ViewModelProviders.of(this).get(SettingsViewModel::class.java) }

    override fun getContentId(): Int {
        return R.layout.activity_terms
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

    }

    fun init() {
        mContext = this
        ivBack.setOnClickListener(mContext)
        termsConditionsApi()
    }

    private fun termsConditionsApi() {
        viewModel.getTermsConditionsAPI(mContext, true)
        viewModel.mResponse.observe(this, mContext)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.ivBack -> {
                onLeftIconClick()
            }

        }
    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is TermsConditionsResponse) {
                    val termsConditionsResponse: TermsConditionsResponse = it.data
                    if (termsConditionsResponse.getCode()!!.equals(Constants.success_code)) {
                        tv_terms.setText(termsConditionsResponse.getBody()!!.content)

                    }else {
                        CommonMethods.AlertErrorMessage(mContext, termsConditionsResponse.getMessage())
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
