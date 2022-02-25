package com.ongouser.Adapter

import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ongouser.R
import com.ongouser.home.activity.product.Productlisting
import com.ongouser.pojo.CategoryListingResponse
import com.ongouser.pojo.GetShopbody
import java.util.ArrayList

class HomeshopadViewallapter(var context: Context, var list: List<GetShopbody>) :
    RecyclerView.Adapter<HomeshopadViewallapter.RecyclerViewHolder>() {
    var inflater: LayoutInflater
    var templist = ArrayList<GetShopbody>()

    init {
        inflater = LayoutInflater.from(context)
        this.templist.addAll(list)
    }
    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var shopimage: ImageView
        var shopname: TextView
        var shopaddress: TextView

        init {
            shopimage = view.findViewById(R.id.iv_shop_img)
            shopname = view.findViewById(R.id.tv_shopname)
            shopaddress = view.findViewById(R.id.tvshopaddress)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {
        val v = inflater.inflate(R.layout.res_nearbyshopvewall, parent, false)
        return RecyclerViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        if(list[position].user!=null) {
            Glide.with(context)
                .load("http://54.252.10.181:8300" + list[position].user!!.vendorDetail!!.image)
                .placeholder(R.mipmap.no_image_placeholder).into(holder.shopimage)
            holder.shopname.text = list[position].user!!.vendorDetail!!.shopName
            holder.shopaddress.text =
                Html.fromHtml(list[position].user!!.vendorDetail!!.shopName)
        }
        holder.itemView.setOnClickListener {
            val i = Intent(context, Productlisting::class.java)
            i.putExtra("categoryId", list[position].vendorId.toString())
            context.startActivity(i)
        }
    }

    fun filter(charText: String,rec_category:RecyclerView, nobooking: TextView) {
        var charText = charText
        charText = charText.toLowerCase()
        val nList: MutableList<GetShopbody> = ArrayList()
        if (charText.length == 0) {
            nList.addAll(templist)
        } else {
            for (wp in templist) {
                if (wp.user!!.vendorDetail!!.name!!.toLowerCase()
                        .contains(charText.toLowerCase())) {
                    nList.add(wp)
                }
            }
        }
        list = nList as ArrayList<GetShopbody>
        notifyDataSetChanged()


        if (list.isEmpty()) {
            nobooking.setVisibility(View.VISIBLE)
            rec_category.setVisibility(View.GONE)
        } else {
            nobooking.setVisibility(View.GONE)
            rec_category.setVisibility(View.VISIBLE)
        }
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    init {
        inflater = LayoutInflater.from(context)
    }
}