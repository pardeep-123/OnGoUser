package com.ongouser.Login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ongouser.Home.HomeActivity
import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status
import com.ongouser.pojo.TermsConditionsResponse
import com.ongouser.utils.others.CommonMethods
import com.ongouser.utils.others.Constants
import com.ongouser.viewmodel.AuthViewModel
import com.trutraits.pojo.ResendOTPResponse
import com.trutraits.pojo.VerifyOTPResponse
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_verfication_code.*
import kotlinx.android.synthetic.main.activity_verfication_code.ivBack
import okhttp3.RequestBody
import java.util.HashMap


class VerficationCodeActivity : BaseActivity(), TextWatcher, View.OnClickListener, Observer<RestObservable> {

    private lateinit var mContext: VerficationCodeActivity
    private val viewModel: AuthViewModel
            by lazy { ViewModelProviders.of(this).get(AuthViewModel::class.java) }
    private var completeOTP = ""

    override fun getContentId(): Int {
        return R.layout.activity_verfication_code
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

    }

    fun init() {
        mContext = this
        ivBack.setOnClickListener(mContext)
        btnSubmit.setOnClickListener(mContext)
        tv_resend_otp.setOnClickListener(mContext)
        editTextone.addTextChangedListener(mContext)
        editTexttwo.addTextChangedListener(mContext)
        editTextthree.addTextChangedListener(mContext)
        editTextfour.addTextChangedListener(mContext)

    }

    private fun verifyOTPApi() {
        if (!mValidationClass.isNetworkConnected) {
            showAlerterRed(resources.getString(R.string.no_internet))
        } else {
            val map = HashMap<String, String>()
            map.put("otp", completeOTP)

            viewModel.verifyOTPApi(mContext, true, map)
            viewModel.mResponse.observe(this, mContext)
        }
    }

    private fun resendOTPApi() {
        if (!mValidationClass.isNetworkConnected) {
            showAlerterRed(resources.getString(R.string.no_internet))
        } else {
            val map = HashMap<String, String>()
            map.put(Constants.SecurityKey, Constants.SecurityKeyValue)

            viewModel.resendOtpApi(mContext, true, map)
            viewModel.mResponse.observe(this, mContext)
        }
    }


    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

    }

    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

    }

    override fun afterTextChanged(editable: Editable) {
/*
        if (editable.length == 1) {
            //  tv_Remaining_time.setVisibility(View.GONE);
            if (editTextone.length() == 1) {
                editTexttwo.requestFocus()
            }
            if (editTexttwo.length() == 1) {
                editTextthree.requestFocus()
            }
            if (editTextthree.length() == 1) {
                editTextfour.requestFocus()
            }
        } else if (editable.length == 0) {
            //  tv_Remaining_time.setVisibility(View.GONE);
            if (editTextfour.length() == 0) {
                editTextthree.requestFocus()
            }
            if (editTextthree.length() == 0) {
                editTexttwo.requestFocus()
            }
            if (editTexttwo.length() == 0) {
                editTextone.requestFocus()
            }
        } else {
            completeOTP = "1111"
*/
/*
            completeOTP= editTextone.text.toString().trim()+ editTexttwo.text.toString().trim()+
                    editTextthree.text.toString().trim()+editTextfour.text.toString().trim()
*//*


        }
*/
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.ivBack -> {
                onLeftIconClick()
            }
            R.id.tv_resend_otp -> {
                resendOTPApi()
            }
            R.id.btnSubmit -> {
                if (!editTextone.text.toString().trim().isEmpty() && !editTextone.text.toString().trim().isEmpty()
                        && !editTextone.text.toString().trim().isEmpty() && !editTextone.text.toString().trim().isEmpty()) {
                    completeOTP = "1111"
                    verifyOTPApi()
                } else {
                    CommonMethods.AlertErrorMessage(mContext, getString(R.string.otp_invalid_message))
                }

            }

        }
    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is VerifyOTPResponse) {
                    val verifyOTPResponse: VerifyOTPResponse = it.data
                    if (verifyOTPResponse.getCode()!!.equals(Constants.success_code)) {
                        showSuccessToast(mContext, verifyOTPResponse.getMessage()!!)
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finishAffinity()

                    } else {
                        CommonMethods.AlertErrorMessage(mContext, verifyOTPResponse.getMessage())
                    }

                }

                if (it.data is ResendOTPResponse) {
                    val resendOTPResponse: ResendOTPResponse = it.data
                    if (resendOTPResponse.getCode()!!.equals(Constants.success_code)) {
                         showSuccessToast(mContext, resendOTPResponse.getMessage()!!)
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)

                    } else {
                        CommonMethods.AlertErrorMessage(mContext, resendOTPResponse.getMessage())
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
