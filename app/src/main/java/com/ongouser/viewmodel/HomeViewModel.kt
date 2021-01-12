package com.ongouser.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ongouser.manager.restApi.RestObservable

import com.ongouser.utils.others.MyApplication
import com.ongouser.utils.others.ValidationsClass
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class HomeViewModel :ViewModel() {
    private val TAG = HomeViewModel::class.java.name
    val restApiInterface = MyApplication.getnstance().provideAuthservice()
    var mResponse: MutableLiveData<RestObservable> = MutableLiveData()

/*
    fun getHomeTraits(activity: Activity, showLoader:Boolean) {
        restApiInterface.getHomeTraits()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }
*/

    fun getProfile(activity: Activity, showLoader:Boolean, map: HashMap<String, String>) {
        restApiInterface.getProfile(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }



/*
    fun editProfile(
        activity: Activity, showLoader: Boolean,
        map: HashMap<String, RequestBody>,
        mImage: String,
        mValidationClass: ValidationsClass
    ) {
        lateinit var profileImageFileBody: MultipartBody.Part
        var updateProfile: Observable<EditProfileResponse>? = null
        if (!mValidationClass.checkStringNull(mImage)) {
            val file = File(mImage)
            profileImageFileBody = mValidationClass.prepareFilePart("image", file)
            updateProfile =  restApiInterface.editProfile(map,profileImageFileBody)
        }else
        {
            updateProfile =  restApiInterface.updateProfileWithoutImage(map)
        }
        updateProfile!!.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }
*/



/*
    fun notificationListApi(activity: Activity, showLoader:Boolean) {
        restApiInterface.notificationsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }
*/

    fun logoutApi(activity: Activity, showLoader:Boolean) {
        restApiInterface.getLogout()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity,showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity,it) }
            )
    }


}