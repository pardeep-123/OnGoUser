package com.onerestaurant.vendor.adapter
/*
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onerestaurant.R
import com.onerestaurant.util.chatModel.Datum


import de.hdodenhof.circleimageview.CircleImageView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ChatAdapter(val context: Context, internal val arrayList: ArrayList<Datum>, val senderId:String, val driverImage:String) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

     val TYPE_USER = 0
     val TYPE_FRIEND = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == TYPE_USER) {
            return (UserViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.user_viewholder,
                    parent,
                    false
                )
            ))
        } else if (viewType == TYPE_FRIEND) {
            return (FriendViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.friend_viewholder,
                    parent,
                    false
                )
            ))
        }

         return (FriendViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.friend_viewholder,
                parent,
                false
            )
        ))

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder.itemViewType == TYPE_FRIEND) {
            val friendViewHolder = holder as FriendViewHolder
            friendViewHolder.txt_friendView.setText(arrayList[position].message)
            friendViewHolder.txt_time.setText(convertTimeStampTime(arrayList[position].created.toLong()))
            Glide.with(context).load(driverImage).placeholder(R.drawable.com_facebook_profile_picture_blank_portrait).into(friendViewHolder.profileimage)

        } else if (holder.itemViewType == TYPE_USER) {
            val userViewHolder = holder as UserViewHolder
            userViewHolder.txt_userView.setText(arrayList[position].message)
            Glide.with(context).load(arrayList[position].senderImage).placeholder(R.drawable.com_facebook_profile_picture_blank_portrait).into(userViewHolder.profileimage)

            userViewHolder.txt_time.setText(convertTimeStampTime(arrayList[position].created.toLong()))
        }
    }

    override fun getItemViewType(position: Int): Int {

        if (senderId==arrayList[position].senderId)
        {

                return TYPE_USER
        }
        else
        {
                return TYPE_FRIEND


        }
    }

    fun convertTimeStampTime(timestamp: Long): String {
        val calendar = Calendar.getInstance()
        val tz = TimeZone.getDefault()
        calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.timeInMillis))
        val sdf = SimpleDateFormat("HH:mm a")
        sdf.timeZone = tz
        val currenTimeZone = Date(timestamp * 1000)
        return sdf.format(currenTimeZone)
    }


    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_userView: TextView = itemView.findViewById<TextView>(R.id.senderText)
        val txt_time: TextView = itemView.findViewById<TextView>(R.id.sender_time)
        val profileimage: CircleImageView = itemView.findViewById<CircleImageView>(R.id.senderImage)
    }

    inner class FriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_friendView: TextView = itemView.findViewById<TextView>(R.id.receiverText)
        val txt_time: TextView = itemView.findViewById<TextView>(R.id.receiver_time)
        val profileimage: CircleImageView = itemView.findViewById<CircleImageView>(R.id.img_profile)

    }
}*/
