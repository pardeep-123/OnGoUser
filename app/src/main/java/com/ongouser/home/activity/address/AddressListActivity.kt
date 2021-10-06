package com.ongouser.home.activity.address

import android.content.Intent

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.Adapter.AddressListingAdapter
import com.ongouser.Adapter.AddressShopListingAdapter

import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.home.activity.payment.PaymentActivity
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status
import com.ongouser.pojo.CommonModel
import com.ongouser.pojo.GetAddressListResponse
import com.ongouser.pojo.ShopAddressBody
import com.ongouser.pojo.ShopAdressModel

import com.ongouser.utils.others.Constants
import com.ongouser.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_address_listing.*



class AddressListActivity : BaseActivity(), View.OnClickListener, Observer<RestObservable>
   {
    private val viewModel: HomeViewModel
            by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }
    private lateinit var rvAddress: RecyclerView
    private var addressList: ArrayList<GetAddressListResponse.AddressListBody> = ArrayList()
    private var shopaddressList: ArrayList<ShopAddressBody> = ArrayList()
    var pos= 0
       var totalamount= ""
       var ispickedup = ""
       var vendorid = ""

       var totalFee = ""
       var totalTax = ""

    lateinit var mContext: AddressListActivity
       lateinit var addressListingAdapter :AddressListingAdapter
       lateinit var shopListingAdapter: AddressShopListingAdapter
       override fun getContentId(): Int {
        return R.layout.activity_address_listing
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        totalamount = intent.getStringExtra(Constants.TotalAmount)!!
        ispickedup = intent.getStringExtra(Constants.isPickedup)!!
        vendorid = intent.getStringExtra(Constants.VendorId)!!
        totalFee = intent.getStringExtra(Constants.TotalFee)!!
        totalTax = intent.getStringExtra(Constants.TotalTax)!!
        rvAddress = findViewById(R.id.rv_address)
        btnnext.setOnClickListener(mContext)
        tv_add_address.setOnClickListener(mContext)

        ivBack.setOnClickListener {
            onBackPressed()
        }

    }

       override fun onBackPressed() {
           super.onBackPressed()
       }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnnext -> {

                if (ispickedup.equals("0"))
                {
                    if (addressListingAdapter.getselectedpos()==-1)
                        showErrorToast(this,"Please select address")
                    else{
                        val intent = Intent(mContext, DeliverytimeSlotActivity::class.java)
                        intent.putExtra(Constants.TotalAmount,totalamount)
                        intent.putExtra(Constants.isPickedup,ispickedup)
                        intent.putExtra(Constants.VendorId,vendorid)

                        intent.putExtra(Constants.TotalFee,totalFee)
                        intent.putExtra(Constants.TotalTax,totalTax)

                        intent.putExtra(Constants.AddressId,addressList[addressListingAdapter.getselectedpos()].id.toString())
                        startActivity(intent)
                    }
                }
                else
                {
                    if (shopListingAdapter.getselectedpos()==-1)
                        showErrorToast(this,"Please select address")
                    else{
                        // skip delivery slot screen in case of package myself
                      //  val intent = Intent(mContext, DeliverytimeSlotActivity::class.java)
                        val intent = Intent(mContext, PaymentActivity::class.java)
                        intent.putExtra(Constants.TotalAmount,totalamount)
                        intent.putExtra(Constants.isPickedup,ispickedup)
                        intent.putExtra(Constants.VendorId,vendorid)

                        intent.putExtra(Constants.TotalFee,totalFee)
                        intent.putExtra(Constants.TotalTax,totalTax)

                        intent.putExtra(Constants.AddressId,"0")
                        startActivity(intent)
                    }
                }


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

            if (ispickedup.equals("0"))
            {
                addresstitle.setText("Delivery Address")
                viewModel.getUserAddressListing(this, true)
                viewModel.mResponse.observe(this, this)
            }
            else{
                addresstitle.setText("Shop Address")
                tv_add_address.visibility = View.GONE
                val map = HashMap<String,String>()
                map["isSelfPickup"]=ispickedup
                map["vendor_id"]=vendorid
                viewModel.getUserAddressListing(this, true,map)
                viewModel.mResponse.observe(this, this)
            }

        }
    }


       fun setAddressListAdapter(addressList: ArrayList<GetAddressListResponse.AddressListBody>) {
            addressListingAdapter = AddressListingAdapter(mContext, addressList, mContext)
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
                        setAddressListAdapter(getAddressListResponse.getBody()!!)
                        if (getAddressListResponse.getBody()!!.size == 0) {
                            no_address_card.visibility = View.VISIBLE
                            rv_address.visibility = View.GONE
                        } else {
                            no_address_card.visibility = View.GONE
                            rv_address.visibility = View.VISIBLE

                        }
                    }

                }
                if (it.data is ShopAdressModel) {
                    val shopAdressModel: ShopAdressModel = it.data
                    if (shopAdressModel.code == Constants.success_code) {
                        shopaddressList.clear()
                        shopaddressList.addAll(shopAdressModel.body!!)
                        rvAddress.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
                     shopListingAdapter =   AddressShopListingAdapter(mContext, shopAdressModel, mContext)
                        rvAddress.adapter  = shopListingAdapter


                        if (shopAdressModel.body!!.isEmpty()) {
                            no_address_card.visibility = View.VISIBLE
                            rv_address.visibility = View.GONE
                        } else {
                            no_address_card.visibility = View.GONE
                            rv_address.visibility = View.VISIBLE

                        }
                    }

                }
                if (it.data is CommonModel) {

                    val data:CommonModel =it.data
                    showSuccessToast(this,data.message!!)
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

              viewModel.deleteUserAddressAPI(mContext, true, id!!)
           }

       }


   }
