package com.ongouser.pojo

import com.google.gson.annotations.SerializedName

data class OrderDetailModel(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("body")
	val body: OrderDetailModelBody? = null
)

data class OrderDetailModelBody(

	@field:SerializedName("orderNo")
	val orderNo: String? = null,

	@field:SerializedName("netAmount")
	val netAmount: String? = null,

	@field:SerializedName("shippingCharges")
	val shippingCharges: String? = null,

	@field:SerializedName("created")
	val created: Int? = null,

	@field:SerializedName("orderStatus")
	val orderStatus: Int? = null,

	@field:SerializedName("vendorId")
	val vendorId: Int? = null,

	@field:SerializedName("taxCharged")
	val taxCharged: String? = null,

	@field:SerializedName("orderItems")
	val orderItems: ArrayList<OrderItemsItem>? = null,

	@field:SerializedName("adminCommission")
	val adminCommission: String? = null,

	@field:SerializedName("total")
	val total: String? = null,

	@field:SerializedName("vendor")
	val vendor: Vendor? = null,

	@field:SerializedName("qty")
	val qty: Int? = null,

	@field:SerializedName("deliverySlot")
	val deliverySlot: String? = null,

	@field:SerializedName("customerId")
	val customerId: Int? = null,

	@field:SerializedName("paymentMethod")
	val paymentMethod: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("deliveryDate")
	val deliveryDate: String? = null,

	@field:SerializedName("updated")
	val updated: Int? = null,

	@field:SerializedName("customer")
	val customer: Customer? = null
)

data class OrderItemsItem(

	@field:SerializedName("product")
	val product: OrderProduct? = null,

	@field:SerializedName("productId")
	val productId: Int? = null,

	@field:SerializedName("orderId")
	val orderId: Int? = null,

	@field:SerializedName("netAmount")
	val netAmount: String? = null,

	@field:SerializedName("shippingCharges")
	val shippingCharges: String? = null,

	@field:SerializedName("created")
	val created: Int? = null,

	@field:SerializedName("taxCharged")
	val taxCharged: String? = null,

	@field:SerializedName("adminCommission")
	val adminCommission: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("total")
	val total: String? = null,

	@field:SerializedName("qty")
	val qty: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("updated")
	val updated: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class OrderVendorDetail(

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

data class Vendor(

	@field:SerializedName("vendorDetail")
	val vendorDetail: OrderVendorDetail? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null
)

data class Customer(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null
)

data class OrderProduct(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("weight")
	val weight: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("weightUnit")
	val weightUnit: Int? = null
)
