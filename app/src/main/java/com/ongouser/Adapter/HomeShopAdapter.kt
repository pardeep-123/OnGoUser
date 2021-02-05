package com.ongouser.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ongouser.R
import com.ongouser.pojo.HomeListingResponse
import com.ongouser.utils.others.Constants



class HomeShopAdapter(var context: Context?,
                      internal var vendorList: ArrayList<HomeListingResponse.Vendor>
                     ) : RecyclerView.Adapter<HomeShopAdapter.HomeVendorHolder>() {


    override fun onBindViewHolder(holder: HomeVendorHolder, position: Int) {
        holder.bindItems(vendorList[position])

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVendorHolder {
        return HomeVendorHolder(
                LayoutInflater.from(context).inflate(R.layout.res_nearbyshop, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return vendorList.size
    }

    inner class HomeVendorHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bindItems(vendorList: HomeListingResponse.Vendor) {
            val ivVendorProfile = itemView.findViewById(R.id.iv_shop_img) as ImageView
            val tvShopname = itemView.findViewById(R.id.tv_shopname) as TextView

            tvShopname.setText(vendorList.name)

            if (vendorList.image != null) {
                Glide.with(context!!).load(vendorList.image).error(R.mipmap.no_image_placeholder).into(ivVendorProfile)
            }

            /*rlvendor.setOnClickListener {
                var intent = Intent(context, DetailProfilevendor::class.java)
                intent.putExtra("vendorId", vendorList.id)
                intent.putExtra("hoursNeeded", hoursNeeded)
                intent.putExtra("jobDescription", jobDescription)
                context!!.startActivity(intent)

            }*/
        }


        init {
            itemView.setOnClickListener {
/*
                var intent = Intent(context, DetailProfilevendor::class.java)
                intent.putExtra("vendorId", vendorList.get(adapterPosition).id)
                context!!.startActivity(intent)
*/
            }

        }

    }
}



