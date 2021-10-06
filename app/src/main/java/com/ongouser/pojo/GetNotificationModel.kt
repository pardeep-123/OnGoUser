package com.ongouser.pojo

import com.google.gson.annotations.SerializedName

data class GetNotificationModel(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("body")
	val body: List<NotificationBody>? = null
)

data class NotificationBody(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("orderid")
	val orderid: Int? = null,

	@field:SerializedName("userby")
	val userby: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("userto")
	val userto: String? = null
)
