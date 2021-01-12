package com.trutraits.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class ChangePasswordResponse
{
    @SerializedName("code")
    @Expose
    private var code: String? = null

    @SerializedName("success")
    @Expose
    private var success: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("body")
    @Expose
    private var body: Body? = null

    fun getCode(): String? {
        return code
    }

    fun setCode(code: String?) {
        this.code = code
    }

    fun getSuccess(): Boolean? {
        return success
    }

    fun setSuccess(success: Boolean?) {
        this.success = success
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
        var id: String? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("email")
        @Expose
        var email: String? = null

        @SerializedName("age")
        @Expose
        var age: String? = null

        @SerializedName("image")
        @Expose
        var image: String? = null

        @SerializedName("image_name")
        @Expose
        var imageName: String? = null

        @SerializedName("country")
        @Expose
        var country: String? = null

        @SerializedName("authorization_key")
        @Expose
        var authorizationKey: String? = null

        @SerializedName("notifications_status")
        @Expose
        var notificationsStatus: String? = null

        @SerializedName("bio")
        @Expose
        var bio: String? = null

    }
}