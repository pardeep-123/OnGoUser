package com.ongouser.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.R
import com.ongouser.home.activity.address.AddAddressActivity
import com.ongouser.home.activity.address.AddressListActivity
import com.ongouser.home.activity.address.DeliverytimeSlotActivity
import com.ongouser.pojo.GetAddressListResponse
import com.ongouser.pojo.ShopAddressBody
import com.ongouser.pojo.ShopAdressModel
import kotlin.collections.ArrayList


class AddressShopListingAdapter(var mContext: Context?, internal var shopAdressModel: ShopAdressModel,
                                internal var addressListActivity: AddressListActivity) : RecyclerView.Adapter<AddressShopListingAdapter.MyViewHolder>() {

    var selectedpos = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.res_addressshop, parent, false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bindItems(shopAdressModel.body!![position]!!)
        if (selectedpos==position)
        {
            holder.rootlinearlayour.setBackgroundResource(R.drawable.card_background_redbox)
            holder.tvName.setTextColor(Color.WHITE)
            holder.tvAddress.setTextColor(Color.WHITE)
            holder.tvPhone.setTextColor(Color.WHITE)

        }
        else{
            holder.rootlinearlayour.setBackgroundResource(R.drawable.card_background_box)
            holder.tvName.setTextColor(Color.BLACK)
            holder.tvAddress.setTextColor(Color.BLACK)
            holder.tvPhone.setTextColor(Color.BLACK)

        }

    }

    override fun getItemCount(): Int {
        return shopAdressModel!!.body!!.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val rootlinearlayour = itemView.findViewById(R.id.root_addressbackground) as LinearLayout
        val tvName = itemView.findViewById(R.id.tv_name) as TextView
        val tvAddress = itemView.findViewById(R.id.tv_address) as TextView
        val tvPhone = itemView.findViewById(R.id.tv_phone) as TextView


        fun bindItems(addressList: ShopAddressBody) {

            tvName.setText("Building Number ${addressList.buildingNumber}")
            tvAddress.setText("City ${addressList.city} State ${addressList.state} \nCountry ${addressList.country}")
            tvPhone.setText("Postal Code ${addressList.postalCode}")



        }


        init {
             itemView.setOnClickListener {
                 selectedpos = adapterPosition
                 notifyDataSetChanged()
             }
        }

    }

    fun getselectedpos():Int
    {
      return  selectedpos
    }
}



