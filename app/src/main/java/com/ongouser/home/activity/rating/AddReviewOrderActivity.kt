package com.ongouser.home.activity.rating

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.databinding.ActivityAddReviewOrderBinding
import com.ongouser.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_add_review_order.*
import java.util.HashMap
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ongouser.Adapter.DeliverytimeslotAdapter
import com.ongouser.home.HomeActivity
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status
import com.ongouser.pojo.DeliverySlotModel
import com.ongouser.utils.others.Constants


class AddReviewOrderActivity : BaseActivity() , Observer<RestObservable?>{

    private val viewModel: HomeViewModel
            by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }


     override fun getContentId(): Int {

        return R.layout.activity_add_review_order
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        ivBacsk.setOnClickListener { onBackPressed() }


        btnUpdate.setOnClickListener {

            if (rb_bar.rating.toString().equals("") || rb_bar.rating.toString().equals("0.0")) {
                showAlerterRed("Please select rate")

            } else  if (et_reviews.text.toString().trim().isEmpty()) {
                showAlerterRed("Please add review")

            } else{
                val map = HashMap<String, String>()
                map.put("userId", "userId")
                map.put("rating", rb_bar.rating.toString())
                map.put("review", et_reviews.text.toString().trim())
                viewModel.add_review(this, true, map)
                viewModel.mResponse.observe(this, this)
            }
        }

    }
    override fun onChanged(it: RestObservable?) {

        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is DeliverySlotModel) {
                    val deliverySlotModel: DeliverySlotModel = it.data
                    if (deliverySlotModel.code == Constants.success_code) {
                       /* val intent = Intent(this, HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)*/
                        onBackPressed()
                    }
                    else
                    {
                        showAlerterGreen(deliverySlotModel.message!!)
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
    override fun onBackPressed() {
        super.onBackPressed()
    }
}