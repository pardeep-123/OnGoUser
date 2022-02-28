package com.ongouser.manager.restApi

import com.google.gson.JsonObject
import com.ongouser.pojo.*

import com.ongouser.utils.others.Constants

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import java.util.*
import kotlin.collections.HashMap


interface RestApiInterface {

    @Multipart
    @POST(Constants.SignUp)
    fun signUp(
            @PartMap map: HashMap<String, RequestBody>, @Part image: MultipartBody.Part?
    ): Observable<SignupResponsess>


    @Multipart
    @POST(Constants.SignUp)
    fun withoutSignUp(
        @PartMap map: HashMap<String, RequestBody>
    ): Observable<SignupResponsess>

    @FormUrlEncoded
    @POST(Constants.Login)
    fun login(
            @FieldMap map: HashMap<String, String>
    ): Observable<LoginResponse>

    @FormUrlEncoded
    @PUT(Constants.Logout)
    fun getLogout(
            @FieldMap map: HashMap<String, String>):
            Observable<LogoutResponse>

    @FormUrlEncoded
    @POST(Constants.SocialLogin)
    fun socialLogin(
            @FieldMap map: HashMap<String, String>
    ): Observable<SocialModel>

    @FormUrlEncoded
    @PUT(Constants.ForgotPassword)
    fun forgotPassword(
            @FieldMap map: HashMap<String, String>
    ): Observable<ForgotPasswordResponse>


    @DELETE(Constants.DeleteUserAddress)
    fun deleteUserAddress(
            @Query("id") id:String
    ): Observable<CommonModel>


    @DELETE(Constants.DeleteCard)
    fun deleteCard(
            @Query("id") id:String
    ): Observable<DeleteCardResponse>

    @DELETE(Constants.DeleteCartItem)
    fun deletecartitem(
            @Query("id") id:String
    ): Observable<DeleteItemCartResponse>

    @FormUrlEncoded
    @PUT(Constants.Updatecartitem)
    fun updatecartitem(
            @Field("id") id:String,
            @Field("qty") qty:String
    ): Observable<CommonModel>




    @FormUrlEncoded
    @PUT(Constants.ChangePassword)
    fun changePassword(
            @FieldMap map: HashMap<String, String>
    ): Observable<ChangePasswordResponse>

    @FormUrlEncoded
    @POST(Constants.ADDTOCART)
    fun addtocart(
            @FieldMap map: HashMap<String, String>
    ): Observable<AddToCartModel>

    @DELETE(Constants.emptycart)
    fun emptycart(
    ): Observable<CommonModel>

    @GET(Constants.GETCARTLIST)
    fun getcartlisting(
    ): Observable<CartListingModel>

    @FormUrlEncoded
    @POST(Constants.GetFavoriteProducts)
    fun getfavoriteproducts(
            @Field("dd") dd:String
    ): Observable<GetMyFavoriteListing>

    @FormUrlEncoded
    @POST(Constants.AddCard)
    fun addCard(
            @FieldMap map: HashMap<String, String>
    ): Observable<AddCardResponse>

    @FormUrlEncoded
    @PUT(Constants.updateCard)
    fun updateCard(
            @FieldMap map: HashMap<String, String>
    ): Observable<AddCardResponse>


    @FormUrlEncoded
    @POST(Constants.AddUserAddress)
    fun addUserAddress(
            @FieldMap map: HashMap<String, String>
    ): Observable<AddAddressResponse>

    @FormUrlEncoded
    @PUT(Constants.UpdateUserAddress)
    fun updateUserAddress(
            @FieldMap map: HashMap<String, String>
    ): Observable<UpdateAddressResponse>

    @FormUrlEncoded
    @POST(Constants.PlaceOrderApi)
    fun placeorder(
            @FieldMap map: HashMap<String, String>
    ): Observable<CommonModel>

    @FormUrlEncoded
    @PUT(Constants.VerifyOtp)
    fun verifyOtp(
            @FieldMap map: HashMap<String, String>
    ): Observable<VerifyOTPResponse>

    @FormUrlEncoded
    @PUT(Constants.ResendOtp)
    fun resendOtp(
            @FieldMap map: HashMap<String, String>
    ): Observable<ResendOTPResponse>


    @GET(Constants.UserAddressListing)
    fun getUserAddressListing():
            Observable<GetAddressListResponse>

    @GET(Constants.UserAddressListing)
    fun getUserAddressListing(
            @QueryMap map: HashMap<String,String>
    ): Observable<ShopAdressModel>

    @GET(Constants.GetProfile)
    fun getProfile():
            Observable<GetProfileResponse>

    @GET(Constants.AllCards)
    fun allCards():
            Observable<GetAddedCardListResponse>

    @Multipart
    @PUT(Constants.EditProfile)
    fun editProfile(
            @PartMap map: HashMap<String, RequestBody>, @Part image: MultipartBody.Part
    ): Observable<EditProfileResponse>

    @Multipart
    @PUT(Constants.EditProfile)
    fun updateProfileWithoutImage(
            @PartMap map: HashMap<String, RequestBody>
    ): Observable<EditProfileResponse>

    @GET(Constants.TermsCondition)
    fun termsCondition():
            Observable<TermsConditionsResponse>

    @GET(Constants.PrivacyPolicy)
    fun privacyPolicy():
            Observable<PrivacyPolicyResponse>

    @GET(Constants.AboutUs)
    fun aboutUs():
            Observable<AboutUsResponse>

    @FormUrlEncoded
    @POST(Constants.HomeListing)
    fun homeListing(@FieldMap map: HashMap<String, String>):
            Observable<HomeListingResponse>

    @GET(Constants.GetNotificationListing)
    fun getnotificationlisting():
            Observable<GetNotificationModel>

    @GET(Constants.CategoryListing)
    fun categoryListing(@QueryMap map: HashMap<String, String>):
            Observable<CategoryListingResponse>

    @GET(Constants.getdeliverytimeslots)
    fun gettimeslots(
            @Query("vendor_id") vendorid:String):
            Observable<DeliverySlotModel>

    @FormUrlEncoded
    @POST(Constants.Getshopbycatid)
    fun getshopbycatid(
            @FieldMap map: HashMap<String, String>):
            Observable<GetShopsModel>

    @FormUrlEncoded
    @POST(Constants.Getproductbyshopid)
    fun getproductbyshopid(
            @FieldMap map: HashMap<String, String>):
            Observable<GetProductModel>

    @FormUrlEncoded
    @POST(Constants.GetMyorders)
    fun getorders(
            @FieldMap map: HashMap<String, String>):
            Observable<MyOrderModel>


    @GET(Constants.Getorderdetail)
    fun getorderdetail(
            @QueryMap map: HashMap<String, String>):
            Observable<OrderDetailModel>

    @FormUrlEncoded
    @POST(Constants.NearbyVendors)
    fun nearbyVendorsList(@FieldMap map: HashMap<String, String>):
            Observable<NearbyVendorResponse>

    @FormUrlEncoded
    @POST(Constants.Addtofavorite)
    fun addtofavorite(@FieldMap map: HashMap<String, String>):
            Observable<AddToFavoriteModel>

    @FormUrlEncoded
    @POST(Constants.add_review)
    fun add_review(@FieldMap map: HashMap<String, String>):
            Observable<AddReviewResponse>

/*
    @FormUrlEncoded
    @POST(Constants.AddCard)
    fun addCard(
        @FieldMap map: HashMap<String, String>
    ): Observable<AddCardResponse>

    @FormUrlEncoded
    @POST(Constants.EditCard)
    fun editCard(
        @FieldMap map: HashMap<String, String>
    ): Observable<EditCardResponse>

    @FormUrlEncoded
    @POST(Constants.SetDefaultCard)
    fun setDefaultCard(
        @FieldMap map: HashMap<String, String>
    ): Observable<SetDefaultCardResponse>
*/


/*
    @GET(Constants.GetCardsList)
    fun getCardList():
            Observable<GetCardListResponse>

    @GET(Constants.GetMyTraits)
    fun getMyTraits():
            Observable<GetMyTraitsListResponse>

    @GET(Constants.NotificationsList)
    fun notificationsList():
            Observable<NotificationListResponse>

    @GET(Constants.PaymentHistory)
    fun paymentHistory():
            Observable<PaymentHistoryListResponse>

    @GET(Constants.Home)
    fun getHomeTraits():
            Observable<HomeListResponse>


    @GET(Constants.GetFAQList)
    fun getFAQList():
            Observable<FaqListResponse>

    @GET(Constants.GetTraits)
    fun getFreeTraitsList():
            Observable<GetFreeTraitsListResponse>

    @GET(Constants.GetConnectionRequests)
    fun getConnectionRequests():
            Observable<GetConnectionRequestsListResponse>

    @GET(Constants.Connections)
    fun getMyConnections():
            Observable<GetMyConnectionsListResponse>

    @FormUrlEncoded
    @POST(Constants.AllConnections)
    fun getAllConnections(
        @FieldMap map: HashMap<String, String>):
            Observable<GetAllConnectionsListResponse>
*/


    /*


     @FormUrlEncoded
     @POST(Constants.HomeUser)
     fun getHomeUser(@FieldMap map: HashMap<String, String>):
             Observable<HomeUserResponse>

     @FormUrlEncoded
     @POST(Constants.HomeUserSearch)
     fun getHomeUserSearch(@Field ("keyword") keyword:String):
             Observable<HomeUserResponse>


     @GET(Constants.CategoryList)
     fun getCategoryList():
             Observable<CategoryResponse>

     @FormUrlEncoded
     @POST(Constants.deleteImage)
     fun deleteImage(@FieldMap map:HashMap<String,String>):
             Observable<CommonResponse>

     @FormUrlEncoded
     @POST(Constants.VideoNotification)
     fun videoNotification(@FieldMap map:HashMap<String,String>):
             Observable<CommonResponse>*/


}