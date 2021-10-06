package com.ongouser.pojo

data class AddReviewResponse(
    var body: Body,
    var code: Int,
    var message: String,
    var success: Boolean
) {
    class Body(
    )
}