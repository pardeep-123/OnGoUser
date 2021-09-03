package com.ongouser.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ongouser.R
import com.ongouser.home.activity.OrdersummryActivity
import com.ongouser.pojo.GetNotificationModel
import com.ongouser.utils.others.CommonMethods
import kotlinx.android.synthetic.main.list_noti.view.*

class NotificationAdapter(var context: Context,val getNotificationModel: GetNotificationModel) :
    RecyclerView.Adapter<NotificationAdapter.RecyclerViewHolder>() {
    var inflater: LayoutInflater
    var rl_list: ImageView? = null

    inner class RecyclerViewHolder(view: View?) : RecyclerView.ViewHolder(
        view!!
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {
        val v = inflater.inflate(R.layout.list_noti, parent, false)
        return RecyclerViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.itemView.notimsg.setText(getNotificationModel.body!![position].message)
        holder.itemView.notitime.setText(CommonMethods.modifyDateLayout(getNotificationModel.body!![position].createdAt))

        holder.itemView.setOnClickListener {
            val openintent = Intent(context, OrdersummryActivity::class.java)
            openintent.putExtra("id", getNotificationModel.body!![position].orderid.toString())
            openintent.putExtra("from", "notification")
            context.startActivity(openintent)
        }
    }
    override fun getItemCount(): Int {
        return getNotificationModel.body!!.size
    }

    init {
        inflater = LayoutInflater.from(context)
    }
}