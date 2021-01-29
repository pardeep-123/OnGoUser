package com.ongouser.Home.activity

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity

import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.facebook.CallbackManager
import com.facebook.share.model.ShareHashtag
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareDialog
import com.sdsmdg.tastytoast.TastyToast
import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status
import com.ongouser.pojo.*
import com.ongouser.utils.others.CommonMethods
import com.ongouser.utils.others.Constants
import com.ongouser.utils.others.MyApplication
import com.ongouser.viewmodel.HomeViewModel
import com.ongouser.pojo.GetProfileResponse
import kotlinx.android.synthetic.main.fragment_profile.*


class MyProfileActivity : BaseActivity(), View.OnClickListener, Observer<RestObservable> {

    private lateinit var mContext: MyProfileActivity
    private var userId=""
    private var userImage=""
    private var countryCode=""
    private var phone=""


    private val viewModel: HomeViewModel
            by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }

    override fun getContentId(): Int {
        return R.layout.fragment_profile
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    fun init() {
        mContext = this
        ivBack.setOnClickListener(mContext)
        btnEditProfile.setOnClickListener(mContext)

    }

    private fun getProfileApi() {
        if (MyApplication.instance!!.checkIfHasNetwork()) {
            val map = HashMap<String, String>()
            map.put(Constants.SecurityKey, Constants.SecurityKeyValue)
            viewModel.getProfile(this, false)
            viewModel.mResponse.observe(this, this)
        } else {
            showAlerterRed(resources.getString(R.string.no_internet))
        }
    }

    override fun onResume() {
        super.onResume()
        getProfileApi()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.ivBack -> {
                onLeftIconClick()
            }
            R.id.btnEditProfile -> {
                var intent = Intent(mContext, EditProfileActivity::class.java)
                intent.putExtra("name", tv_name.text)
                intent.putExtra("email", tv_email.text.toString())
                intent.putExtra("phone", phone)
                intent.putExtra("countryCode", countryCode)
                intent.putExtra("image", userImage)
                intent.putExtra("userId", userId)

                startActivity(intent)
            }

        }
    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is GetProfileResponse) {
                    val getProfileResponse: GetProfileResponse = it.data
                    if (getProfileResponse.getCode()!!.equals(Constants.success_code)) {
                    //    showSuccessToast(mContext, getProfileResponse!!.getMessage()!!)
                        userId= getProfileResponse.getBody()!!.id!!.toString()
                        userImage= getProfileResponse.getBody()!!.image!!
                        phone= getProfileResponse.getBody()!!.phone!!
                        countryCode= getProfileResponse.getBody()!!.countryCode!!
                        tv_name.setText(getProfileResponse.getBody()!!.name)
                        tv_phone.setText("+"+countryCode+"-"+phone)
                        tv_email.setText(getProfileResponse.getBody()!!.email)
                        Glide.with(mContext).load(getProfileResponse.getBody()!!.image).error(R.mipmap.no_image_placeholder).into(iv_user_pic)

                    } else {
                        CommonMethods.AlertErrorMessage(mContext, getProfileResponse.getMessage())
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
