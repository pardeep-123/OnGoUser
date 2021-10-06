package com.ongouser.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.R
import com.ongouser.home.activity.ViewAllActivity
import com.ongouser.pojo.CategoryListingResponse
import com.ongouser.utils.others.CategoryClick
import java.util.*

class CategoryAdapter(var context: Context, var list: ArrayList<CategoryListingResponse.Body>,var categoryClick: CategoryClick) : RecyclerView.Adapter<CategoryAdapter.RecyclerViewHolder>() {
    var inflater: LayoutInflater

    var templist: ArrayList<CategoryListingResponse.Body>

    init {
        inflater = LayoutInflater.from(context)
        this.templist = list
    }

    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView

        init {
            tvName = view.findViewById(R.id.tvName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val v = inflater.inflate(R.layout.res_shopcategory, parent, false)
        return RecyclerViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.tvName.text = list[position].name
       /* holder.itemView.setOnClickListener {
            val i = Intent(context, ViewAllActivity::class.java)
            context.startActivity(i)
        }*/
        holder.itemView.setOnClickListener { categoryClick.categoryClickk(position, list[position].id.toString(),
                list[position].name!!, list.size) }

    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun filter(charText: String,rec_category:RecyclerView, nobooking: TextView) {
        var charText = charText
        charText = charText.toLowerCase()
        val nList: MutableList<CategoryListingResponse.Body?> =
                ArrayList<CategoryListingResponse.Body?>()
        if (charText.length == 0) {
            nList.addAll(templist)
        } else {
            for (wp in templist) {
                if (wp!!.name!!.toLowerCase()
                                .contains(charText.toLowerCase())) {
                    nList.add(wp)
                }
            }
        }
        list = nList as ArrayList<CategoryListingResponse.Body>
        notifyDataSetChanged()


        if (list.isEmpty()) {
            nobooking.setVisibility(View.VISIBLE)
            rec_category.setVisibility(View.GONE)
        } else {
            nobooking.setVisibility(View.GONE)
            rec_category.setVisibility(View.VISIBLE)
        }
    }

    init {
        inflater = LayoutInflater.from(context)
    }
}