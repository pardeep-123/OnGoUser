package com.ongouser.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class UpdateAddressResponse {

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
    private var body: Body? = null

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

    fun getBody(): Body? {
        return body
    }

    fun setBody(body: Body?) {
        this.body = body
    }

    class Body {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("isDefault")
        @Expose
        var isDefault: Int? = null

        @SerializedName("userId")
        @Expose
        var userId: Int? = null

        @SerializedName("address")
        @Expose
        var address: String? = null

        @SerializedName("latitude")
        @Expose
        var latitude: String? = null

        @SerializedName("longitude")
        @Expose
        var longitude: String? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("countryCode")
        @Expose
        var countryCode: String? = null

        @SerializedName("phone")
        @Expose
        var phone: String? = null

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

    }

}