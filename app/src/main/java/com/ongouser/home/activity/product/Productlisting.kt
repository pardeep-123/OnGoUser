package com.ongouser.home.activity.product

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.Adapter.CreatedProductAdapter
import com.ongouser.Adapter.ShopcategoryProductsapter
import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status
import com.ongouser.pojo.GetProductModel
import com.ongouser.utils.others.CommonMethods
import com.ongouser.utils.others.Constants
import com.ongouser.utils.others.MyApplication
import com.ongouser.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_productlisting.*
import kotlinx.android.synthetic.main.activity_view_all.*
import java.util.HashMap

class Productlisting :   BaseActivity() , Observer<RestObservable> {
    private val viewModel: HomeViewModel
            by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }

    lateinit var ivBack: ImageView
    lateinit var cart: ImageView
    lateinit var mContext: Productlisting
    lateinit var recyclerview: RecyclerView
    lateinit var productAdapter: CreatedProductAdapter
    override fun getContentId(): Int {
        return R.layout.activity_productlisting
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ivBack = findViewById(R.id.back)
        mContext = this
        recyclerview = findViewById(R.id.rec_product)

        ivBack.setOnClickListener(View.OnClickListener {
            onLeftIconClick() })



    }

    override fun onResume() {
        super.onResume()
        getproduct()
    }
    private fun getproduct() {
        if (!MyApplication.instance!!.checkIfHasNetwork())
            showAlerterRed(resources.getString(R.string.no_internet))
        else {
            val map = HashMap<String, String>()
            map.put("vendor_id", intent.getStringExtra("categoryId")!!)

            viewModel.getproductbyshopid(this
                    , true, map)
            viewModel.mResponse.observe(this, this)
        }
    }
    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is GetProductModel) {


                    val getProductModel: GetProductModel = it.data

                    var name = with(getProductModel)
                    {

                    }
                    if (getProductModel.code == Constants.success_code) {
                        showSuccessToast(this,getProductModel.message!!)
                        if (getProductModel.body!!.size ==0){
                            tvnoproduct.visibility = View.VISIBLE
                        }else {
                            tvnoproduct.visibility = View.GONE
                            productAdapter = CreatedProductAdapter(mContext,getProductModel)
                            recyclerview.setLayoutManager(LinearLayoutManager(mContext))
                            recyclerview.setAdapter(productAdapter)
                        }

                    } else {
                        CommonMethods.AlertErrorMessage(
                                this,
                                getProductModel.message
                        )
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