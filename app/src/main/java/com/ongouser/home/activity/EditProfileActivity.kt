package com.ongouser.home.activity

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log

import android.view.View

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.rilixtech.widget.countrycodepicker.Country
import com.rilixtech.widget.countrycodepicker.CountryCodePicker
import com.ongouser.R

import com.ongouser.base.BaseActivity
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status

import com.ongouser.utils.others.CommonMethods
import com.ongouser.utils.others.Constants
import com.ongouser.utils.others.SharedPrefUtil
import com.ongouser.pojo.EditProfileResponse
import com.ongouser.utils.others.MyApplication
import com.ongouser.viewmodel.MyAccountViewModel
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.AlbumFile
import com.yanzhenjie.album.api.widget.Widget

import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_edit_profile.ivBack
import kotlinx.android.synthetic.main.fragment_profile.*

import okhttp3.RequestBody
import java.util.HashMap


class EditProfileActivity : BaseActivity(), View.OnClickListener, Observer<RestObservable> {

    private lateinit var mContext: EditProfileActivity
    private var userId=""
    private var defaultImage=""
    var countryCode = ""


    var mImagePath =""
    private var mAlbumFiles = ArrayList<AlbumFile>()

    private val viewModel: MyAccountViewModel
            by lazy { ViewModelProviders.of(this).get(MyAccountViewModel::class.java) }

    override fun getContentId(): Int {
        return R.layout.activity_edit_profile
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    fun init() {
        mContext = this
        ivBack.setOnClickListener(mContext)
        btnUpdate.setOnClickListener(mContext)
        ivAdd.setOnClickListener(mContext)

        if (intent !=null){
            userId= intent.getStringExtra("userId")!!
            countryCode= intent.getStringExtra("countryCode")!!



            try {
                if (countryCode.equals("1") && intent.getStringExtra("country").equals("United States")){
                    ccp.defaultCountryCode
                }else {
                    ccp.setCountryForPhoneCode(countryCode.toInt())
                }
            } catch (e: Exception) {

            }

            et_name.setText(intent.getStringExtra("name"))
            et_email.setText(intent.getStringExtra("email"))
            et_phone.setText(intent.getStringExtra("phone"))
            if (!intent.getStringExtra("image")!!.isEmpty()) {
                defaultImage = intent.getStringExtra("image").toString()
               // Glide.with(mContext).load(defaultImage).error(R.mipmap.no_image_placeholder).into(iv_profile_pic)


                Glide.with(mContext)
                        .load(defaultImage)
                        .listener(object : RequestListener<Drawable?> {
                            override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable?>, isFirstResource: Boolean): Boolean {
                                progressedit.setVisibility(View.GONE)
                                return false
                            }

                            override fun onResourceReady(resource: Drawable?, model: Any, target: Target<Drawable?>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                                progressedit.setVisibility(View.GONE)
                                return false
                            }
                        })
                        .into(iv_profile_pic)

            }

        }


        ccp.setOnCountryChangeListener(object : CountryCodePicker.OnCountryChangeListener {
            override  fun onCountrySelected(selectedCountry: Country?) {
                ccp.getSelectedCountryCode()
                ccp.enableHint(true)
                ccp.setKeyboardAutoPopOnSearch(false)
                countryCode =  ccp.getSelectedCountryCode()
             }
        })

        rl_clicl.setOnClickListener {  }


    }


    private fun editProfileApi() {
        if (isValid()) {
           // val bodyimage = mValidationClass.prepareFilePart("image", File(mImagePath))
            val partdeviceType = mValidationClass.createPartFromString(Constants.Device_Type)
            val partdeviceToken = mValidationClass.createPartFromString(SharedPrefUtil.getInstance().deviceToken)
            val partName = mValidationClass.createPartFromString(et_name.text.toString().trim())
            val partEmail = mValidationClass.createPartFromString(et_email.text.toString().trim())
            val partPhone = mValidationClass.createPartFromString(et_phone.text.toString().trim())
            val partCountryCode = mValidationClass.createPartFromString(countryCode)

            val map = HashMap<String, RequestBody>()
            map.put("deviceType", partdeviceType)
            map.put("deviceToken", partdeviceToken)
            map.put("name", partName)
            map.put("email", partEmail)
            map.put("phone", partPhone)
            map.put("countryCode", partCountryCode)

            viewModel.editProfile(this, true, map, mImagePath,mValidationClass)
            viewModel.mResponse.observe(this, this)

        }
    }


    private fun isValid(): Boolean {
        var check = false
        if (!mValidationClass.isNetworkConnected)
            showAlerterRed(resources.getString(R.string.no_internet))
        else if (mValidationClass.checkStringNull(et_name.text.toString().trim()))
            showAlerterRed(resources.getString(R.string.error_name))
        else if (mValidationClass.checkStringNull(defaultImage))
            showAlerterRed(resources.getString(R.string.error_image))
        else if (mValidationClass.checkStringNull(countryCode))
            showAlerterRed(resources.getString(R.string.error_country_code))
        else if (mValidationClass.checkStringNull(et_phone.text.toString().trim()))
            showAlerterRed(resources.getString(R.string.error_phone_number))
        else if (!mValidationClass.validatePhoneNumber(et_phone.text.toString().trim()))
            showAlerterRed(resources.getString(R.string.error_invalid_phone_number))
        else if (mValidationClass.checkStringNull(et_email.text.toString().trim()))
            showAlerterRed(resources.getString(R.string.error_email))
        else if (!mValidationClass.isValidEmail(et_email.text.toString().trim()))
            showAlerterRed(resources.getString(R.string.error_validemail))
        else
            check = true
        return check
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.ivBack -> {
                onLeftIconClick()
            }
            R.id.ivAdd -> {
                mAlbumFiles = ArrayList()
                selectAlbum()
            }
            R.id.btnUpdate -> {
                hideKeyboard(btnUpdate)
                editProfileApi()
            }
        }
    }

    private fun selectAlbum() {
        Album.image(this)
            .singleChoice()
            .columnCount(4)
            .camera(true)
            .widget(
                Widget.newDarkBuilder(this)
                    .title(getString(R.string.app_name))
                    .build()
            )
            .onResult { result ->
                //1 image 2 video
                mAlbumFiles = result
                for ( i in 0 until  mAlbumFiles.size) {
                    Log.e("imagePath",mAlbumFiles.get(i).path)
                    mImagePath=mAlbumFiles.get(i).path
                    Glide.with(mContext).load(mImagePath).into(iv_profile_pic)

                }
            }
            .onCancel {}
            .start()
    }


    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is EditProfileResponse) {
                    val editProfileResponse: EditProfileResponse = it.data
                    if (editProfileResponse.getCode()!!.equals(Constants.success_code)) {
                        showSuccessToast(mContext, editProfileResponse!!.getMessage()!!)
                        SharedPrefUtil.getInstance().saveName(editProfileResponse.getBody()!!.name)
                        SharedPrefUtil.getInstance().saveImage(editProfileResponse.getBody()!!.image)
                        SharedPrefUtil.getInstance().saveImage(editProfileResponse.getBody()!!.image)
                        MyApplication.getnstance().setString(Constants.AuthKey, editProfileResponse.getBody()!!.token!!)
                        finish()

                    } else {
                        CommonMethods.AlertErrorMessage(mContext, editProfileResponse.getMessage())
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
