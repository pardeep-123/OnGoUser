package com.ongouser.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ongouser.R
import com.ongouser.home.activity.product.Productlisting
import com.ongouser.pojo.VendorsItem


class HomeShopAdapter(var context: Context?,
                      internal var vendorList: ArrayList<VendorsItem>
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


        fun bindItems(vendorList: VendorsItem) {
            val ivVendorProfile = itemView.findViewById(R.id.iv_shop_img) as ImageView
            val tvShopname = itemView.findViewById(R.id.tv_shopname) as TextView

            tvShopname.setText(vendorList.name)
             /*tvShopname.setOnClickListener {
                 Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show()

             }*/
            ivVendorProfile.setOnClickListener {
                val i = Intent(context, Productlisting::class.java)
                i.putExtra("categoryId", vendorList.userId.toString())
                context!!.startActivity(i)
            }
            if (vendorList.image != null) {
                Glide.with(context!!).load(vendorList.image).error(R.mipmap.no_image_placeholder).into(ivVendorProfile)
            }

            itemView.setOnClickListener {

            }

        }


        init {

        }

    }
}



