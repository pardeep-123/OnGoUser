package com.ongouser.pojo

import com.google.gson.annotations.SerializedName

data class ShopAdressModel(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("body")
	val body: List<ShopAddressBody>? = null
)

data class ShopAddressBody(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("streetNumber")
	val streetNumber: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("geoLocation")
	val geoLocation: String? = null,

	@field:SerializedName("postalCode")
	val postalCode: String? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("buildingNumber")
	val buildingNumber: String? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null
)
