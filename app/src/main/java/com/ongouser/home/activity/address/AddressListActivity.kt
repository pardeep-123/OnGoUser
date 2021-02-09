package com.ongouser.home.activity.address

import android.content.Intent

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.Adapter.AddressListingAdapter

import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status
import com.ongouser.pojo.GetAddressListResponse

import com.ongouser.utils.others.Constants
import com.ongouser.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_address_listing.*



class AddressListActivity : BaseActivity(), View.OnClickListener, Observer<RestObservable>
   {
    private val viewModel: HomeViewModel
            by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }
    private lateinit var rvAddress: RecyclerView
    private var addressList: ArrayList<GetAddressListResponse.AddressListBody> = ArrayList()
    var pos= 0
    lateinit var mContext: AddressListActivity

    override fun getContentId(): Int {
        return R.layout.activity_address_listing
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        rvAddress = findViewById(R.id.rv_address)
        btnnext.setOnClickListener(mContext)
        tv_add_address.setOnClickListener(mContext)

    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnnext -> {
                val intent = Intent(mContext, DeliverytimeSlotActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_add_address -> {
                val intent = Intent(mContext, AddAddressActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun isValid(): Boolean {
        var check = false
        if (!mValidationClass.isNetworkConnected)
            showAlerterRed(resources.getString(R.string.no_internet))
          else
            check = true
        return check
    }

    private fun getAddressListingApi() {
        if (isValid()) {
            viewModel.getUserAddressListing(this, true)
            viewModel.mResponse.observe(this, this)
        }
    }


       fun setAddressListAdapter(addressList: ArrayList<GetAddressListResponse.AddressListBody>) {
           val addressListingAdapter = AddressListingAdapter(mContext, addressList, mContext)
           rvAddress.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
           rvAddress.adapter = addressListingAdapter

       }


       override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is GetAddressListResponse) {
                    val getAddressListResponse: GetAddressListResponse = it.data
                    if (getAddressListResponse.getCode() == Constants.success_code) {
                     addressList.clear()
                     addressList.addAll(getAddressListResponse!!.getBody()!!)

                        if (getAddressListResponse.getBody()!!.size == 0) {
                            no_address_card.visibility = View.VISIBLE
                            rv_address.visibility = View.GONE
                        } else {
                            no_address_card.visibility = View.GONE
                            rv_address.visibility = View.VISIBLE
                            setAddressListAdapter(getAddressListResponse.getBody()!!)
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

    override fun onResume() {
        super.onResume()
        getAddressListingApi()
    }

       fun deleteAPIMethod(position: Int, id: String?) {
           pos = position
           if (!mValidationClass!!.isNetworkConnected()) {
               showAlerterRed(resources.getString(R.string.no_internet))
           } else {
               val map = HashMap<String, String>()
               map.put("id", id!!)
              viewModel.deleteUserAddressAPI(mContext, true, map)
           }

       }


   }
