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
import kotlin.collections.ArrayList


class AddressListingAdapter(var mContext: Context?, internal var addressListdata: ArrayList<GetAddressListResponse.AddressListBody>,
                            internal var addressListActivity: AddressListActivity) : RecyclerView.Adapter<AddressListingAdapter.MyViewHolder>() {

    var selectedpos = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.res_address, parent, false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bindItems(addressListdata[position])
        if (selectedpos==position)
        {
            holder.rootlinearlayour.setBackgroundResource(R.drawable.card_background_redbox)
            holder.tvName.setTextColor(Color.WHITE)
            holder.tvAddress.setTextColor(Color.WHITE)
            holder.tvPhone.setTextColor(Color.WHITE)
            holder.tvEdit.setTextColor(Color.WHITE)
            holder.ivDelete.visibility = View.GONE
            holder.ivDelete1.visibility = View.VISIBLE
        }
        else{
            holder.rootlinearlayour.setBackgroundResource(R.drawable.card_background_box)
            holder.tvName.setTextColor(Color.BLACK)
            holder.tvAddress.setTextColor(Color.BLACK)
            holder.tvPhone.setTextColor(Color.BLACK)
            holder.tvEdit.setTextColor(Color.BLACK)
            holder.ivDelete.visibility = View.VISIBLE
            holder.ivDelete1.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int {
        return addressListdata!!.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val rootlinearlayour = itemView.findViewById(R.id.root_addressbackground) as LinearLayout
        val tvName = itemView.findViewById(R.id.tv_name) as TextView
        val tvAddress = itemView.findViewById(R.id.tv_address) as TextView
        val tvPhone = itemView.findViewById(R.id.tv_phone) as TextView
        val tvEdit = itemView.findViewById(R.id.tv_edit) as TextView
        val ivDelete = itemView.findViewById(R.id.iv_delete) as ImageView
        val ivDelete1 = itemView.findViewById(R.id.iv_delete1) as ImageView

        fun bindItems(addressList: GetAddressListResponse.AddressListBody) {

            tvName.setText(addressList.name)
            tvAddress.setText(addressList.address)
            tvPhone.setText(addressList.countryCode + "-" + addressList.phone)

            tvEdit.setOnClickListener()
            {
                val intent = Intent(mContext, AddAddressActivity::class.java)
                intent.putExtra("id", addressList.id.toString())
                intent.putExtra("name", addressList.name)
                intent.putExtra("address", addressList.address)
                intent.putExtra("countryCode", addressList.countryCode.toString())
                intent.putExtra("phone", addressList.phone)
                intent.putExtra("latitude", addressList.latitude)
                intent.putExtra("longitude", addressList.longitude)
                mContext!!.startActivity(intent)
            }

            ivDelete.setOnClickListener {
                addressListActivity.deleteAPIMethod(adapterPosition, addressList.id.toString())
                addressListdata.remove(addressList)
                notifyDataSetChanged()
            }


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



