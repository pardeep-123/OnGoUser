package com.ongouser.home.activity


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status
import com.ongouser.pojo.ChangePasswordResponse

import com.ongouser.utils.others.CommonMethods
import com.ongouser.viewmodel.MyAccountViewModel
import kotlinx.android.synthetic.main.activity_change_password.*


class ChangePasswordActivity : BaseActivity(), View.OnClickListener, Observer<RestObservable> {
// all good
    private lateinit var mContext: ChangePasswordActivity

    private val viewModel: MyAccountViewModel
            by lazy { ViewModelProviders.of(this).get(MyAccountViewModel::class.java) }

    override fun getContentId(): Int {
        return R.layout.activity_change_password
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        ivBack.setOnClickListener(mContext)
        btnUpdate.setOnClickListener(mContext)
    }

    private fun changePasswordApi() {
        if (isValid()) {
        val map = HashMap<String, String>()
        map.put("oldPassword", et_oldpassword.text.toString().trim())
        map.put("newPassword", et_newpassword.text.toString().trim())

        viewModel.changePassword(this, true, map)
        viewModel.mResponse.observe(this, this)
    }}


    private fun isValid(): Boolean {
        var check = false
        if (!mValidationClass.isNetworkConnected)
            showAlerterRed(resources.getString(R.string.no_internet))

        else if (mValidationClass.checkStringNull(et_oldpassword.text.toString().trim()))
            showAlerterRed(resources.getString(R.string.error_password))

        else if (!mValidationClass.isValidPassword(et_oldpassword.text.toString().trim()))
            showAlerterRed(resources.getString(R.string.error_password_length))

        else if (mValidationClass.checkStringNull(et_newpassword.text.toString().trim()))
            showAlerterRed(resources.getString(R.string.error_new_password))

        else if (!mValidationClass.isValidPassword(et_newpassword.text.toString().trim()))
            showAlerterRed(resources.getString(R.string.error_password_length))

        else if (mValidationClass.checkStringNull(et_confirmpassword.text.toString().trim()))
            showAlerterRed(resources.getString(R.string.error_cpassword))

        else if (!et_confirmpassword.text.toString().trim().equals(et_newpassword.text.toString()) ) {
            showAlerterRed(resources.getString(R.string.error_password_not_matched))
        } else
            check = true
        return check
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.ivBack -> {
                onLeftIconClick()
            }
            R.id.btnUpdate -> {
                changePasswordApi()
                hideKeyboard(btnUpdate)
            }
        }
    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is ChangePasswordResponse) {
                    val changePasswordResponse: ChangePasswordResponse = it.data
                    if (changePasswordResponse.getCode()!!.equals("200")) {
                     //   showSuccessToast(mContext, changePasswordResponse!!.getMessage()!!)
                        showSuccessToast(mContext, "Password updated successfully.")
                        finish()
                    } else {
                        CommonMethods.AlertErrorMessage(mContext, changePasswordResponse.getMessage())
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
