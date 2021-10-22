package com.ongouser.home.activity.product

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ongouser.Adapter.CreatedProductAdapter
import com.ongouser.Adapter.SpecificationAdapter
import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.home.activity.address.AddressListActivity
import com.ongouser.home.activity.cart.CartActivity
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status
import com.ongouser.pojo.*
import com.ongouser.utils.others.*
import com.ongouser.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_product1.*
import kotlinx.android.synthetic.main.activity_productlisting.*
import java.util.*

class ProductDetailActivity : BaseActivity(), View.OnClickListener,Observer<RestObservable> {

    private val viewModel: HomeViewModel
            by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }

    lateinit var back: ImageView
    lateinit var cart: ImageView
    lateinit var btnaddcart: Button
    lateinit var mContext: ProductDetailActivity
    var getProductModelBody:GetProductModelBody?=null
    var count = 1
    var productid:String = ""
    var vendorid:String = ""

    override fun getContentId(): Int {
        return R.layout.activity_product1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext= this
        back = findViewById(R.id.back)
        cart = findViewById(R.id.cart)
        btnaddcart = findViewById(R.id.btnaddcart)

        if (intent.extras!=null)
        {
            getProductModelBody = intent.getSerializableExtra("data") as GetProductModelBody
            getProductModelBody?.run {

                productid = id.toString()
                vendorid = this.vendorId.toString()
                Glide.with(this@ProductDetailActivity).load(image).placeholder(R.mipmap.no_image_placeholder).into(ivProfile)
                detail_productname.setText(name)
                detail_productdescription.setText(description)
                detail_price.setText(mrp)
                detail_quantity.setText("${count} ")
                detail_plus.setOnClickListener {
                    count++
                    detail_quantity.setText("${count}")
                }
                detailminus.setOnClickListener {
                    if (count>1)
                    {
                        count--
                        detail_quantity.setText("${count}")
                    }
                }
            }

            if (getProductModelBody!!.isFavorite==0){
                productlikebtn.visibility = View.GONE
                productdislikebtn.visibility = View.VISIBLE
            }else{
                productlikebtn.visibility = View.VISIBLE
                productdislikebtn.visibility = View.GONE
            }
            if (getProductModelBody?.productSpecifications!!.size>0) {
                specification.visibility = View.VISIBLE
                recyclerview.visibility = View.VISIBLE
                val adapter = SpecificationAdapter(getProductModelBody?.productSpecifications!!)
                recyclerview.adapter = adapter
            }else{
                specification.visibility = View.GONE
                recyclerview.visibility = View.GONE
            }
        }

        back.setOnClickListener(mContext)
        cart.setOnClickListener(mContext)
        btnaddcart.setOnClickListener(mContext)
        productdislikebtn.setOnClickListener {
            val map = HashMap<String, String>()
            map.put("id", productid)

            viewModel.addtofavorite(this
                    , true, map)
            viewModel.mResponse.observe(this, this)
            productlikebtn.visibility = View.VISIBLE
            productdislikebtn.visibility = View.GONE
        }
        productlikebtn.setOnClickListener {
            val map = HashMap<String, String>()
            map.put("id", productid)

            viewModel.addtofavorite(this
                    , true, map)
            viewModel.mResponse.observe(this, this)
            productlikebtn.visibility = View.GONE
            productdislikebtn.visibility = View.VISIBLE
        }
    }
    private fun addtocart() {
        if (!MyApplication.instance!!.checkIfHasNetwork())
            showAlerterRed(resources.getString(R.string.no_internet))
        else {
            val map = HashMap<String, String>()
            map.put("productId", productid)
            map.put("qty", "$count")
            map.put("vendorId", vendorid)

            viewModel.addtocartAPI(this
                    , true, map)
            viewModel.mResponse.observe(this, this)
        }
    }
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.back -> {
                onLeftIconClick()
            }
            R.id.cart -> {
                val i = Intent(mContext, CartActivity::class.java)
                startActivity(i)
            }
          R.id.btnaddcart -> {

              if (SharedPrefUtil.getInstance().getvednorId() == 0) {
                  addtocart()
              } else if (SharedPrefUtil.getInstance().getvednorId() == getProductModelBody!!.vendorId)
                  addtocart()
              else {
                  GlobalVariable.globalyesno_btndialog(this, "You have already added item from other store, click 'Yes' to remove items", object : GlobalVariable.OnOkselectforlocation {
                      override fun yesselect() {
                          emptycartitems()
                      }

                      override fun noselect() {

                      }

                  })
              }

          }
        }
    }
    fun emptycartitems()
    {
        viewModel.emptycartapi(this,true)
        viewModel.mResponse.observe(this,this)
    }

    override fun onChanged(it: RestObservable?) {

        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is GetProductModel) {


                    val getProductModel: GetProductModel = it.data


                    if (getProductModel.code == Constants.success_code) {
                        showSuccessToast(this,getProductModel.message!!)


                    } else {
                        CommonMethods.AlertErrorMessage(
                                this,
                                getProductModel.message
                        )
                    }
                }
                if (it.data is AddToFavoriteModel)
                {
                    var data:AddToFavoriteModel =it.data
                    showSuccessToast(this,data.message!!)
                }

                if (it.data is CommonModel)
                {
                    addtocart()
                }
                if (it.data is AddToCartModel)
                {
                    val data = it.data
                    if (data.code==200)
                    {
                        val i = Intent(mContext, CartActivity::class.java)
                        startActivity(i)
                        var badge = SharedPrefUtil.getInstance().badge
                        badge = badge+1
                        SharedPrefUtil.getInstance().saveVendorid(data.body!!.vendorId!!)
                        SharedPrefUtil.getInstance().saveBadge(badge)
                    }
                    else
                    {
                        showAlerterRed(data.message!!)
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