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
import com.ongouser.home.activity.OrdersummryActivity
import com.ongouser.pojo.PastdatesItem
import com.ongouser.utils.others.CommonMethods
import kotlinx.android.synthetic.main.activity_edit_profile.*

class OrderDetailAdapter(var context: Context, var type: String,var orderlist : ArrayList<PastdatesItem>) : RecyclerView.Adapter<OrderDetailAdapter.RecyclerViewHolder>() {
    var inflater: LayoutInflater
    var ll_1: RelativeLayout? = null

    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView
        var orderno: TextView
        var vendorimage: ImageView
        var orderamount: TextView
        var tvstatus: TextView
        var tvdate: TextView
        var tv_reorder: TextView

        init {
            orderamount = view.findViewById(R.id.orderamount)
            tvName = view.findViewById(R.id.tvName)
            orderno = view.findViewById(R.id.orderno)
            vendorimage = view.findViewById(R.id.vendorimage)
            tvstatus = view.findViewById(R.id.tvstatus)
            tvdate = view.findViewById(R.id.tvdate)
            tv_reorder = view.findViewById(R.id.tv_reorder)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val v = inflater.inflate(R.layout.list_past, parent, false)
        return RecyclerViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.tvName.setText(orderlist[position].vendorName)
        holder.orderno.setText("Order Number ${orderlist[position].orderNo}")
        holder.orderamount.setText("$${orderlist[position].totalamount}")
        holder.tvdate.text = "Ordered on: "+ CommonMethods.parseDateToddMMyyyy(orderlist[position].createdAt,"dd-MM-yyyy")
        Glide.with(context).load(orderlist[position].vendorImage).error(R.mipmap.no_image_placeholder).into(holder.vendorimage)

        if (type == "2") {
           // holder.tvstatus.visibility = View.VISIBLE
            holder.tvdate.visibility = View.GONE
            holder.tv_reorder.visibility = View.GONE
        } else {
           // holder.tvstatus.visibility = View.GONE
            holder.tvdate.visibility = View.VISIBLE
            holder.tv_reorder.visibility = View.GONE
        }

        textStatusUpdate(orderlist[position].orderStatus,holder.tvstatus)
        holder.itemView.setOnClickListener {
            if (type == "1") {
                val i = Intent(context, OrdersummryActivity::class.java)
                i.putExtra("id", orderlist[position].id.toString())
                context.startActivity(i)
            }
        }
    }

    private fun textStatusUpdate(orderStatus: Int?, tvstatus: TextView) {
/*
0: 'Pending', 1: 'Accepted', 2: 'Packed', 3: 'Shipped', 4: 'Delivered', 5: 'Cancelled', 6: 'Declined',*/
        when(orderStatus){

            0->{
                tvstatus.text="Pending"
            }
            1->{
                tvstatus.text="Accepted"
            }
            2->{
                tvstatus.text="Packed"
            }
            3->{
                tvstatus.text="Shipped"
            }
            4->{
                tvstatus.text="Delivered"
            }
            5->{
                tvstatus.text="Cancelled"
            }
            6->{
                tvstatus.text="Declined"
            }

        }
    }

    override fun getItemCount(): Int {
        return orderlist.size
    }

    init {
        inflater = LayoutInflater.from(context)
    }
}