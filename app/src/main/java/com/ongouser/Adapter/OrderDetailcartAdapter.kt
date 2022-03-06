package com.ongouser.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import com.ongouser.R
import com.ongouser.pojo.GetCartItemsModel
import com.ongouser.pojo.OrderItemsItem

class OrderDetailcartAdapter(var context: Context ,var orderitems:ArrayList<OrderItemsItem>) : RecyclerView.Adapter<OrderDetailcartAdapter.RecyclerViewHolder>() {
    var inflater: LayoutInflater

    inner class RecyclerViewHolder(view: View?) : RecyclerView.ViewHolder(view!!)
    {
        val productimage = view!!.findViewById<RoundedImageView>(R.id.ivDetail)
        val name = view!!.findViewById<TextView>(R.id.cart_productname)
        val price = view!!.findViewById<TextView>(R.id.ca_tvprice)
        val qty = view!!.findViewById<TextView>(R.id.ca_qty)
        val plus = view!!.findViewById<TextView>(R.id.ca_plustv)
        val minus = view!!.findViewById<TextView>(R.id.ca_minustv)
        val removeitem = view!!.findViewById<ImageView>(R.id.ca_removeitem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val v = inflater.inflate(R.layout.res_cart_order, parent, false)
        return RecyclerViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.minus.visibility = View.GONE
        holder.plus.visibility = View.GONE
        holder.removeitem.visibility = View.GONE
        Glide.with(context).load(orderitems[position]!!.product!!.image).placeholder(R.mipmap.no_image_placeholder).into(holder.productimage)
        holder.qty.setText("Qty: ${orderitems[position]!!.qty.toString()}")

        try {
            val value=orderitems[position]!!.netAmount!!.toFloat()* orderitems[position]!!.qty!!.toInt()
            holder.price.text = "$"+value
        } catch (e: Exception) {
        }
        orderitems[position].product?.run {
            holder.name.text = name
        }
        /*holder.itemView.setOnClickListener {
            val i = Intent(context, ProductDetailActivity::class.java)
            context.startActivity(i)
        }*/
    }


    override fun getItemCount(): Int {
        return orderitems.size
    }

    init {
        inflater = LayoutInflater.from(context)
    }

}