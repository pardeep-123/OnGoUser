package com.ongouser.pojo

import com.google.gson.annotations.SerializedName

data class HomeListingResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("body")
	val body: Body? = null
)

data class Body(

	@field:SerializedName("categories")
	val categories: ArrayList<CategoriesItem>? = null,

	@field:SerializedName("vendors")
	val vendors: ArrayList<VendorsItem>? = null,

	@field:SerializedName("banners")
	val banners: ArrayList<BannersItem?>? = null,

	@field:SerializedName("products")
	val products: ArrayList<ProductsItem?>? = null
)

data class BannersItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("created")
	val created: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("updated")
	val updated: Int? = null,

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class CategoriesItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("created")
	val created: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("updated")
	val updated: Int? = null,

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class VendorsItem(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("postalCode")
	val postalCode: String? = null,

	@field:SerializedName("shopOpenTime")
	val shopOpenTime: String? = null,

	@field:SerializedName("productPriceHighest")
	val productPriceHighest: Int? = null,

	@field:SerializedName("isShopAdded")
	val isShopAdded: Int? = null,

	@field:SerializedName("shopAddress")
	val shopAddress: String? = null,

	@field:SerializedName("shopCloseTime")
	val shopCloseTime: String? = null,

	@field:SerializedName("deliveryPolicy")
	val deliveryPolicy: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("buildingNumber")
	val buildingNumber: String? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("streetNumber")
	val streetNumber: String? = null,

	@field:SerializedName("isDeliveryOptionsAdded")
	val isDeliveryOptionsAdded: Int? = null,

	@field:SerializedName("shopLogo")
	val shopLogo: String? = null,

	@field:SerializedName("created")
	val created: Int? = null,

	@field:SerializedName("sellerInformation")
	val sellerInformation: String? = null,

	@field:SerializedName("bankAddress")
	val bankAddress: String? = null,

	@field:SerializedName("approvalStatusReason")
	val approvalStatusReason: String? = null,

	@field:SerializedName("isDeliveryDaysAdded")
	val isDeliveryDaysAdded: Int? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("deliveriesPerDay")
	val deliveriesPerDay: Int? = null,

	@field:SerializedName("vendorDetailId")
	val vendorDetailId: Int? = null,

	@field:SerializedName("updated")
	val updated: Int? = null,

	@field:SerializedName("paymentPolicy")
	val paymentPolicy: String? = null,

	@field:SerializedName("isDeliveryChargesAdded")
	val isDeliveryChargesAdded: Int? = null,

	@field:SerializedName("isHomeDeliveryAdded")
	val isHomeDeliveryAdded: Int? = null,

	@field:SerializedName("distance")
	val distance: Double? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("shopName")
	val shopName: String? = null,

	@field:SerializedName("bankName")
	val bankName: String? = null,

	@field:SerializedName("accountHolderName")
	val accountHolderName: String? = null,

	@field:SerializedName("shopCategory")
	val shopCategory: String? = null,

	@field:SerializedName("approvalStatus")
	val approvalStatus: Int? = null,

	@field:SerializedName("bsbNumber")
	val bsbNumber: String? = null,

	@field:SerializedName("bankBranch")
	val bankBranch: String? = null,

	@field:SerializedName("abn")
	val abn: String? = null,

	@field:SerializedName("taxInPercent")
	val taxInPercent: Int? = null,

	@field:SerializedName("accountNumber")
	val accountNumber: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null,

	@field:SerializedName("ifscSwiftCode")
	val ifscSwiftCode: String? = null,

	@field:SerializedName("productPriceLowest")
	val productPriceLowest: Int? = null,

	@field:SerializedName("geoLocation")
	val geoLocation: String? = null,

	@field:SerializedName("taxValue")
	val taxValue: Int? = null,

	@field:SerializedName("shopDescription")
	val shopDescription: String? = null,

	@field:SerializedName("homeDelivery")
	val homeDelivery: Int? = null
)

data class ProductsItem(

	@field:SerializedName("isAvailable")
	val isAvailable: Int? = null,

	@field:SerializedName("distance")
	val distance: Double? = null,

	@field:SerializedName("vendorId")
	val vendorId: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("barcodeImage")
	val barcodeImage: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("dimensionsUnit")
	val dimensionsUnit: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("minimumSellingPrice")
	val minimumSellingPrice: String? = null,

	@field:SerializedName("isApproved")
	val isApproved: Int? = null,

	@field:SerializedName("sku")
	val sku: String? = null,

	@field:SerializedName("barcode")
	val barcode: String? = null,

	@field:SerializedName("height")
	val height: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("brandName")
	val brandName: String? = null,

	@field:SerializedName("isBarcodeItem")
	val isBarcodeItem: Int? = null,

	@field:SerializedName("created")
	val created: Int? = null,

	@field:SerializedName("length")
	val length: String? = null,

	@field:SerializedName("weight")
	val weight: String? = null,

	@field:SerializedName("mrp")
	val mrp: String? = null,

	@field:SerializedName("subCategoryId")
	val subCategoryId: Int? = null,

	@field:SerializedName("vendorEmployeeId")
	val vendorEmployeeId: Int? = null,

	@field:SerializedName("gtinNumber")
	val gtinNumber: String? = null,

	@field:SerializedName("percentageDiscount")
	val percentageDiscount: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("width")
	val width: String? = null,

	@field:SerializedName("countryOfOrigin")
	val countryOfOrigin: String? = null,

	@field:SerializedName("updated")
	val updated: Int? = null,

	@field:SerializedName("categoryId")
	val categoryId: Int? = null,

	@field:SerializedName("skuImage")
	val skuImage: String? = null,

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("taxCategoryId")
	val taxCategoryId: Int? = null,

	@field:SerializedName("weightUnit")
	val weightUnit: Int? = null
)
