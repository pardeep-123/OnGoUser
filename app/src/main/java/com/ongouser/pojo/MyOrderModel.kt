package com.ongouser.pojo

import com.google.gson.annotations.SerializedName

data class MyOrderModel(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("body")
	val body: MyOrderBody? = null
)

data class MyOrderBody(

	@field:SerializedName("pastdates")
	val pastdates: List<PastdatesItem>? = null,

	@field:SerializedName("future_dates")
	val futureDates: List<PastdatesItem>? = null
)

data class PastdatesItem(

	@field:SerializedName("userAddress")
	val userAddress: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

   @field:SerializedName("total")
	val totalamount: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("vendor_image")
	val vendorImage: String? = null,

	@field:SerializedName("vendor_name")
	val vendorName: String? = null,

	@field:SerializedName("orderNo")
	val orderNo: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("deliverySlot")
	val deliverySlot: String? = null,

	@field:SerializedName("isSelfpickup")
	val isSelfpickup: Int? = null,

	@field:SerializedName("orderStatus")
	val orderStatus: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
