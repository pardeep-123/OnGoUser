package com.ongouser.home.activity.cart

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.Adapter.CreatedProductAdapter
import com.ongouser.Adapter.cartAdapter
import com.ongouser.R
import com.ongouser.base.BaseActivity
import com.ongouser.home.HomeActivity
import com.ongouser.home.activity.payment.PaymentActivity
import com.ongouser.home.activity.PickupOptionsActivity
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status
import com.ongouser.pojo.*
import com.ongouser.utils.others.CommonMethods
import com.ongouser.utils.others.Constants
import com.ongouser.utils.others.MyApplication
import com.ongouser.utils.others.SharedPrefUtil
import com.ongouser.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_productlisting.*
import kotlinx.android.synthetic.main.activity_productlisting.tvnoproduct
import java.util.HashMap

class CartActivity: BaseActivity() , View.OnClickListener, Observer<RestObservable> {
    private val viewModel: HomeViewModel
            by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }

    lateinit var ivBack: ImageView
    lateinit var mContext: CartActivity
    lateinit var btnpayment: Button
    lateinit var btncheckout:Button
    lateinit var recyclerview: RecyclerView
    lateinit var cartAdapter: cartAdapter
    var totalamount = 0.0
    var taxfee = 0.0
    var itemremovepositoin = -1
    var vendorid = ""
    private var carttotalamount : TextView?=null
    lateinit var getCartItemsModel: GetCartItemsBody

    var calculatedFee = 0.0
    var calculatedGst = 0.0

    var newtotalamount = 0.0
    var gstfee = 0.0

    override fun getContentId(): Int {
        return R.layout.activity_cart
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this
        ivBack = findViewById(R.id.back)
        recyclerview = findViewById(R.id.rec_product)
        carttotalamount = findViewById(R.id.carttotalamount)
        btnpayment = findViewById(R.id.btnpayment)
        btncheckout = findViewById(R.id.btncheckout)


        try {
            if (intent.getStringExtra("data") == "call") {
                btncheckout.setVisibility(View.VISIBLE)
            }
        } catch (e: Exception) {
            btncheckout.setVisibility(View.VISIBLE)
        }
        ivBack.setOnClickListener(mContext)
        btnpayment.setOnClickListener(mContext)
        btncheckout.setOnClickListener(mContext)
        getcartapi()
    }

    private fun getcartapi() {
        if (!MyApplication.instance!!.checkIfHasNetwork())
            showAlerterRed(resources.getString(R.string.no_internet))
        else {


            viewModel.getcartlisting(this
                    , true)
            viewModel.mResponse.observe(this, this)
        }
    }

    override fun onBackPressed() {
        try {
          //  if (intent.getStringExtra("data") == "call") {
                val i = Intent(mContext, HomeActivity::class.java)
                startActivity(i)
         //   }else{
               // super.onBackPressed()
          //  }
        } catch (e: java.lang.Exception) {
            super.onBackPressed()
        }
    }

    override fun onClick(v: View?) {
       when(v !!.id){
           R.id.btncheckout -> {
               val i = Intent(mContext, PickupOptionsActivity::class.java)
               i.putExtra(Constants.TotalAmount,totalamount.toString())
               i.putExtra(Constants.VendorId,vendorid)
               // Send Seperated fee and gst
               i.putExtra(Constants.TotalFee,calculatedFee.toString())
               i.putExtra(Constants.TotalTax,calculatedGst.toString())

               startActivity(i)
           }
           R.id.btnpayment -> {
               val i = Intent(mContext, PaymentActivity::class.java)

               startActivity(i)
           }

           R.id.back -> {
               onBackPressed()
           }

       }
    }

    fun deletecartitem(id:String)
    {
        viewModel.deletecartitem(this, true,id)
        viewModel.mResponse.observe(this, this)
    }

    fun updatecart(id:String,qty:String)
    {
        viewModel.updatecartitem(this
                , false,id,qty)
        viewModel.mResponse.observe(this, this)
    }
    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is DeleteItemCartResponse){
                    cartAdapter.removecartitem(itemremovepositoin)

                    showSuccessToast(this,it.data.message)
                    totalCalculate()

                    if (getCartItemsModel.cartItems!!.size ==0){
                        SharedPrefUtil.getInstance().saveVendorid(0)
                        tvnoproduct.visibility = View.VISIBLE
                        rl_linear.visibility = View.GONE
                        btncheckout.visibility = View.GONE
                    }else {
                        tvnoproduct.visibility = View.GONE
                        btncheckout.visibility = View.VISIBLE
                        rl_linear.visibility = View.VISIBLE
                    }

                }
                if (it.data is GetCartItemsModel) {


                   getCartItemsModel  = it.data.body!!


                    if (it.data.code == Constants.success_code) {
                        //showSuccessToast(this,getCartItemsModel.message!!)
                        if (getCartItemsModel.cartItems!!.size ==0){
                            tvnoproduct.visibility = View.VISIBLE
                            rl_linear.visibility = View.GONE
                            btncheckout.visibility = View.GONE
                        }else {
                            btncheckout.visibility = View.VISIBLE
                            rl_linear.visibility = View.VISIBLE
                            tvnoproduct.visibility = View.GONE
                            SharedPrefUtil.getInstance().saveBadge(getCartItemsModel!!.cartItems?.size!!)

                            cartAdapter = cartAdapter(mContext,getCartItemsModel,object : cartAdapter.CartItemRemoved{
                                override fun onitemremoved(position:Int,id: String) {

                                    itemremovepositoin = position
                                   deletecartitem(id)

                                }

                                override fun updatecartitem(id: String, qty: String) {

                                    Log.e("update","vaa")
                                      updatecart(id,qty)
                                }

                            })

                            recyclerview.layoutManager = LinearLayoutManager(mContext)
                            recyclerview.adapter = cartAdapter
                            vendorid = getCartItemsModel.cartItems!![0]!!.vendorId.toString()
                            totalCalculate()
                           /* getCartItemsModel!!.cartItems?.run {
                                forEach { it?.product?.run {
                                    totalamount += mrp!!.toDouble()
                                    totalamount=totalamount*it.qty!!
                                } }
                            }
                            carttotalamount.setText("$$totalamount")*/
                        }

                    } else {
                        CommonMethods.AlertErrorMessage(
                                this,
                                it.data.message
                        )
                    }
                }
                if (it.data is CommonModel)
                {
                    val data:CommonModel  =it.data
                    showSuccessToast(this,data.message!!)
                   /* totalamount = getCartItemsModel.cartItems!![itemremovepositoin]!!.product!!.mrp!!.toInt()-totalamount;

                    carttotalamount.setText("$$totalamount")

                    SharedPrefUtil.getInstance().saveBadge(getCartItemsModel!!.cartItems?.size!!)
                    Log.e("modelsize",""+getCartItemsModel.cartItems!!.size)*/
                    totalCalculate()
                    if (getCartItemsModel.cartItems!!.size ==0){
                        tvnoproduct.visibility = View.VISIBLE
                        rl_linear.visibility = View.GONE
                        btncheckout.visibility = View.GONE
                    }else {
                        tvnoproduct.visibility = View.GONE
                        btncheckout.visibility = View.VISIBLE
                        rl_linear.visibility = View.VISIBLE
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

//    fun totalCalculate(){
//        totalamount=0.0
//        for ( i in 0 until getCartItemsModel.cartItems!!.size){
//
//            val mrp = getCartItemsModel.cartItems!![i]!!.product!!.mrp!!.toDouble()
//            val temp=mrp* getCartItemsModel.cartItems!![i]!!.qty!!.toInt()
//             taxfee = getCartItemsModel.taxDetails?.siteComission?.toDouble()!!
//            totalamount += temp
//        }
//
//        tv_subtotal.text = "$$totalamount"
//        tax.text = "$"+getCartItemsModel.taxDetails?.siteComission
//       // carttotalamount.text = "$$totalamount"
//        carttotalamount?.text = "$"+(totalamount+taxfee).toString()
//        Log.e("modelsize",""+getCartItemsModel.cartItems!!.size)
//        SharedPrefUtil.getInstance().saveBadge(getCartItemsModel.cartItems?.size!!)
//    }

    private fun totalCalculate(){

        totalamount=0.0
        for ( i in 0 until getCartItemsModel.cartItems!!.size){

            val mrp = getCartItemsModel.cartItems!![i]!!.product!!.mrp!!.toDouble()
            val temp=mrp* getCartItemsModel.cartItems!![i]!!.qty!!.toInt()
            taxfee = getCartItemsModel.taxDetails?.siteComission!!.toDouble()
            gstfee = getCartItemsModel.taxDetails?.gst!!.toDouble()
            totalamount += temp

            newtotalamount = (String.format("%.2f",(totalamount+((totalamount*taxfee).div(100))+
                    ((totalamount*gstfee).div(100))))).toDouble()

            calculatedFee = ((totalamount*taxfee).div(100))
            calculatedGst = ((totalamount*gstfee).div(100))
        }

        tv_subtotal.text = "$$totalamount"
        tax.text = getCartItemsModel.taxDetails?.siteComission.toString() +"%"
        gst.text = getCartItemsModel.taxDetails?.gst.toString() +"%"

        // Set Total Amount including gst and grocery fees
        carttotalamount?.text = "$"+ newtotalamount
        // carttotalamount.text = "$$totalamount"
        Log.e("modelsize",""+getCartItemsModel.cartItems!!.size)
        SharedPrefUtil.getInstance().saveBadge(getCartItemsModel.cartItems?.size!!)
    }
}