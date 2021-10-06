package com.ongouser.viewmodel

import android.annotation.SuppressLint
import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ongouser.manager.restApi.RestObservable
import com.ongouser.utils.others.MyApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.util.*

class AuthViewModel :ViewModel() {
    private val TAG = AuthViewModel::class.java.name
    val restApiInterface = MyApplication.getnstance().provideAuthservice()
    var mResponse: MutableLiveData<RestObservable> = MutableLiveData()

    fun signUpApi(
            activity: Activity, showLoader: Boolean,
            map: HashMap<String, RequestBody>,
            mImage: MultipartBody.Part?
    ) {
        restApiInterface.signUp(map,mImage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
                .subscribe(
                        { mResponse.value = RestObservable.success(it) },
                        { mResponse.value = RestObservable.error(activity, it) }
                )
    }
    fun subCategoryListApi(activity: Activity, showLoader:Boolean,
                           map: java.util.HashMap<String, String>
    ) {
        restApiInterface.getshopbycatid(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
                .subscribe(
                        { mResponse.value = RestObservable.success(it) },
                        { mResponse.value = RestObservable.error(activity,it) }
                )
    }

    fun loginApi(activity: Activity, showLoader:Boolean,
                 map: HashMap<String, String>
    ) {
        restApiInterface.login(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
                .subscribe(
                        { mResponse.value = RestObservable.success(it) },
                        { mResponse.value = RestObservable.error(activity,it) }
                )
    }

    fun verifyOTPApi(activity: Activity, showLoader:Boolean,
                 map: HashMap<String, String>
    ) {
        restApiInterface.verifyOtp(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
                .subscribe(
                        { mResponse.value = RestObservable.success(it) },
                        { mResponse.value = RestObservable.error(activity,it) }
                )
    }
    fun resendOtpApi(activity: Activity, showLoader:Boolean,
                 map: HashMap<String, String>
    ) {
        restApiInterface.resendOtp(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
                .subscribe(
                        { mResponse.value = RestObservable.success(it) },
                        { mResponse.value = RestObservable.error(activity,it) }
                )
    }

    @SuppressLint("CheckResult")
    fun socialLoginApi(activity: Activity, showLoader:Boolean,
                       map: HashMap<String, String>
    ) {
        restApiInterface.socialLogin(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
                .subscribe(
                        { mResponse.value = RestObservable.success(it) },
                        { mResponse.value = RestObservable.error(activity,it) }
                )
    }

    fun forgotPasswordApi(activity: Activity, showLoader:Boolean,
                 map: HashMap<String, String>
    ) {
        restApiInterface.forgotPassword(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }


}