package com.ongouser.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.R
import com.ongouser.home.activity.address.AddAddressActivity
import com.ongouser.home.activity.address.AddressListActivity
import com.ongouser.pojo.GetAddressListResponse
import kotlin.collections.ArrayList


class AddressListingAdapter(var mContext: Context?, internal var addressList: ArrayList<GetAddressListResponse.AddressListBody>,
                            internal var addressListActivity: AddressListActivity) : RecyclerView.Adapter<AddressListingAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.res_address, parent, false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bindItems(addressList[position])

    }

    override fun getItemCount(): Int {
        return addressList!!.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvName = itemView.findViewById(R.id.tv_name) as TextView
        val tvAddress = itemView.findViewById(R.id.tv_address) as TextView
        val tvPhone = itemView.findViewById(R.id.tv_phone) as TextView
        val tvEdit = itemView.findViewById(R.id.tv_edit) as TextView
        val ivDelete = itemView.findViewById(R.id.iv_delete) as ImageView

        fun bindItems(addressList: GetAddressListResponse.AddressListBody) {

            tvName.setText(addressList.name)
            tvAddress.setText(addressList.address)
            tvPhone.setText(addressList.countryCode + "-" + addressList.phone)


            tvEdit.setOnClickListener()
            {
                var intent = Intent(mContext, AddAddressActivity::class.java)
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
            }

        }


        init {

        }

    }
}



