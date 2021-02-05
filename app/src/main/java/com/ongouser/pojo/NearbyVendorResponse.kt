package com.ongouser.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class NearbyVendorResponse
{
    @SerializedName("success")
    @Expose
    private var success: Boolean? = null

    @SerializedName("code")
    @Expose
    private var code: Int? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("body")
    @Expose
    private var body: ArrayList<Body>? = null

    fun getSuccess(): Boolean? {
        return success
    }

    fun setSuccess(success: Boolean?) {
        this.success = success
    }

    fun getCode(): Int? {
        return code
    }

    fun setCode(code: Int?) {
        this.code = code
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    fun getBody(): ArrayList<Body>? {
        return body
    }

    fun setBody(body: ArrayList<Body>?) {
        this.body = body
    }

    class Body {
        @SerializedName("productPriceLowest")
        @Expose
        var productPriceLowest: Int? = null

        @SerializedName("productPriceHighest")
        @Expose
        var productPriceHighest: Int? = null

        @SerializedName("distance")
        @Expose
        var distance: Float? = null

        @SerializedName("approvalStatus")
        @Expose
        var approvalStatus: Int? = null

        @SerializedName("approvalStatusReason")
        @Expose
        var approvalStatusReason: String? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("image")
        @Expose
        var image: String? = null

        @SerializedName("phone")
        @Expose
        var phone: String? = null

        @SerializedName("shopName")
        @Expose
        var shopName: String? = null

        @SerializedName("shopLogo")
        @Expose
        var shopLogo: String? = null

        @SerializedName("shopCategory")
        @Expose
        var shopCategory: String? = null

        @SerializedName("abn")
        @Expose
        var abn: String? = null

        @SerializedName("buildingNumber")
        @Expose
        var buildingNumber: String? = null

        @SerializedName("streetNumber")
        @Expose
        var streetNumber: String? = null

        @SerializedName("city")
        @Expose
        var city: String? = null

        @SerializedName("state")
        @Expose
        var state: String? = null

        @SerializedName("country")
        @Expose
        var country: String? = null

        @SerializedName("postalCode")
        @Expose
        var postalCode: String? = null

        @SerializedName("shopOpenTime")
        @Expose
        var shopOpenTime: String? = null

        @SerializedName("shopCloseTime")
        @Expose
        var shopCloseTime: String? = null

        @SerializedName("homeDelivery")
        @Expose
        var homeDelivery: Int? = null

        @SerializedName("deliveriesPerDay")
        @Expose
        var deliveriesPerDay: Int? = null

        @SerializedName("latitude")
        @Expose
        var latitude: String? = null

        @SerializedName("longitude")
        @Expose
        var longitude: String? = null

        @SerializedName("geoLocation")
        @Expose
        var geoLocation: String? = null

        @SerializedName("shopAddress")
        @Expose
        var shopAddress: String? = null

        @SerializedName("shopDescription")
        @Expose
        var shopDescription: String? = null

        @SerializedName("paymentPolicy")
        @Expose
        var paymentPolicy: String? = null

        @SerializedName("deliveryPolicy")
        @Expose
        var deliveryPolicy: String? = null

        @SerializedName("sellerInformation")
        @Expose
        var sellerInformation: String? = null

        @SerializedName("taxInPercent")
        @Expose
        var taxInPercent: Int? = null

        @SerializedName("taxValue")
        @Expose
        var taxValue: Int? = null

        @SerializedName("bankName")
        @Expose
        var bankName: String? = null

        @SerializedName("bankBranch")
        @Expose
        var bankBranch: String? = null

        @SerializedName("accountHolderName")
        @Expose
        var accountHolderName: String? = null

        @SerializedName("accountNumber")
        @Expose
        var accountNumber: String? = null

        @SerializedName("bsbNumber")
        @Expose
        var bsbNumber: String? = null

        @SerializedName("ifscSwiftCode")
        @Expose
        var ifscSwiftCode: String? = null

        @SerializedName("bankAddress")
        @Expose
        var bankAddress: String? = null

        @SerializedName("isShopAdded")
        @Expose
        var isShopAdded: Int? = null

        @SerializedName("isHomeDeliveryAdded")
        @Expose
        var isHomeDeliveryAdded: Int? = null

        @SerializedName("isDeliveryOptionsAdded")
        @Expose
        var isDeliveryOptionsAdded: Int? = null

        @SerializedName("isDeliveryDaysAdded")
        @Expose
        var isDeliveryDaysAdded: Int? = null

        @SerializedName("isDeliveryChargesAdded")
        @Expose
        var isDeliveryChargesAdded: Int? = null

        @SerializedName("created")
        @Expose
        var created: Int? = null

        @SerializedName("updated")
        @Expose
        var updated: Int? = null

        @SerializedName("createdAt")
        @Expose
        var createdAt: String? = null

        @SerializedName("updatedAt")
        @Expose
        var updatedAt: String? = null

        @SerializedName("userId")
        @Expose
        var userId: Int? = null

        @SerializedName("vendorDetailId")
        @Expose
        var vendorDetailId: Int? = null

    }

}