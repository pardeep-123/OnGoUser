package com.ongouser.home.activity.product

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.Adapter.CreatedProductAdapter
import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status
import com.ongouser.pojo.GetProductModel
import com.ongouser.utils.others.CommonMethods
import com.ongouser.utils.others.Constants
import com.ongouser.utils.others.MyApplication
import com.ongouser.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_productlisting.tvnoproduct
import kotlinx.android.synthetic.main.activity_productlistingsearch.*
import java.util.*


class ProductlistingSearch :   BaseActivity() , Observer<RestObservable> {
    private val viewModel: HomeViewModel
            by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }

    lateinit var ivBack: ImageView
    lateinit var cart: ImageView
    lateinit var mContext: ProductlistingSearch
    lateinit var recyclerview: RecyclerView
    lateinit var productAdapter: CreatedProductAdapter
    override fun getContentId(): Int {
        return R.layout.activity_productlistingsearch
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ivBack = findViewById(R.id.back)
        mContext = this
        recyclerview = findViewById(R.id.rec_product)

        ivBack.setOnClickListener {
            onLeftIconClick()
        }
        searchbtn.setOnClickListener {
           getproduct()
       }
        productsearchedt.onDone { getproduct() }

        productsearchedt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.toString().isEmpty()){
                    recyclerview.visibility=View.GONE
                }


            }
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {

            }
        })


    }
    override fun onBackPressed() {
        super.onBackPressed()
    }
    override fun onResume() {
        super.onResume()

    }
    fun EditText.onDone(callback: () -> Unit) {
        setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                callback.invoke()
                true
            }
            false
        }
    }

    private fun getproduct() {
        if (!MyApplication.instance!!.checkIfHasNetwork())
            showAlerterRed(resources.getString(R.string.no_internet))
        else {
            val map = HashMap<String, String>()
            map["keyword"] = productsearchedt.text.toString().trim()

            viewModel.getproductbyshopid(this, true, map)
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
                        showSuccessToast(this, getProductModel.message!!)
                        if (getProductModel.body!!.size ==0){
                            tvnoproduct.visibility = View.VISIBLE
                        }else {
                            tvnoproduct.visibility = View.GONE
                            productAdapter = CreatedProductAdapter(mContext, getProductModel)
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