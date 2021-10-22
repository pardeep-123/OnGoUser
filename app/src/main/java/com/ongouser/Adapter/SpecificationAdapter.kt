package com.ongouser.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.R
import com.ongouser.pojo.ProductSpecifications

class SpecificationAdapter(var list : ArrayList<ProductSpecifications>) : RecyclerView.Adapter<SpecificationAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SpecificationAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.items_specifications, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: SpecificationAdapter.ViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.value.text = list[position].value
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.fv_productname)
        val value = itemView.findViewById<TextView>(R.id.fv_productvalue)
    }
}