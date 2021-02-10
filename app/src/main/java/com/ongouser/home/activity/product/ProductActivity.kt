package com.ongouser.home.activity.product

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.Adapter.ProductAdapter
import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.home.activity.cart.CartActivity
import java.util.*

class ProductActivity : BaseActivity() ,View.OnClickListener {

    lateinit var ivBack: ImageView
    lateinit var cart: ImageView
    lateinit var ivnot2: ImageView
    lateinit var ivnot21: ImageView
    lateinit var mContext: ProductActivity
    lateinit var btn_filter: Button
    lateinit var btn_sort: Button
    lateinit var recyclerview: RecyclerView
    lateinit var productAdapter: ProductAdapter
    override fun getContentId(): Int {
        return R.layout.activity_product
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this
        ivBack = findViewById(R.id.back)
        recyclerview = findViewById(R.id.rec_product)
        btn_sort = findViewById(R.id.btn_sort)
        btn_filter = findViewById(R.id.btn_filter)
        cart = findViewById(R.id.cart)

        productAdapter = ProductAdapter(mContext)
        recyclerview.setLayoutManager(LinearLayoutManager(mContext))
        recyclerview.setAdapter(productAdapter)

        btn_sort.setOnClickListener(mContext)
        cart.setOnClickListener(mContext)
        btn_filter.setOnClickListener(mContext)
    }

    fun sort() {
        val dialogSuccessful = Dialog(Objects.requireNonNull(mContext), R.style.Theme_Dialog)
        dialogSuccessful.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogSuccessful.setContentView(R.layout.dilog_sort)
        dialogSuccessful.setCancelable(false)
        Objects.requireNonNull(dialogSuccessful.window)!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT)
        dialogSuccessful.setCanceledOnTouchOutside(false)
        dialogSuccessful.window!!.setGravity(Gravity.BOTTOM)
        val ok = dialogSuccessful.findViewById<Button>(R.id.btnapply)
        val cross = dialogSuccessful.findViewById<ImageView>(R.id.cross)
        cross.setOnClickListener { dialogSuccessful.dismiss() }
        ok.setOnClickListener { dialogSuccessful.dismiss() }
        ivnot2 = dialogSuccessful.findViewById(R.id.ivnot2)
        ivnot21 = dialogSuccessful.findViewById(R.id.ivnot21)
        ivnot2.setOnClickListener(View.OnClickListener {
            ivnot2.setImageResource(R.drawable.radio_button)
            ivnot21.setImageResource(R.drawable.rado_button)
        })
        ivnot21.setOnClickListener(View.OnClickListener {
            ivnot21.setImageResource(R.drawable.radio_button)
            ivnot2.setImageResource(R.drawable.rado_button)
        })
        dialogSuccessful.show()
    }

    fun filter() {
        val dialogSuccessful = Dialog(Objects.requireNonNull(mContext), R.style.Theme_Dialog)
        dialogSuccessful.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogSuccessful.setContentView(R.layout.dilog_filter)
        dialogSuccessful.setCancelable(false)
        Objects.requireNonNull(dialogSuccessful.window)!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT)
        dialogSuccessful.setCanceledOnTouchOutside(false)
        dialogSuccessful.window!!.setGravity(Gravity.BOTTOM)
        ivnot2 = dialogSuccessful.findViewById(R.id.ivnot2)
        ivnot21 = dialogSuccessful.findViewById(R.id.ivnot21)
        val ok = dialogSuccessful.findViewById<Button>(R.id.btnapply)
        val cross = dialogSuccessful.findViewById<ImageView>(R.id.cross)
        ivnot2.setOnClickListener(View.OnClickListener {
            ivnot2.setImageResource(R.drawable.radio_button)
            ivnot21.setImageResource(R.drawable.rado_button)
        })
        ivnot21.setOnClickListener(View.OnClickListener {
            ivnot21.setImageResource(R.drawable.radio_button)
            ivnot2.setImageResource(R.drawable.rado_button)
        })
        cross.setOnClickListener { dialogSuccessful.dismiss() }
        ok.setOnClickListener { dialogSuccessful.dismiss() }
        dialogSuccessful.show()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_filter ->{
                filter()
            }
          R.id.cart ->{
              val i = Intent(mContext, CartActivity::class.java)
              startActivity(i)
            }
          R.id.btn_sort ->{
              sort()
          }
        }
    }
}