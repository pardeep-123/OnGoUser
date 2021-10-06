package com.ongouser.utils.others

class Constants {
    companion object
    {
        var isPickedup = "ispickedup"
        var AddressId = "Addressid"
        var TimeslotDAy = "timeslots"
        var TotalAmount = "totalamount"
        var TotalFee = "totalfee"
        var TotalTax = "totaltax"
        var TimeslotsTime = "timeslotsdates"
        var VendorId = "VendorId"

        const val BASE_URL = "http://54.252.10.181:8300/api/"
        val success_code = 200
        val errorCode = 403
        val Device_Type = "0"      //(0 => Android, 1 => IOS)
        var SecurityKey = "securitykey"
        var SecurityKeyValue = "__ongo"
        val token = "token"
        val AuthKey = "Authorization"
        val UserData = "userData"
        val IsBusinessProfileAdded = "isBusinessAdded"

        const val TYPE_USER = "1"
        const val TYPE_FRIEND = 2

        const val Name = "name"
        const val Age = "age"
        const val Country = "country"
        const val Email = "email"
        const val Password = "password"
        const val PhoneNumber = "phone_number"
        const val CountryCode = "country_code"
        const val TermsCondition = "termsAndConditions"
        const val FOR_GOOGLE_TYPE = "2"
        const val FOR_FACEBOOK_TYPE = "1"

        const val SignUp = BASE_URL + "signup"
        const val Login = BASE_URL + "login"
       // const val SocialLogin = BASE_URL + "socail_login"
        const val SocialLogin = BASE_URL + "socialLogin"
        const val ForgotPassword = BASE_URL + "forgotPassword"
        const val GetProfile = BASE_URL + "getProfile"
        const val EditProfile = BASE_URL + "editProfile"
        const val ChangePassword = BASE_URL + "changePassword"
        const val AddCard = BASE_URL + "addCard"
        const val updateCard = BASE_URL + "updateCard"
        const val ADDTOCART = BASE_URL + "add_to_cart"
        const val emptycart = BASE_URL + "empty_cart"
        const val GETCARTLIST = BASE_URL + "cart_listing"
        const val GetFavoriteProducts = BASE_URL + "fav_product_list"
        const val AddUserAddress = BASE_URL + "addUserAddress"
        const val VerifyOtp = BASE_URL + "verifyOtp"
        const val ResendOtp = BASE_URL + "resendOtp"
        const val AboutUs = BASE_URL + "aboutUs"
        const val PrivacyPolicy = BASE_URL + "privacyPolicy"
        const val DeleteUserAddress = BASE_URL + "deleteUserAddress"
        const val DeleteCard = BASE_URL + "deleteCard"
        const val DeleteCartItem = BASE_URL + "delete_item_cart"
        const val Updatecartitem = BASE_URL + "update_cart_item"
        const val AllCards = BASE_URL + "allCards"
        const val HomeListing = BASE_URL + "homeListing"              // Home
        const val CategoryListing = BASE_URL + "shop_categories"
        const val getdeliverytimeslots = BASE_URL + "get_delivery_options"
        const val Addtofavorite = BASE_URL + "productFav"
        const val NearbyVendors = BASE_URL + "nearbyVendors"
        const val Getshopbycatid = BASE_URL + "get_shop_by_category_id"
        const val Getproductbyshopid = BASE_URL + "get_product_by_shop_id"
        const val GetMyorders = BASE_URL + "my_order"
        const val Getorderdetail = BASE_URL + "orderDetail"
        const val UserAddressListing = BASE_URL + "userAddressListing"
        const val UpdateUserAddress = BASE_URL + "updateUserAddress"
        const val PlaceOrderApi = BASE_URL + "addOrder"
        const val add_review = BASE_URL + "add_review"
        const val GetNotificationListing = BASE_URL + "notification_listing"

        const val NotificationOnOff = BASE_URL + "notification_on_off"
        const val NotificationsList = BASE_URL + "notifications_list"
        const val RemoveNotification = BASE_URL + "remove_push"

        const val HomeUserSearch = BASE_URL + "home_user_search"
        const val Logout = BASE_URL + "logout"
        }

}