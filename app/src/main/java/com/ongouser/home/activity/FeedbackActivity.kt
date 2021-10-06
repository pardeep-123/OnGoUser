package com.ongouser.home.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.Adapter.FeedbackAdapter
import com.ongouser.R
import com.ongouser.base.BaseActivity
import kotlinx.android.synthetic.main.activity_feedback.*
import java.util.*

class FeedbackActivity : BaseActivity(), View.OnClickListener {
    lateinit var rec_category: RecyclerView
    lateinit var mContext: Context


    override fun getContentId(): Int {
        return R.layout.activity_feedback
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        rec_category = findViewById(R.id.rec_category)
        back.setOnClickListener {
            finish()
        }
        val shop = FeedbackAdapter(mContext)
        rec_category.setLayoutManager(LinearLayoutManager(mContext, RecyclerView.VERTICAL, false))
        rec_category.setAdapter(shop)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.back -> {
                onLeftIconClick()
            }
        }
    }
}