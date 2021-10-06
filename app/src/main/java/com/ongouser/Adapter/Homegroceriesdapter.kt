package com.ongouser.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ongouser.R
import com.ongouser.home.activity.ViewAllActivity
import com.ongouser.pojo.CategoriesItem
import java.util.*

class Homegroceriesdapter(var context: Context, var categoriesItems: ArrayList<CategoriesItem>) :
    RecyclerView.Adapter<Homegroceriesdapter.RecyclerViewHolder>() {
    var inflater: LayoutInflater

    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var catimage: ImageView
        var ivOff: ImageView? = null
        var temp = 2
        var catname: TextView

        init {
            catname = view.findViewById(R.id.tv_grocname)
            catimage = view.findViewById(R.id.iv_shop_img)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {
        val v = inflater.inflate(R.layout.res_groceries, parent, false)
        return RecyclerViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        Glide.with(context).load(categoriesItems[position].image)
            .placeholder(R.mipmap.no_image_placeholder).into(holder.catimage)
        holder.catname.text = categoriesItems[position].name
        holder.itemView.setOnClickListener {
            val i = Intent(context, ViewAllActivity::class.java)
            i.putExtra("categoryId", categoriesItems[holder.adapterPosition].id.toString())
            i.putExtra("categoryName", categoriesItems[holder.adapterPosition].name)
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return categoriesItems.size
    }

    init {
        inflater = LayoutInflater.from(context)
    }
}