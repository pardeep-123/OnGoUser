package com.ongouser.utils.others

import android.app.Activity
import android.content.Context
import android.content.DialogInterface

import android.util.Log
import android.view.View


import com.google.android.material.snackbar.Snackbar

import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.ongouser.R
import java.util.*


class GlobalVariable() {



    companion object{



       public fun showmessage(view:View,msg:String)
        {
            Snackbar
                .make(view, msg, Snackbar.LENGTH_LONG)
                .show()
        }





        fun getCompleteAddressString(context: Context,LATITUDE: Double, LONGITUDE: Double): String {
            var strAdd = ""
            val geocoder = Geocoder(context, Locale.getDefault())
            try {
                val addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1)
                if (addresses != null) {
                    val returnedAddress = addresses[0]
                    val strReturnedAddress = StringBuilder("")

                    for (i in 0..returnedAddress.maxAddressLineIndex) {
                        strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n")
                    }
                    strAdd = strReturnedAddress.toString()
                    Log.w("My Current loction", strReturnedAddress.toString())
                } else {
                    Log.w("My Current loction", "No Address returned!")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.w("My Current loction", "Canont get Address!")
            }

            return strAdd
        }


         public fun globaldialog(context: Context,message:String,okselect: OnOkselect) {

                val builder = AlertDialog.Builder(context)
                builder.setMessage(message)
                builder.setPositiveButton("Ok"
                ) { dialog, which ->

                    okselect.onselect()
                    dialog.dismiss()
                }


            builder.setCancelable(false)
                val dialog = builder.create()
                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.setOnShowListener(DialogInterface.OnShowListener {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                        .setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                        .setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
                })
                dialog.show()

        }


        fun globalyesno_btndialog(context: Context, message:String, okselect: OnOkselectforlocation) {

            val builder = AlertDialog.Builder(context)
            builder.setMessage(message)
            builder.setPositiveButton(context.getString(R.string.yes)
            ) { dialog, which ->

                okselect.yesselect()
                dialog.dismiss()
            }
            builder.setNegativeButton("no"
            ) { dialog, which ->
                okselect.noselect()
                dialog.dismiss()
            }

            builder.setCancelable(false)
            val dialog = builder.create()
            //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            dialog.setOnShowListener(DialogInterface.OnShowListener {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                    .setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                    .setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
            })
            dialog.show()

        }


        /************************* distance calculator*********************************/

         fun feet(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Long {
            val locationA = Location("pointA")

            locationA.latitude = lat1
            locationA.longitude = lon1

            val locationB = Location("pointB")

            locationB.latitude = lat2
            locationB.longitude = lon2
            val meter = locationA.distanceTo(locationB)
            val km = meter/1000
            val feet = km*3280.84
            val distance = Math.round(feet)
            return distance
        }

        fun meter(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Int {
            val locationA = Location("pointA")

            locationA.latitude = lat1
            locationA.longitude = lon1

            val locationB = Location("pointB")

            locationB.latitude = lat2
            locationB.longitude = lon2
            val meter = locationA.distanceTo(locationB)
            val distance = Math.round(meter)
            return distance
        }





    }

    interface OnOkselect
    {
        fun onselect()
    }
    interface OnOkselectforlocation
    {
        fun yesselect()
        fun noselect()
    }

    interface variables{
        companion object{
            const val isfirstime = "yes"
            const val Get_Profile = "getprofile"
            const val Token = "token"

            const val CameraPick = "camera"
            const val GaleeryPick = "gallery"
            const val NoImagePick = "noimage"


        }
    }

}