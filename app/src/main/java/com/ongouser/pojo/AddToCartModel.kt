package com.ongouser.pojo

import com.google.gson.annotations.SerializedName

data class AddToCartModel(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("body")
	val body: AddToCartBody? = null
)

data class AddToCartBody(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("product")
	val product: AddtocartProduct? = null,

	@field:SerializedName("productId")
	val productId: Int? = null,

	@field:SerializedName("created")
	val created: Int? = null,

	@field:SerializedName("qty")
	val qty: Int? = null,

	@field:SerializedName("vendorId")
	val vendorId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("userId")
	val userId: Int? = null,

	@field:SerializedName("updated")
	val updated: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class AddtocartProduct(

	@field:SerializedName("isAvailable")
	val isAvailable: Int? = null,

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
