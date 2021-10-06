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
import com.ongouser.home.activity.product.ProductDetailActivity
import com.ongouser.pojo.FavoriteBodyItem
import com.ongouser.pojo.NotificationBody
import java.util.*

class FavouritesAdapter(var context: Context, var getMyFavoriteListing: ArrayList<FavoriteBodyItem?>, var getMyFavoriteListingTemp: ArrayList<FavoriteBodyItem?>, var onLikeListener: OnLikeListener) : RecyclerView.Adapter<FavouritesAdapter.RecyclerViewHolder>() {
    var inflater: LayoutInflater
    var rl_list: RelativeLayout? = null

    inner class RecyclerViewHolder(view: View?) : RecyclerView.ViewHolder(view!!)
    {
        var fvproductlike = view!!.findViewById<ImageView>(R.id.fv_productlike)
        var productimage = view!!.findViewById<ImageView>(R.id.fv_productimage)
        var productname = view!!.findViewById<TextView>(R.id.fv_productname)
        var productdescription = view!!.findViewById<TextView>(R.id.fv_productdescription)
        var productprice = view!!.findViewById<TextView>(R.id.fv_productprice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val v = inflater.inflate(R.layout.res_favourites, parent, false)
        return RecyclerViewHolder(v)
    }

    public fun  filter(charText: String, rcyMenderList: RecyclerView, tv_no_mender: TextView) {
        var  charText = charText.toLowerCase()
        var nList = ArrayList<FavoriteBodyItem?>()
        if (charText.length == 0) {
            nList.addAll(getMyFavoriteListingTemp!!)
        } else {
            for (wp in getMyFavoriteListingTemp) {
                var value = wp!!.product!!.name
                if (value!!.toLowerCase().contains(charText.toLowerCase())) {
                    nList.add(wp)
                }
            }
        }
        getMyFavoriteListing= nList;
        notifyDataSetChanged();
        if (!getMyFavoriteListing.isEmpty()){

            tv_no_mender.visibility=View.GONE
            rcyMenderList.visibility=View.VISIBLE
        }else{

            tv_no_mender.visibility=View.VISIBLE
            rcyMenderList.visibility=View.GONE
        }

    }
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        Glide.with(context)
                .load(getMyFavoriteListing!![position]!!.product!!.image)
                .error(R.drawable.ic_image_placeholder)
                .placeholder(R.drawable.ic_image_placeholder)
                .into(holder.productimage)

        holder.productname.setText(getMyFavoriteListing!![position]!!.product!!.name)
        holder.productdescription.setText(getMyFavoriteListing.get(position)!!.product!!.description)
        holder.productprice.setText("$${getMyFavoriteListing.get(position)!!.product!!.mrp}")
        holder.fvproductlike.setOnClickListener {
            onLikeListener.OnClick(getMyFavoriteListing.get(position)!!.product!!.id.toString(), position)
        }

        holder.itemView.setOnClickListener {
            val i = Intent(context, ProductDetailActivity::class.java)
            i.putExtra("data", getMyFavoriteListing.get(position)!!.product)
            context.startActivity(i)
        }
    }

    fun removeitem(position: Int)
    {
        getMyFavoriteListing.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeRemoved(position, itemCount)
    }
    override fun getItemCount(): Int {
        return getMyFavoriteListing.size
    }

    init {
        inflater = LayoutInflater.from(context)
    }
}
interface OnLikeListener{
    fun OnClick(id: String, position: Int)
}