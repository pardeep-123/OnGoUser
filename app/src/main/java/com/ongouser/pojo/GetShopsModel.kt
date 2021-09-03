package com.ongouser.pojo

import com.google.gson.annotations.SerializedName

data class GetShopsModel(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("body")
	val body: List<GetShopbody>? = null
)

data class User(

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("vendorDetail")
	val vendorDetail: VendorDetail? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null
)

data class VendorDetail(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("isDeliveryChargesAdded")
	val isDeliveryChargesAdded: Int? = null,

	@field:SerializedName("isHomeDeliveryAdded")
	val isHomeDeliveryAdded: Int? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("postalCode")
	val postalCode: String? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("shopName")
	val shopName: String? = null,

	@field:SerializedName("shopOpenTime")
	val shopOpenTime: String? = null,

	@field:SerializedName("detailId")
	val detailId: Int? = null,

	@field:SerializedName("bankName")
	val bankName: String? = null,

	@field:SerializedName("isShopAdded")
	val isShopAdded: Int? = null,

	@field:SerializedName("shopAddress")
	val shopAddress: String? = null,

	@field:SerializedName("shopCloseTime")
	val shopCloseTime: String? = null,

	@field:SerializedName("accountHolderName")
	val accountHolderName: String? = null,

	@field:SerializedName("shopCategory")
	val shopCategory: String? = null,

	@field:SerializedName("deliveryPolicy")
	val deliveryPolicy: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("buildingNumber")
	val buildingNumber: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("approvalStatus")
	val approvalStatus: Int? = null,

	@field:SerializedName("bsbNumber")
	val bsbNumber: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("streetNumber")
	val streetNumber: String? = null,

	@field:SerializedName("bankBranch")
	val bankBranch: String? = null,

	@field:SerializedName("isDeliveryOptionsAdded")
	val isDeliveryOptionsAdded: Int? = null,

	@field:SerializedName("shopLogo")
	val shopLogo: String? = null,

	@field:SerializedName("created")
	val created: Int? = null,

	@field:SerializedName("sellerInformation")
	val sellerInformation: String? = null,

	@field:SerializedName("abn")
	val abn: String? = null,

	@field:SerializedName("taxInPercent")
	val taxInPercent: Int? = null,

	@field:SerializedName("accountNumber")
	val accountNumber: String? = null,

	@field:SerializedName("bankAddress")
	val bankAddress: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null,

	@field:SerializedName("approvalStatusReason")
	val approvalStatusReason: String? = null,

	@field:SerializedName("ifscSwiftCode")
	val ifscSwiftCode: String? = null,

	@field:SerializedName("isDeliveryDaysAdded")
	val isDeliveryDaysAdded: Int? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("geoLocation")
	val geoLocation: String? = null,

	@field:SerializedName("taxValue")
	val taxValue: Int? = null,

	@field:SerializedName("shopDescription")
	val shopDescription: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("deliveriesPerDay")
	val deliveriesPerDay: Int? = null,

	@field:SerializedName("homeDelivery")
	val homeDelivery: Int? = null,

	@field:SerializedName("updated")
	val updated: Int? = null,

	@field:SerializedName("paymentPolicy")
	val paymentPolicy: String? = null
)

data class GetShopbody(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("shopCategoryId")
	val shopCategoryId: Int? = null,

	@field:SerializedName("created")
	val created: Int? = null,

	@field:SerializedName("vendorId")
	val vendorId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("updated")
	val updated: Int? = null,

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
