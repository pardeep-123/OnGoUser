package com.ongouser.home.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.Adapter.HomeshopadViewallapter
import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.home.activity.cart.CartActivity
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status
import com.ongouser.pojo.GetShopsModel
import com.ongouser.utils.others.CommonMethods
import com.ongouser.utils.others.Constants
import com.ongouser.utils.others.MyApplication
import com.ongouser.utils.others.SharedPrefUtil
import com.ongouser.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_view_all.*



class ViewAllActivity : BaseActivity(),Observer<RestObservable>
{
    lateinit var rec_nearshop: RecyclerView
    lateinit var mContext: Context
    lateinit var back: ImageView
    lateinit var cart: ImageView
    private val viewModel: HomeViewModel
            by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }
    var shop : HomeshopadViewallapter?=null
    var titleText : TextView?=null
    override fun getContentId(): Int {
        return R.layout.activity_view_all
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        back = findViewById(R.id.back)
        rec_nearshop = findViewById(R.id.rec_nearshop)
        cart = findViewById(R.id.cart)
        titleText = findViewById(R.id.titleText)
        mContext = this

        titleText?.text = intent.getStringExtra("categoryName")!!

        cart.setOnClickListener {
            val i = Intent(this@ViewAllActivity, CartActivity::class.java)
            startActivity(i)
        }
        categorysearchedt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (shop != null)

                    shop!!.filter(s.toString().trim(), rec_nearshop, tvnoshop!!);
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        back.setOnClickListener { finish() }

        getshops()
    }
    private fun getshops() {
        if (!MyApplication.instance!!.checkIfHasNetwork())
            showAlerterRed(resources.getString(R.string.no_internet))
        else {
            val map = HashMap<String, String>()
            map.put("category_id", intent.getStringExtra("categoryId")!!)

            viewModel.getshopbycatid(this
                    , true, map)
            viewModel.mResponse.observe(this, this)
        }
    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is GetShopsModel) {
                    val getShopsModel: GetShopsModel = it.data
                    if (getShopsModel.code == Constants.success_code) {
                        showSuccessToast(this,getShopsModel.message!!)
                        if (getShopsModel.body!!.size ==0){
                            tvnoshop.visibility = View.VISIBLE
                        }else {
                            tvnoshop.visibility = View.GONE
                             shop = HomeshopadViewallapter(mContext,getShopsModel.body)
                            rec_nearshop.setLayoutManager(GridLayoutManager(this, 2))
                            rec_nearshop.setAdapter(shop)                        }

                    } else {
                        CommonMethods.AlertErrorMessage(
                                this,
                                getShopsModel.message
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

    override fun onResume() {
        super.onResume()

        tv_cartbadges.text = SharedPrefUtil.getInstance().badge.toString()
        if (SharedPrefUtil.getInstance().badge.toString()=="0"){
            badgeLayout.visibility = View.GONE
        }else
            badgeLayout.visibility = View.VISIBLE
    }
}