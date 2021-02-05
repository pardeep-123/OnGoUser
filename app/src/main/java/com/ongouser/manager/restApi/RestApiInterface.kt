package com.ongouser.manager.restApi

import com.google.gson.JsonObject
import com.ongouser.pojo.*

import com.ongouser.utils.others.Constants

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import java.util.*


interface RestApiInterface {

    @Multipart
    @POST(Constants.SignUp)
    fun signUp(
            @PartMap map: HashMap<String, RequestBody>, @Part image: MultipartBody.Part
    ): Observable<SignupResponsess>

    @FormUrlEncoded
    @PUT(Constants.Login)
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
    ): Observable<SocialLoginResponse>

    @FormUrlEncoded
    @PUT(Constants.ForgotPassword)
    fun forgotPassword(
            @FieldMap map: HashMap<String, String>
    ): Observable<ForgotPasswordResponse>


    @FormUrlEncoded
    @PUT(Constants.ChangePassword)
    fun changePassword(
            @FieldMap map: HashMap<String, String>
    ): Observable<ChangePasswordResponse>

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


    @GET(Constants.GetProfile)
    fun getProfile():
            Observable<GetProfileResponse>

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

    @FormUrlEncoded
    @POST(Constants.CategoryListing)
    fun categoryListing(@FieldMap map: HashMap<String, String>):
            Observable<CategoryListingResponse>

    @FormUrlEncoded
    @POST(Constants.NearbyVendors)
    fun nearbyVendorsList(@FieldMap map: HashMap<String, String>):
            Observable<NearbyVendorResponse>


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