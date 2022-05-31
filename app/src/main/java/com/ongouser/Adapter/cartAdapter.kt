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
import com.ongouser.pojo.CartListingModel
import com.ongouser.pojo.GetCartItemsBody
import com.ongouser.pojo.GetCartItemsModel

class cartAdapter(var context: Context, var getCartItemsModel: CartListingModel.Body, var cartItemRemoved: CartItemRemoved) : RecyclerView.Adapter<cartAdapter.RecyclerViewHolder>() {
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
        Glide.with(context).load(getCartItemsModel.cartItems[position].product.image).placeholder(R.mipmap.no_image_placeholder).into(holder.productimage)
        holder.qty.setText(getCartItemsModel.cartItems[position].qty.toString())
        getCartItemsModel.cartItems!![position]!!.product?.run {
            holder.name.setText(name)
            holder.price.setText("$$mrp")
        }

        holder.removeitem.setOnClickListener {
         cartItemRemoved.onitemremoved(position,getCartItemsModel.cartItems!![position]!!.id!!.toString())

        }
        holder.plus.setOnClickListener {
            Log.e("sdf","++")
            getCartItemsModel.cartItems!![position]!!.qty = getCartItemsModel.cartItems!![position]!!.qty!!+1
            notifyItemChanged(position)
            cartItemRemoved.updatecartitem(getCartItemsModel.cartItems!![position]!!.id!!.toString(), getCartItemsModel.cartItems!![position]!!.qty.toString())

        }
        holder.minus.setOnClickListener {
            if (getCartItemsModel.cartItems!![position]!!.qty!!>1)
            {
                Log.e("sdf","--")
                getCartItemsModel.cartItems!![position]!!.qty = getCartItemsModel.cartItems!![position]!!.qty!!-1
                notifyItemChanged(position)
                cartItemRemoved.updatecartitem(getCartItemsModel.cartItems!![position]!!.id!!.toString(), getCartItemsModel.cartItems!![position]!!.qty.toString())


            }

        }

        /*holder.itemView.setOnClickListener {
            val i = Intent(context, ProductDetailActivity::class.java)
            context.startActivity(i)
        }*/
    }

    fun removecartitem(position: Int)
    {
        getCartItemsModel.cartItems.removeAt(position);
        notifyItemRemoved(position)
    }
    override fun getItemCount(): Int {
        return getCartItemsModel.cartItems.size
    }

    init {
        inflater = LayoutInflater.from(context)
    }
    interface CartItemRemoved{
        fun onitemremoved(position: Int,id:String)
        fun updatecartitem(id: String,qty:String)
    }
}