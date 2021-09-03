package com.ongouser.home.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.Adapter.FavouritesAdapter
import com.ongouser.Adapter.OnLikeListener
import com.ongouser.R
import com.ongouser.base.BaseFragment
import com.ongouser.home.activity.cart.CartActivity
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.manager.restApi.Status
import com.ongouser.pojo.*
import com.ongouser.utils.others.CommonMethods
import com.ongouser.utils.others.Constants
import com.ongouser.utils.others.MyApplication
import com.ongouser.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_favorites_frag.*
import java.util.*

class Favorites_frag : BaseFragment(),Observer<RestObservable> {

    private val viewModel: HomeViewModel
            by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }

    var v: View? = null
    var rec_category: RecyclerView? = null
    var mContext: Context? = null
    var back: ImageView? = null
    var cart: ImageView? = null
    var list = ArrayList<String>()
    lateinit var shop:FavouritesAdapter
    lateinit var editSearch:EditText
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_favorites_frag, container, false)
        mContext = activity
        rec_category = v?.findViewById(R.id.rec_category)
        cart = v?.findViewById(R.id.cart)
        editSearch = v!!.findViewById(R.id.et_search_fav)
        cart?.setOnClickListener(View.OnClickListener {
            val i = Intent(activity, CartActivity::class.java)
            startActivity(i)
        })

        getfavoriteproducts()

        editSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (shop != null)
                    shop!!.filter(s.toString().trim(), rec_category!!, tvnoproduct_Fav!!);
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        return v
    }



    private fun getfavoriteproducts() {
        if (!MyApplication.instance!!.checkIfHasNetwork())
            showAlerterRed(resources.getString(R.string.no_internet))
        else {

            viewModel.getfavoriteproducts(requireActivity()
                    , true)
            viewModel.mResponse.observe(requireActivity(), this)
        }
    }

    fun addtofavapi(productid:String)
    {
        val map = HashMap<String, String>()
        map.put("id", productid)
        viewModel.addtofavorite(requireActivity(), true, map)
        viewModel.mResponse.observe(requireActivity(), this)
    }

    var pos=0
    lateinit var getMyFavoriteListing:ArrayList<FavoriteBodyItem?>
    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is GetMyFavoriteListing) {


                      getMyFavoriteListing = it.data.body!!

                    if (it.data.code == Constants.success_code) {
                        showSuccessToast(it.data.message!!)

                        shop = FavouritesAdapter(mContext!!,getMyFavoriteListing,getMyFavoriteListing,object :OnLikeListener{
                            override fun OnClick(id: String,position:Int) {
                                pos=position
                                addtofavapi(id)
                               // shop.removeitem(position)
                            }
                        })
                        rec_category?.setLayoutManager(LinearLayoutManager(mContext, RecyclerView.VERTICAL, false))
                        rec_category?.setAdapter(shop)

                        emptyCheck()
                    } else {
                        CommonMethods.AlertErrorMessage(
                                requireActivity(),
                                it.data.message
                        )
                    }
                }
                if (it.data is AddToFavoriteModel)
                {
                    var data:AddToFavoriteModel = it.data
                    showSuccessToast(data.message)

                    getMyFavoriteListing.removeAt(pos)
                    shop.notifyDataSetChanged()

                    emptyCheck()

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

    fun emptyCheck(){
        if (getMyFavoriteListing.isEmpty()){

            tvnoproduct_Fav.visibility=View.VISIBLE
            rec_category!!.visibility=View.GONE
            rl_search!!.visibility=View.GONE

        }else{
            tvnoproduct_Fav.visibility=View.GONE
            rec_category!!.visibility=View.VISIBLE
            rl_search!!.visibility=View.VISIBLE

        }
    }
}