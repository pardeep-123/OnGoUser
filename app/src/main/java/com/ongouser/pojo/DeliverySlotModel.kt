package com.ongouser.pojo

import com.google.gson.annotations.SerializedName

data class DeliverySlotModel(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("body")
	val body: List<Deliveryslotmodelbody>? = null
)

data class Deliveryslotmodelbody(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("created")
	val created: Int? = null,

	@field:SerializedName("sortOrder")
	val sortOrder: Int? = null,

	@field:SerializedName("vendorId")
	val vendorId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("deliveryTimeTo")
	val deliveryTimeTo: String? = null,

	@field:SerializedName("day")
	val day: String? = null,

	@field:SerializedName("updated")
	val updated: Int? = null,

	@field:SerializedName("deliveryTimeFrom")
	val deliveryTimeFrom: String? = null,

	@field:SerializedName("noDelivery")
	val noDelivery: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
