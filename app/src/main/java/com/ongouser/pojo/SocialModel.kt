package com.ongouser.pojo

data class SocialModel(
    val body: SocialBody,
    val code: Int,
    val message: String,
    val success: Boolean
)

data class SocialBody(
    val countryCode: String,
    val created: Int,
    val createdAt: String,
    val email: String,
    val facebookId: String,
    val forgotPasswordHash: String,
    val googleId: String,
    val id: Int,
    val image: String,
    val name: String,
    val otp: Int,
    val phone: String,
    val role: Int,
    val status: Int,
    val token: String,
    val updated: Int,
    val updatedAt: String,
    val userId: Int,
    val username: String,
    val verified: Int
)