package com.ongouser.Adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.R
import com.ongouser.pojo.DeliverySlotModel
import com.ongouser.pojo.Deliveryslotmodelbody

class DeliverytimeslotAdapter(var context: Context, val list: MutableList<Deliveryslotmodelbody>, var timeslotclick: Timeslotclick) : RecyclerView.Adapter<DeliverytimeslotAdapter.RecyclerViewHolder>() {
    var inflater: LayoutInflater
    var rl_list: RelativeLayout? = null
    var positi = -1

    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvday: TextView
        var tvtime: TextView
        var tvavailable: TextView
        var cardback: CardView

        init {
            tvday = view.findViewById(R.id.tvday)
            tvtime = view.findViewById(R.id.tvtime)
            tvavailable = view.findViewById(R.id.tvavailable)
            cardback = view.findViewById(R.id.cardback)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val v = inflater.inflate(R.layout.res_timeslot, parent, false)
        return RecyclerViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        when(list!![position]!!.day){
           "sun" -> holder.tvday.setText("Sunday")
           "mon" -> holder.tvday.setText("Monday")
           "tue" -> holder.tvday.setText("Tuesday")
           "wed" -> holder.tvday.setText("Wednesday")
           "thu" -> holder.tvday.setText("Thursday")
           "fri" -> holder.tvday.setText("Friday")
           "sat" -> holder.tvday.setText("Saturday")
        }

        holder.tvtime.text = list!![position]!!.deliveryTimeFrom + " - " + list!![position]!!.deliveryTimeTo
        if (positi == position) {
            holder.tvavailable.setTextColor(Color.parseColor("#ffffff"))
            holder.tvday.setTextColor(Color.parseColor("#ffffff"))
            holder.tvtime.setTextColor(Color.parseColor("#ffffff"))
            holder.cardback.setCardBackgroundColor(Color.parseColor("#49c191"))
        } else {
            holder.tvavailable.setTextColor(Color.parseColor("#000000"))
            holder.tvday.setTextColor(Color.parseColor("#000000"))
            holder.tvtime.setTextColor(Color.parseColor("#000000"))
            holder.cardback.setCardBackgroundColor(Color.parseColor("#ffffff"))
        }
        holder.itemView.setOnClickListener {
            positi = position
            timeslotclick.onClick(list[position]!!.day!!, list[position]!!.deliveryTimeFrom + " - " + list[position]!!.deliveryTimeTo)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    init {
        inflater = LayoutInflater.from(context)
    }
    interface Timeslotclick{
        fun onClick(day:String,slot:String)
    }
}