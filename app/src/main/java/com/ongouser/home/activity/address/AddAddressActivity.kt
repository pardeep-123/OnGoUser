package com.ongouser.home.activity.address

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status
import com.ongouser.pojo.AddAddressResponse
import com.ongouser.pojo.AddCardResponse
import com.ongouser.utils.others.Constants
import com.ongouser.utils.others.SharedPrefUtil
import com.ongouser.viewmodel.HomeViewModel
import com.rilixtech.widget.countrycodepicker.Country
import com.rilixtech.widget.countrycodepicker.CountryCodePicker
import kotlinx.android.synthetic.main.activity_add_address.*


class AddAddressActivity : BaseActivity(), View.OnClickListener, Observer<RestObservable> {

    private lateinit var mContext: AddAddressActivity
    private val viewModel: HomeViewModel
            by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }

    var addressId = ""
    var countryCode = "91"
    var selectedLatitude = "30.23112300"
    var selectedLongitude = "76.36982340"

    override fun getContentId(): Int {
        return R.layout.activity_add_address
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        ivBack.setOnClickListener(mContext)
        btnadd.setOnClickListener(mContext)

        ccp.setOnCountryChangeListener(object : CountryCodePicker.OnCountryChangeListener {
            override fun onCountrySelected(selectedCountry: Country?) {
                ccp.getSelectedCountryCode()
                ccp.showFlag(false)
                ccp.enableHint(true)
                ccp.setKeyboardAutoPopOnSearch(false)
                countryCode = ccp.selectedCountryCode

            }
        })

        if (intent.extras != null) {
            countryCode = intent.getStringExtra("countryCode")!!
            addressId = intent.getStringExtra("id")!!
            selectedLatitude = intent.getStringExtra("latitude")!!
            selectedLongitude = intent.getStringExtra("longitude")!!
            et_name.setText(intent.getStringExtra("name")!!)
            edaddresss.setText(intent.getStringExtra("address")!!)
            et_phone.setText(intent.getStringExtra("phone")!!)
        }
    }


    private fun isValid(): Boolean {
        var check = false
        if (!mValidationClass.isNetworkConnected)
            showAlerterRed(resources.getString(R.string.no_internet))
        else if (mValidationClass.checkStringNull(et_name.text.toString().trim()))
            showAlerterRed(resources.getString(R.string.name_missing_message))
       else if (mValidationClass.checkStringNull(edaddresss.text.toString().trim()))
            showAlerterRed(resources.getString(R.string.address_missing))
       else if (mValidationClass.checkStringNull(edaddresss.text.toString().trim()))
            showAlerterRed(resources.getString(R.string.error_phone_number))
        else if (edaddresss.text.toString().trim().length !=10)
            showAlerterRed(resources.getString(R.string.error_invalid_phone_number))
        /*else if (mValidationClass.checkStringNull(edcity.text.toString().trim()))
            showAlerterRed(resources.getString(R.string.city_missing))
        else if (mValidationClass.checkStringNull(edstate.text.toString().trim()))
            showAlerterRed(resources.getString(R.string.state_missing))
        else if (mValidationClass.checkStringNull(edpostalcode.text.toString().trim()))
            showAlerterRed(resources.getString(R.string.postal_code_missing))*/
        else
            check = true
        return check
    }


    fun addAddressAPI() {
        if (isValid()) {
            val map = HashMap<String, String>()
            map.put("address", edaddresss.text.toString().trim()/*+edcity.text.toString()+edpostalcode.text.toString()*/)
            map.put("name", et_name.text.toString())
            map.put("countryCode", countryCode)
            map.put("phone", et_phone.text.toString())
            map.put("latitude", selectedLatitude)
            map.put("longitude", selectedLongitude)

            viewModel.addUserAddressAPI(this, true, map)
            viewModel.mResponse.observe(this, this)

        }
    }

    fun updateUserAddressAPI() {
        if (isValid()) {
            val map = HashMap<String, String>()
            map.put("address", edaddresss.text.toString().trim()/*+edcity.text.toString()+edpostalcode.text.toString()*/)
            map.put("name", et_name.text.toString())
            map.put("countryCode", countryCode)
            map.put("id", addressId)
            map.put("phone", et_phone.text.toString())
            map.put("latitude", selectedLatitude)
            map.put("longitude", selectedLongitude)

            viewModel.updateUserAddressAPI(this, true, map)
            viewModel.mResponse.observe(this, this)

        }
    }

    override fun onClick(view: View?) {
        val itemid = view!!.id
        when (itemid) {
            R.id.ivBack -> {
                onLeftIconClick()
            }
            R.id.btnadd -> {
                if (addressId.equals("")) {
                    addAddressAPI()
                } else {
                    updateUserAddressAPI()
                }
            }

        }

    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is AddAddressResponse) {
                    val addAddressResponse: AddAddressResponse = it.data
                    if (addAddressResponse!!.getCode() == Constants.success_code) {
                        showSuccessToast(mContext, addAddressResponse.getMessage()!!)
                        finish()
                    } else {
                        showAlerterRed(addAddressResponse.getMessage() as String)

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